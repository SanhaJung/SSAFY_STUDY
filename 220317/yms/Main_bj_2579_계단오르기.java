package bj2579;

import java.util.*;
import java.io.*;

public class Main_bj_2579_계단오르기 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(in.readLine());
		int[] stairs = new int[n+1];
		
		for(int i=1; i<=n; i++) {
			stairs[i] = Integer.parseInt(in.readLine());
		}
		
		int[] dp = new int[n+1];
		dp[1] = stairs[1];
		
		if(n==1) {
			System.out.println(dp[1]);
		}
		else {
			dp[2] = stairs[1]+stairs[2];
			
			for(int i=3; i<=n; i++) {
				// 연속 3개를 넘으면 안되고 마지막 계단은 항상 밟아야 하므로 XOO, OXO 2가지 경우가 생김
				// XOO의 경우 마지막 계단과 마지막-1 계단을 더한뒤 마지막-2 계단을 건너뛰고 마지막-3 계단까지 갔을 때의 최대값을 더해주고
				// OXO의 경우 마지막 계단을 밟고 마지막-1 계단을 건너뛴 뒤 마지막-2 계단까지의 최대값을 더해줌
				dp[i] = Math.max(stairs[i]+stairs[i-1]+dp[i-3], stairs[i]+dp[i-2]);
			}
			
			System.out.println(dp[n]);
		}
	}
}
