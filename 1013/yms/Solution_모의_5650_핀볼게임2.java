package a5650;

import java.util.*;
import java.io.*;

public class Solution_모의_5650_핀볼게임2 {
	static int N, answer;
	static int[][] map;
	static Index[][] wormhole;
	static int[] dr = { 0, 1, 0, -1 };		// 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };

	static class Index {
		int x;
		int y;

		public Index(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void start(int x, int y, int d) { 
		int nx = x, ny = y, nd = d;
		int score = 0;
		
		while (true) {
			nx = nx + dr[nd];
			ny = ny + dc[nd];
			if (nx == x && ny == y || map[nx][ny] == -1)	// 시작점 or 블랙홀
				break;
			
			if (map[nx][ny] == 0)
				continue;
			
			else if (1 <= map[nx][ny] && map[nx][ny] <= 5) { // 벽 만남
				nd = changeDir(map[nx][ny], nd);
				score++;				
			} 
			else if (6 <= map[nx][ny] && map[nx][ny] <= 10) { // 웜홀 만남
				Index[] hole = wormhole[map[nx][ny] - 6];
				if (hole[0].x == nx && hole[0].y == ny) {
					nx = hole[1].x;
					ny = hole[1].y;
				} else {
					nx = hole[0].x;
					ny = hole[0].y;
				}
			}
		}
		
		answer = Math.max(answer, score);
	}

	static int changeDir(int wall, int d) { // 우 하 좌 상
		int nd = (d + 2) % 4; // 반대 방향
		switch (wall) {
			case 1:
				if (d == 1)
					nd = 0;
				if (d == 2)
					nd = 3;
				break;
			case 2:
				if (d == 2)
					nd = 1;
				if (d == 3)
					nd = 0;
				break;
			case 3:
				if (d == 0)
					nd = 1;
				if (d == 3)
					nd = 2;
				break;
			case 4:
				if (d == 1)
					nd = 2;
				if (d == 0)
					nd = 3;
				break;
		}
		return nd;
	}

	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/a5650/input_모의_5650.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(in.readLine());
			map = new int[N + 2][N + 2];
			wormhole = new Index[5][2];
			answer = Integer.MIN_VALUE/2;
			for (int i = 0; i < N + 2; i++) {
				if (i == 0 || i == N + 1) {
					for (int j = 0; j < N + 2; j++) {	// 맨 윗줄과 아랫줄을 5로 채움
						map[i][j] = 5;
					}
				} else {
					map[i][0] = 5;						// 행의 양 끝을 5로 채움
					map[i][N + 1] = 5;
					st = new StringTokenizer(in.readLine());
					for (int j = 1; j <= N; j++) {
						map[i][j] = Integer.parseInt(st.nextToken());
						if (6 <= map[i][j] && map[i][j] <= 10) {		// 웜홀일경우
							int idx = map[i][j] - 6;
							if (wormhole[idx][0] == null)				// 처음 입력 받으면
								wormhole[idx][0] = new Index(i, j);	// 0번 자리에
							else
								wormhole[idx][1] = new Index(i, j);	// 이미 입력받은 웜홀이 있다면 1번자리에
						}
					}
				}
			}
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][j] == 0) {
						for(int d=0; d<4; d++) {
							start(i, j, d);									// 모든 좌표에 대해 실행
						}
					}
				}
			}
			
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.println(sb.toString());
		in.close();
	}
}
	

	
