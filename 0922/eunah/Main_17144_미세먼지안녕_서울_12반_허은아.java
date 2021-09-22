package as0922;

import java.io.*;
import java.util.*;

public class Main_17144_미세먼지안녕_서울_12반_허은아 {
	static int R, C;
	static int[][] house;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static List<int[]> airCleaner;
	static ArrayDeque<Dust> dust;

	static class Dust {
		int r, c, amount;

		public Dust(int r, int c, int amount) {
			this.r = r;
			this.c = c;
			this.amount = amount;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		int T = Integer.parseInt(st.nextToken()); // T초

		house = new int[R][C];
		airCleaner = new ArrayList<>();
		dust = new ArrayDeque<>();
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < C; c++) {
				house[r][c] = Integer.parseInt(st.nextToken());
				if (house[r][c] == -1)
					airCleaner.add(new int[] { r, c });
				if (house[r][c] != 0)
					dust.add(new Dust(r, c, house[r][c]));
			}
		}
		bfs(dust, T);
//		for (int[] a : house) {
//			System.out.println(Arrays.toString(a));
//		}

		int result = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (house[r][c] > 0)
					result += house[r][c];
			}
		}
		System.out.println(result);
		br.close();
	}

	static void bfs(ArrayDeque<Dust> q, int T) {
		while (T-- > 0) {
			// 미세먼지 확산
			while (!q.isEmpty()) {
				Dust dust = q.poll();
				int cnt = 0; // 몇 개의 인접한 칸으로 확산하는지
				for (int i = 0; i < 4; i++) { // 인접한 곳 탐색
					int nr = dust.r + dr[i];
					int nc = dust.c + dc[i];
					if (0 <= nr && nr < R && 0 <= nc && nc < C && house[nr][nc] != -1) {
						// 범위 안에 들어오고, 공기청정기가 아니라면
						house[nr][nc] += dust.amount / 5;
						cnt++;
					}
				}
				house[dust.r][dust.c] -= (dust.amount / 5) * cnt;
			}

			// 공기청정기 작동

			// 위 : 우 상 좌 하
			int[] ur = { 0, -1, 0, 1 };
			int[] uc = { 1, 0, -1, 0 };
			int[] upCleaner = airCleaner.get(0);
			int sr = upCleaner[0];
			int sc = upCleaner[1] + 1;
			int tmp = house[sr][sc];
			house[sr][sc] = 0;
			end: for (int i = 0; i < 4; i++) {
				while (true) {
					sr += ur[i];
					sc += uc[i];

					if (0 <= sr && sr < R && 0 <= sc && sc < C) {
						if (house[sr][sc] == -1)
							break end;

						int umm = house[sr][sc];
						house[sr][sc] = tmp;
						tmp = umm;
					} else
						break;
				}
				sr -= ur[i];
				sc -= uc[i];
			}

			// 아래 : 우 하 좌 상
			int[] dr = { 0, 1, 0, -1 };
			int[] dc = { 1, 0, -1, 0 };
			int[] downCleaner = airCleaner.get(1);
			int ar = downCleaner[0];
			int ac = downCleaner[1] + 1;
			tmp = house[ar][ac];
			house[ar][ac] = 0;

			end: for (int i = 0; i < 4; i++) {
				while (true) {
					ar += dr[i];
					ac += dc[i];
					if (0 <= ar && ar < R && 0 <= ac && ac < C) {
						if (house[ar][ac] == -1)
							break end;

						int umm = house[ar][ac];
						house[ar][ac] = tmp;
						tmp = umm;
					} else
						break;
				}
				ar -= dr[i];
				ac -= dc[i];

			}

			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (house[r][c] > 0)
						q.add(new Dust(r, c, house[r][c]));
				}
			}
		}
	}
}