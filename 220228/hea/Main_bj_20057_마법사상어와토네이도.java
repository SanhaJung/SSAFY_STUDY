package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class Main_bj_20057_마법사상어와토네이도 {
	static int[][] map;
	static int N;

	// 토네이도 이동
	static int[] dr = { 0, 1, 0, -1 }; // 좌하우상
	static int[] dc = { -1, 0, 1, 0 }; // 좌하우상

	static int[][] tr = { { -1, -1, -1, 1, 1, 1, -2, 2, 0 },
				   { -1, 0, 1, -1, 0, 1, 0, 0, 2 },
				   { -1, -1, -1, 1, 1, 1, -2, 2, 0 },
				   { 1, 0, -1, 1, 0, -1, 0, 0, -2 } };
	static int[][] tc = { { 1, 0, -1, 1, 0, -1, 0, 0, -2 },
				   { -1, -1, -1, 1, 1, 1, -2, 2, 0 },
				   { -1, 0, 1, -1, 0, 1, 0, 0, 2 },
				   { -1, -1, -1, 1, 1, 1, -2, 2, 0 } };

	static int[] percent = { 1, 7, 10, 1, 7, 10, 2, 2, 5 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 맵 크기, 홀수

		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int sandAmount = 0;

		// 가운데는 (N/2, N/2)
		// 토네이도 위치
		int sr = N / 2;
		int sc = N / 2;
		
		int cnt = 1; // 반복횟수
		int d = 0;   // 토네이도 이동 방향
		end: while (true) {
			for (int n = 0; n < 2; n++) { // 두 번씩 반복 
				for (int k = 0; k < cnt; k++) {
					// 토네이도 이동
					sr += dr[d];
					sc += dc[d];
					
					if(sr == 0 && sc == -1) break end;
					
					if(map[sr][sc] == 0) continue; // 옮길 모래 없으면 다음
					
					int sand = map[sr][sc];
					for (int t = 0; t < 9; t++) {
						// 모래 이동 위치
						int r = sr + tr[d][t];
						int c = sc + tc[d][t];
						
						// 이동할 모래 양
						int outSand = sand * percent[t] / 100;

						map[sr][sc] -= outSand; // 현재 위치에서 모래 빼주고
						if(r < 0 || r >= N || c < 0 || c >= N) {
							sandAmount += outSand;
							continue;
						}
						map[r][c] += outSand; // 이동 위치에 모래 더하기
					}
					// alpha
					int alpahR = sr + dr[d];
					int alpahC = sc + dc[d];
					if(alpahR < 0 || alpahR >= N || alpahC < 0 || alpahC >= N) {
						sandAmount += map[sr][sc];
					}else {
						map[alpahR][alpahC] += map[sr][sc];
					}
					map[sr][sc] = 0;
				}
				//방향 바꾸기
				d = (d + 1) % 4;
			}
			cnt++;
		}
		System.out.println(sandAmount);
	}
}
