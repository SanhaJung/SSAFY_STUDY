package studyAlgo;

import java.io.*;
import java.util.*;

public class Main_bj_17136_색종이붙이기 {
	static int N = 10;
	static int[][] map;
	static int[] papers;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		papers = new int[6];
		Arrays.fill(papers, 5);
		result = Integer.MAX_VALUE;
		dfs(0, 0, 0);

		result = result == Integer.MAX_VALUE ? -1 : result;
		System.out.println(result);
		br.close();

	}

	private static void dfs(int x, int y, int cnt) {
		// 배열의 마지막 좌표까지 다 탐색 완료했으면 최솟값 갱신
		if (x >= 9 && y > 9) {
			result = Math.min(result, cnt);
			return;
		}

		// 사용한 색종이 수가 갱신된 최솟값보다 크면 더이상 진행하지 않음
		if (cnt >= result) return;
		

		// y 값이 배열 밖을 벗어났으면 x좌표 밑으로 이동
		if (y > 9) {
			dfs(x + 1, 0, cnt);
			return;
		}

		if (map[x][y] == 1) {
			for (int len = 5; len >= 1; len--) {
				// len 길이의 색종이로 덮을 수 있을 때
				if (papers[len] > 0 && isPossible(x, y, len)) {
					// 색종이 사용
					papers[len]--;
					// 색종이로 덮음
					stick(x, y, len, 0);

					// 다음칸부터 다시 탐색
					dfs(x, y + 1,  cnt + 1);

					// 색종이 수거
					papers[len]++;
					stick(x, y, len, 1);
				}
			}
		} else {
			// map[x][y] == 0 아무런 칸도 아니면 다음 칸 탐색
			dfs(x, y + 1,  cnt);
		}
	}

	static boolean isPossible(int r, int c, int k) {
		for (int nr = r; nr < r + k; nr++) {
			for (int nc = c; nc < c + k; nc++) {
				if (nr >= N || nc >= N)
					return false;
				if (map[nr][nc] == 0)
					return false;
			}
		}
		return true;
	}

	static void stick(int r, int c, int k, int n) {
		for (int nr = r; nr < r + k; nr++) {
			for (int nc = c; nc < c + k; nc++) {
				map[nr][nc] = n;
			}
		}
	}

}