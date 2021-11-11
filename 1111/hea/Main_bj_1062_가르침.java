package studyAlgo;

import java.io.*;
import java.util.*;

public class Main_bj_1062_가르침 {
	static int N, K;
	static boolean[] v;
	static int result = 0;
	static String[] words;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		result = Integer.MIN_VALUE;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if (K < 5) {
			System.out.println(0);
			System.exit(0);
		} else if (K == 26) {
			System.out.println(N);
			System.exit(0);
		}

		v = new boolean[26];
		String str = "acint";
		for (int i = 0; i < str.length(); i++) {
			v[str.charAt(i) - 'a'] = true;
		}
		// anta *...* tica -> a c i n t

		words = new String[N];
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			words[i] = str.substring(4, str.length() - 4);
		}
		comb(0, 0);
		System.out.println(result);
		br.close();
	}

	// 알파벳 뽑기
	static void comb(int cnt, int start) {
		if (cnt == K - 5) {
			String ss = "";
			for (int i = 0; i < v.length; i++) {
				if (v[i]) ss += (char) (i + 'a');
			}
			System.out.println(ss);
			
			int count = 0;
			next: for (int i = 0; i < N; i++) {
				String word = words[i];
				for (int j = 0; j < word.length(); j++) {
					char c = word.charAt(j);
					if (!ss.contains(c + "")) continue next;
				}
				count++;
			}
			result = Math.max(result, count);
			return;
		}

		for (int i = start; i < 26; i++) {
			if (!v[i]) {
				v[i] = true;
				comb(cnt + 1, i + 1);
				v[i] = false;
			}
		}
	}

}