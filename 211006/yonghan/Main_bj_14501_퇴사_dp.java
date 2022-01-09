package a1006;

import java.io.*;
import java.util.*;

public class Main_bj_14501_퇴사_dp {
	static int N, money;
	static int[] T, P, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		T = new int[N + 1];
		P = new int[N + 1];
		dp = new int[N + 2]; // n일 부터 퇴사날 까지 얻을 수 있는 최대 이익
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		dp[0] = 0;
		money = 0;

		for (int i = N; i >0 ; i--) {
			int day = i + T[i]; // i번째 날의 총 상담기간 
			
			//상담일 초과 (N번째 날도 상담할수 있으므로 N+2 이상일때 상담못함)
			if ( day > N+1) dp[i] = dp[i+1]; 
			
			else {
				//다음 날의 최대 이익과   당일에 상담했을 시 최대 이익 중 큰 값
				dp[i] = Math.max(P[i] + dp[day], dp[i + 1]);
			}
			System.out.println(Arrays.toString(dp));
		}
		
		System.out.println(dp[1]);

	}

}
