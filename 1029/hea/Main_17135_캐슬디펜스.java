package studyAlgo;

import java.io.*;
import java.util.*;

public class Main_17135_캐슬디펜스 {
	static int[][] map;
	static int N, M, D, result;
	static int[] pick;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		pick = new int[3];
		result = Integer.MIN_VALUE;
		comb(0, 0);

		System.out.println(result);
		br.close();
	}

	static void comb(int cnt, int start) {
		if (cnt == 3) {
			int[][] copy_map = new int[N][M];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					copy_map[r][c] = map[r][c];
				}
			}
			int[][] archers = { { N, pick[0] }, { N, pick[1] }, { N, pick[2] } };
			int count = 0;
			while (true) {
				// 궁수 세 명 쏘기
				int[][] shoot = { { -1, -1 }, { -1, -1 }, { -1, -1 } };
				for (int n = 0; n < 3; n++) {
					int[] archer = archers[n];

					int nr = -1;
					int nc = -1;
					int mind = Integer.MAX_VALUE;
					for (int r = archer[0] - 1; r >= 0; r--) {
						for (int c = 0; c < M; c++) {
							if (copy_map[r][c] == 1) { // 적이면
								int distance = Math.abs(r - archer[0]) + Math.abs(c - archer[1]);
								if (distance > D)
									continue;
								if (distance < mind) {
									nr = r;
									nc = c;
									mind = distance;
								} else if (distance == mind && c < nc) {
									nr = r;
									nc = c;
								}
							}
						}
					}
					archer[0]--;
					if (nr != -1 && nc != -1) {
						shoot[n][0] = nr;
						shoot[n][1] = nc;
					}
				}
				for (int i = 0; i < 3; i++) {
					int[] sh = shoot[i];
					if (sh[0] != -1 && copy_map[sh[0]][sh[1]] == 1) {
						count++;
						copy_map[sh[0]][sh[1]] = 0;
					}
				}
				if (archers[0][0] == 0)
					break;
			}
			
			result = Math.max(result, count);
			return;
		}

		for (int i = start; i < M; i++) {
			pick[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

}