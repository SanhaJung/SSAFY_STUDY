package bj20057;

import java.util.*;
import java.io.*;

public class Main_bj_20057_마법사상어와토네이도 {
	
	static int[][] map;
	static int N,answer;
	static int[] dr = {0,1,0,-1}, dc = {-1,0,1,0};	// 왼,아래,오른,위
	static int[][] sr = {{-1,1,-2,-1,1,2,-1,1,0}, {-1,-1,0,0,0,0,1,1,2},{1,-1,2,1,-1,-2,1,-1,0}, {1,1,0,0,0,0,-1,-1,-2}};       
	static int[][] sc = {{1,1,0,0,0,0,-1,-1,-2},{-1,1,-2,-1,1,2,-1,1,0},{-1,-1,0,0,0,0,1,1,2},{1,-1,2,1,-1,-2,1,-1,0}};
	static int[] info = {1,1,2,7,7,2,10,10,5};
	
	static void tornado(int r, int c) {
		int cur = 1;
		int d = 0;
		
		while(cur<N) {
			for(int i=0; i<cur; i++) {
				r += dr[d];
				c += dc[d];
				spread(r,c,d);
			}
			d = (d+1)%4;
			
			for(int i=0; i<cur; i++) {
				r += dr[d];
				c += dc[d];
				spread(r,c,d);
			}
			d = (d+1)%4;
			
			cur++;
		}
		
		for(int i=0; i<cur-1; i++) {
			r += dr[d];
			c += dc[d];
			spread(r,c,d);
		}
	}
	
	static void spread(int r, int c, int d) {
		int sum = 0;
		
		for(int i=0; i<9; i++) {
			int nr = r+sr[d][i];
			int nc = c+sc[d][i];
			if(0<=nr && nr<N && 0<=nc && nc<N) {
				map[nr][nc] += (map[r][c]*info[i])/100;
			}
			else {
				answer += (map[r][c]*info[i])/100;
			}
			sum += (map[r][c]*info[i])/100;
		}
		
		if(0<=r+dr[d] && r+dr[d]<N && 0<=c+dc[d] && c+dc[d]<N) {
			map[r+dr[d]][c+dc[d]] += map[r][c]-sum;
		}
		else {
			answer += map[r][c]-sum;
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(in.readLine());
		
		map = new int[N][N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		tornado(N/2, N/2);
		
		System.out.println(answer);
	}
}
