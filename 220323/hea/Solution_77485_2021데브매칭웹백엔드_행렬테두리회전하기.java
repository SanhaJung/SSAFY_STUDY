package programmers;

import java.util.Arrays;

public class Solution_77485_2021데브매칭웹백엔드_행렬테두리회전하기 {
	public static void main(String[] args) {
		solution(6, 6, new int[][] { { 2, 2, 5, 4 }, { 3, 3, 6, 6 }, { 5, 1, 6, 3 } });
		solution(3, 3, new int[][] { { 1, 1, 2, 2 }, { 1, 2, 2, 3 }, { 2, 1, 3, 2 }, { 2, 2, 3, 3 } });
		solution(100, 97, new int[][] { { 1, 1, 100, 97 } });
	}

	static int[] solution(int rows, int columns, int[][] queries) {
		int[] answer = new int[queries.length];

		// 초기 행렬
		int[][] matrix = new int[rows][columns];
		int k = 1;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				matrix[r][c] = k++;
			}
		}

		for (int n = 0; n < queries.length; n++) {
			int sr = queries[n][0] - 1; // 시작 행
			int sc = queries[n][1] - 1; // 시작 열
			int er = queries[n][2] - 1; // 끝 행
			int ec = queries[n][3] - 1; // 끝 열

			int width = ec - sc;
			int height = er - sr;

			int minValue = matrix[sr][sc];

			int btmp = matrix[sr][sc];	// 이동 전
			int tmp = matrix[sr][sc];	// 이동할 곳

			// → 
			for (int i = 1; i <= width; i++) {
				tmp = matrix[sr][sc + i];
				minValue = Math.min(minValue, tmp);
				matrix[sr][sc + i] = btmp;
				btmp = tmp;
			}
			
			// ↓
			for (int i = 1; i <= height; i++) {
				tmp = matrix[sr + i][ec];
				minValue = Math.min(minValue, tmp);
				matrix[sr + i][ec] = btmp;
				btmp = tmp;
			}
			
			//  ←
			for (int i = 1; i <= width; i++) {
				tmp = matrix[er][ec - i];
				minValue = Math.min(minValue, tmp);
				matrix[er][ec - i] = btmp;
				btmp = tmp;
			}
			
			// ↑
			for (int i = 1; i <= height; i++) {
				tmp = matrix[er - i][sc];
				minValue = Math.min(minValue, tmp);
				matrix[er - i][sc] = btmp;
				btmp = tmp;
			}
			answer[n] = minValue;
		}
		System.out.println(Arrays.toString(answer));

		return answer;
	}
}