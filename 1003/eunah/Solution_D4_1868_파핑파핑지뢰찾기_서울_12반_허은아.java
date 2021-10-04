package a1001.hw;

import java.io.*;
import java.util.*;

public class Solution_D4_1868_파핑파핑지뢰찾기_서울_12반_허은아 {
	static int N;
	static char[][] map;
	static int[][] cntMap;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine()); // N*N
			int click = 0;
			map = new char[N][N];
			cntMap = new int[N][N];
			for (int r = 0; r < N; r++) {
				map[r] = br.readLine().toCharArray();
			}

			count();

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (cntMap[r][c] == 0 && map[r][c] != '*') {
						click(r, c);
						click++;
					}
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (cntMap[r][c] > 0 && map[r][c] != '*') {
						click++;
					}
				}
			}
			sb.append("#").append(test_case).append(" ").append(click).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

	private static void count() {

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == '.') {
					int cnt = 0;
					for (int d = 0; d < 8; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (0 <= nr && nr < N && 0 <= nc && nc < N && map[nr][nc] == '*')
							cnt++;
					}
					cntMap[r][c] = cnt;
				}
			}
		}

	}

	private static void click(int r, int c) {
		int n = cntMap[r][c];
		cntMap[r][c] = -1;

		if (n == 0) {
			for (int i = 0; i < 8; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || cntMap[nr][nc] == -1 || map[nr][nc] == '*')
					continue;
				click(nr, nc);
			}
		}
	}
}
