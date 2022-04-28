package baekjoon;

import java.io.*;
import java.util.*;

public class Main_bj_2573_빙산 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] map;
	static int N, M;

	static class Ice {
		int r, c, ice;

		public Ice(int r, int c, int ice) {
			super();
			this.r = r;
			this.c = c;
			this.ice = ice;
		}

	}
	static ArrayDeque<Ice> iceDq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		iceDq = new ArrayDeque<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] > 0) {
					iceDq.offer(new Ice(r, c, map[r][c]));
				}
			}
		}

		int count = 0;
		boolean[][] visited = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0 || visited[r][c]) continue;
				
				checkIce(r, c, visited);
				count++;
			}
		}
		
		if (count >= 2) {
			System.out.println(0);
			System.exit(0);
		}

		int year = 0;
		end: while (!iceDq.isEmpty()) {
			year++;
			
			// 얼음 녹이기
			meltingIce();
			if (iceDq.isEmpty()) {
				System.out.println(0);
				System.exit(0);
			}

			// 두 덩어리 이상인지 확인
			count = 0;
			visited = new boolean[N][M];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] == 0 || visited[r][c]) continue;
					checkIce(r, c, visited);
					count++;
				}
			}
			if (count >= 2) break end;
		}

		System.out.println(year);
		br.close();
	}

	private static void checkIce(int r, int c, boolean[][] visited) {
		  visited[r][c] = true;
		  
	      int nr, nc;
	      for (int i = 0; i < 4; i++) {
	          nr = r + dr[i];
	          nc = c + dc[i];

	          if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
	              continue;
	          }
	 
	          if (map[nr][nc] != 0 && !visited[nr][nc]) {
	          	checkIce(nr, nc, visited);
	          }
	     }
	}

	private static void meltingIce() {
		int size = iceDq.size();
		for (int n = 0; n < size; n++) {
			Ice ice = iceDq.poll();
			int r = ice.r;
			int c = ice.c;
			
			int count = 0;
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if (map[nr][nc] == 0) count++;
			}

			if (map[r][c] > count)  {
				ice.ice -= count;
				iceDq.add(ice);
			}
		}
		
		map = new int[N][M];
		size = iceDq.size();
		for (int n = 0; n < size; n++) {
			Ice ice = iceDq.poll();
			map[ice.r][ice.c] = ice.ice;
			iceDq.offer(ice);
		}
	}
}
