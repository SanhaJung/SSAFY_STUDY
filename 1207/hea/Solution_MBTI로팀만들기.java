package swTestSample;

import java.io.*;
import java.util.*;

public class Solution_MBTI로팀만들기 {
	static int N;
	static Student[] students;
	static List<int[]> combList;
	static int[] pick;
	static int[] resultTeam;

	static class Student {
		String mbti, btype;
		int num, power;

		public Student(int num, String mbti, String btype, int power) {
			this.num = num;
			this.mbti = mbti;
			this.btype = btype;
			this.power = power;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			students = new Student[N];

			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine(), " ");
				String mbti = st.nextToken();
				String btype = st.nextToken();
				int power = Integer.parseInt(st.nextToken());
				students[n] = new Student(n + 1, mbti, btype, power);
			}
			combList = new ArrayList<>();
			pick = new int[2];
			HashSet<Integer> set = new HashSet<>();
			comb(0, 0, set); // 팀이 될 수 있는 조합

			resultTeam = new int[] { -1, -1, Integer.MIN_VALUE };
			if (set.size() == N) {
				pick = new int[N / 2];
				combAll(0, 0, "");
			}

			sb.append("#").append(test_case).append(" ");
			if (resultTeam[0] == -1)
				sb.append(-1);
			else {
				sb.append(resultTeam[0]).append(" ").append(resultTeam[1]).append(" ").append(resultTeam[2]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();
	}

	// 'O'형 포함 시 +10
	// 같은 혈액형끼리 구성되면 서로의 능력치 각각 -15
	// 'E'끼리 만나면 서로 +20
	// "MBTI의 구성요소 3개가 같으면 능력치 합이 +40
	static void comb(int cnt, int start, HashSet set) {
		if (cnt == 2) {
			Student s1 = students[pick[0]];
			Student s2 = students[pick[1]];
			set.add(s1.num);
			set.add(s2.num);

			int power = s1.power + s2.power;
			if (s1.btype.equals("O") || s2.btype.equals("O")) {
				power += 10;
			}
			if (s1.btype.equals(s2.btype)) {
				power -= 30;
			}
			if (s1.mbti.charAt(0) == 'E' && s2.mbti.charAt(0) == 'E') {
				power += 40;
			}
			int same = 0;
			for (int i = 0; i < 4; i++) {
				if (s1.mbti.charAt(i) == s2.mbti.charAt(i))
					same++;
			}
			if (same == 3)
				power += 40;

			combList.add(new int[] { s1.num, s2.num, power });
//			System.out.println("팀 : " + students[pick[0]]);
//			System.out.println("     " + students[pick[1]]);
//			System.out.println();

			return;
		}

		// 똑같은 MBTI끼리는 같은 팀 구성 안 함
		// 'I'끼리는 팀 구성 안 됨
		// 'T'는 반드시 'P'와 팀을 구성하여야 한다.
		// 'O'형은 'O'형이 아닌 팀원과만 팀을 이룰 수 있다.
		// 반드시 모든 학생이 팀을 구성
		for (int i = start; i < N; i++) {
			if (cnt == 1) {
				Student s1 = students[pick[0]];
				Student s2 = students[i];
				if (s1.mbti.equals(s2.mbti))
					continue;
				if (s1.mbti.charAt(0) == 'I' && s2.mbti.charAt(0) == 'I')
					continue;
				if (s1.mbti.charAt(2) == 'T' && s2.mbti.charAt(3) == 'J')
					continue;
				if (s2.mbti.charAt(2) == 'T' && s1.mbti.charAt(3) == 'J')
					continue;
				if (s1.btype.equals("O") && s2.btype.equals("O"))
					continue;

				pick[cnt] = i;
				comb(cnt + 1, i + 1, set);
			} else {
				pick[cnt] = i;
				comb(cnt + 1, i + 1, set);
			}
		}
	}

	static void combAll(int cnt, int start, String nums) {
		if (cnt == N / 2) {
//			System.out.println(nums);
			for (int n : pick) {
				int[] team = combList.get(n);
				if (team[2] > resultTeam[2])
					resultTeam = team;
			}
			return;
		}

		for (int i = start; i < combList.size(); i++) {
			int[] team = combList.get(i);
			if (cnt >= 1) {
				if (!nums.contains(String.valueOf(team[0])) && !nums.contains(String.valueOf(team[1]))) {
					pick[cnt] = i;
					combAll(cnt + 1, i + 1, nums + String.valueOf(team[0]) + String.valueOf(team[1]));
				}
			} else {
				pick[cnt] = i;
				combAll(cnt + 1, i + 1, nums + String.valueOf(team[0]) + String.valueOf(team[1]));
			}
		}
	}

}