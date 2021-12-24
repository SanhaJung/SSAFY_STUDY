package bj17070;

import java.util.*;
import java.io.*;

public class Main_bj_17070_파이프옮기기1 {
	static int[][] map;
	static int[] dr = {0,1,1}, dc = {1,0,1}; // →, ↓, ↘, status : 0 = 가로, 1 = 세로, 2 = 대각선
	static boolean[][] visited;
	static int N,answer;
	
	//가로일땐 가로,대각선 = status:0일땐 d=0,2
	//세로일땐 세로,대각선 = status:1일땐 d=1,2
	//대각선일땐 가로,세로,대각선 = status:2일땐 d=0,1,2
	
	static void dfs(int r, int c, int status) {
		if(r==N-1 && c == N-1) {
			answer++;
			return;
		}
		
		for(int d=0; d<3; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(0<=nr && nr<N && 0<=nc && nc<N && !visited[nr][nc] && map[nr][nc]==0) {
				if(d==0) {	// 가로 파이프를 놓을 때
					if(status==0 || status==2) {
						visited[nr][nc] = true;
						dfs(nr,nc,d);
						visited[nr][nc] = false;
					}
				}
				else if (d==1) {	// 세로 파이프를 놓을 때
					if(status==1 || status == 2) {
						visited[nr][nc] = true;
						dfs(nr,nc,d);
						visited[nr][nc] = false;
					}
				}
				else {	// 대각선 파이프를 놓을 때
					if (!visited[nr-1][nc] && !visited[nr][nc-1] && map[nr-1][nc]==0 && map[nr][nc-1]==0) {
						visited[nr][nc] = true;
						visited[nr-1][nc] = true;
						visited[nr][nc-1] = true;
						dfs(nr,nc,d);
						visited[nr][nc] = false;
						visited[nr-1][nc] = false;
						visited[nr][nc-1] = false;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited[0][0] = true;
		visited[0][1] = true;
		dfs(0,1,0);
		
		System.out.println(answer);
	}
}
