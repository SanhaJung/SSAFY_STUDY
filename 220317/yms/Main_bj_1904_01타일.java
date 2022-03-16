package bj1904;

import java.util.*;
import java.io.*;

public class Main_bj_1904_01타일 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		int[] dp = new int[N+1];
		
		dp[1] = 1;
		if(N==1) System.out.println(dp[1]);
		else {
			dp[2] = 2;
			for(int i=3; i<=N; i++) {
				// 타일 1을 붙이는 경우와 타일 00을 붙이는 경우 2가지 경우가 존재함
				// 1을 붙일 땐 타일 1개만 사용하므로 타일 i-1개를 사용했을 때의 경우의 수와 같고
				// 00을 붙일 땐 타일 2개를 사용하므로 타일 i-2개를 사용했을 때의 경우의 수와 같음
				// 따라서 두 경우 더해주면 타일 i개를 사용했을 때의 경우의 수가 나옴
				dp[i] = (dp[i-1]+dp[i-2])%15746;
			}
			System.out.println(dp[N]);
		}
	}
}
