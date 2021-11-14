package bj14500;

import java.util.*;
import java.io.*;

public class Main_bj_14500_테트로미노 {
	
	static int N,M;
	static int[][] board;
	static int[] dr = {-1,-1,0,1,1,1,0,-1}, dc = {0,1,1,1,0,-1,-1,-1};
	static int answer = Integer.MIN_VALUE/2;
	static boolean[][] visited;
		
	static void dfs(int r, int c, int sum, int cnt) {
		if(cnt==3) {
			for(int d=0; d<8; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(0<=nr && nr<N && 0<=nc && nc<M && !visited[nr][nc] && check(nr,nc)) {
					answer = Math.max(answer, sum+board[nr][nc]);
				}
			}
			return;
		}
		
		for(int d=0; d<8; d+=2) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(0<=nr && nr<N && 0<=nc && nc<M && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr,nc,sum+board[nr][nc], cnt+1);
				visited[nr][nc] = false;
			}
		}
	}
	
	static boolean check(int r, int c) {
		for(int d=0; d<8; d+=2) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(0<=nr && nr<N && 0<=nc && nc<M && visited[nr][nc]) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				dfs(i,j,0,0);
			}
		}
		
		System.out.println(answer);
	}
}
