package bj2156;

import java.util.*;
import java.io.*;

public class Main_bj_2156_포도주시식 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		int[] wine = new int[n+1];
		int answer = 0;
		
		for(int i=1; i<=n; i++) {
			wine[i] = Integer.parseInt(in.readLine());
		}
		
		int[] dp = new int[n+1];
		
		dp[1] = wine[1];
		
		if(n==1) answer = dp[1];
		
		else {
			dp[2] = wine[1]+wine[2];
			
			for(int i=3; i<=n; i++) {
				dp[i] = Math.max(dp[i-1], Math.max(dp[i-2]+wine[i],dp[i-3]+wine[i-1]+wine[i]));
			}
			
			for(int i=1; i<=n; i++) {
				answer = Math.max(answer,dp[i]);
			}
		}
		System.out.println(answer);
		
	}
}
