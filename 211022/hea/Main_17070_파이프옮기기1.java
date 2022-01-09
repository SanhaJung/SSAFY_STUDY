package studyAlgo;

import java.io.*;
import java.util.*;

public class Main_17070_파이프옮기기1 {
	static int[] dr = { 0, 1, 1 }; // 우 우하 하
	static int[] dc = { 1, 1, 0 };
	static int[][] map;
	static int N, result;
	static int[][] dir = { { 0, 1 }, { 0, 1, 2 }, { 1, 2 } }; // 현재 방향에 따른 이동할 수 있는 방향의 인덱스들

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];		
		// 맵 상태 0은 빈 칸, 1은 벽
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 1, 0);
		System.out.println(result);
	}

	static void dfs(int r, int c,  int d) {
		if (r == N - 1 && c == N - 1) {
			result++;
			return;
		}

		int[] nd = dir[d]; // 현재 방향에 따른 이동 가능한 방향들
		for (int n : nd) {
			int nr = r + dr[n];
			int nc = c + dc[n];

			boolean flag = true; // 이동 가능하면 true, 이동 불가능하면 false
			if (n == 1) {		 // 대각선을 향하고 있으면 - 우,우하,하 확인
				for (int i = 0; i < 3; i++) {
					int rr = r + dr[i];
					int cc = c + dc[i];
					if (rr < 0 || rr >= N || cc < 0 || cc >= N || map[rr][cc] == 1) {
						flag =false;
						break;
					}
				}
			} else {
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1) {
					flag =false;
				}
			}
			if(flag) {
				dfs(nr, nc, n);
			}
		}
	}
}