package programmers;

import java.util.*;

// 슬라이딩 윈도우..?

public class Solution_2020_kakao_internship_67258_보석쇼핑_sol {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new String[] { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" })));
		System.out.println(Arrays.toString(solution(new String[] { "AA", "AB", "AC", "AA", "AC" })));
		System.out.println(Arrays.toString(solution(new String[] { "XYZ", "XYZ", "XYZ" })));
		System.out.println(Arrays.toString(solution(new String[] { "ZZZ", "YYY", "NNNN", "YYY", "BBB" })));
		System.out.println(Arrays.toString(solution(new String[] { "DIA", "EM", "EM", "RUB", "DIA" })));
		System.out.println(Arrays.toString(solution(new String[] { "A", "A", "A", "B", "B" })));
		System.out.println(Arrays.toString(solution(new String[] { "A" })));
		System.out.println(Arrays.toString(solution(new String[] { "A", "B", "B", "B", "B", "B", "B", "C", "B", "A" })));
	}

	static int[] solution(String[] gems) {
		int[] answer = new int[2];

		HashSet<String> gemSet = new HashSet<>(Arrays.asList(gems)); // 모든 보석 종류

		// 구간 최소 길이
		int len = Integer.MAX_VALUE;

		// 구간
		int left = 0;
		int right = 0;
						
						// 최소 구간 
		int start = 0; 	// 답 시작점
		int end = 0;	// 답 끝점

		// (key, value)
		HashMap<String, Integer> map = new HashMap<>();
		int total = 0;
		while (true) {
			total++;
			if (map.size() == gemSet.size()) {						   // 모든 보석을 포함하면
				map.put(gems[left], map.get(gems[left]) - 1);	       // 구간 줄이기

				if (map.get(gems[left]) == 0) map.remove(gems[left]);  // 해당 보석이 0개이면 map에서 지우기
				left++;
			} else if (right == gems.length) break;					   // 보석 배열 끝까지 오면 종료
			else {
				// 보석이 부족하면
				map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);  // 보석 추가
				right++;													 // 구간 증가시키기
			}

			if (map.size() == gemSet.size()) {	// 모든 보석을 포함하고
				if (right - left < len) {		// 구간의 길이가 짧으면
					len = right - left;			// 구간 길이,
					start = left + 1;			// 구간 갱신
					end = right;
				}
			}
		}
		
		System.out.println(total);
		answer[0] = start;
		answer[1] = end;

		return answer;
	}
}