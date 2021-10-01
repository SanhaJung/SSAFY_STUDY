package a1001.algo;

import java.io.*;
import java.util.*;

public class Solution_d4_파핑파핑지뢰찾기 {
	
	static int N;
	static char[][] map;	// 원본배열
	static int[][] pan;		// 지뢰판
	static int[] di = {-1,-1,-1, 0, 1, 1, 1, 0};	// 좌상 상 우상 우 우하 하 좌하 좌
	static int[] dj = {-1, 0, 1, 1, 1, 0,-1,-1};
	static List<int[]> blankList = new ArrayList<>(); // pan에서 인접 지뢰수가 0인 좌표 저장
	static int[] dx = {-1, 0, 1, 0};	// 상우하좌
	static int[] dy = { 0, 1, 0,-1};
	static boolean[][] visited;
	static int minClick=0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d4_1868_파핑파핑지뢰찾기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1;test_case<=T;test_case++) {
			minClick=0;
			N = Integer.parseInt(br.readLine());
			
			map = new char[N][];
			pan = new int[N][N];
			
			for(int i=0;i<N;i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			// 인접 지뢰 수로 판만들기
			makePan();
			
//			for(int i=0;i<N;i++) {
//				for(int j=0;j<N;j++) {
//					System.out.print(pan[i][j]);
//	
//				}
//				System.out.println();
//			}

			// 최소클릭 수 구하기
			visited = new boolean[N][N];
			while(!blankList.isEmpty()) {
				minClick++;
				int[] temp = blankList.get(0);
				ClickBFS(temp[0], temp[1]);
	
			}

			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!visited[i][j] && pan[i][j]!=-1) {
						visited[i][j] = true;
						minClick++;
					}
				}
			}

			sb.append("#").append(test_case).append(" ").append(minClick).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

	private static void ClickBFS(int x, int y) {
		Deque<int[]> q= new ArrayDeque<int[]>();
		int remain = 0;
		
		visited[x][y] = true;
		q.offer(new int[] {x, y});
		blankList.remove(0);
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int pi = cur[0];
			int pj = cur[1];
			int cnt = 0;
			
			for(int d=0;d<8;d++) {
				int ni = pi + di[d];
				int nj = pj + dj[d];
				
				if(0<=ni && ni<N && 0<=nj && nj<N && !visited[ni][nj]) {

					if(pan[ni][nj]==0) { 		// 0인칸은 방문처리 후 큐에 추가
						visited[ni][nj] = true; 
						q.offer(new int[] {ni, nj});
						int size = blankList.size();
						for(int k=size-1;k>=0;k--) {
							if(blankList.get(k)[0]==ni && blankList.get(k)[1]==nj) {
								blankList.remove(k);
								break;
							}
						}
					}
					else if(pan[ni][nj]==-1 ) { // 지뢰있는 칸은 방문처리만
						visited[ni][nj] = true; 
					} else { 					// 그외 숫자라도 방문처리만
						visited[ni][nj] = true;
						//q.offer(new int[] {ni, nj});
					}
				}
			}
		}
		

	}

//	private static void makePanBFS() {
//		Deque<int[]> q= new ArrayDeque<int[]>();
//		boolean[][] v = new boolean[N][N];
//		
//		v[0][0] = true;
//		q.offer(new int[] {0, 0});
//		
//		while(!q.isEmpty()) {
//			int[] cur = q.poll();
//			int pi = cur[0];
//			int pj = cur[1];
//			int cnt = 0;
//			
//			for(int d=0;d<8;d++) {
//				int ni = pi + di[d];
//				int nj = pj + dj[d];
//				
//				if(0<=ni && ni<N && 0<=nj && nj<N && !v[ni][nj]) {
//					if(map[ni][nj]=='*') {	// 지뢰라면 지뢰의 수 증가
//						cnt++;
//						pan[ni][nj] = -1;   // 인접 지뢰 수 판에는 지뢰자리 -1
//					}
//					if(map[ni][nj]=='.') {	// 빈칸이라면 방문처리 후 큐에 추가
//						v[ni][nj] = true;
//						q.offer(new int[] {ni, nj});
//					}
//					
//				}
//			}
//			// 8방 탐색 후 지뢰의 수 저장
//			pan[pi][pj] = cnt;
//			if(cnt==0) blankList.add(new int[] {pi, pj});
//		}
//	}
	
	private static void makePan() {

		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]=='.') {
					int cnt = 0;
					
					for(int d=0;d<8;d++) {
						int ni = i + di[d];
						int nj = j + dj[d];
						
						if(0<=ni && ni<N && 0<=nj && nj<N) {
							if(map[ni][nj]=='*') {	// 지뢰라면 지뢰의 수 증가
								cnt++;
								pan[ni][nj] = -1;   // 인접 지뢰 수 판에는 지뢰자리 -1
							}
						}
					}
					// 8방 탐색 후 지뢰의 수 저장
					pan[i][j] = cnt;
					if(cnt==0) blankList.add(new int[] {i, j});
				} else { ///////////////////////////////////////// else처리안해줘서!!!
					pan[i][j] = -1;
				}
			}
				
			
		}
		
	}

}
