package a0924.hw;

import java.io.*;
import java.util.*;

public class Main_bj_7566_토마토_서울_12반_허은아_2차원 {
	static int M, N, H, days, unripe;
	static int[][] box;
	static int[] dr = { -1, 0, 1, 0 }; // 위 아래 상 우 하 좌
	static int[] dc = { 0, 1, 0, -1 };
	static ArrayDeque<int[]> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		q = new ArrayDeque<>();
		box = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < M; c++) {
				box[r][c] = Integer.parseInt(st.nextToken());
				if (box[r][c] == 1) q.offer(new int[] { r, c });
				if (box[r][c] == 0) unripe++;
			}
		}
		if (unripe == 0) {
			System.out.println(0);
		} else {
			bfs();
			System.out.println(days);
		}
		br.close();
	}

	static void bfs() {
		boolean[][] v = new boolean[N][M];

		while (true) {
			List<int[]> temp = new ArrayList<>();

			while (!q.isEmpty()) {
				int[] cur = q.poll();
				for (int i = 0; i < 4; i++) {
					int nr = cur[0] + dr[i];
					int nc = cur[1] + dc[i];
					if (0 <= nr && nr < N && 0 <= nc && nc < M && !v[nr][nc] && box[nr][nc] == 0) {
						box[nr][nc] = 1;
						v[nr][nc] = true;
						temp.add(new int[] { nr, nc });
						unripe--;
					}
				}
			}
			days++;
			if (unripe == 0) break; // 안 익은 토마토가 없으면
			if (temp.size() == 0) break;
			for (int i = 0; i < temp.size(); i++) {
				q.offer(temp.get(i));
			}
		}
		if (unripe != 0) days = -1;
	}
}