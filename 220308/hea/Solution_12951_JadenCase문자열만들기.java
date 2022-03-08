package programmers;

public class Solution_12951_JadenCase문자열만들기 {
	public static void main(String[] args) {
		System.out.println(solution("3people unFollowed me"));
		System.out.println(solution("for the last week"));
	}

	static String solution(String s) {
		StringBuilder sb = new StringBuilder();

		boolean flag = true;	// 단어의 첫 글자인지 확인하는 변수
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == ' ') {
				flag = true;
				sb.append(s.charAt(i));
				continue;
			}
			
			if(flag) {  											   // 첫 글자면
				sb.append(String.valueOf(s.charAt(i)).toUpperCase());  // 대문자로
				flag = false;
			}else {											 // 첫 글자가 아니면
				sb.append((s.charAt(i) + "").toLowerCase());   // 소문자로
			}
		} 

		return sb.toString();
	}
}