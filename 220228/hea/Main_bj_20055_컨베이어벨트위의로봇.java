package baekjoon;

import java.io.*;
import java.util.*;

// 다시 하기 
public class Main_bj_20055_컨베이어벨트위의로봇 {
	static int N, K;
	static int[] belt;
	static List<Integer> robots;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 1번 칸 : 올리는 위치
		// N번 칸 : 내리는 위치
		// 내구도 0인 칸 >= K이면 종료
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //
		K = Integer.parseInt(st.nextToken()); //

		belt = new int[2 * N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}

		robots = new ArrayList<>();
		int result = 0;
		while (true) {
			// 1
			belt = rotate();

			// 2
			move();

			// 3
			insert();

			// 4
			result++;
			if (countZero()) break;

		}
		System.out.println(result);
	}
	


	// 1. 벨트가 각 칸 위에 있는 로봇과 한 칸 회전한다.
	private static int[] rotate() {
		int[] temp = new int[2 * N];
		temp[0] = belt[2 * N - 1];
		for (int i = 1; i < 2 * N; i++) {
			temp[i] = belt[i - 1];
		}
		
		int n = robots.size();
		int remove = -1;
		for (int i = 0; i < n; i++) {
			int robot = (robots.get(i) + 1) % (2 * N) ;
			robots.set(i, robot);
			if (robot == N - 1) remove = i;
		}
		if (remove != -1) robots.remove(remove);
		return temp;
	}

	// 2. 로봇 이동
	private static void move() {
		int n = robots.size();
		int remove = -1;
		for (int i = 0; i < n; i++) {
			
			int robot = (robots.get(i) + 1) % (2 * N);  // 로봇이 이동할 위치

			if (!robots.contains(robot) && belt[robot] > 0) {
				robots.set(i, robot); 					// 로봇 이동
				belt[robot]--; 							// 내구도 감소
				
				if (robot == N - 1) remove = i; 		// 내리는 위치에 온 로봇
			}
		}
		if (remove != -1) robots.remove(remove);
	}
	
	// 3. 로봇 올리기
	private static void insert() {
		if (belt[0] != 0) {
			robots.add(0);
			belt[0]--;
		}
	}
	
	// 4. 내구도 0인 개수 세기
		private static boolean countZero() {
			int cnt = 0;
			for (int i = 0; i < belt.length; i++) {
				if (belt[i] == 0) cnt++;
				if (cnt >= K) return true;
			}
			return false;
		}
}
