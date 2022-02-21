package programmers;

import java.util.ArrayDeque;
import java.util.Arrays;

import all.Solution;

public class Solution_kakao2017_카카오프렌즈컬러링북 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) {
	System.out.println(Arrays.toString(solution(6,4, new int[][] {{1,1,1,0},{1,1,1,0},{0,0,0,1},{0,0,0,1},{0,0,0,1},{0,0,0,1}})));
	}

	static int[] solution(int m, int n, int[][] picture) {
		int numberOfArea = 0;
		int maxSizeOfOneArea = Integer.MIN_VALUE;

		boolean[][] checked = new boolean[m][n];
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		for (int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				if (checked[r][c] || picture[r][c] == 0) continue;
				dq.offer(new int[] { r, c  });
				checked[r][c] = true;
				int color = picture[r][c];
				int size = 1;
				while (!dq.isEmpty()) {
					int[] cur = dq.poll(); 
					for (int d = 0; d < 4; d++) {
						int nr = cur[0] + dr[d];
						int nc = cur[1] + dc[d];
						if (nr < 0 || nr >= m || nc < 0 || nc >= n || checked[nr][nc])
							continue;
						if (picture[nr][nc] == color) {
							checked[nr][nc] = true;
							dq.add(new int[] { nr, nc });
							maxSizeOfOneArea = Math.max(maxSizeOfOneArea, ++size);
						}
					}
				}
				numberOfArea++;
			}
		}
		int[] answer = { numberOfArea, maxSizeOfOneArea };
		return answer;
	}
}
