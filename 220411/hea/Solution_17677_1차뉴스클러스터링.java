package programmers;

import java.util.*;

public class Solution_17677_1차뉴스클러스터링 {

	public static void main(String[] args) {
		System.out.println(solution("FRANCE", "french"));
		System.out.println(solution("handshake", "shake hands"));
	}

	static int solution(String str1, String str2) {
		
		List<String> str1List = clustering(str1.toLowerCase());
		List<String> str2List = clustering(str2.toLowerCase());
		
		int total = str1List.size() + str2List.size();

		// 교집합
		int same = 0;
		for (String str : str1List) {
			if (str2List.contains(str)) {
				same++;
				str2List.remove(str);
			}
		}
		
		// same == total == 0
		if(same == total) return 65536;

		// 자카드 유사도
		// 합집합 A + B - AB
		return same * 65536 / (total - same);
	}

	// 두 글자씩 끊어서 다중집합의 원소로 만들기 
	static ArrayList<String> clustering(String str) {
		ArrayList<String> list = new ArrayList<>();

		for (int i = 0; i < str.length() - 1; i++) {
			if (String.valueOf(str.charAt(i)).matches("[a-z]") && String.valueOf(str.charAt(i + 1)).matches("[a-z]"))
				list.add(str.substring(i, i + 2));
		}

		return list;
	}

}