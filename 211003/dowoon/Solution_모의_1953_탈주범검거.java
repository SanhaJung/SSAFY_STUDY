package s1003;
import java.io.*;
import java.util.*;


public class Solution_모의_1953_탈주범검거 {
	
	static class Tunnel{
		int y;
		int x;
		int time;
		
		public Tunnel(int y, int x, int time) {
			super();
			this.y = y;
			this.x = x;
			this.time = time;		// 탈주범에게 제한 시간이 있기때문에 BFS 탐색 깊이 저장
		}
	}
	static int N, M, R, C, L, answer;
	static int[][] map;
	static boolean[][] v;
	static int[] dy = {-1, 1, 0, 0};// 상하좌우 순 
	static int[] dx = { 0, 0,-1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			v = new boolean[N][M];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0;j<M;j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); 
				}
			}
			
			answer = 0;
			bfs(R, C, L);
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}


	private static void bfs(int R, int C, int L) {
		Queue<Tunnel> q = new ArrayDeque<>(); 
		v[R][C] = true;
		q.offer(new Tunnel(R, C, 0)); 

		while(!q.isEmpty()) {
			Tunnel cur = q.poll();
			int y = cur.y;
			int x = cur.x;
			int time = cur.time;
			if(time<L) {
				answer++;
				if(map[y][x]!=0) { 
					switch (map[y][x]) {
					case 1:
						for(int d=0; d<4; d++){
							int ny = y + dy[d];
							int nx = x + dx[d];
							
							if(ny<0||ny>=N || nx<0||nx>=M || map[ny][nx]==0 || v[ny][nx]) continue;
							
							if(d==0 && (map[ny][nx]==1 || map[ny][nx]==2 || map[ny][nx]==5 || map[ny][nx]==6)) {								
								v[ny][nx] = true;
								q.offer(new Tunnel(ny, nx, time+1));
							} else if(d==1 && (map[ny][nx]==1 || map[ny][nx]==2 || map[ny][nx]==4 || map[ny][nx]==7)) {
								v[ny][nx] = true;
								q.offer(new Tunnel(ny, nx, time+1));								
							} else if(d==2 && (map[ny][nx]==1 || map[ny][nx]==3 || map[ny][nx]==4 || map[ny][nx]==5)) {
								v[ny][nx] = true;
								q.offer(new Tunnel(ny, nx, time+1));								
							} else if(d==3 && (map[ny][nx]==1 || map[ny][nx]==3 || map[ny][nx]==6 || map[ny][nx]==7)) {
								v[ny][nx] = true;
								q.offer(new Tunnel(ny, nx, time+1));								
							}
						}
						break;
					case 2:
						for(int d=0; d<4; d++){
							if(d>1) continue;
							
							int ny = y + dy[d];
							int nx = x + dx[d];
							
							if(ny<0||ny>=N || nx<0||nx>=M || map[ny][nx]==0 || v[ny][nx]) continue;
							
							if(d==0 && (map[ny][nx]==1 || map[ny][nx]==2 || map[ny][nx]==5 || map[ny][nx]==6)) {								
								v[ny][nx] = true;
								q.offer(new Tunnel(ny, nx, time+1));
							} else if(d==1 && (map[ny][nx]==1 || map[ny][nx]==2 || map[ny][nx]==4 || map[ny][nx]==7)) {
								v[ny][nx] = true;
								q.offer(new Tunnel(ny, nx, time+1));								
							}
						}
						break;
					case 3:
						for(int d=0; d<4; d++){
							if(d<2) continue;
							
							int ny = y + dy[d];
							int nx = x + dx[d];
							
							if(ny<0||ny>=N || nx<0||nx>=M || map[ny][nx]==0 || v[ny][nx]) continue;
							
							if(d==2 && (map[ny][nx]==1 || map[ny][nx]==3 || map[ny][nx]==4 || map[ny][nx]==5)) {
								v[ny][nx] = true;
								q.offer(new Tunnel(ny, nx, time+1));								
							} else if(d==3 && (map[ny][nx]==1 || map[ny][nx]==3 || map[ny][nx]==6 || map[ny][nx]==7)) {
								v[ny][nx] = true;
								q.offer(new Tunnel(ny, nx, time+1));								
							}
						}
						break;
					case 4:
						for(int d=0; d<4; d++){
							if(d==1 || d==2) continue;
							
							int ny = y + dy[d];
							int nx = x + dx[d];
							
							if(ny<0||ny>=N || nx<0||nx>=M || map[ny][nx]==0 || v[ny][nx]) continue;
							
							if(d==0 && (map[ny][nx]==1 || map[ny][nx]==2 || map[ny][nx]==5 || map[ny][nx]==6)) {								
								v[ny][nx] = true;
								q.offer(new Tunnel(ny, nx, time+1));
							} else if(d==3 && (map[ny][nx]==1 || map[ny][nx]==3 || map[ny][nx]==6 || map[ny][nx]==7)) {
								v[ny][nx] = true;
								q.offer(new Tunnel(ny, nx, time+1));								
							}
						}
						break;
					case 5:
						for(int d=0; d<4; d++){
							if(d%2==0) continue; 
							
							int ny = y + dy[d];
							int nx = x + dx[d];
							
							if(ny<0||ny>=N || nx<0||nx>=M || map[ny][nx]==0 || v[ny][nx]) continue;
							
							if(d==1 && (map[ny][nx]==1 || map[ny][nx]==2 || map[ny][nx]==4 || map[ny][nx]==7)) {								
								v[ny][nx] = true;
								q.offer(new Tunnel(ny, nx, time+1));
							} else if(d==3 && (map[ny][nx]==1 || map[ny][nx]==3 || map[ny][nx]==6 || map[ny][nx]==7)) {
								v[ny][nx] = true;
								q.offer(new Tunnel(ny, nx, time+1));								
							}
						}
						break;

					case 6:
						for(int d=0; d<4; d++){
							if(d==0 || d==3) continue;
							
							int ny = y + dy[d];
							int nx = x + dx[d];
							
							if(ny<0||ny>=N || nx<0||nx>=M || map[ny][nx]==0 || v[ny][nx]) continue;
							
							if(d==1 && (map[ny][nx]==1 || map[ny][nx]==2 || map[ny][nx]==4 || map[ny][nx]==7)) {								
								v[ny][nx] = true;
								q.offer(new Tunnel(ny, nx, time+1));
							} else if(d==2 && (map[ny][nx]==1 || map[ny][nx]==3 || map[ny][nx]==4 || map[ny][nx]==5)) {
								v[ny][nx] = true;
								q.offer(new Tunnel(ny, nx, time+1));								
							}
						}
						break;
					case 7:
						for(int d=0; d<4; d++){
							if(d%2==1) continue;
							int ny = y + dy[d];
							int nx = x + dx[d];
							
							if(ny<0||ny>=N || nx<0||nx>=M || map[ny][nx]==0 || v[ny][nx]) continue;
							
							if(d==0 && (map[ny][nx]==1 || map[ny][nx]==2 || map[ny][nx]==5 || map[ny][nx]==6)) {								
								v[ny][nx] = true;
								q.offer(new Tunnel(ny, nx, time+1));
							} else if(d==2 && (map[ny][nx]==1 || map[ny][nx]==3 || map[ny][nx]==4 || map[ny][nx]==5)) {
								v[ny][nx] = true;
								q.offer(new Tunnel(ny, nx, time+1));								
							}
						}
						break;
					}
				}
			}	
		}
	}
}