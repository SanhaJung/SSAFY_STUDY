package dp;

import java.io.*;

public class Main_bj_9461_파도수열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			long[] dp = new long[N + 1];
			if(N <= 3) {
				sb.append(1).append("\n");
				continue;
			}
			dp[1] = 1;
			dp[2] = 1;
			dp[3] = 1;
			
			for (int i = 4; i <= N; i++) {
				dp[i] = dp[i-2] + dp[i-3];
			}
			sb.append(dp[N]).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

}
