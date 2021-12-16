package bj20058;

import java.util.*;
import java.io.*;

public class Main_bj_20058_마법사상어와파이어스톰 {
	
	static int N,Q,L,big,cnt;
	static int[][] map;
	static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
	static boolean[][] visited;
	
	static void rotate() {
		int size = (int) Math.pow(2, L);
		
		int[][] temp = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				temp[i][j] = map[i][j];
			}
		}
		
		for(int x=0; x<N; x+=size) {
			for(int y=0; y<N; y+=size) {
				
				for(int i=x; i<x+size; i++) {
					for(int j=y; j<y+size; j++) {
						map[j-y+x][x+size-1-i+y] = temp[i][j];
					}
				}
				
			}
		}
	}
	
	static void check() {
		int[][] temp = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				temp[i][j] = map[i][j];
			}
		}
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				int cnt = 0;
				for(int d=0; d<4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					if(0<=nr && nr<N && 0<=nc && nc<N) {
						if(temp[nr][nc]>0) cnt++;
					}
				}
				if(cnt<3) {
					if(map[r][c]>0) map[r][c]--;
				}
			}
		}
	}

//	1.bfs로 탐색
//	static void bfs(int r, int c) {
//		ArrayDeque<int[]> q = new ArrayDeque<>();
//		int cnt = 1;
//		visited[r][c] = true;
//		q.offer(new int[] {r,c});
//		
//		while(!q.isEmpty()) {
//			int[] cur = q.poll();
//			r = cur[0];
//			c = cur[1];
//			for(int d=0; d<4; d++) {
//				int nr = r+dr[d];
//				int nc = c+dc[d];
//				if(0<=nr && nr<N && 0<=nc && nc<N && !visited[nr][nc] && map[nr][nc]>0) {
//					visited[nr][nc] = true;
//					q.offer(new int[] {nr,nc});
//					cnt++;
//				} 
//			}
//		}
//		
//		big = Math.max(big, cnt);
//		return;
//	}	
	
//	2. cnt라는 전역변수를 두고 dfs로 탐색
//	static void dfs(int r, int c) {	
//		visited[r][c] = true;
//		for(int d=0; d<4; d++) {
//			int nr = r+dr[d];
//			int nc = c+dc[d];
//			if(0<=nr && nr<N && 0<=nc && nc<N && !visited[nr][nc] && map[nr][nc]>0) {
//				cnt++;
//				dfs(nr,nc);
//			}
//		}
//	}
	
//	3. 전역변수를 안쓰고 dfs로 탐색
	static int dfs(int r, int c) {
		visited[r][c] = true;
		int cnt = 1;
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(0<=nr && nr<N && 0<=nc && nc<N && !visited[nr][nc] && map[nr][nc]>0) {
				cnt += dfs(nr,nc);
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = (int) Math.pow(2,Integer.parseInt(st.nextToken()));
		Q = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(in.readLine());
		
		for(int i=0; i<Q; i++) {
			L = Integer.parseInt(st.nextToken());
			rotate();
			check();
		}
		
		int sum = 0;
		big = 0;
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sum += map[i][j];
				
				if(!visited[i][j] && map[i][j]>0) {
//					bfs(i,j);										// 1. bfs로 탐색
					
//					cnt = 1;
//					dfs(i,j);										// 2. cnt라는 전역변수를 두고 dfs로 탐색
					
					big = Math.max(big, dfs(i,j));					// 3. 전역변수를 안쓰고 dfs로 탐색
				}
			}
		}
		
		System.out.println(sum);
		System.out.println(big);
		
	}
}
