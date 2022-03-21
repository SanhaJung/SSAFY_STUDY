package bj11053;

import java.util.*;
import java.io.*;

public class Main_bj_11053_가장긴증가하는부분수열 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int[] arr = new int[N];
		int[] dp = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++) {
			dp[i] = 1;
		}
		
		for(int i=1; i<N; i++) {
			for(int j=0; j<i; j++) {
				// dp[i] : i번째 숫자까지 만들수있는 최장수열
				if(arr[i]>arr[j]) dp[i] = Math.max(dp[i], dp[j]+1);
			}
		}
		
		int answer = 0;
		
		for(int i=0; i<N; i++) {
			answer = Math.max(answer, dp[i]);
		}
		
		System.out.println(answer);
		
	}
}
