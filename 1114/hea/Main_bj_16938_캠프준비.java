package studyAlgo;

import java.io.*;
import java.util.*;

public class Main_bj_16938_캠프준비 {
	static int N, L, R, X;
	static int[] problems, pick;
	static boolean[] v;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // N문제
		L = Integer.parseInt(st.nextToken()); // 문제 합의 최소값
		R = Integer.parseInt(st.nextToken()); // 문제 합의 최대값
		X = Integer.parseInt(st.nextToken()); // 가장 어려운 문제 - 가장 쉬운 문제 >= X

		problems = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			problems[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(problems);
		for (int n = 2; n <= N; n++) {
			v = new boolean[n];
			pick = new int[n];
			comb(n, 0, 0, 0);
		}

		System.out.println(result);
		br.close();
	}

	static void comb(int n, int cnt, int start, int sum) {
		if (sum > R) return;

		if (cnt == n) {
			if (sum < L) return;
			if (pick[n - 1] - pick[0] < X) return;
			result++;

			return;
		}

		for (int i = start; i < N; i++) {
			pick[cnt] = problems[i];

			v[cnt] = true;
			comb(n, cnt + 1, i + 1, sum + problems[i]);
			v[cnt] = false;
		}
	}

}