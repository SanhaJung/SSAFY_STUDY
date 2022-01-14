package baekjoon;

import java.io.*;
import java.util.*;

public class Main_bj_2230_수고르기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // N개의 수
		int M = Integer.parseInt(st.nextToken()); // i~j번째 수의 합이 M이 된다.

		long[] num = new long[N];
		for (int index = 0; index < N; index++) {
			num[index] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(num); // 수열 정렬(오름차순)

		long diff = Long.MAX_VALUE;
		int start = 0;
		int end = 0;
		// M = 3
		while (end < N) {
			if (num[end] - num[start] < M) {
				end++;
				continue;
			}

			if (num[end] - num[start] == M) {
				diff = M;
				break;
			}
			diff = Math.min(diff, num[end] - num[start]);
			start++;
		}
		System.out.println(diff);
		br.close();
	}
}
