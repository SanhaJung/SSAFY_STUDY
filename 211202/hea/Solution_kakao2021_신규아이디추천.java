package studyAlgoProgrammers;

public class Solution_kakao2021_신규아이디추천 {

	public static void main(String[] args) {
		System.out.println(solution("...!@BaT#*..y.abcdefghijklm") + "\n");
		System.out.println(solution("z-+.^.") + "\n");
		System.out.println(solution("=.=") + "\n");
		System.out.println(solution("123_.def") + "\n");
		System.out.println(solution("abcdefghijklmn.p") + "\n");
	}

	static String solution(String new_id) {
		// 1단계 : 대문자 -> 소문자 치환
		new_id = new_id.toLowerCase();
		System.out.println("1단계 : " + new_id);

		// 2단계 : 소문자, 숫자, -, _, . 제외한 모든 문자 제거
		String id2 = "";
		for (int i = 0; i < new_id.length(); i++) {
			char c = new_id.charAt(i);
			if ('a' <= c && c <= 'z') {
				id2 += String.valueOf(c);
			} else if ('0' <= c && c <= '9') {
				id2 += String.valueOf(c);
			} else if (c == '-' || c == '_' || c == '.') {
				id2 += String.valueOf(c);
			}
		}
		System.out.println("2단계 : " + id2);

		// 3단계 : ..여러 개 -> . 한 개로
		String id3 = "";
		int cnt = 0; // . 개수
		for (int i = 0; i < id2.length(); i++) {
			char c = id2.charAt(i);
			if (c == '.') {
				if (cnt == 0) {
					id3 += String.valueOf(c);
				}
				cnt++;
			} else {
				cnt = 0;
				id3 += String.valueOf(c);
			}
		}
		System.out.println("3단계 : " + id3);

		new_id = id3;

		// 4단계 : 처음이나 마지막에 위치한 . 제거
//		if (new_id.charAt(0) == '.') {
//			new_id = new_id.substring(1, new_id.length());
//		}
//		if (new_id.charAt(new_id.length() - 1) == '.') {
//			new_id = new_id.substring(0, new_id.length() - 1);
//		}

		if (new_id.startsWith(".")) {
			new_id = new_id.substring(1, new_id.length());
		} else if (new_id.endsWith(".")) {
			new_id = new_id.substring(0, new_id.length() - 1);
		}
		System.out.println("4단계 : " + new_id);

		// 5단계 : 빈 문자열이라면, new_id에 "a"대입
		if (new_id.length() == 0)
			new_id = "a";
		System.out.println("5단계 : " + new_id);

		// 6단계 : id>=16이면 첫 15개 문자 제외 제거, 마지막 문자가 .이면 그것도 제거
		if (new_id.length() >= 16) {
			new_id = new_id.substring(0, 15);
		}
		if (new_id.charAt(new_id.length() - 1) == '.') {
			new_id = new_id.substring(0, new_id.length() - 1);
		}
		System.out.println("6단계 : " + new_id);

		// 7단계 : new_id의 길이가 2자 이하이면,
		// new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙이기
		if (new_id.length() <= 2) {
			char c = new_id.charAt(new_id.length() - 1);
			while (new_id.length() < 3) {
				new_id += String.valueOf(c);
			}
		}
		System.out.println("7단계 : " + new_id);

		return new_id;
	}
}
