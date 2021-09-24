package a0924.hw;

import java.io.*;
import java.util.*;

public class Main_bj_19236_청소년상어_서울_12반_허은아 {
	static int[][] water;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 8방 탐색
	static int[] dc = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static ArrayDeque<int[]> q;
	static Fish shark;
	static Fish[] fishes;
	static int result;

	static class Fish {
		int d, r, c;

		public Fish(int d, int r, int c) {
			super();
			this.d = d;
			this.r = r;
			this.c = c;
		}
	}

	// 청소년 상어는 0,0부터 시작, 0,0에 있던 물고기의 방향과 같다
	// 번호가 작은 물고기부터 순서대로 이동
	// 물고기가 다른 물고기가 있는 칸으로 이동할 때는 서로의 위치를 바꾸는 식으로 이동
	// 상어가 있거나, 공간 벗어나면 이동 못하는데,
	// 이동할 수 있을 때까지 45도 반시계방향으로 회전
	// 이동할 수 있는 칸이 없으면 이동 안 함
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		q = new ArrayDeque<>();
		fishes = new Fish[17];
		water = new int[4][4];
		for (int r = 0; r < 4; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < 4; c++) {
				int num = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken()) - 1;
				Fish f = new Fish(d, r, c);
				water[r][c] = num;
				fishes[num] = f;
			}
		}
		// 번호가 낮은 물고기부터 이동하므로 정렬해주기
		result = water[0][0];
		Fish f = fishes[water[0][0]];
		fishes[water[0][0]] = null;
		shark = new Fish(f.d, 0, 0);
		water[0][0] = -1; // 상어
		fishes[0] = shark;
//		System.out.println(sum);
//		for (Fish s :fishes) {
//			System.out.println(s);
//		}
		move(water, shark, fishes, result);
		System.out.println(result);
		br.close();
	}

	// 상어가 이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 간다.
	// 물고기가 없는 칸으로는 이동할 수 없다.
	// 상어는 방향에 있는 칸으로 이동 가능
	static void move(int[][] water, Fish shark, Fish[] fishes, int sum) {
		// 맵 복사
		int[][] tmpWater = new int[4][4];
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				tmpWater[r][c] = water[r][c];
			}
		}
		// 상어 복사
		Fish tmpShark = new Fish(shark.d, shark.r, shark.c);

		// 물고기 리스트 복사
		Fish[] tmpFishes = new Fish[17];
		for (int i = 1; i <= 16; i++) {
			Fish f = fishes[i];
			if (f != null)
				tmpFishes[i] = new Fish(f.d, f.r, f.c);
		}

		// 이제 상어 이동
//		for (int i = 1; i <= 3; i++) {
//			int sr = tmpShark.r + i * dr[tmpShark.d];
//			int sc = tmpShark.c + i * dc[tmpShark.d];
////				System.out.println("상어 이동" + i);
//			if (0 <= sr && sr < 4 && 0 <= sc && sc < 4) {
//				// 이동하려는 곳에 물고기가 없으면 이동 못 함
//				if (tmpWater[sr][sc] == 0)
//					continue;
//
//				// 범위 안에 들어오고
//				// 물고기가 있으면
//				Fish f = tmpFishes[tmpWater[sr][sc]]; // 먹을 물고기
//				// 상어의 원래 상태 저장해두기
//				Fish copyShark = new Fish(tmpShark.num, tmpShark.d, tmpShark.r, tmpShark.c);
//
//				tmpFishes[tmpWater[sr][sc]] = null; // 물고기 리스트에서 지우고
//				tmpWater[tmpShark.r][tmpShark.c] = null; // 원래 상어 위치 null
//				tmpShark = new Fish(-1, f.d, sr, sc);
////					tmpShark.r = sr;
////					tmpShark.c = sc; // 상어 이동
////					tmpShark.d = f.d; // 상어의 방향을 먹은 물고기의 방향으로
//				tmpWater[sr][sc] = new Fish(tmpShark.num, tmpShark.d, tmpShark.r, tmpShark.c);
//
//				move(tmpWater, tmpShark, tmpFishes, sum + f.num);
//
//				tmpShark = new Fish(copyShark.num, copyShark.d, copyShark.r, copyShark.c);
//				tmpWater[tmpShark.r][tmpShark.c] = new Fish(copyShark.num, copyShark.d, copyShark.r, copyShark.c);
//				tmpWater[sr][sc] = new Fish(f.num, f.d, f.r, f.c);
//				tmpFishes.add(f);
//
//				Collections.sort(tmpFishes);
////				for (Fish fs : tmpFishes) {
////					System.out.println(fs);
////				}
//
//			} else
//				break;
//		}
		// System.out.println("result : " + result);
		result = Math.max(result, sum); // 상어가 이동할 곳이 없으면
	}

}