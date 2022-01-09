package a1001.hw;

import java.io.*;
import java.util.*;

public class Solution_SW_4013_특이한자석_서울_12반_허은아 {
	static List<Boolean>[] gear;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		for (int test_case = 1; test_case <= T; test_case++) {
			gear = new ArrayList[4];
			for (int i = 0; i < 4; i++) {
				gear[i] = new ArrayList<Boolean>();
			}

			int K = Integer.parseInt(br.readLine()); // 회전 횟수

			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				List<Boolean> g = gear[i];
				for (int j = 0; j < 8; j++) {
					int n = Integer.parseInt(st.nextToken());
					if (n == 1) g.add(true); // S극
					else g.add(false); // N극
				}
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int n = Integer.parseInt(st.nextToken()) - 1; // 회전시킬 톱니바퀴 번호
				int d = Integer.parseInt(st.nextToken()); // 1:시계방향, -1:반시계방향

				boolean flagleft = true;
				boolean flagright = true;

				boolean[] isRotate = new boolean[4]; // 회전 여부 저장
				int[] rotateD = { d, d, d, d }; // 회전 방향 저장

				boolean cur2 = gear[n].get(2);
				boolean cur6 = gear[n].get(6);
				for (int j = 1; j <= 3; j++) {

					if (0 <= n - j && flagleft) {
						// 왼쪽
						if (gear[n - j].get(2) != cur6) {
							isRotate[n - j] = true;
							isRotate[n - j + 1] = true;
							if ((n - j + n) % 2 != 0) {
								rotateD[n - j] = -d;
							}
							cur6 = gear[n - j].get(6);
						} else {
							flagleft = false;
						}
					}

					if (n + j <= 3 && flagright) {
						// 오른쪽
						if (gear[n + j].get(6) != cur2) {
							isRotate[n + j] = true;
							isRotate[n + j - 1] = true;
							if ((n + j + n) % 2 != 0) {
								rotateD[n + j] = -d;
							}
							cur2 = gear[n + j].get(2);
						} else {
							flagright = false;
						}
					}
				}
				isRotate[n] = true;
				for (int k = 0; k < 4; k++) {
					if (isRotate[k]) gear[k] = rotate(rotateD[k], gear[k]);
					
				}
			}

			int result = 0;
			for (int i = 0; i < 4; i++) {
				if (gear[i].get(0))
					result += Math.pow(2, i);
			}

			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
		br.close();
	}

	static List<Boolean> rotate(int d, List<Boolean> g) {// 1:시계방향, -1:반시계방향
		if (d == -1) {
			g.add(g.get(0));
			g.remove(0);
		} else if (d == 1) {
			g.add(0, g.get(g.size() - 1)); // 맨 앞에 넣기
			g.remove(g.size() - 1);
		}
		return g;
	}
}
