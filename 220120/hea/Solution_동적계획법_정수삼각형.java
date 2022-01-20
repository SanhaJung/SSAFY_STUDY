package programmers;

public class Solution_동적계획법_정수삼각형 {

	public static void main(String[] args) {
//		int[] result = solution(  );
//		System.out.println(Arrays.toString(result)); 
		int[][] test = new int[][] { { 7 }, { 3, 8 }, { 8, 1, 0 }, { 2, 7, 4, 4 }, { 4, 5, 2, 6, 5 } };
		System.out.println(solution(test));
	}

	static int solution(int[][] triangle) {
		int answer = 0;

		int N = triangle.length;

		int[][] memo = new int[N][];
		for (int i = 0; i < N; i++) {
			memo[i] = new int[triangle[i].length];
		}

		memo[0][0] = triangle[0][0];
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < triangle[i + 1].length - 1; j++) {
				memo[i + 1][j] = Math.max(memo[i + 1][j], memo[i][j] + triangle[i + 1][j]);
				memo[i + 1][j + 1] = Math.max(memo[i + 1][j + 1], memo[i][j] + triangle[i + 1][j + 1]);
			}
		}

		for (int i = 0; i < memo[N - 1].length; i++) {
			answer = Math.max(answer, memo[N - 1][i]);
		}

		return answer;
	}
}