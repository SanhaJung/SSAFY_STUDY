package studyAlgo;

import java.io.*;
import java.util.*;

public class Main_17406_배열돌리기4 {
	static int[][] map, ops;
	static int N, M, K, result;
	static int[] pick;
	static boolean[] v;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		ops = new int[K][3];
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());

			ops[k] = new int[] { r, c, s };
		}
		pick = new int[K];
		v = new boolean[K];
		result = Integer.MAX_VALUE;
		perm(0);

		System.out.println(result);
	}

	static void perm(int cnt) {
		if (cnt == K) {
			int[][] tmpMap = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					tmpMap[i][j] = map[i][j];
				}
			}
			for (int p : pick) {
				rotate(ops[p], tmpMap);
			}
			calc(tmpMap);
		}

		for (int i = 0; i < K; i++) {
			if (v[i]) continue;

			pick[cnt] = i;
			v[i] = true;
			perm(cnt + 1);
			v[i] = false;
		}
	}

	static void rotate(int[] op, int[][] map) {
		int n = op[2]; // 회전 횟수

		int r = op[0];
		int c = op[1];

		for (int i = 1; i <= n; i++) {

			int cnt = map[r - i][c - i];
			// 윗 행 이동
			for (int k = c - i + 1; k < c - i + 1 + i * 2; k++) {
				int temp = map[r - i][k];
				map[r - i][k] = cnt;
				cnt = temp;
			}

			// 오른쪽 열 이동
			for (int k = r - i + 1; k < r - i + 1 + i * 2; k++) {
				int temp = map[k][c + i];
				map[k][c + i] = cnt;
				cnt = temp;
			}

			// 아래 행 이동
			for (int k = c + i - 1; k > c + i - 1 - i * 2; k--) {
				int temp = map[r + i][k];
				map[r + i][k] = cnt;
				cnt = temp;
			}

			// 왼쪽 열 이동
			for (int k = r + i - 1; k > r + i - 1 - i * 2; k--) {
				int temp = map[k][c - i];
				map[k][c - i] = cnt;
				cnt = temp;
			}
		}
	}

	static void calc(int[][] map) {
		next: for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < M; j++) {
				sum += map[i][j];
				if (sum >= result) continue next;
			}
			result = Math.min(sum, result);
		}
	}

}