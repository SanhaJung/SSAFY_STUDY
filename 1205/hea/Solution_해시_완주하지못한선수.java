package studyAlgoProgrammers;

import java.util.HashMap;

public class Solution_해시_완주하지못한선수 {

	public static void main(String[] args) {
		System.out.println(solution(new String[] { "leo", "kiki", "eden" }, new String[] { "eden", "kiki" }));
	}

	static String solution(String[] participant, String[] completion) {
		String answer = "";

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < participant.length; i++) {
			String name = participant[i];
			if (map.containsKey(name)) {
				map.replace(name, map.get(name) + 1);
			} else {
				map.put(name, 1);
			}
		}

		for (String name : completion) {
			map.replace(name, map.get(name) - 1);
			if (map.get(name) == 0) {
				map.remove(name);
				break;  
			}
		}

		for (String name : map.keySet()) {
			answer+= name;
		}

		return answer;
	}
}
