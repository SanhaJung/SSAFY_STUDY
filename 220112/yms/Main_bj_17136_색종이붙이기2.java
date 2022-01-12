package bj17136;

import java.util.*;
import java.io.*;

//백트래킹
public class Main_bj_17136_색종이붙이기2 {
	
	static int answer = Integer.MAX_VALUE/2;
	
	static int[][] map = new int[10][10];
	static int[] paper = {0,5,5,5,5,5};
	
	static void dfs(int r, int c, int cnt) {
		
		if(cnt>=answer)
			return;
		
		if(r==10 && c==0) {
			answer = Math.min(answer, cnt);
			return;
		}
		
		if(c>9) {
			dfs(r+1,0,cnt);
			return;
		}
		
		if(map[r][c]==1) {
			for(int size=5; size>=1; size--) {
				if(paper[size]>0 && check(r,c,size)) {
					cover(r,c,size,0);
					paper[size]--;
					dfs(r,c+1,cnt+1);
					cover(r,c,size,1);
					paper[size]++;
				}
			}
		}
		else {
			dfs(r,c+1,cnt);
		}
	}
	
	static boolean check(int r, int c, int size) {
		if(r+size>10 || c+size>10) return false;
		for(int i=r; i<r+size; i++) {
			for(int j=c; j<c+size; j++) {
				if(map[i][j]==0) return false;
			}
		}
		return true;
	}
	
	static void cover(int r, int c, int size, int num) {
		for(int i=r; i<r+size; i++) {
			for(int j=c; j<c+size; j++) {
				map[i][j]=num;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for(int i=0; i<10; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,0,0);
		if(answer == Integer.MAX_VALUE/2) answer = -1;
		System.out.println(answer);
	}
}
