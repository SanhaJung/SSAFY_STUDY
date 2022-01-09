package bj1600;

import java.util.*;
import java.io.*;

public class Main_bj_1600_말이되고픈원숭이 {
	
	static int[] mr = {-1,0,1,0};
	static int[] mc = {0,1,0,-1};
	static int[] hr = {-2,-1,1,2,2,1,-1,-2};
	static int[] hc = {1,2,2,1,-1,-2,-2,-1};
	static int[][] arr;
	static int[][][] visited;
	static int K,H,W,answer;
	
	static void bfs(int r, int c, int k) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r,c,k});
		visited[r][c][k] = 1;
		int cnt = 0;
		
		while(!q.isEmpty()) {	
			int[] cur = q.poll();
			r = cur[0];
			c = cur[1];
			k = cur[2];
			
			if(r==H-1 && c==W-1) {
				answer = visited[r][c][k];
				break;
			}
			
			for(int d=0; d<4; d++) {
				int nr = r+mr[d];
				int nc = c+mc[d];
				if(0<=nr && nr<H && 0<=nc && nc<W && arr[nr][nc]==0 && visited[nr][nc][k]==0) {
					visited[nr][nc][k] = visited[r][c][k]+1;
					q.offer(new int[] {nr,nc,k});
				}
			}
			for(int d=0; d<8; d++) {
				int nr = r+hr[d];
				int nc = c+hc[d];
				if(0<=nr && nr<H && 0<=nc && nc<W  && arr[nr][nc]==0 && k<K && visited[nr][nc][k+1]==0) {
					visited[nr][nc][k+1] = visited[r][c][k]+1;
					q.offer(new int[] {nr,nc,k+1});
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		arr = new int[H][W];
		visited = new int[H][W][K+1];
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<W; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs(0,0,0);
		
		System.out.println(answer-1);
		
	}
}
