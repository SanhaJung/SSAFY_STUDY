package baekjoon;

import java.io.*;
import java.util.*;

//6 35 ~
public class Main_bj_20058_마법사상어와파이어스톰 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int N, Q, size, ice_sum, dump_size;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 2^N 맵의 크기
		Q = Integer.parseInt(st.nextToken()); // Q번의 파이어스톰 명령

		size = (int) Math.pow(2, N);
		map = new int[size][size];
		for (int r = 0; r < size; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < size; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int q = 0; q < Q; q++) {
			int L = Integer.parseInt(st.nextToken());

			int[][] temp = copyMap();
			rotate(L, temp);

			// 얼음 양 줄이기
			temp = copyMap();
			for (int r = 0; r < size; r++) {
				for (int c = 0; c < size; c++) {
					if (temp[r][c] == 0) continue;
					if (decrease(r, c, temp))
						map[r][c]--;
				}
			}

		}

		boolean[][] visited = new boolean[size][size];
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				ice_sum += map[r][c];
				if (visited[r][c] || map[r][c] == 0)
					continue;
				bfs(r, c, visited);
			}
		}
		System.out.println(ice_sum);
		System.out.println(dump_size);
		br.close();
	}

	private static void bfs(int r, int c, boolean[][] visited) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] { r, c });

		int count = 0;
		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			for (int k = 0; k < 4; k++) {
				int nr = cur[0] + dr[k];
				int nc = cur[1] + dc[k];
				if (nr < 0 || nr >= size || nc < 0 || nc >= size || visited[nr][nc] || map[nr][nc] == 0)
					continue;
				dq.add(new int[] { nr, nc });
//				ice_sum += map[nr][nc];
				visited[nr][nc] = true;
				count++;
			}
		}
		dump_size = Math.max(count, dump_size);
	}

	private static boolean decrease(int r, int c, int[][] temp) {
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < 0 || nr >= size || nc < 0 || nc >= size || temp[nr][nc] == 0)
				continue;
			cnt++;
		}
		if (cnt >= 3)
			return false;
		return true;
	}

	static void rotate(int L, int[][] temp) {
		L = (int) Math.pow(2, L);
		for (int r = 0; r < size; r += L) {
			for (int c = 0; c < size; c += L) {

				// 시작점 : (r, c)
				
				// i번째 행,  j번째 열
					// -> j번째 행, (L - i - 1)번째 열
				
				// 시작점 적용하면
				// [r + i][c + j] -> [r + j][c + L - i - 1]
				for (int i = 0; i < L; i++) {
					for (int j = 0; j < L; j++) {
						map[r + j][c + L - i - 1] = temp[r + i][c + j];
					}
				}

			}
		}
	}

	static int[][] copyMap() {
		int[][] temp = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				temp[i][j] = map[i][j];
			}
		}
		return temp;
	}
}