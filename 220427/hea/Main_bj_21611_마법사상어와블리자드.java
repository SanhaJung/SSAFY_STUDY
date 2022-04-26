package baekjoon;

import java.io.*;
import java.util.*;

public class Main_bj_21611_마법사상어와블리자드 {
	static int[] dr = { 0, 1, 0, -1 }; // 회전용 좌하우상
	static int[] dc = { -1, 0, 1, 0 };

	static int[] br = { -1, 1, 0, 0 }; // 얼음 던지기용
	static int[] bc = { 0, 0, -1, 1 };

	static int N, M;
	static int[][] map;
	static ArrayList<Integer> marbles;
	static int[] marbleCnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 상어:0, (N/2, N/2)
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		marbles = new ArrayList<>();
		marbleCnt = new int[4];
		for (int m = 0; m < M; m++) {
		
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());

			// 얼음 파편 던지기
			throwIce(d, s);
			
			// 구슬 이동
			moveMarble(0);

			if(marbles.size() == 0) break;
			
			// 구슬 폭발
			while (true) {
				if (bombMarble()) continue;
				else break;
			}

			// 구슬 A, B로 분개
			changeABMarble();

			// 다시 맵에 뿌리기
			mapMarble(0);
		}
		
		int sum = 0;
		for (int n = 1; n <= 3; n++) {
			sum += n * marbleCnt[n];
		}
		System.out.println(sum);
		br.close();
	}

	static void mapMarble(int d) {
		map = new int[N][N];
		if(marbles.size() == 0) return;

		int cnt = 1;
		int sr = N / 2;
		int sc = N / 2;
		int n = 1;
		end: while (true) {
			for (int k = 0; k < 2; k++) {
				for (int go = 0; go < n; go++) {
					sr += dr[d];
					sc += dc[d];
					map[sr][sc] = marbles.get(cnt - 1);
					cnt++;
					if (cnt == marbles.size() + 1 || cnt == N * N) break end;
				}
				d++;
				if (d == 4) d = 0;
			}
			n++;
		}
	}

	private static void changeABMarble() {
		if(marbles.size() == 0) return;
		
		ArrayList<Integer> temp = new ArrayList<>();
		temp.addAll(marbles);
		marbles.clear();
		
		int cnt = 1;
		int before = temp.get(0);
		for (int n = 1; n < temp.size(); n++) {
			int cur = temp.get(n);

			if (cur == before) {
				cnt++;
			} else {
				marbles.add(cnt);
				marbles.add(before);
				cnt = 1;
			}
			before = cur;
		}
		marbles.add(cnt);
		marbles.add(before);
	}

	private static boolean bombMarble() {
		boolean flag = false;

		int size = marbles.size();
		if (size == 0) return flag;

		int cnt = 1; // 연속 개수
		int before = marbles.get(size - 1);
		for (int n = size - 2; n >= 0; n--) {
			int cur = marbles.get(n);
			if (cur == before)
				cnt++;
			else {
				if (cnt >= 4) {
					flag = true;
					marbleCnt[before] += cnt;
					for (int k = 0; k < cnt; k++) {
						marbles.remove(n + 1);
					}
				}
				cnt = 1;
			}
			before = cur;
		}
		if(cnt >= 4) {
			for (int k = 0; k < cnt; k++) {
				marbles.remove(0);
			}
			marbleCnt[before] += cnt;
		}
		return flag;
	}

	private static void throwIce(int d, int s) {
		int sr = N / 2;
		int sc = N / 2;
		for (int n = 1; n <= s; n++) {
			sr += br[d];
			sc += bc[d];
			map[sr][sc] = 0;
		}
	}

	static void moveMarble(int d) {
		marbles.clear();

		int cnt = 1;
		int sr = N / 2;
		int sc = N / 2;
		int n = 1;
		end: while (true) {
			for (int k = 0; k < 2; k++) {
				for (int go = 0; go < n; go++) {
					sr += dr[d];
					sc += dc[d];
					if (map[sr][sc] != 0) marbles.add(map[sr][sc]);
					cnt++;
					if (cnt == N * N) break end;
				}
				d++;
				if (d == 4) d = 0;
			}
			n++;
		}
	}
}