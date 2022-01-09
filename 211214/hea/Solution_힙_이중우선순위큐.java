package programmers;

import java.util.*;

public class Solution_힙_이중우선순위큐 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new String[] { "I 16", "D 1" })));
	}

	static int[] solution(String[] operations) {
		ArrayList<Integer> list = new ArrayList<>();
		for (String str : operations) {
			String[] oper = str.split(" ");

			String op = oper[0];
			int N = Integer.parseInt(oper[1]);

			if (op.equals("I")) {
				list.add(N);
			} else {
				if (list.size() == 0) continue;		 // 리스트가 비어있으면 연산 무시
				
				Collections.sort(list);
				if (N == 1) {						 // 최대값 삭제
					list.remove(list.size() - 1);
				} else if (N == -1) {				 // 최소값 삭제
					list.remove(0);
				}
			}
		}

		if (list.size() == 0) {
			return new int[] { 0, 0 };
		} else {
			Collections.sort(list);
			return new int[] { list.get(list.size() - 1), list.get(0) };
		}
	}
}