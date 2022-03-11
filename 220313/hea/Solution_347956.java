package programmers; 

public class Solution_347956 {
	public static void main(String[] args) {
		System.out.println(solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log", "cog"}));
		System.out.println(solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log"}));
	}

	static int result;
	static int solution(String begin, String target, String[] words) {
		boolean[] visited = new boolean[words.length];
		result = Integer.MAX_VALUE;
		dfs(0, begin, target, words, visited);

		if(result == Integer.MAX_VALUE) result = 0;
		
		return result;
	}

	static void dfs(int n, String begin, String target, String[] words, boolean[] used) {
		if (begin.equals(target)) {
			result = Math.min(n, result);
			return;
		}
		
		for (int i = 0; i < words.length; i++) {
			if(used[i]) continue;  // 이미 사용한 단어면 pass
			
			String str = words[i];
			int cnt = 0;
			for (int k = 0; k < str.length(); k++) {
				if(begin.charAt(k) != str.charAt(k)) cnt++;
			}
			
			if(cnt == 1) { // 한 글자만 다르면
				used[i] = true;
				dfs(n + 1, str, target, words, used);
				used[i] = false;
			}
		}
	}
}