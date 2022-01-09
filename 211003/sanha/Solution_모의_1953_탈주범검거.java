package a0930.algo;

import java.io.*;
import java.util.*;

class Tunnel{
	int x, y, deep;

	public Tunnel(int x, int y, int deep) {
		super();
		this.x = x;
		this.y = y;
		this.deep = deep;		// 탈주범에게 제한 시간이 있기때문에 BFS 탐색 깊이 저장
	}
}

public class Solution_모의_1953_탈주범검거 {
	
	static int N, M, R, C, L;
	static int[][] map;
	static boolean[][] v;
	// 상하좌우 - 터널의 방향을 타입별로 조절하기 편한 순서
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = { 0, 0,-1, 1};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_모의_1953_탈주범검거.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			Cnt=0;
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// 행렬의 크기
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());	// 맨홀 뚜껑
			C = Integer.parseInt(st.nextToken()); 	
			L = Integer.parseInt(st.nextToken());	// 탈출 후 소요시간
			
			map = new int[N][M];
			v = new boolean[N][M];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0;j<M;j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); 
				}
			}
			
			// 맨홀 뚜껑을 시작으로 bfs
			bfs(R, C, L);
			
			sb.append("#").append(t).append(" ").append(Cnt).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}

	static Deque<Tunnel> q = new ArrayDeque<>();
	static int Cnt =0;

	private static void bfs(int R, int C, int L) {
		q.clear();			// test case가 있으므로 초기화하고 bfs 시작
		v[R][C] = true;
		q.offer(new Tunnel(R, C, 0)); 

		while(!q.isEmpty()) {
			Tunnel cur = q.poll();
			// 탐색 깊이가 L보다 작을때 탐색
			if(cur.deep<L) {
				Cnt++;						// poll한것을 세어줌
				if(map[cur.x][cur.y]!=0){	// 현재좌표가 
					
					next(cur.x, cur.y, cur.deep,map[cur.x][cur.y]);
				}
			}
			
		}
		
		
		
	}

	private static void next(int pi, int pj, int deep, int type) {  
		if(type==1) {  // 상하좌우
			for(int d=0;d<4;d++) {
				int ni = pi+di[d];
				int nj = pj+dj[d];
				
				
				if(0<=ni && ni<N && 0<=nj && nj<M && !v[ni][nj] && map[ni][nj]!=0) {
					// 터널 구조물 타입별
					
					if(check(map[ni][nj], d) ) {	// 다음 터널과 현재 터널이 이어진다면
						v[ni][nj] = true;			// 방문처리 후 큐에 저장
						q.offer(new Tunnel(ni, nj, deep+1));
					}
				}
			}
		}
		if(type==2) {	// 상하
			for(int d=0;d<2;d++) {
				int ni = pi+di[d];
				int nj = pj+dj[d];
				
				if(0<=ni && ni<N && 0<=nj && nj<M && !v[ni][nj]&& map[ni][nj]!=0) {
					// 터널 구조물 타입별로 처리
					if(check(map[ni][nj], d) ) {
						v[ni][nj] = true;
						q.offer(new Tunnel(ni, nj, deep+1));
					}
				}
			}
		}
		if(type==3) {	// 좌우
			for(int d=2;d<4;d++) {
				int ni = pi+di[d];
				int nj = pj+dj[d];
				
				if(0<=ni && ni<N && 0<=nj && nj<M && !v[ni][nj]&& map[ni][nj]!=0) {
					// 터널 구조물 타입별로 처리
					if(check(map[ni][nj], d) ) {
						v[ni][nj] = true;
						q.offer(new Tunnel(ni, nj, deep+1));
					}
				}
			}
		}
		if(type==4) {	// 상우
			for(int d=0;d<4;d++) {
				if(d%3==0) {
					int ni = pi+di[d];
					int nj = pj+dj[d];
					
					if(0<=ni && ni<N && 0<=nj && nj<M && !v[ni][nj]&& map[ni][nj]!=0) {
						// 터널 구조물 타입별로 처리
						if(check(map[ni][nj], d) ) {
							v[ni][nj] = true;
							q.offer(new Tunnel(ni, nj, deep+1));
						}
					}
				}
				
			}
		}
		if(type==5) {	// 하우
			for(int d=0;d<4;d++) {
				if(d%2==1) {
					int ni = pi+di[d];
					int nj = pj+dj[d];
					
					if(0<=ni && ni<N && 0<=nj && nj<M && !v[ni][nj]&& map[ni][nj]!=0) {
						// 터널 구조물 타입별로 처리
						if(check(map[ni][nj], d) ) {
							v[ni][nj] = true;
							q.offer(new Tunnel(ni, nj, deep+1));
						}
					}
				}
				
			}
		}
		if(type==6) {	// 하좌
			for(int d=0;d<4;d++) {
				if(d==1 || d==2) {
					int ni = pi+di[d];
					int nj = pj+dj[d];
					
					if(0<=ni && ni<N && 0<=nj && nj<M && !v[ni][nj]&& map[ni][nj]!=0) {
						// 터널 구조물 타입별로 처리
						if(check(map[ni][nj], d) ) {
							v[ni][nj] = true;
							q.offer(new Tunnel(ni, nj, deep+1));
						}
					}
				}
				
			}
		}
		if(type==7) {	// 상좌
			for(int d=0;d<4;d++) {
				if(d%2==0) {
					int ni = pi+di[d];
					int nj = pj+dj[d];
					
					if(0<=ni && ni<N && 0<=nj && nj<M && !v[ni][nj]&& map[ni][nj]!=0) {
						// 터널 구조물 타입별로 처리
						if(check(map[ni][nj], d) ) {
							v[ni][nj] = true;
							q.offer(new Tunnel(ni, nj, deep+1));
						}
					}
				}
				
			}
		}
	}

	private static boolean check(int type, int d) { 		//다음 터널과 이어지는지 확인 (이어지면 true)
		if(d==0) {			// 상	
			// 하 가능
			if(type==1 || type==2 || type==5 || type==6) {
				return true;
			}
		} else if(d==1) { 	// 하
			// 상 가능
			if(type==1 || type==2 || type==4 || type==7) {
				return true;
			}
			
		} else if(d==2) {	// 좌
			// 우 가능
			if(type==1 || type==3 || type==4 || type==5) {
				return true;
			}
		} else if(d==3) {	// 우
			// 좌 가능
			if(type==1 || type==3 || type==6 || type==7) {
				return true;
			}
		}
		return false;
	}

	

}
