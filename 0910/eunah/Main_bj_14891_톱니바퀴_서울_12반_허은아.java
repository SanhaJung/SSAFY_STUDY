package as0909;

import java.io.*;
import java.util.*;

public class Main_bj_14891_톱니바퀴_서울_12반_허은아 {
	// 맞닿은 극이 다르면 반대로 회전
	// 같으면 회전 안 함
	// 1:시계방향, -1:반시계방향
	static List<Boolean>[] gear;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gear = new ArrayList[4];
		for (int i = 0; i < 4; i++) {
			gear[i] = new ArrayList<Boolean>();
		}

		StringTokenizer st;
		for (int i = 0; i < 4; i++) {
			String s = br.readLine();
			List<Boolean> g = gear[i];
			for (int j = 0; j < s.length(); j++) {
				int n = s.charAt(j) - '0';
				if (n == 1)
					g.add(true); // S극
				else
					g.add(false); // N극
			}
		}

		int K = Integer.parseInt(br.readLine()); // 회전 횟수
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken()) - 1; // 회전시킬 톱니바퀴 번호
			int d = Integer.parseInt(st.nextToken()); // 1:시계방향, -1:반시계방향


			boolean[] isRotate = new boolean[4]; // 회전 여부 저장
			int[] rotateD = { d, d, d, d }; // 회전 방향 저장

			boolean flagleft = true;
			boolean flagright = true;

			boolean cur2 = gear[n].get(2);
			boolean cur6 = gear[n].get(6);
			for (int j = 1; j <= 3; j++) {

				if (0 <= n - j && flagleft) { // 톱니바퀴 범위 안에 들어오고, 이전 톱니바퀴가 회전한다면
					// 입력받은 톱니바퀴 기준으로 왼쪽
					if (gear[n - j].get(2) != cur6) { // 맞닿은 면이 다르다면
						isRotate[n - j] = true;
						isRotate[n - j + 1] = true;
						if ((n - j + n) % 2 != 0) {
							rotateD[n - j] = -d;
						}
						cur6 = gear[n - j].get(6);
					} else {
						// 이전의 톱니바퀴가 회전을 하지 않는다면 이후의 톱니바퀴는 회전을 할 일이 없으므로
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
			isRotate[n] = true; // 입력받은 톱니바퀴는 무조건 회전
			for (int k = 0; k < 4; k++) {
				if (isRotate[k]) {
					gear[k] = rotate(rotateD[k], gear[k]);
				}
			}

		}

		int result = 0;
		for (int i = 0; i < 4; i++) {
			if (gear[i].get(0))
				result += Math.pow(2, i);
		}
		System.out.println(result);
	}

	static List<Boolean> rotate(int d, List<Boolean> g) {// 1:시계방향, -1:반시계방향
		// 반시계면 맨 앞이 맨 뒤로
		// 시계면 맨 뒤가 맨 앞으로
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
