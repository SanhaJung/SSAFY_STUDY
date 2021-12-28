package programmers;

import java.util.*;

public class Solution_kakao2019_오픈채팅방 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new String[] { "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
				"Change uid4567 Ryan" })));
	}

	static String[] solution(String[] record) {
		List<String> list = new ArrayList<>();

		HashMap<String, String> user = new HashMap<>();
		for (int i = record.length - 1; i >= 0; i--) {
			String[] chat = record[i].split(" ");

			if (!chat[0].equals("Leave")) { // 제일 최근에 입장하거나, 마지막으로 이름 바꾼 경우에만 이름 수정
				if (!user.containsKey(chat[1])) {
					user.put(chat[1], chat[2]);
				} else
					continue;
			}
		}

		for (int i = 0; i < record.length; i++) {
			String[] comm = record[i].split(" ");

			String name = user.get(comm[1]);
			if (name != null && !comm[0].equals("Leave")) { // 나가는 경우 배열의 크기가 2
				comm[2] = name;
			}

			if (comm[0].equals("Enter")) {
				list.add(comm[2] + "님이 들어왔습니다.");
			} else if (comm[0].equals("Leave")) {
				list.add(user.get(comm[1]) + "님이 나갔습니다.");
			} else if (comm[0].equals("Change")) {
				continue;
			}
		}
		String[] answer = new String[list.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = list.get(i);
		}
		return answer;
	}
}