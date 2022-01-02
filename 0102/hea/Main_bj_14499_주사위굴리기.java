package baekjoon;

import java.io.*;
import java.util.*;

public class Main_bj_14499_주사위굴리기 {
	static int dr[] = { 0, 0, -1, 1 };
	static int dc[] = { 1, -1, 0, 0 };

	static class Dice {
		int top, left, right, up, down, bottom;

		public Dice(int top, int left, int right, int up, int down, int bottom) {
			super();
			this.top = top;
			this.left = left;
			this.right = right;
			this.up = up;
			this.down = down;
			this.bottom = bottom;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());

		int K = Integer.parseInt(st.nextToken()); // 명령의 개수

		int[][] map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		Dice dice = new Dice(0, 0, 0, 0, 0, 0); // 초기 주사위
		st = new StringTokenizer(br.readLine());
		for (int k = 0; k < K; k++) {
			int dir = Integer.parseInt(st.nextToken()) - 1;
			int nx = x + dr[dir];
			int ny = y + dc[dir];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

			x = nx;
			y = ny;

//			new Dice(top, left, right, up, down, bottom)
			if (dir == 0) { // 동쪽
				dice = new Dice(dice.left, dice.bottom, dice.top, dice.up, dice.down, dice.right);
			} else if (dir == 1) { // 서쪽
				dice = new Dice(dice.right, dice.top, dice.bottom, dice.up, dice.down, dice.left);
			} else if (dir == 2) { // 북쪽
				dice = new Dice(dice.down, dice.left, dice.right, dice.top, dice.bottom, dice.up);
			} else if (dir == 3) { // 남쪽
				dice = new Dice(dice.up, dice.left, dice.right, dice.bottom, dice.top, dice.down);
			}

			if (map[x][y] == 0) map[x][y] = dice.bottom;
			else {
				dice.bottom = map[x][y];
				map[x][y] = 0;
			}
			sb.append(dice.top).append("\n");
		}

		System.out.println(sb);
		br.close();
	}
}