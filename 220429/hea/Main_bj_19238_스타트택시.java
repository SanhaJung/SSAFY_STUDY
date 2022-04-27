package baekjoon;

import java.io.*;
import java.util.*;

public class Main_bj_19238_스타트택시 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int N, M;
	static int[][] map;
	static int[] taxi; // 택시 위치
	static PriorityQueue<Passenger> passengers;
	static Passenger[][] psMap;

	static class Passenger implements Comparable<Passenger> {
		int minDistance;
		int startR, startC, endR, endC;

		public Passenger(int startR, int startC, int endR, int endC, int minDistance) {
			super();
			this.startR = startR;
			this.startC = startC;
			this.endR = endR;
			this.endC = endC;
			this.minDistance = minDistance;
		}

		@Override
		public int compareTo(Passenger o) {
			if (this.minDistance == o.minDistance) {
				if (this.startR == o.startR) {
					return this.startC - o.startC;
				}
				return this.startR - o.startR;
			}
			return this.minDistance - o.minDistance;
		}

		@Override
		public String toString() {
			return "Passenger [minDistance=" + minDistance + ", startR=" + startR + ", startC=" + startC + ", endR="
					+ endR + ", endC=" + endC + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 맵 크기
		M = Integer.parseInt(st.nextToken()); // M명의 승객 태우기
		int fuel = Integer.parseInt(st.nextToken()); // 초기 연료

		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken()); // 0은 빈칸, 1은 벽
			}
		}

		st = new StringTokenizer(br.readLine());

		// 택시 시작 위치
		int x = Integer.parseInt(st.nextToken()) - 1;
		int y = Integer.parseInt(st.nextToken()) - 1;
		taxi = new int[] { x, y, fuel };

		psMap = new Passenger[N][N];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken()) - 1;
			int sc = Integer.parseInt(st.nextToken()) - 1;
			int er = Integer.parseInt(st.nextToken()) - 1;
			int ec = Integer.parseInt(st.nextToken()) - 1;
			map[sr][sc] = 2; // 승객 표시
			psMap[sr][sc] = new Passenger(sr, sc, er, ec, Integer.MAX_VALUE);
		}

		passengers = new PriorityQueue<>();
		while (M > 0) {
			// 가장 가까운 승객 찾기
			passengers.clear();
			findPassenger(taxi[0], taxi[1]);
			Passenger passenger = passengers.poll();
//			System.out.println(passenger);
			

			if (passenger == null || passenger.minDistance > taxi[2]) {
				taxi[2] = -1;
				break;
			} else {
				map[passenger.startR][passenger.startC] = 0;
				psMap[passenger.startR][passenger.startC] = null;
				taxi[2] -= passenger.minDistance;
			}
//			System.out.println("승객 태우고 난 뒤 연료 : " + taxi[2]);
			taxi[0] = passenger.startR;
			taxi[1] = passenger.startC;

			// 이동
			int distance = goDestination(passenger);
			if (distance == Integer.MAX_VALUE || distance > taxi[2]) {
				taxi[2] = -1;
				break;
			} else {
				taxi[2] += distance;
				M--;
				taxi[0] = passenger.endR;
				taxi[1] = passenger.endC;
			}
//			System.out.println("목적지까지의 거리 : "+ distance);
//			System.out.println("목적지 도착 후 연료 : "+taxi[2]);
//			System.out.println();
		}
		System.out.println(taxi[2]);
		br.close();
	}

	private static void findPassenger(int x, int y) {
		if(psMap[x][y] != null) {
			passengers.offer(new Passenger(x, y, psMap[x][y].endR, psMap[x][y].endC, 0));
			return;
		}
		
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] { x, y, 0 });
		boolean[][] visited = new boolean[N][N];
		visited[x][y] = true;

		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			for (int k = 0; k < 4; k++) {
				int nr = cur[0] + dr[k];
				int nc = cur[1] + dc[k];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1 || visited[nr][nc])
					continue;
				if (map[nr][nc] == 2) {
					Passenger p = psMap[nr][nc];
					passengers.offer(new Passenger(nr, nc, p.endR, p.endC, cur[2] + 1));
				}
				visited[nr][nc] = true;
				dq.add(new int[] { nr, nc, cur[2] + 1 });
			}
		}
	}

	private static int goDestination(Passenger ps) {
		if (taxi[0] == ps.endR && taxi[1] == ps.endC)
			return 0;

		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] { ps.startR, ps.startC, 0 });
		boolean[][] visited = new boolean[N][N];
		visited[ps.startR][ps.startC] = true;

		int distance = Integer.MAX_VALUE;
		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			for (int k = 0; k < 4; k++) {
				int nr = cur[0] + dr[k];
				int nc = cur[1] + dc[k];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1 || visited[nr][nc])
					continue;

				// 목적지 도착하면
				if (nr == ps.endR && nc == ps.endC) {
					distance = Math.min(distance, cur[2] + 1);
				}
				visited[nr][nc] = true;
				dq.add(new int[] { nr, nc, cur[2] + 1 });
			}
		}

		return distance;
	}

}
