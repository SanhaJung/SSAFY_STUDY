package bj14501;

import java.util.*;
import java.io.*;

public class Main_bj_14501_퇴사 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		StringTokenizer st;
		int[][] arr = new int[N][2];
		int[] dp = new int[N];
		int answer = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken()); // 걸리는 날짜
			arr[i][1] = Integer.parseInt(st.nextToken()); // 비용
			dp[i] = arr[i][1];
		}
		
		for(int i=1; i<N; i++) {
			for(int j=0; j<i; j++) {
				if(j+arr[j][0]<=i)
					dp[i] = Math.max(dp[i], dp[j]+arr[i][1]);
			}
			
		}
	
		for(int i=0; i<N; i++)
			System.out.println(dp[i]);
		
		for(int i=0; i<N; i++) {
			if(i+arr[i][0]<=N)
				answer = Math.max(answer, dp[i]);
		}
		System.out.println(answer);
	}
}
