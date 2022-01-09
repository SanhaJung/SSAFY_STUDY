package baekjoon;

import java.io.*;
import java.util.*;

public class Main_bj_21610_마법사상어와비바라기 {
	static int[] dr = { 0, -1, -1, -1, 0, 1, 1, 1 }; // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
	static int[] dc = { -1, -1, 0, 1, 1, 1, 0, -1 }; //
	static int[][] map;
	static int N, M;
	static boolean[][] flag;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 맵의 크기
		M = Integer.parseInt(st.nextToken()); // M번의 이동 명령

		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 초기 구름 생성
		ArrayList<int[]> clouds = new ArrayList<>();
		clouds.add(new int[] { N - 1, 0 });
		clouds.add(new int[] { N - 1, 1 });
		clouds.add(new int[] { N - 2, 0 });
		clouds.add(new int[] { N - 2, 1 });

		// 방향 ←, ↖, ↑, ↗, →, ↘, ↓, ↙
		for (int command = 0; command < M; command++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken()) - 1; // 방향
			int s = Integer.parseInt(st.nextToken()); // 거리

			flag = new boolean[N][N];

			// 1. 모든 구름이 d방향으로 s칸 이동
			move(clouds, d, s);

			// 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가
			rain(clouds);
			// 3. 구름이 모두 사라진다.
			clouds.clear();

			// 4. 2에서 물이 증가한 칸(r,c)에 물복사버그 마법 시전
			copyWater();

			// 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다.
			// 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
			createCloud(clouds);
		}
		// M번의 이동이 끝나고 바구니에 들어있는 물의 양의 합
		int waterSum = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				waterSum += map[r][c];
			}
		}
		System.out.println(waterSum);
	}

	static void createCloud(ArrayList<int[]> clouds) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] >= 2 && !flag[r][c]) {
					clouds.add(new int[] { r, c });
					map[r][c] -= 2;
				}
			}
		}
	}

	static void copyWater() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!flag[r][c])
					continue;

				int count = 0;
				for (int k = 1; k < 8; k += 2) {
					int nr = r + dr[k];
					int nc = c + dc[k];
					if (nr < 0 || nr >= N | nc < 0 || nc >= N)
						continue;
					if (map[nr][nc] == 0)
						continue;
					count++;
				}
				map[r][c] += count;
			}
		}

	}

	// 2.
	static void rain(ArrayList<int[]> clouds) {
		for (int[] cloud : clouds) {
			int r = cloud[0];
			int c = cloud[1];
			flag[r][c] = true; // 비가 내린 곳 기록
			map[r][c]++; // 비 내리기
		}
	}

	// 모든 구름들 d 방향으로 s 칸 이동
	static void move(ArrayList<int[]> clouds, int d, int s) {
		for (int i = 0; i < clouds.size(); i++) {
			int[] cloud = clouds.get(i);
			cloud[0] = (cloud[0] +s * dr[d]) % N;
			cloud[1] = (cloud[1] +s * dc[d]) % N;
 
			if (cloud[0] < 0) cloud[0] += N;
			if (cloud[1] < 0) cloud[1] += N;
 
			clouds.set(i, cloud);
		}
	}
}