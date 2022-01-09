package programmers;

public class Solution_kakao2019_징검다리건너기 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1 }, 3));
	}

	static int solution(int[] stones, int k) {
		int answer = 0;

		int min = 1;
		int max = 200000000;

		while (min <= max) {
			int mid = (min + max) / 2;

			if (across(stones, k, mid)) {// 건널 수 있으면
				min = mid + 1;
				answer = Math.max(answer, mid);
			} else {
				max = mid - 1;
			}
		}

		return answer;
	}

	static boolean across(int[] stones, int k, int friends) {
		int skip = 0;

		for (int s = 0; s < stones.length; s++) {
			if (stones[s] - friends < 0)
				skip++;
			else
				skip = 0;
			if (skip == k)
				return false;
		}

		return true;
	}
}