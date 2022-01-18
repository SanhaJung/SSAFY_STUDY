package programmers;

import java.util.*;

public class Solution_스택큐_주식가격 {

	public static void main(String[] args) {
		solution(new int[] { 1, 1, 1 });
//		solution(new int[] { 1, 2, 3, 2, 3 });
//		System.out.println(solution(new int[] { 6, 10, 2 }));
//		System.out.println(solution(new int[] { 3, 30, 34, 5, 9 }));
//		System.out.println(solution(new int[] { 1, 112 }));
	}

	static int[] solution(int[] prices) {
		int N = prices.length;
		int[] answer = new int[N];

		ArrayDeque<Integer> dq = new ArrayDeque<>();
		dq.add(0);
		for (int i = 1; i < N; i++) {
			int cur = prices[i];
			int size = dq.size();
			for (int k = 0; k < size; k++) {
				int index = dq.poll();
				if (prices[index] > cur) {
					answer[index] = i - index;
				} else {
					dq.offer(index);
				}
			}
			dq.offer(i);
		}

		while (!dq.isEmpty()) {
			int index = dq.poll();
			answer[index] = N - 1 - index;
		}

		System.out.println(Arrays.toString(answer));
		return answer;
	}
}