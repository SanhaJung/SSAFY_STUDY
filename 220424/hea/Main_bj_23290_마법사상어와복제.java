package baekjoon;

import java.io.*;
import java.util.*;

public class Main_bj_23290_마법사상어와복제 {
	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 }; // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 좌상우하 0246

	static int[] sx = {0, -1, 0, 1, 0 };
	static int[] sy = {0, 0, -1, 0, 1 };

	static int N = 4, M, S;
	static int[][] smell;
	static ArrayList<Fish>[][] map;
	static ArrayList<Fish> fishes;
	static ArrayList<Fish> copyFishes;
	static int[] shark;

	static class Fish {
		int x, y, d;

		public Fish(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	// 상어가 이동 가능한 경우
	static class Case implements Comparable<Case>{
		int sol, fishCnt;
		
		public Case(int sol, int fishCnt) {
			super();
			this.sol = sol;
			this.fishCnt = fishCnt;
		}

		@Override
		public int compareTo(Case o) {
			if(this.fishCnt == o.fishCnt) return this.sol - o.sol;
			return o.fishCnt - this.fishCnt;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		smell = new int[N][N];
		map = new ArrayList[4][4];
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				map[x][y] = new ArrayList<Fish>();
			}
		}

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		M = Integer.parseInt(st.nextToken()); // 물고기 M마리
		S = Integer.parseInt(st.nextToken()); // 마법 연습 횟수

		fishes = new ArrayList<>();
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int fx = Integer.parseInt(st.nextToken()) - 1;
			int fy = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;

			fishes.add(new Fish(fx, fy, d));
		}

		// 상어 초기 위치
		st = new StringTokenizer(br.readLine(), " ");
		shark = new int[2];
		shark[0] = Integer.parseInt(st.nextToken()) - 1;
		shark[1] = Integer.parseInt(st.nextToken()) - 1;

		for (int s = 0; s < S; s++) {
			// 1. 복제 마법 : 위치, 방향 그대로 5번에서 완료됨
			copy();

			// 2. 모든 물고기 한 칸 이동 : 물고기 냄새 x, 격자 범위 벗어나면 x,
			// 이동 가능할 때까지 45도 반시계 회전, 이동 가능한 칸이 없으면 이동하지 않음
			moveFish();

			// 3. 상어가 연속해서 3칸 이동 (상하좌우),
			// 연속해서 이동하는 칸 중에 격자 벗어나면 불가능한 이동 방법
			// 연속해서 이동하는 중에 물고기 있으면 다 없어지고 냄새 남김
			// 가능한 이동 방법 중 제외되는 물고기의 수가 가장 많은 방법으로 이동, 여러가지면 사전 순으로
			moveShark(shark[0], shark[1]);

			// 4. 두 번 전 연습에서 생긴 냄새 없애기
			removeSmell();

			// 5. 복제마법 완료
			completeCopy();
		}
		System.out.println(fishes.size());
		br.close();
	}

	// 5. 복제마법 완료
	private static void completeCopy() {
		fishes.clear();
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				for (Fish fish : map[x][y]) {
					fishes.add(fish);
				}
			}
		}
		
		for (Fish fish : copyFishes) {
			fishes.add(fish);
		}
	}

	// 4. 물고기 냄새 제거
	private static void removeSmell() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if(smell[x][y] > 0) smell[x][y]--;
			}
		}
		
	}

	// 상어 이동
	private static void moveShark(int x, int y) {
		PriorityQueue<Case> cases = new PriorityQueue<>();
		// 상좌하우 1234
		for (int i = 1; i <= 4; i++) {
			int ix = x + sx[i];
			int iy = y + sy[i];
			if(!possibleMove(ix, iy)) continue;
			
			for (int j = 1; j <= 4; j++) {
				int jx = ix + sx[j];
				int jy = iy + sy[j];
				if(!possibleMove(jx, jy)) continue;
				
				for (int k = 1; k <= 4; k++) {
				
					int kx = jx + sx[k];
					int ky = jy + sy[k];
					if(!possibleMove(kx, ky)) continue;
					
					// 이동할 곳 체크
					// 겹치게 이동 가능해서 
					boolean[][] visit = new boolean[4][4];
					visit[ix][iy] = true;
					visit[jx][jy] = true;
					visit[kx][ky] = true;
					
					String sol = i+""+j+""+k;
					cases.offer(new Case(Integer.parseInt(sol), countFish(visit)));
				}
			}
		}
		Case ca = cases.poll();
		String sol = String.valueOf(ca.sol);
		
		for (int n = 0; n < sol.length(); n++) {
			int d = sol.charAt(n) - '0';
			
			// 상어 이동
			x += sx[d];
			y += sy[d];
			
			// 물고기 사라지고 냄새 남기기
			if(map[x][y].size() != 0) smell[x][y] = 3;
			map[x][y].clear();
		}
		
		// 상어 위치 갱신
		shark[0] = x;
		shark[1] = y;
	}
	
	// 상어가 지나갈 곳의 총 물고기 수 세기
	private static int countFish(boolean[][] visit) {
		int fishCount = 0;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if(visit[x][y]) fishCount += map[x][y].size();
			}
		}
		return fishCount;
	}

	// 이동 가능한지
	private static boolean possibleMove(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) return false;
		return true;
	}

	// 2. 물고기 이동
	private static void moveFish() {
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				map[x][y] = new ArrayList<Fish>();
			}
		}
		for (Fish fish : fishes) {
			int cnt = 0;
			while (true) {
				cnt++;

				// 8방향 모두 이동 불가
				if (cnt == 9) break;

				int nx = fish.x + dx[fish.d];
				int ny = fish.y + dy[fish.d];

				// 격자 벗어나거나, 냄새 있거나, 상어 있으면 이동 불가
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || smell[nx][ny] > 0 || (nx == shark[0] && ny == shark[1])) {
					fish.d--;
					if (fish.d < 0) fish.d += 8;
					continue;
				}

				// 이동 가능하면
				fish.x = nx;
				fish.y = ny;

				break;
			}
			map[fish.x][fish.y].add(fish);
		}
	}

	// 1. 물고기 복제
	private static void copy() {
		copyFishes = new ArrayList<>();
		for (Fish fish : fishes) {
			copyFishes.add(new Fish(fish.x, fish.y, fish.d));
		}
	}

}