package a0924.hw;

import java.io.*;
import java.util.*;

public class Main_bj_19236_청소년상어_답 {

	static int N = 4, res;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 8방 탐색
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };

	static class Fish {
		int x, y, dir;

		public Fish(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] map = new int[N][N];
		Fish[] fishes = new Fish[17];
		// 공간 정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken()); // 물고기 번호
				int b = Integer.parseInt(st.nextToken()) - 1; // 방향

				// map에는 물고기 번호를 저장
				map[i][j] = a;
				// 해당 번호의 물고기의 위치와 방향 저장
				fishes[a] = new Fish(i, j, b);
			}
		}

		// 먼저 상어가 (0,0)공간에 들어간다.
		// (0,0)에 있는 물고기를 먹고
		res = map[0][0];
		// 상어의 방향은 (0, 0)에 있던 물고기의 방향을 갖는다.
		Fish shark = new Fish(0, 0, fishes[map[0][0]].dir);
		// 먹힌 고기는 정보를 지워주자
		fishes[map[0][0]] = null;
		// 상어를 map에 위치시켜주자. (상어는 -1 번)
		map[0][0] = -1;

		// map, shark, fishes 데이터는 계속 변하므로 매개변수로 들고 다니자.
		process(map, shark, fishes, res);

		System.out.println(res);

	}

	private static void process(int[][] map, Fish shark, Fish[] fishes, int sum) {

		/*
		 * 원본 데이터를 유지하기 위해 map, shark, fishes 를 복사해서 사용.
		 */
		// map 복사
		int[][] tmpMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}
		// shark 복사
		Fish tmpShark = new Fish(shark.x, shark.y, shark.dir);
		// fishes 복사
		Fish[] tmpFishes = new Fish[17];
		for (int i = 1; i <= 16; i++) {
			if (fishes[i] == null)
				continue;
			tmpFishes[i] = new Fish(fishes[i].x, fishes[i].y, fishes[i].dir);
		}

		/*
		 * 먼저 물고기가 이동
		 */
		for (int f = 1; f <= 16; f++) {

			// i번 물고기의 정보
			Fish now = tmpFishes[f];
			// 먹힌 물고기면 pass
			if (now == null)
				continue;

			// 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전
			int origDir = now.dir;
			do {
				// 물고기는 한 칸을 이동
				int xx = now.x + dx[now.dir];
				int yy = now.y + dy[now.dir];

				// 상어가 있거나, 공간을 넘어가면 회전
				if (xx < 0 || xx >= N || yy < 0 || yy >= N || tmpMap[xx][yy] == -1) {
					now.dir = (now.dir + 1) % 8;
					continue;
				}

				// 빈 칸 or 다른 물고기가 있는 칸이라면 이동하고 위치를 바꿔주자
				// 이동할 위치의 물고기 정보 저장
				Fish ftmp = tmpFishes[tmpMap[xx][yy]];
				if (ftmp == null) { // 이동할 위치에 물고기가 없다면
					// 내 위치만 갱신
					tmpFishes[f] = new Fish(xx, yy, now.dir);
				} else { // 이동할 위치에 물고기가 있다면
							// 내 위치와 이동할 위치의 정보를 교환
					tmpFishes[tmpMap[xx][yy]] = new Fish(now.x, now.y, ftmp.dir);
					tmpFishes[f] = new Fish(xx, yy, now.dir);
				}

				// 물고기 정보도 교환
				int ntmp = tmpMap[xx][yy];
				tmpMap[xx][yy] = f;
				tmpMap[now.x][now.y] = ntmp;

				break;

			} while (now.dir != origDir); // 처음 방향으로 돌아올 때까지
		} // 이동할 수 있는 칸이 없으면 이동을 하지 않는다

		/*
		 * 상어가 이동
		 */
		// 이동할 수 있을 때까지 다 이동해보자(최대 3칸)
		for (int d = 1; d <= 3; d++) {
			int xx = tmpShark.x + dx[tmpShark.dir] * d;
			int yy = tmpShark.y + dy[tmpShark.dir] * d;

			// 범위 넘어가면 pass
			if (xx < 0 || xx >= N || yy < 0 || yy >= N)
				break;
			// 물고기가 없는 칸일 경우 pass
			if (tmpMap[xx][yy] == 0)
				continue;

			// 상어가 물고기가 있는 칸으로 이동했다면
			// 그 칸에 있는 물고기를 먹고, 그 물고기의 방향을 가지게 된다.
			int eatNum = tmpMap[xx][yy]; // 이동할 위치의 물고기 번호
			Fish fish = tmpFishes[tmpMap[xx][yy]]; // 이동할 위치의 물고기 정보

			// 먹힌 물고기 정보에서 삭제
			tmpFishes[tmpMap[xx][yy]] = null;
			// 상어 정보 갱신
			tmpShark = new Fish(fish.x, fish.y, fish.dir);
			// map 갱신
			tmpMap[xx][yy] = -1;
			// 원래 상어가 있던 위치는 비우자
			tmpMap[shark.x][shark.y] = 0;

			// 수정된 tmp 데이터를 전송(원본 데이터는 보존)
			process(tmpMap, tmpShark, tmpFishes, sum + eatNum);

			/*
			 * 백트래킹
			 */

			// 초기 상어 위치로 되돌리기
			tmpMap[shark.x][shark.y] = -1;
			// 먹힌 물고기를 되돌리기
			tmpMap[xx][yy] = eatNum;
			// 초기 상어 정보 되돌리기
			tmpShark = new Fish(shark.x, shark.y, shark.dir);
			// 먹힌 물고기 정보 되돌리기
			tmpFishes[tmpMap[xx][yy]] = new Fish(fish.x, fish.y, fish.dir);
		} // 상어가 이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 간다.

		// 결과 갱신
		res = Math.max(res, sum);
	}

}
