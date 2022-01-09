package baekjoon;

import java.io.*;
import java.util.*;

public class Main_bj_23288_주사위굴리기2 {
	static int dr[] = { -1, 0, 1, 0 }, dc[] = { 0, 1, 0, -1 };
	static int[][] score;
	static int[][] count;
	static int N, M, max;

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

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int K = Integer.parseInt(st.nextToken()); // 명령의 개수

		int x = 0;
		int y = 0;

		count = new int[N][M];
		score = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				score[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		Dice dice = new Dice(1, 4, 3, 2, 5, 6);
		int result = 0;
		int dir = 1;
		for (int k = 0; k < K; k++) {
			if (x + dr[dir] < 0 || x + dr[dir] >= N || y + dc[dir] < 0 || y + dc[dir] >= M) {
				// 이동 방향 반대로 바꾸기
				if (dir == 0 || dir == 2) dir = 2 - dir;
				else if (dir == 1 || dir == 3) dir = 4 - dir;
			}

			x = x + dr[dir];
			y = y + dc[dir];

			if (dir == 0) { // 북쪽
				dice = new Dice(dice.down, dice.left, dice.right, dice.top, dice.bottom, dice.up);
			} else if (dir == 1) { // 동쪽
				dice = new Dice(dice.left, dice.bottom, dice.top, dice.up, dice.down, dice.right);
			} else if (dir == 2) { // 남쪽
				dice = new Dice(dice.up, dice.left, dice.right, dice.bottom, dice.top, dice.down);
			} else if (dir == 3) { // 서쪽
				dice = new Dice(dice.right, dice.top, dice.bottom, dice.up, dice.down, dice.left);
			}

			if (count[x][y] == 0) {
				bfs(x, y);
			}
			result += count[x][y] * score[x][y];
			
			if (dice.bottom > score[x][y]) {
				// 시계 방향으로 90도
				dir = (dir + 1) % 4;
			} else if (dice.bottom < score[x][y]) {
				// 반시계 방향으로 90도
				dir = (dir + 3) % 4;
			}
		}
		System.out.println(result);
		br.close();
	}

	static void bfs(int r, int c) {
		boolean[][] visited = new boolean[N][M];
		visited[r][c] = true;
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] { r, c });

		int num = score[r][c];
		int cnt = 1;
		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || score[nr][nc] != num)
					continue;
				visited[nr][nc] = true;
				dq.add(new int[] { nr, nc });
				cnt++;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j])
					count[i][j] = cnt;
			}
		}
	}
}