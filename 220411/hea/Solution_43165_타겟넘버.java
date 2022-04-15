package programmers;

public class Solution_43165_타겟넘버 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 1, 1, 1, 1, 1 }, 3));
		System.out.println(solution(new int[] { 4, 1, 2, 1 }, 4));
	}

	static int[] multiply = { 1, -1 };
	static int N, answer;

	static int solution(int[] numbers, int target) {
		answer = 0;
		N = numbers.length;
		dfs(0, target, numbers, 0);
		return answer;
	}

	static void dfs(int cnt, int target, int[] numbers, int sum) {
		if (cnt == N) {
			if (sum == target) answer++;
			return;
		}

		for (int i = 0; i < 2; i++) {
			dfs(cnt + 1, target, numbers, sum + numbers[cnt] * multiply[i]);
		}
	}
}