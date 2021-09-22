package as0922;

import java.io.*;
import java.util.*;

public class Main_17143_낚시왕_서울_12반_허은아 {
	static int R, C;
	static int[] dr = { -1, 1, 0, 0 }; // 상하 우좌
	static int[] dc = { 0, 0, 1, -1 };

	static List<Shark> sharks;

	static class Shark {
		int r, c;
		int speed, d, size;

		public Shark(int r, int c, int speed, int d, int size) {
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.d = d;
			this.size = size;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); //
		C = Integer.parseInt(st.nextToken()); //
		int M = Integer.parseInt(st.nextToken()); // 상어의 수

		sharks = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken()); // 속력
			int d = Integer.parseInt(st.nextToken()) - 1; // 방향
			int z = Integer.parseInt(st.nextToken()); // 크기

			sharks.add(new Shark(r, c, s, d, z));
		}

		int result = 0;
		for (int i = 0; i < C; i++) {
			Collections.sort(sharks, new Comparator<Shark>() {
				@Override
				public int compare(Shark o1, Shark o2) {
					return Integer.compare(o1.r, o2.r);
				}
			});
			List<Shark> colSharks = new ArrayList<>();
			// i열에 있는 상어들
			for (int s = 0; s < sharks.size(); s++) {
				Shark sk = sharks.get(s);
				if (sk.c == i)
					colSharks.add(sk);
			}

			if (colSharks.size() == 0) continue; // 열에 상어가 없으면 넘기기
			result += colSharks.get(0).size;
			System.out.println("result : " + result);
			sharks.remove(colSharks.get(0)); // 상어 없애기

			// 배열에 위치 시키고 cnt가 2이상이면 잡아먹기!
			for (int s = 0; s < sharks.size(); s++) {
				Shark sk = sharks.get(s);

				for (int k = 0; k < sk.speed; k++) {
					int nr = sk.r + dr[sk.d];
					int nc = sk.c + dr[sk.d];
					if (0 <= nr && nr < R && 0 <= nc && nc < C) { // 범위 안에 들어오면
						sk.r = nr;
						sk.c = nc;
					} else {
						sk.d = (sk.d + 2) % 4; // 방향 바꾸기 
						sk.r += dr[sk.d];
						sk.c += dc[sk.d];
					}
				}

			}

			int[][] temp = new int[R][C];
			for (int s = 0; s < sharks.size(); s++) {
				Shark sk = sharks.get(s);
//				System.out.println(sk.r+", "+sk.c);
				temp[sk.r][sk.c]++;
			}
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (temp[r][c] > 1) {
						List<Shark> find = eat(r, c);
						for (int j = 0; j < find.size() - 1; j++) {
							sharks.remove(find.get(j));
						}
					}
				}
			}

		}
		System.out.println(result);

		br.close();
	}

	static List<Shark> eat(int r, int c) {
		List<Shark> find = new ArrayList<>();
		for (int s = 0; s < sharks.size(); s++) {
			Shark sk = sharks.get(s);
			if (sk.r == r && sk.c == sk.c) {
				find.add(sk);
			}
		}
		Collections.sort(find, new Comparator<Shark>() {
			@Override
			public int compare(Shark o1, Shark o2) {
				return Integer.compare(o1.size, o2.size);
			}
		});
		return find;
	}

}