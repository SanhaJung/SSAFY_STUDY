package bj14889;

import java.util.*;
import java.io.*;

public class Main_bj_14889_스타트와링크 {
	
	static boolean[] team;
	static int N;
	static int[][] arr;
	static int answer = Integer.MAX_VALUE/2;
	static int total;
	
	static void combination(int cnt, int start) {	
		
		if(cnt==N/2) {
			int a = 0;
			int b = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(team[i] && team[j]) a+=arr[i][j];
					else if (!team[i] && !team[j]) b+= arr[i][j];
				}
			}
			answer = Math.min(answer, Math.abs(a-b));
			return;
		}
		
		for(int i=start; i<N; i++) {
			team[i] = true;
			combination(cnt+1,i+1);
			team[i] = false;	
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		arr = new int[N][N];
		team = new boolean[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		combination(0,0);
		System.out.println(answer);
	}
}
