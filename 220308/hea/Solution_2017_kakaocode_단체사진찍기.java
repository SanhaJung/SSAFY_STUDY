package programmers;

import java.util.*;

public class Solution_2017_kakaocode_단체사진찍기 {
	static String[] friends = { "A", "C", "F", "J", "M", "N", "R", "T" };
	static String[] pick;
	static boolean[] picked;
	static int result = 0;

	public static void main(String[] args) {
		System.out.println(solution(2, new String[] { "N~F=0", "R~T>2" }));
		System.out.println(solution(2, new String[] { "M~C<2", "C~M>1" }));
	}

	static int solution(int n, String[] data) {
		result = 0;
		picked = new boolean[8];
		pick = new String[8];
		permutation(0, data);
	
		return result;
	}

	static void permutation(int cnt, String[] data) {
		if (cnt == 8) {
			boolean flag = true;
			for (int i = 0; i < data.length; i++) {
				char f1 = data[i].charAt(0);			// 캐릭터1
				char f2 = data[i].charAt(2);			// 캐릭터2
				char oper = data[i].charAt(3);			// = < >
				int num = data[i].charAt(4) - '0';		// 거리
				
				// 캐릭터1의 위치와 캐릭터2의 위치 차이
				int diff = Math.abs(Arrays.asList(pick).indexOf(f1 + "") - Arrays.asList(pick).indexOf(f2 + "")) - 1;
				if (oper == '=') {
					if (diff != num) {
						flag = false;
						break;
					}
				} else if (oper == '<') {
					if (diff >= num) {
						flag = false;
						break;
					}
				} else if (oper == '>') {
					if (diff <= num) {
						flag = false;
						break;
					}
				}
			}
			// 주어진 조건을 모두 만족 시에만 세기
			if (flag) result++;

			return;
		}

		// 줄 세우기 순열 
		for (int i = 0; i < friends.length; i++) {
			if (picked[i]) continue;

			pick[cnt] = friends[i];
			picked[i] = true;

			permutation(cnt + 1, data);
			picked[i] = false;

		}
	}
}