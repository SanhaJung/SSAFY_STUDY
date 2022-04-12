package bj1912;

import java.util.*;
import java.io.*;

public class Main_bj_1912_연속합 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
		int n = Integer.parseInt(in.readLine());
		int[] arr = new int[n];
		int[] dp = new int[n];
		int answer = Integer.MIN_VALUE/2;
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0] = arr[0];
		
		for(int i=1; i<n; i++) {
			// i번째수까지의 누적합과 현재 수를 비교했을 때, 현재 수가 더 크면 기존 합 초기화
			dp[i] = Math.max(dp[i-1]+arr[i],arr[i]);
		}
		
		for(int i=0; i<n; i++) {
			answer = Math.max(answer, dp[i]);
		}
		
		System.out.println(answer);
		
	}
}
