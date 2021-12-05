package studyAlgoProgrammers;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution_스택큐_기능개발 {

	public static void main(String[] args) {
		int[] result = solution(new int[] { 95, 90, 99, 99, 80, 99 }, new int[] { 1, 1, 1, 1, 1, 1 });
		System.out.println(Arrays.toString(result));
		result = solution(new int[] { 93, 30, 55 }, new int[] { 1, 30, 5 });
		System.out.println(Arrays.toString(result));
	}

	static int[] solution(int[] progresses, int[] speeds) {
		int[] answer = {};

		ArrayList<Integer> list = new ArrayList<>();
		boolean flag = true;
		int start = 0; // 시작 인덱스

		while (flag) {
			int days = (100 - progresses[start]) / speeds[start];
			if ((100 - progresses[start]) % speeds[start] != 0)
				days += 1;
			int cnt = 0;
			boolean ff = true;
			for (int i = start; i < progresses.length; i++) {
				progresses[i] += days * speeds[i];
				if (progresses[i] >= 100 && ff) {
					start++;
					cnt++;
				} else if (progresses[i] < 100) {
					ff = false;
				}
			}
			if (start == progresses.length)
				flag = false;
			list.add(cnt);
		}
		answer = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}
		return answer;
	}
}
