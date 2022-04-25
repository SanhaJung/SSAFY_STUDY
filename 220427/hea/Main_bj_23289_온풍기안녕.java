package baekjoon;

import java.io.*;
import java.util.*;

public class Main_bj_23289_온풍기안녕 {
	static int R, C, K, W;

	static int[] dr = { 0, 0, -1, 1 }; // 우좌상하
	static int[] dc = { 1, -1, 0, 0 };
	static Room[][] room;
	static ArrayList<Heater> heaters;
	static ArrayList<int[]> checkList;

	static class Heater {
		int r, c, d;

		public Heater(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	static class Room {
		int temperature;
		boolean[] wall = new boolean[4]; // 우좌상하

		public Room(int temperature) {
			super();
			this.temperature = temperature;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		room = new Room[R][C];
		heaters = new ArrayList<>(); // 온풍기 리스트
		checkList = new ArrayList<>(); // 조사 대상
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				int info = Integer.parseInt(st.nextToken());
				room[r][c] = new Room(0);
				if (info == 5) {
					checkList.add(new int[] { r, c });
					continue;
				} else if (info > 0) {
					heaters.add(new Heater(r, c, info - 1));
				}
			}
		}

		// 벽 정보 저장
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		for (int w = 0; w < W; w++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int t = Integer.parseInt(st.nextToken());

			// 우좌상하
			if (t == 0) { // (x, y), (x-1, y) 상하
				room[x][y].wall[2] = true;
				room[x - 1][y].wall[3] = true;
			} else { // (x, y), (x, y+1) 좌우
				room[x][y].wall[0] = true;
				room[x][y + 1].wall[1] = true;
			}
		}

		int chocolate = 0;
		while (true) {
			if(chocolate == 100) {
				chocolate++;
				break;
			}
			// 1. 온풍기 바람
			windHeater();
			
			// 2. 온도 조절
			moveTemperature();
			
			// 3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
			decreaseOutside();

			// 4. 초콜릿 하나 먹기
			chocolate++;

			// 5. 조사하는 모든 칸의 온도가 K 이상이 되었는지 검사
			// 모든 칸의 온도가 K이상이면 테스트를 중단
			if (checkTemperature()) break;
		}
		System.out.println(chocolate);
		br.close();
	}

	// 1. 집에 있는 모든 온풍기에서 바람이 나옴
	private static void windHeater() {
		for (Heater heater : heaters) {
			int d = heater.d;
			
			// 바람 퍼지는 방향
			int[] dir = {0, d, 1};
			if(d == 0 || d == 1) dir = new int[] {2, d, 3};
			
			// 온풍기의 바람이 나오는 방향에 있는 칸은 항상 존재
			int hr = heater.r;
			int hc = heater.c;
			
			int[][] up = new int[R][C];
			up[hr + dr[d]][hc + dc[d]] = 5;
			for (int n = 0; n < 5; n++) {
				hr += dr[d];
				hc += dc[d];
				if (hr < 0 || hr >= R || hc < 0 || hc >= C) break;
				
				if(up[hr][hc] > 0) check(up, hr, hc, d, dir, 4 - n);
				
				for (int k = 1; k <= n; k++) {
					for (int di = 0; di < 3; di+=2) {
						int nr = hr + k * dr[dir[di]];
						int nc = hc + k * dc[dir[di]];
						
						if (nr < 0 || nr >= R || nc < 0 || nc >= C || up[nr][nc] == 0) continue;
						check(up, nr, nc, d, dir, 4 - n);
					}
				}
			}
			
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					room[r][c].temperature += up[r][c];
				}
			}
		}
	}

	// d : 온풍기 방향
	// dir : 상우하, 
	// 우좌상하
	private static void check(int[][] up, int nr, int nc, int d, int[] dir, int tmp) {
		for (int n = 0; n < 3; n++) {
			int mr = nr;
			int mc = nc;
			
			// 바람이 좌우이면
			if(d == 0 || d == 1) {
				  mr += dr[dir[n]];
				  mc += dc[d];
			}else {
				  mr += dr[d];
				  mc += dc[dir[n]];
			}
			
			// 범위 벗어나거나 이미 확인했거나 벽 있으면
			if(mr < 0 || mr >= R || mc < 0 || mc >= C) continue;
			if(up[mr][mc] > 0) continue;
			if(room[nr][nc].wall[dir[n]]) continue;
			
			int cd = 0;
			if(d == 0 || d == 1) cd = 1 - d ;
			else cd = 5 - d;
			if(room[mr][mc].wall[cd]) continue;
			
			up[mr][mc] = tmp;
		}
	}

	// 2. 온도가 조절됨 - 동시에
	private static void moveTemperature() {
		int[][] temp = new int[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				temp[r][c] = room[r][c].temperature;
			}
		}

		// 우하 확인
		int[] dir = { 0, 3 };
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				for (int d = 0; d < dir.length; d++) {
					int nr = r + dr[dir[d]];
					int nc = c + dc[dir[d]];
					if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

					// 벽 유무 확인
					if (d == 0) { // 좌우
						if (room[r][c].wall[0]) continue;
					} else if (d == 1) { // 상하
						if (room[r][c].wall[3]) continue;
					}

					// 온도 같으면 이동 x
					if (temp[r][c] == temp[nr][nc]) continue;

					int diff = Math.abs(temp[r][c] - temp[nr][nc]) / 4;
					if (temp[r][c] > temp[nr][nc]) {
						room[r][c].temperature -= diff;
						room[nr][nc].temperature += diff;
					} else {
						room[r][c].temperature += diff;
						room[nr][nc].temperature -= diff;
					}
				}
			}
		}
	}

	// 3. 온도가 1 이상인 가장 바깥쪽 칸의 온도 1 감소
	private static void decreaseOutside() {
		for (int c = 0; c < C; c++) {
			if (room[0][c].temperature >= 1) room[0][c].temperature--;
			if (room[R - 1][c].temperature >= 1) room[R - 1][c].temperature--;
		}

		for (int r = 1; r < R - 1; r++) {
			if (room[r][0].temperature >= 1) room[r][0].temperature--;
			if (room[r][C - 1].temperature >= 1) room[r][C - 1].temperature--;
		}
	}

	// 4. 조사 대상 칸 온도 >= K인지 확인
	private static boolean checkTemperature() {
		for (int[] check : checkList) {
			if (room[check[0]][check[1]].temperature < K) return false;
		}
		return true;
	}
}
