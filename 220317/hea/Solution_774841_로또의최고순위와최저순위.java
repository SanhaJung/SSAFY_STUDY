package programmers;

import java.util.*;

public class Solution_774841_로또의최고순위와최저순위 {
	public static void main(String[] args) {

	}

	static int[] solution(int[] lottos, int[] win_nums) {
		int[] answer = {};

		int same = 0;
		int temp = 0;

		ArrayList<Integer> win_list = new ArrayList<>();
		for (int i = 0; i < win_nums.length; i++) {
			win_list.add(win_nums[i]);
		}

		for (int i = 0; i < lottos.length; i++) {
			if (lottos[i] == 0) temp++;
			else if (win_list.contains(lottos[i])) same++;
		}
		answer = new int[] { (same == 0 && temp == 0 ? 6 : 7 - (same + temp)), (same < 1 ? 6 : 7 - same) };

		return answer;
	}
}