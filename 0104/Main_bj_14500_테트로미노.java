package baekjoon;

import java.io.*;
import java.util.*;

public class Main_bj_14500_테트로미노 {
	static int dr[] = { 0, 0, -1, 1 };
	static int dc[] = { 1, -1, 0, 0 };
	static int N, M, map[][], result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		boolean[][] v = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				v[r][c] = true;
				dfs(r, c, 1, map[r][c], v);
				v[r][c] = false;
				mountain(r, c);
			}
		}
		System.out.println(result);
		br.close();
	}

	static void dfs(int r, int c, int cnt, int sum, boolean[][] v) {
		if (cnt >= 4) {
			result = Math.max(result, sum);
			return;
		}

		for (int k = 0; k < 4; k++) {
			int nr = r + dr[k];
			int nc = c + dc[k];
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || v[nr][nc])
				continue;

			v[nr][nc] = true;
			dfs(nr, nc, cnt + 1, sum + map[nr][nc], v);
			v[nr][nc] = false;
		}
	}

	static void mountain(int r, int c) {
		if (r + 2 < N && c + 1 < M) // ㅏ
			result = Math.max(result, map[r][c] + map[r + 1][c] + map[r + 2][c] + map[r + 1][c + 1]);

		if (r > 0 && c + 2 < M) // ㅗ
			result = Math.max(result, map[r][c] + map[r][c + 1] + map[r][c + 2] + map[r - 1][c + 1]);

		if (r + 1 < N && c + 2 < M) // ㅜ
			result = Math.max(result, map[r][c] + map[r][c + 1] + map[r][c + 2] + map[r + 1][c + 1]);

		if (r + 2 < N && c > 0) // ㅓ
			result = Math.max(result, map[r][c] + map[r + 1][c] + map[r + 2][c] + map[r + 1][c - 1]);
	}
}
