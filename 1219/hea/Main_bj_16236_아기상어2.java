package baekjoon;

import java.io.*;
import java.util.*;

import baekjoon.Main_bj_16236_아기상어.Fish;

public class Main_bj_16236_아기상어2 {
	static int N;
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };
	static int[][] map;
	static Shark shark;
	static PriorityQueue<Fish> fishes;

	static class Fish implements Comparable<Fish> {
		int r;
		int c;
		int d;

		public Fish(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public int compareTo(Fish o) {
			if (this.d - o.d == 0) {
				if (this.r - o.r == 0)
					return this.c - o.c;
				return this.r - o.r;
			}
			return this.d - o.d;
		}
	}

	static class Shark {
		int r, c, size, cnt;

		public Shark(int r, int c, int size, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
			this.cnt = cnt;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		fishes = new PriorityQueue<>();
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 9) {
					shark = new Shark(r, c, 2, 0); // r,c, 아기상어 크기, cnt
				}
			}
		}
		int time = 0;
		while (true) {
			fishes.clear();
			Fish fish = bfs(shark); // 먹을 수 있는 물고기 담아오기
			if (fish == null)
				break;

			map[shark.r][shark.c] = 0; // 물고기 먹기
			shark.r = fish.r;
			shark.c = fish.c;
			map[shark.r][shark.c] = 9; // 상어 위치

			time += fish.d;
			shark.cnt++;
			if (shark.cnt == shark.size) {
				shark.size++;
				shark.cnt = 0;
			}
		}

		System.out.println(time);
	}

	static Fish bfs(Shark shark) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] { shark.r, shark.c });

		int[][] distance = new int[N][N];
		distance[shark.r][shark.c] = 0;

		while (!dq.isEmpty()) {
			int[] cur = dq.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;
				if (distance[nr][nc] != 0)
					continue;

				if (map[nr][nc] <= shark.size) {
					distance[nr][nc] = distance[cur[0]][cur[1]] + 1;
					dq.offer(new int[] { nr, nc });
					if (0 < map[nr][nc] && map[nr][nc] < shark.size) {
						fishes.offer(new Fish(nr, nc, distance[nr][nc]));
					}
				}
			}
		}
		if (fishes.isEmpty())
			return null;
		return fishes.poll();
	}
}