package programmers;

import java.util.*;

public class Solution_2019_kakao_winter_internship_튜플 {
	public static void main(String[] args) {
		solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");
		solution("{{1,2,3},{2,1},{1,2,4,3},{2}}");
		solution("{{20,111},{111}}");
		solution("{{123}}");
		solution("{{4,2,3},{3},{2,3,4,1},{2,3}}");
	}

	static int[] solution(String s) {
		int[] answer = {};

		String[] arr = s.substring(2, s.length() - 2).replace("{", "").split("},");
//		System.out.println(s.substring(2, s.length() - 2).replace("{", ""));
//		System.out.println(s.substring(2, s.length() - 2).replace("{", "").replace("},", " "));
//		System.out.println(Arrays.toString(arr));
//		System.out.println();
		
		List<int[]> elements = new ArrayList<int[]>();     // 집합의 원소들
		for (int i = 0; i < arr.length; i++) {
			String[] str = arr[i].split(",");			   // 2,1,3 -> 2 1 3
			int[] num = new int[str.length];			   // 원소의 자연수들 담을 배열
			
			int n = 0;
			for (String st : str) {
				num[n++] = Integer.parseInt(st);
			}
			elements.add(num);
		}
		Collections.sort(elements, (o1, o2) -> o1.length - o2.length);		// 길이 짧은 순으로 정렬

		List<Integer> tuple = new ArrayList<>();		  // 중복되는 원소가 없는 튜플
		for (int i = 0; i < elements.size(); i++) {
			int[] element = elements.get(i);
			for (int k = 0; k < element.length; k++) {
				if (tuple.contains(element[k])) continue;	
				tuple.add(element[k]);
			}
		}
		answer = new int[tuple.size()];
		for (int i = 0; i < tuple.size(); i++) {
			answer[i] = tuple.get(i);
		}
//		System.out.println(Arrays.toString(answer));
		return answer;
	}
}
