package bj15684;

import java.util.*;
import java.io.*;

public class Main_bj_15684_사다리조작 {
	
	static boolean[][] ladder;
	static int N,H;
	static int answer = Integer.MAX_VALUE;
	
	static boolean game() {
		
		for(int i=1; i<=N; i++) {
			int start = i;
			for(int j=1; j<=H; j++) {
				if(ladder[j][i]) {
					i++;
				}
				else if(ladder[j][i-1]) {
					i--;
				}
			}
			if(start==i) continue;
			else return false;
		}
		
		return true;
		
	}
	
	static void combination(int start, int cnt) {
		
		if(cnt>3) {
			return;
		}
		
		if(game()) {
			answer = Math.min(answer, cnt);
			return;
		}
		
		for(int i=start; i<=H; i++) {
			for(int j=1; j<N; j++) {
				if (ladder[i][j] == true) continue;
	            if (ladder[i][j-1] == true) continue;
	            if (ladder[i][j+1] == true) continue;
	            
	            ladder[i][j] = true;
	            combination(i, cnt + 1);
	            ladder[i][j] = false;

			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		ladder = new boolean[H+1][N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ladder[a][b] = true;
		}
		
//		for(int i=1; i<=H; i++) {
//			for(int j=1; j<=N; j++) {
//				System.out.print(ladder[i][j]+"\t");
//			}
//			System.out.println();
//		}
		
		combination(0,0);
		
		if(answer==Integer.MAX_VALUE) answer = -1;
		
		System.out.println(answer);
		
		
	
	}
}
