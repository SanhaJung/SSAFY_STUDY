package baekjoon;

import java.io.*;
import java.util.*;

public class Main_bj_21608_상어초등학교 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int N;
	static ArrayList[] likesList;
	static int[][] map;
	static PriorityQueue<Location> locQ;

	static class Location implements Comparable<Location> {
		int r, c, likeCnt, tempCnt;

		public Location(int r, int c, int likeCnt, int tempCnt) {
			super();
			this.r = r;
			this.c = c;
			this.likeCnt = likeCnt;
			this.tempCnt = tempCnt;
		}

		@Override
		public int compareTo(Location o) {
			if (this.likeCnt == o.likeCnt) {
				if (this.tempCnt == o.tempCnt) {
					if (this.r == o.r) {
						return this.c - o.c;
					} else
						return this.r - o.r;
				} else
					return o.tempCnt - this.tempCnt;

			}
			return o.likeCnt - this.likeCnt;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // N * N, 맵의 크기

		map = new int[N][N];
		likesList = new ArrayList[N * N + 1];
		int[] studentOrder = new int[N * N];
		for (int n = 0; n < N * N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			int studentNum = Integer.parseInt(st.nextToken());

			studentOrder[n] = studentNum;
			likesList[studentNum] = new ArrayList<Integer>();
			for (int k = 0; k < 4; k++) {
				likesList[studentNum].add(Integer.parseInt(st.nextToken()));
			}
		}

		locQ = new PriorityQueue<>();
		for (int n = 0; n < N * N; n++) {
			int studentNum = studentOrder[n];

			// 인접 칸 찾기
			locQ.clear();
			findNearLoc(studentNum);
			Location loc = locQ.poll();

			map[loc.r][loc.c] = studentNum;
		}

		int sum = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int likeCnt = 0;
				ArrayList<Integer> likeList = likesList[map[r][c]];
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					if (likeList.contains(map[nr][nc])) likeCnt++;
				}
				if(likeCnt !=0)	sum += Math.pow(10, likeCnt - 1);
			}
		}
		System.out.println(sum);
		br.close();
	}

	private static void findNearLoc(int studentNum) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 0) {
					// 인접 칸 확인
					int likeCnt = 0;
					int tempCnt = 0;
					ArrayList<Integer> likeList = likesList[studentNum];
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
						if (map[nr][nc] == 0) tempCnt++;
						if (likeList.contains(map[nr][nc])) likeCnt++;
					}
					//
					locQ.add(new Location(r, c, likeCnt, tempCnt));
				}
			}
		}
	}
}