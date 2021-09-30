package s0930;

import java.io.*;
import java.util.*;

public class Main_15683_감시 {
	
	static int N, M, answer;
	static int[][] map, copy;
	static List<Cctv> cctvList;
	static class Cctv{
		int y;
		int x;
		int type;
		public Cctv(int y, int x, int type) {
			super();
			this.y = y;
			this.x = x;
			this.type = type;
		}
	}
	static int[] dy = {-1, 0, 1, 0};					// 상, 우, 하, 좌 순서 **
	static int[] dx = {0, 1, 0, -1};
	static int[] cctvDirSet;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cctvList = new ArrayList<>();					// map에 존재하는 cctv의 좌표정보를 저장할 리스트 
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {					// map초기화
				map[i][j] = Integer.parseInt(st.nextToken());					
				if(1<=map[i][j] && map[i][j]<=5) {		// 만약 cctv이면 cctv list에 저장 
					cctvList.add(new Cctv(i, j, map[i][j]));
				}
			}
		}
		answer = Integer.MAX_VALUE;
		cctvDirSet = new int[cctvList.size()];			// cctv 개수만큼의 길이를 가진 배열 생성(순열에 상요할 배열)		
		permu(0, cctvList.size());						// 각각의 cctv에 대한 방향정보를 저장할 순열 생성 
		System.out.println(answer);
		br.close();
	}
	
	private static void permu(int depth, int size) {
		if(depth==size) {								// 각 cctv의 방향이 모두 설정되었으면, 
			initTmpMap();								// 해당 방향대로 감시했을 때의 상태를 저장할 copy map 생성 
			for(int i=0; i<cctvList.size(); i++) {		// 방향이 설정된 각각의 cctv를 설정된 방향대로 copy map을 비춰봄 
				setDirection(cctvList.get(i), cctvDirSet[i]);	
			}
			getSafeArea();								// 최소의 사각지대 개수 업데이트 
			return;
		} else {
			for(int i=0; i<4; i++) {					// 순열 코드 
				cctvDirSet[depth] = i;
				permu(depth+1, size);
			}
		}
	}

	private static void getSafeArea() {
		int cnt=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(copy[i][j] == 0) cnt++;
			}
		}
		answer = Math.min(answer, cnt);
	}

	private static void setDirection(Cctv cctv, int d) {
		int type = cctv.type;							// 현재 cctv의 타입 
		if(type==1) {									// 1번 타입 → ← ↑ ↓ 
			if(d==0) watch(cctv, 0); 					// ↑ 
			else if(d==1) watch(cctv, 1); 				// → 
			else if(d==2) watch(cctv, 2); 				// ↓  
			else if(d==3) watch(cctv, 3); 				// ←
		} else if(type==2) {							// 2번 타입 
			if(d==0 || d==2) {							// 180도 회전하면 처음 상태와 동일하므로 0과 2에 해당되는 방향은 동일 처리 
				watch(cctv, 0); 						// ↑
				watch(cctv, 2); 						// ↓ 
			}else {
				watch(cctv, 1); 						// →					
				watch(cctv, 3); 						// ←
			}
		} else if(type==3) {							// 3번 타입 
			if(d==0) {
				watch(cctv, 0); 						// ↑
				watch(cctv, 1);							// →
			} else if(d==1) { 
				watch(cctv, 1);  						// →
				watch(cctv, 2);							// ↓
			} else if(d==2) { 
				watch(cctv, 2);  						// ↓
				watch(cctv, 3);							// ←
			} else if(d==3) { 
				watch(cctv, 3);							// ←
				watch(cctv, 0);  						// ↑
			}
		} else if(type == 4) {							// 4번 타입 
			if(d == 0) {								
				watch(cctv, 0); 						// ↑
				watch(cctv, 3);							// ←
				watch(cctv, 1);							// →
			} else if(d == 1) {							 
				watch(cctv, 0); 						// ↑
				watch(cctv, 1);							// →
				watch(cctv, 2);							// ↓
			} else if(d == 2) {							 
				watch(cctv, 3);							// ←
				watch(cctv, 1); 						// →
				watch(cctv, 2);							// ↓
			} else if(d == 3) {							 
				watch(cctv, 0); 						// ↑
				watch(cctv, 3);							// ←
				watch(cctv, 2);							// ↓
			}
		} else if(type == 5) { 							// 5번 타입 
			watch(cctv, 0);								// ↑
			watch(cctv, 1);								// →
			watch(cctv, 2);								// ↓
			watch(cctv, 3);								// ←
		}
	}

	private static void watch(Cctv cctv, int d) {
		Queue<Cctv> q = new ArrayDeque<>();
		boolean[][] v = new boolean[N][M];

		v[cctv.y][cctv.x] = true;
		q.offer(cctv);

		while(!q.isEmpty()) {
			Cctv cur = q.poll();
			int ny = cur.y + dy[d];
			int nx = cur.x + dx[d];
			if(ny<0||ny>=N || nx<0||nx>=M) break;		// 설정한 방향으로 움직인 위치가 범위 밖이면 stop
			
			if(copy[ny][nx]==6) break;  				// 범위 안이지만 벽을 만나도 stop
			

			if(copy[ny][nx]==0) copy[ny][nx] = -1; 		// 빈칸이라면 -1로 변경 
 			q.add(new Cctv(ny, nx, cctv.type)); 		// 큐에 다음 위치좌표 저장 

		}
	}

	private static void initTmpMap() {
		copy = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				copy[i][j] = map[i][j];
			}
		}
	}
}
