package baekjoon;

import java.io.*;
import java.util.*;

public class Main_bj_20056_마법사상어와파이어볼 {
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 }; // ↑, ↗, →, ↘, ↓, ↙, ←, ↖
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 }; //
	static List<FireBall>[][] map;
	static int N, M, K;
	static List<FireBall> list;

	static class FireBall {
		int r, c, m, s, d; // r, c, 질량, 속력, 방향

		public FireBall(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}

//		@Override
//		public String toString() {
//			return "FireBall [r=" + r + ", c=" + c + ", m=" + m + ", s=" + s + ", d=" + d + "]";
//		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 맵의 크기
		M = Integer.parseInt(st.nextToken()); // M개의 파이어볼
		K = Integer.parseInt(st.nextToken()); // K번의 이동 명령

		map = new ArrayList[N][N];
		list = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = new ArrayList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			list.add(new FireBall(r, c, m, s, d));
		}
		for (int k = 0; k < K; k++) {
			move(); // 파이어볼 이동

			for (FireBall fb : list) {
				map[fb.r][fb.c].add(fb);
//				System.out.println(fb);
			}

			list.clear();
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c].size() >= 2) {
						int weight_sum = 0;
						int speed_sum = 0;
						int odd_cnt = 0;
						int even_cnt = 0;
						
						for (FireBall fb : map[r][c]) {
							weight_sum += fb.m; // 질량 합
							speed_sum += fb.s;
							if (fb.d % 2 == 0) even_cnt++;
							else odd_cnt++;
						}
						
						if (weight_sum / 5 == 0) {
							map[r][c].clear();
							continue;
						}
						
						if (even_cnt == 0 || odd_cnt == 0) {
							for (int i = 0; i < 8; i += 2) {
								list.add(new FireBall(r, c, weight_sum / 5, speed_sum / map[r][c].size(), i));
							}
						} else {
							for (int i = 1; i < 8; i += 2) {
								list.add(new FireBall(r, c, weight_sum / 5, speed_sum / map[r][c].size(), i));
							}
						}

					} else if (map[r][c].size() == 1) {
						list.add(map[r][c].get(0));
					}
					map[r][c].clear();
				}
			}
		}
		
		int result = 0;
		for (FireBall fb : list) {
			result += fb.m;
		}
		System.out.println(result);
		br.close();
	}

	private static void move() {
		for (FireBall fireball : list) {
			int nr = (fireball.r + fireball.s * dr[fireball.d]) % N;
			int nc = (fireball.c + fireball.s * dc[fireball.d]) % N;

			if (nr < 0) nr += N;
			if (nc < 0) nc += N;

			fireball.r = nr;
			fireball.c = nc;
		}
	}

}