package bj19238;

import java.util.*;
import java.io.*;

public class Main_bj_19238_스타트택시 {
	
	static int N,M,FUEL;
	static int[][] map;
	static int[] dr = {-1,0,1,0}, dc = {0,-1,0,1};
	static int answer = -1;
	
	static void find_passenger(int r, int c) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		q.offer(new int[] {r,c,FUEL});
		visited[r][c] = true;
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			r = cur[0];
			c = cur[1];
			int fuel = cur[2];
			
			if(fuel==0)
				continue;
			
			if(cnt==M) {
				answer = fuel;
				return;
			}
			
			if(map[r][c]>=0) {
				int er = map[r][c]/N;
				int ec = map[r][c]%N;
				
				int distance = getDist(r,c,er,ec,fuel);
				if(distance==-1) {
					answer = -1;
					return;
				}
				else {
					q.clear();
					visited = new boolean[N][N];
					q.offer(new int[] {er,ec,fuel+distance});
					visited[er][ec] = true;
					cnt++;
					map[r][c]=-1;
				}
			}
			else {
				for(int d=0; d<4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					if(0<=nr&&nr<N && 0<=nc && nc<N && !visited[nr][nc] && map[nr][nc]!= -2) {
						q.offer(new int[] {nr,nc,fuel-1});
						visited[nr][nc] = true;
					}
				}
			}
		}
	}
	
	static int getDist(int r, int c, int er, int ec, int fuel) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		int[][] visited = new int[N][N];
		q.offer(new int[] {r,c});
		visited[r][c] = 1;
		
		while(!q.isEmpty()) {
				int[] cur = q.poll();
				r = cur[0];
				c = cur[1];
				
				if(visited[r][c]-1>fuel)
					continue;
				
				if(r == er && c == ec) {
					return visited[r][c]-1;
				}
				
				for(int d=0; d<4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					if(0<=nr&&nr<N && 0<=nc && nc<N && visited[nr][nc]==0 && map[nr][nc]!= -2) {
						q.offer(new int[] {nr,nc});
						visited[nr][nc] = visited[r][c]+1;
					}
				}
			}
		return -1;
		}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		FUEL = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());		//-1 = 빈칸, -2 = 벽
				if(map[i][j]==0)
					map[i][j] = -1;
				else {
					map[i][j] = -2;
				}
			}
		}
		
		st = new StringTokenizer(in.readLine());
		int r = Integer.parseInt(st.nextToken())-1;
		int c = Integer.parseInt(st.nextToken())-1;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine());
			int sr = Integer.parseInt(st.nextToken())-1;
			int sc = Integer.parseInt(st.nextToken())-1;
			int er = Integer.parseInt(st.nextToken())-1;
			int ec = Integer.parseInt(st.nextToken())-1;
			
			map[sr][sc] = er*N+ec;
		}
		
		find_passenger(r,c);
		
		System.out.println(answer);
		
	}
}
