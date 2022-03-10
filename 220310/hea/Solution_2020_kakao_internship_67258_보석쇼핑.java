package programmers;

import java.util.*;

// 슬라이딩 윈도우..?
public class Solution_2020_kakao_internship_67258_보석쇼핑 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new String[] { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" })));
		System.out.println(Arrays.toString(solution(new String[] { "AA", "AB", "AC", "AA", "AC" })));
		System.out.println(Arrays.toString(solution(new String[] { "XYZ", "XYZ", "XYZ" })));
		System.out.println(Arrays.toString(solution(new String[] { "ZZZ", "YYY", "NNNN", "YYY", "BBB" })));
		System.out.println(Arrays.toString(solution(new String[] { "DIA", "EM", "EM", "RUB", "DIA" })));
		System.out.println(Arrays.toString(solution(new String[] { "A","A","A","B","B" })));
		System.out.println(Arrays.toString(solution(new String[] { "A" })));
		System.out.println(Arrays.toString(solution(new String[] { "A","B","B","B","B","B","B","C","B","A" })));
	}

	static int[] solution(String[] gems) {
		int[] answer = {};

		HashSet<String> all = new HashSet<>(Arrays.asList(gems));
		int cnt = all.size();

		// 보석 개수랑, gems 길이랑 똑같으면 처음부터 끝까지
		if (cnt == gems.length) {
			answer = new int[] { 1, cnt };
			return answer;
		}

		HashSet<String> section = new HashSet<>(); // 구간에 들어있는 보석들
		int start = 0; // 구간 시작점
		int end = cnt - 1; // 구간 끝점

		// 최소 보석의 개수만큼 시작
		ArrayList<String> list = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(gems, start, cnt)));
		section = new HashSet<>(list);
		
		if (cnt == section.size()) {
			answer = new int[] { start + 1, end + 1 };
			return answer;
		}

		
		int len = Integer.MAX_VALUE;
		int total = 0;
		while (true) {
			if(end == gems.length - 1 && end - start + 1 < cnt) break;
			total++;
			//
			section = new HashSet<>(list); // 현재 보석의 종류들

			// 보석이 부족하면
			if (section.size() < cnt) {
				if(end + 1 == gems.length) break;
				list.add(gems[++end]);
				continue;
			}

			// 보석이
			else if (section.size() == cnt) { 
				// 현재 구간이 더 짧을 때만 갱신
				if (len > end - start) {
					len = end - start;
					answer = new int[] { start + 1, end + 1 };
				}
				
				// 
				if (list.size() == cnt) break;
				list.remove(gems[start++]); //
			}
		} 
		System.out.println(total);
		return answer;
	}
}