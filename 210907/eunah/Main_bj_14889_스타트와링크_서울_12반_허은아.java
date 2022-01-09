package as0907;

import java.io.*;
import java.util.*;

public class Main_bj_14889_스타트와링크_서울_12반_허은아 {
	static int[] a;
	static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[][] link = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				link[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		a = new int[N / 2];
		min = Integer.MAX_VALUE;
		comb(N / 2, 0, 0, link);
		System.out.println(min);
	}

	static void comb(int n, int cnt, int start, int[][] link) {
		if (cnt == n) {
			int sum1 = 0;
			boolean[] v = new boolean[n * 2];
			for (int num : a) {
				v[num] = true;
				for (int num2 : a) {
					sum1 += link[num][num2];
				}	
			}
			
			int[] b = new int[n];
			int k = 0;
			for (int i = 0; i < v.length; i++) {
				if (!v[i]) {
					b[k++] = i;
				}
			}
			
			int sum2=0;
			for (int num : b) {
				for (int num2 : b) {
					sum2 += link[num][num2];
				}	
			}
			min = Math.min(min, Math.abs(sum1-sum2));
			return;

		}

		for (int i = start; i < link.length; i++) {
			a[cnt] = i;
			comb(n, cnt + 1, i + 1, link);
		}

	}

}
