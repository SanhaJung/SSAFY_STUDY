package programmers;

import java.util.*;

public class Solution_42577_전화번호목록 {
	public static void main(String[] args) {
		solution(new String[] { "123", "1000", "1234", "12", "12345" });
	}

	static boolean solution(String[] phone_book) {
		boolean answer = true;

		// 사전 순으로 정렬 된다.
		Arrays.sort(phone_book);
		System.out.println(Arrays.toString(phone_book));
		for (int i = 0; i < phone_book.length - 1; i++) {
			if (phone_book[i + 1].startsWith(phone_book[i])) {
				answer = false;
				break;
			}
		}

		return answer;
	}
}