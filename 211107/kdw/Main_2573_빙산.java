package s1107;

import java.io.*;
import java.util.*;

public class Main_2573_빙산 {

	static int N, M, answer;
	static int[][] map, copy;
	static boolean separated, zero;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());					// row 값 입력 
		M = Integer.parseInt(st.nextToken());					// col 값 입력 
		map = new int[N][M];									// 원본 배열 입력 
		copy = new int[N][M];									// 원본 배열에서 작업 이후 값들을 저장할 배열 
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());	// 원본 배열 값 초기화 
			}
		}
		
		answer = 0;												// 빙산이 쪼개지는데 걸리는 최소시간(년)을 저장할 변수 
		int year = 0;											// 최소년수 
		
		a: while(true) {										// 빙산(map)이 두개로 쪼개질 때까지 빙산 개수 구하기(getIceberg) -> 테두리 녹이기(melt) 진행 
			separated = false;									// 빙산이 쪼개졌음을 확인하기 위한 변수 
			zero = false;										// 빙산이 모두 사라질 때까지 쪼개지지 않았을 경우를 체크할 변수 
			getIceberg();										// 빙산(이어진 덩어리) 개수 구하기 (bfs 사용)
			if(separated) {										// 만약 빙산의 갯수가 2개 이상이면 separated가 true로 변경됨 
				answer = year;									// 빙산이 두개 이상으로 쪼개졌으므로 answer값을 년도로 초기화 
				break a;										// 루프 탈출 
			}
			if(zero) {											// 만약 빙산이 아직 쪼개지지 않았지만, 다 녹아버렸다면,
				answer = 0;										// 문제에 제시된 대로 answer(최소년수) 값을 0으로 초기화하고
				break a;										// 루프 탈출 
			}
			melt();												// 위에서 루프를 탈출하지 못했다면, 빙산이 아직 있고, 나눠지지 않은 경우이므로 테두리를 인접한 공백의 수만큼 녹이기 시작 
			year++;												// 년수 증가 
		}
		
		System.out.println(answer);
		br.close();
	}

	private static void melt() {

		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				copy[i][j] = 0;									// copy를 먼저 0으로 초기화해주고 (계속 원본 map을 바라보고 업데이트 해야하기 떄문)
				if(map[i][j]>0) {
					int cnt = 0;								// 인접한 바다의 수를 구하기 위한 변수 
					for(int d=0; d<4; d++) {
						int nr = i+dr[d];
						int nc = j+dc[d];
						if(nr>=0&&nr<N && nc>=0&&nc<M && map[nr][nc]==0) {
							cnt++;								// 만약 빙산에 인접하면서 범위 내인 바다일 경우 cnt값 증가 
						}
					}
					copy[i][j] = map[i][j] - cnt;				// copy값을 map에서 녹인 만큼으로 초기화 
					if(copy[i][j]<0) copy[i][j] = 0;			// 음수인 경우 0으로 초기화(굳이 안해줘도 됨) 
				}
			}
		}
		
		for(int i=0; i<N; i++) {								// map 초기화(마찬가지로 빙산이었다가 녹은 곳이 있기 떄문에 먼저 0으로 초기화 하고 copy값으로 초기화)
			for(int j=0; j<M; j++) {
				map[i][j] = 0;
				map[i][j] = copy[i][j];
			}
		}
	}

	private static void getIceberg() {
		boolean isZero = true;									// 빙산이 존재하는지를 체크할 변수 
		Queue<int[]> q = new ArrayDeque<int[]>();				// 이어진 빙산끼리 덩어리 처리하기 위한 큐 
		boolean[][] v = new boolean[N][M];						// bfs에서 사용할 방문처리 용도 
		int cnt = 0;											// 빙산 덩어리의 갯수를 카운트할 변수 
		
		loop: for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]>0 && !v[i][j]) {					// 원본map을 순회하면서 빙산의 일부이고, 아직 방문되지 않은 빙산을 탐색 
					isZero = false;								// 빙산이 발견되었으므로 isZero를 false로 초기화 
					++cnt;										// 빙산 덩어리 개수 증가 
					if(cnt>=2) {								// 만약 cnt값이 2이상이라면, 빙산이 두 덩어리 이상이라는 의미이므로 
						separated = true;						// 빙산이 쪼개졋음을 의미하고 
						break loop; 							// bfs 루프 탈출
					}
					q.offer(new int[] {i,j});					// 연결된 빙산 bfs 처리 
					v[i][j] = true;
					
					while(!q.isEmpty()) {
						int[] cur = q.poll();
						for(int d=0; d<4; d++) {
							int nr = cur[0]+dr[d];
							int nc = cur[1]+dc[d];
							if(nr>=0&&nr<N && nc>=0&&nc<M && map[nr][nc]>0 && !v[nr][nc]) {
								q.offer(new int[] {nr, nc});
								v[nr][nc] = true;
							}
						}
					}
				}
			}
		}
		if(isZero) {											// 위 반복문에서 iszero가 계속 true로 남아있다면, 빙산이 없다는 의미이므로 zero(빙산이 한덩이인채로 녹음을 의미하는 변수)를 true로 변경  
			zero = true;
		}
	}

}
