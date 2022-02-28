package baekjoon;

import java.io.*;
import java.util.*;

public class Main_bj_16236_아기상어 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static Shark shark;
	static int[][] map;

	static int N, M;

	static class Shark {
		int r, c, size, exp;

		public Shark(int r, int c, int size, int exp) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.exp = exp;
		}
	}

	static class Fish implements Comparable<Fish> {
		int r, c, size, d;

		public Fish(int r, int c, int size, int d) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
			this.d = d;
		}

		@Override
		public int compareTo(Fish o) {
			if(this.d < o.d) return this.d - o.d;
			else if (this.d == o.d) {
				if (this.r < o.r) return this.r - o.r;
				else if (this.r == o.r) {
					return this.c - o.c;
				}
			}
			return 0;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					shark = new Shark(i, j, 2, 0);
					map[i][j] = 0;
				} 
			}
		}
		
		// 물고기 잡아먹으러
		int time = 0;
		while(true) {
			Fish fish = findFish(shark.r, shark.c); // 먹으러 갈 물고기 
			
			if(fish == null) break; // 먹을 물고기가 없으면 끝
			
			// 먹을 물고기 존재하면
			// 상어 이동
			shark.r = fish.r;
			shark.c = fish.c;
			
			// 경험치 +1
			shark.exp++;
			
			if(shark.exp == shark.size) {
				shark.size++;
				shark.exp = 0;
			}
			
			map[shark.r][shark.c] = 0; // 먹은 물고기 위치 = 현재 상어 위치 0으로
			time+= fish.d;
		}
		System.out.println(time);
	}

	static Fish findFish(int i, int j) {
		boolean[][] visited = new boolean[N][N];
		visited[i][j] = true;
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] { i, j, 0 });

		// 먹을 수 있는 물고기 목록
		List<Fish> fishes = new ArrayList<Fish>();

		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			for (int k = 0; k < 4; k++) {
				int nr = cur[0] + dr[k];
				int nc = cur[1] + dc[k];

				// 범위를 벗어나거나 방문했으면 다음
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;

				if (map[nr][nc] > shark.size) continue;  // 상어보다 큰 물고기는 못 지나간다.
   			    else if (map[nr][nc] > 0 && map[nr][nc] < shark.size) { // 상어보다 작은 물고기
					fishes.add(new Fish(nr, nc, map[nr][nc], cur[2] + 1));
				}
				visited[nr][nc] = true;
				dq.add(new int[] { nr, nc, cur[2] + 1 });
			}
		}
		Collections.sort(fishes); // 우선순위 큐로 바꿔보기
		
		if(fishes.size() == 0) return null;
		return fishes.get(0);
	}
}
