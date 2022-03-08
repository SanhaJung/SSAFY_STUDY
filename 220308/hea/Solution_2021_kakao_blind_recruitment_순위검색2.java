package programmers;

import java.util.*;

public class Solution_2021_kakao_blind_recruitment_순위검색2 {
	public static void main(String[] args) {
		int[] result = solution(
				new String[] { "java backend junior pizza 150", "python frontend senior chicken 210",
						"python frontend senior chicken 150", "cpp backend senior pizza 260",
						"java backend junior chicken 80", "python backend senior chicken 50" },
				new String[] { "java and backend and junior and pizza 100",
						"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
						"- and backend and senior and - 150", "- and - and - and chicken 100",
						"- and - and - and - 150" });
		System.out.println(Arrays.toString(result));
//		solution(new String[] {"java backend junior pizza 150"}, new String[] {"- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"});
	}

	static int[] solution(String[] info, String[] query) {
		// 모든 쿼리 조합
		// key : 쿼리, value : 쿼리에 해당하는 사용자들의 점수 리스트
		HashMap<String, ArrayList<Integer>> queries = new HashMap<>();
		for (String language : new String[] { "cpp", "java", "python", "-" }) {
			for (String group : new String[] { "backend", "frontend", "-" }) {
				for (String career : new String[] { "junior", "senior", "-" }) {
					for (String soulfood : new String[] { "chicken", "pizza", "-" }) {
						queries.put(language + " and " + group + " and " + career + " and " + soulfood,
								new ArrayList<Integer>());
					}
				}
			}
		}
		
		// 사용자가 들어갈 수 있는 모든 조합
		// key : 쿼리, value : 쿼리에 해당하는 사용자들의 점수 리스트
		for (String infoStr : info) {
			String[] user = infoStr.split(" ");
			for (String language : new String[] { user[0], "-" }) {
				for (String group : new String[] { user[1], "-" }) {
					for (String career : new String[] { user[2], "-" }) {
						for (String soulfood : new String[] { user[3], "-" }) {
							queries.get(language + " and " + group + " and " + career + " and " + soulfood).add(Integer.parseInt(user[4]));
						}
					}
				}
			}
		}

		for (String str : queries.keySet()) {
			Collections.sort(queries.get(str));
		}

		int[] answer = new int[query.length];
		for (int i = 0; i < query.length; i++) {
			int score = Integer.parseInt(query[i].split(" ")[7]); // 쿼리의 점수 조건

			String str = query[i].substring(0, query[i].length() - query[i].split(" ")[7].length() - 1);
			ArrayList<Integer> scoreList = queries.get(str);  // 쿼리에 해당하는 사용자들이 점수 리스트

			// 이분탐색
			int left = 0;
			int right = scoreList.size() - 1;
		
			while (left <= right) {
				int middle = (left + right) / 2;
				if (scoreList.get(middle) < score) {
					left = middle + 1;
				} else {
					right = middle - 1;
				}
			}
			answer[i] = scoreList.size() - left;
		}
		return answer;
	}
}