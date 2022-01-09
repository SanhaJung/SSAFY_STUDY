package a0928.hw;

import java.io.*;
import java.util.*;

public class Main_bj_14503_로봇청소기_서울_12반_허은아 {
	// 1. 현재 위치를 청소한다.
	// 2. 현재 방향을 기준으로 왼쪽 방향부터 차례대로 인접한 칸을 탐색한다.
	// a. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행
	// b. 왼쪽 방향에 청소할 공간이 없다면. 그 방향으로 회전하고 2번으로 돌아간다.
	// c. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
	// d. 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 뱡향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
	// 로봇 청소기는 이미 청소되어있는 칸을 또 청소하지 않으며, 벽을 통과할 수 없다.

	// d 값 = 0 : 북쪽, 1: 동쪽, 2 : 남쪽, 3 : 서쪽
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class Robot {
		int r, c, d;

		public Robot(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());			 // 세로 크기(행)
		int M = Integer.parseInt(st.nextToken());			 // 가로 크기(열)

		
		st = new StringTokenizer(br.readLine());			 // 로봇 청소기 첫 상태
		int r = Integer.parseInt(st.nextToken());			 // r행
		int c = Integer.parseInt(st.nextToken());			 // c열
		int d = Integer.parseInt(st.nextToken()); 			 // 방향 - 0123 북동남서
		Robot robot = new Robot(r, c, d);					// 로봇 첫 위치

		
		
		// 지도의 첫 행, 마지막 행, 첫 열, 마지막 열에 있는 모든 칸은 벽 = 1
		// 로봇 청소기가 있는 칸의 상태는 항상 빈 칸 = 0
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int cleancount = 0; 						// 청소한 영역 세기
		next: while (true) {						// 청소기가 이동할 곳이 없을 때까지 반복
			if (map[robot.r][robot.c] == 0) { 		// 청소하지 않은 곳이면
				map[robot.r][robot.c] = 2; 			// 청소 표시
				cleancount++; 						// count +1
			}

														
			for (int i = 1; i <= 4; i++) {			// 왼쪽부터 네 방향 보기
				robot.d = (robot.d + 3) % 4; 		// 회전
				int dir = robot.d;

				int nr = robot.r + dr[dir];
				int nc = robot.c + dc[dir];
				if (0 <= nr && nr < N && 0 <= nc && nc < M && map[nr][nc] == 0) { // 청소하지 않은 곳이면
					// 전진
					robot.r = nr;
					robot.c = nc;
					continue next;
				} else continue; 				    // 범위를 벗어나거나, 청소한 곳 = 2, 벽이면 = 1, 다음 방향 보기 
			}

			int sr = robot.r - dr[robot.d];					// 4방향 모두 청소할 곳이 없다면 후진
			int sc = robot.c - dc[robot.d];
			if (0 <= sr && sr < N && 0 <= sc && sc < M) {	// 범위 안에 들어오고
				if (map[sr][sc] != 1) {						// 벽이 아니면 (빈 칸, 청소한 곳)
					robot.r = sr;							// 후진
					robot.c = sc;
					continue;
				} else break;								// 벽이면 종료
			} else break;									// 범위 벗어나도 종료
		}
		System.out.println(cleancount);
	}
}
