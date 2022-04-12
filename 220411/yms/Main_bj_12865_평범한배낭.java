package bj12865;

import java.util.*;
import java.io.*;

public class Main_bj_12865_평범한배낭 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] weights = new int[N];
		int[] profits = new int[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			weights[i] = Integer.parseInt(st.nextToken());
			profits[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[K+1];
		// dp[i] : 배낭에 무게를 i만큼 넣었을 때 넣을 수 있는 최대 가치
		for(int i=0; i<N; i++) {
			for(int j=K; j>=weights[i]; j--) {
				// 무게를 j만큼 넣었을 때 현재 가치와 i번째 물건을 넣을 때의 가치
				dp[j] = Math.max(dp[j], dp[j-weights[i]]+profits[i]); 
			}
		}
		
		System.out.println(dp[K]);
	}
}
