package baekjoon;

import java.io.*;
import java.util.*;

public class Main_bj_23291_어항정리 {
	static int N, K;
	static int[] dr = { 0, -1, 0, 1 }; // 좌상우하
	static int[] dc = { -1, 0, 1, 0 };

	static int[] basic;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		basic = new int[N];
		st = new StringTokenizer(br.readLine());
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for (int n = 0; n < N; n++) {
			basic[n] = Integer.parseInt(st.nextToken());
			min = Math.min(min, basic[n]);
			max = Math.max(max, basic[n]);
		}
		
		// 높이, 너비 구하기
		int width = (int) Math.sqrt(N);
		int height = N / width;
		if (height - width > 1) height = width + 1;
		
		int count = 0;
		while (max - min > K) {
			count++;

			// 1. 물고기 수가 가장 적은 어항에 한 마리 넣기
			addFish();

			// 바깥부터 채우기!!
			// (height - 1, width - 1)부터 채우기
			int[][] fishbowl = new int[height][];
			for (int h = 0; h < height; h++) {
				fishbowl[h] = new int[width];
			}
			// width + (N - height * width)남은 개수
			fishbowl[height - 1] = new int[width + (N - height * width)];

			// 2. 어항 채우기(공중부양)
			fillFishBowl(fishbowl, height, width, 0);

			// 3. 물고기 수 조절
			adjustFish(fishbowl, height, width);

			// 4. 일렬로 놓기
			lineUpFish(fishbowl, height, width);

			// 5. N/2 공중부양 작업
			int[][] bowl2 = fillFishBowl2();

			// 6. 물고기 수 조절
			adjustFish(bowl2, 4, N / 4);

			// 7. 일렬로 놓기
			lineUpFish(bowl2, 4, N / 4);

			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			for (int n = 0; n < N; n++) {
				min = Math.min(min, basic[n]);
				max = Math.max(max, basic[n]);
			}
		}
		System.out.println(count);
		br.close();
	}

	private static int[][] fillFishBowl2() {
		int[][] bowl = new int[4][N / 4];

		// 행 2 1 0 3
		int[] row = { 2, 1, 0, 3 };
		// 좌 우 좌 우 0 2 0 2
		
		int d = 0;
		int cnt = 0;
		int c = N / 4 - 1;
		for (int n = 0; n < row.length; n++) {
			int r = row[n];

			if (d == 0) c = N / 4 - 1;
			else if (d == 2) c = 0;

			for (int k = 0; k < N / 4; k++) {
				bowl[r][c] = basic[cnt++];
				if (d == 0) c--;
				else if (d == 2) c++;
			}
			d = 2 - d;
		}

		return bowl;
	}

	private static void lineUpFish(int[][] fishbowl, int height, int width) {
		int n = 0;
		for (int c = 0; c < width; c++) {
			for (int r = height - 1; r >= 0; r--) {
				basic[n++] = fishbowl[r][c];
			}
		}

		for (int c = width; c < fishbowl[height - 1].length; c++) {
			basic[n++] = fishbowl[height - 1][c];
		}
	}

	private static void adjustFish(int[][] fishbowl, int height, int width) {
		int[][] temp = new int[height][];
		for (int r = 0; r < height; r++) {
			temp[r] = new int[fishbowl[r].length];
			for (int c = 0; c < temp[r].length; c++) {
				temp[r][c] = fishbowl[r][c];
			}
		}

		for (int r = 0; r < height; r++) {
			for (int c = 0; c < fishbowl[r].length; c++) {

				// 우, 하
				for (int d = 2; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					// 어항 벗어나면
					if (nr >= height || nc >= fishbowl[nr].length) continue;

					int a = temp[r][c];
					int b = temp[nr][nc];

					// 몫
					int quotient = Math.abs(a - b) / 5;
					if (quotient == 0) continue;
					if (a > b) {
						fishbowl[r][c] -= quotient;
						fishbowl[nr][nc] += quotient;
					} else {
						fishbowl[r][c] += quotient;
						fishbowl[nr][nc] -= quotient;
					}
				}
			}
		}

	}

	private static void addFish() {
		int min = Integer.MAX_VALUE;
		for (int x = 0; x < N; x++) {
			min = Math.min(min, basic[x]);
		}

		for (int x = 0; x < N; x++) {
			if (basic[x] == min) basic[x]++;
		}
	}

	private static void fillFishBowl(int[][] fishbowl, int height, int width, int d) {
		int r = height - 1;
		int c = width;
		int cnt = height * width - 1;
		// (height - 1, width - 1)부터 채우기
		while (cnt >= 0) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			// 어항을 벗어나거나 fishbowl[nr][nc] ==
			if (nr < 0 || nr >= height || nc < 0 || nc >= width || fishbowl[nr][nc] > 0) {
				d++;
				if (d >= 4) d -= 4;
			}

			r += dr[d];
			c += dc[d];

			fishbowl[r][c] = basic[cnt--];
		}
		cnt = width * height;
		for (int n = width; n < fishbowl[height - 1].length; n++) {
			fishbowl[height - 1][n] = basic[cnt++];
		}
	}

}
