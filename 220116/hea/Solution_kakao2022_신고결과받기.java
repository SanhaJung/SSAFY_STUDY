package programmers;

import java.util.*;

public class Solution_kakao2022_신고결과받기 {

	public static void main(String[] args) {
		int[] result = solution(new String[] { "muzi", "frodo", "apeach", "neo" },
				new String[] { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "muzi neo", "apeach muzi" }, 2);
		System.out.println(Arrays.toString(solution(new String[] { "con", "ryan" },
				new String[] { "ryan con", "ryan con", "ryan con", "ryan con" }, 3)));

		System.out.println(Arrays.toString(result));
	}

	static int[] solution(String[] id_list, String[] report, int k) {
		int[] answer = {};

		int N = id_list.length;
		HashMap<String, ArrayList<String>> map = new HashMap<>(); // 해당 아이디가 신고당한 횟수
		for (int i = 0; i < N; i++) {
			String id = id_list[i];
			map.put(id, new ArrayList<>());
		}
		

		for (String repo : report) {
			String from = repo.split(" ")[0]; // 신고한 아이디 
			String to = repo.split(" ")[1]; // 신고당한 아이디
			
			ArrayList<String> list = map.get(to);
			if(list.contains(from))continue;
			list.add(from);
			map.replace(to, list);
		}
		
		HashMap<String, Integer> resMap = new HashMap<>();
		for (int i = 0; i < N; i++) {
			String id = id_list[i];
			resMap.put(id, 0);
		}
		for (String id : id_list) {
			
			// id : 신고 당한 아이디
			ArrayList<String> list = map.get(id);
			// 신고한 아이디들
			if(list.size() < k) continue;
			
			for (String str : list) {
				resMap.replace(str, resMap.get(str)+1);
			}
		}
		
		answer = new int[N];
		int i = 0;
		for (String id : id_list) {
			answer[i++] = resMap.get(id);
		}

		return answer;
	}
}
