package a0924.hw;

import java.io.*;
import java.util.*;

public class Main_bj_7569_토마토_서울_12반_허은아_3차원 {
	static int M, N, H, days, unripe;
	static int[][][] box;
	static int[] dh = { 1, -1, 0, 0, 0, 0 }; //
	static int[] dr = { 0, 0, -1, 0, 1, 0 }; // 위 아래 상 우 하 좌
	static int[] dc = { 0, 0, 0, 1, 0, -1 };
	static ArrayDeque<int[]> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken()); // 상자 개수

		q = new ArrayDeque<>();
		box = new int[H][N][M];
		for (int h = 0; h < H; h++) {
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < M; c++) {
					box[h][r][c] = Integer.parseInt(st.nextToken());
					if (box[h][r][c] == 1)
						q.offer(new int[] { h, r, c });
					if (box[h][r][c] == 0)
						unripe++;
				}
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
		boolean[][][] v = new boolean[H][N][M];

		while (true) {
			List<int[]> temp = new ArrayList<>();

			while (!q.isEmpty()) {
				int[] cur = q.poll();

				for (int i = 0; i < 6; i++) {
					int nh = cur[0] + dh[i];
					int nr = cur[1] + dr[i];
					int nc = cur[2] + dc[i];
					if (rangeIn(nh, nr, nc) && !v[nh][nr][nc] && box[nh][nr][nc] == 0) {
						box[nh][nr][nc] = 1;
						v[nh][nr][nc] = true;
						temp.add(new int[] { nh, nr, nc });
						unripe--;
					}
				}
			}
			days++;
			if (unripe == 0)
				break; // 안 익은 토마토가 없으면
			if (temp.size() == 0)
				break;
			for (int i = 0; i < temp.size(); i++) {
				q.offer(temp.get(i));
			}
		}
		if (unripe != 0)
			days = -1;
	}

	static boolean rangeIn(int nh, int nr, int nc) {
		if (0 <= nh && nh < H && 0 <= nr && nr < N && 0 <= nc && nc < M)
			return true;
		return false;
	}
}