package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_bj_9095_123더하기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] dp = new int[N + 1];

			dp[1] = 1;			   // 1
			if (N >= 2) dp[2] = 2; // 1 1 / 2 
			if (N >= 3) dp[3] = 4; // 1 1 1 / 1 2 / 2 1 / 3
			 
			if (N >= 4) {
				for (int i = 4; i <= N; i++) {
					dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
				}
			}
			
			// i = 4
			// dp[4] = 
			// dp[3] = 111 12 21 3
			// dp[2] = 11 2
			// dp[1] = 1
			//
			
			sb.append(dp[N]).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}
