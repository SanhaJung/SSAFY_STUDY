package programmers;

import java.util.Arrays;

public class Solution_동적계획법_등굣길2 {
	public static void main(String[] args) {
		int[][] test = new int[][] { { 2, 2 } };
		System.out.println(solution(4, 3, test));
	}

	static int solution(int m, int n, int[][] puddles) {
		int mod = 1000000007;

		int[][] dp = new int[n][m];
		for (int i = 0; i < puddles.length; i++) {
			dp[puddles[i][1] - 1][puddles[i][0] - 1] = -1;
		}

		dp[0][0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (dp[i][j] == -1) continue; // 웅덩이
				
				if (i - 1 >= 0 && dp[i - 1][j] > 0) dp[i][j] += dp[i - 1][j] % mod; // 위에서 오는 경우 
				if (j - 1 >= 0 && dp[i][j - 1] > 0) dp[i][j] += dp[i][j - 1] % mod; // 왼쪽에서 오는 경우
			}
		}
		for (int[] is : dp) {
			System.out.println(Arrays.toString(is));
		}
		return dp[n - 1][m - 1] % mod;
	}
}
