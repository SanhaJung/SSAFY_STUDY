package studyAlgo;

import java.io.*;
import java.util.*;

public class Main_17780_새로운게임_서울_12반_허은아 {
	static class Horse {
		int num, r, c, d;

		public Horse(int num, int r, int c, int d) {
			super();
			this.num = num;
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		int[] dr = { 0, 0, -1, 1 };
		int[] dc = { 1, -1, 0, 0 };
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()); // 체스판의 크기
		int K = Integer.parseInt(st.nextToken()); // 말의 개수

		ArrayList[][] map = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<Horse>();
			}
		}

		int[][] colorMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				colorMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Horse[] horses = new Horse[K];
		for (int num = 0; num < K; num++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;

			Horse horse = new Horse(num, r, c, d);
			horses[num] = horse;
			map[r][c].add(horse);
		}

		int time = 0;
		end: while (time < 1000) {
			time++;
			for (int num = 0; num < K; num++) { // 말 이동
				Horse horse = horses[num]; // 현재 말
				
				int r = horse.r; // 출발위치
				int c = horse.c; // 출발위치

				if(map[r][c].indexOf(horse) != 0) continue;
				
				int nr = r + dr[horse.d];
				int nc = c + dc[horse.d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N || colorMap[nr][nc] == 2) { // 범위 벗어나거나 파란색
					int d = horse.d;
					if (d < 2) d = 1 - d;
					else d = 5 - d;
					horse.d = d; // 방향 반대로 하고

					nr = r + dr[horse.d];
					nc = c + dc[horse.d];

				}
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || colorMap[nr][nc] == 2) continue;
				
				if (colorMap[nr][nc] == 0) { // 흰색이면 현재 말과 현재 말 위에 쌓인 애들 데리고 가기
					int start = map[r][c].indexOf(horse); // 현재 말의 인덱스
					for (int i = start; i < map[r][c].size(); i++) {
						Horse cur = (Horse) map[r][c].get(i);
						cur.r = nr;
						cur.c = nc;
						map[nr][nc].add(cur);
					}
					for (int i = map[r][c].size() - 1; i >= start; i--) {
						map[r][c].remove(i);
					}

				} else if (colorMap[nr][nc] == 1) { // 빨간색 :현재 말과 현재 말 위에 쌓인 애들 데리고 가고 이동한 애들만 순서 바꾸기
					int start = map[r][c].indexOf(horse); // 현재 말의 인덱스
					for (int i = map[r][c].size() - 1; i >= start; i--) {
						Horse cur = (Horse) map[r][c].get(i);
						cur.r = nr;
						cur.c = nc;
						map[nr][nc].add(map[r][c].get(i));
					}
					for (int i = map[r][c].size() - 1; i >= start; i--) {
						map[r][c].remove(i);
					}
				}
				if (map[nr][nc].size() >= 4)
					break end; // 말이 4개 이상 쌓이면 게임 끝
			}

		}
		System.out.println(time == 1000 ? -1 : time);
		br.close();
	}
}