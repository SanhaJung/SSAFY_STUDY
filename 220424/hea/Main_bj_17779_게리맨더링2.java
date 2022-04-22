package baekjoon;

import java.io.*;
import java.util.*;

public class Main_bj_17779_게리맨더링2 {
	static int N, map[][];
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int totalSum = 0;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				totalSum += map[r][c];
			}
		}

		result = Integer.MAX_VALUE;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				for (int d1 = 1; d1 <= N; d1++) {
					for (int d2 = 1; d2 < N; d2++) {
						if (x + d1 + d2 >= N) continue;
						if (y - d1 < 0 || y + d2 >= N) continue;

						devide(x, y, d1, d2, totalSum);
					}
				}
			}
		}
		br.close();
		System.out.println(result);
	}

	private static void devide(int x, int y, int d1, int d2, int totalSum) {
		boolean[][] boundary = new boolean[N][N];
		
		// 경계선
		boundary[x][y] = true;
		for (int i = 1; i <= d1; i++) {
			for (int j = 1; j <= d2; j++) {
				boundary[x + i][y - i] = true;
				boundary[x + j][y + j] = true;
				
				boundary[x + d1 + j][y - d1 + j] = true;
				boundary[x + d2 + i][y + d2 - i] = true;
			}
		}
		
		// 선거구 별 인구 수
		int[] peopleSum = new int[5];

		// 1번 선거구
		for (int r = 0; r < x + d1; r++) {
			for (int c = 0; c <= y; c++) {
				if(boundary[r][c]) break;
				peopleSum[0] += map[r][c];
			}
		}

		// 2번 선거구
		for (int r = 0; r <= x + d2; r++) {
			for (int c = N - 1; c >= y + 1; c--) {
				if(boundary[r][c]) break;
				peopleSum[1] += map[r][c];
			}
		}

		// 3번 선거구
		for (int r = x + d1; r < N; r++) {
			for (int c = 0; c < y - d1 + d2; c++) {
				if(boundary[r][c]) break;
				peopleSum[2] += map[r][c];
			}
		}
		
		// 4번 선거구
		for (int r = x + d2 + 1; r < N; r++) {
			for (int c = N - 1 ; c >= y - d1 + d2; c--) {
				if(boundary[r][c]) break;
				peopleSum[3] += map[r][c];
			}
		}
		
		peopleSum[4] = totalSum - (peopleSum[0] + peopleSum[1] + peopleSum[2] + peopleSum[3]);
		Arrays.sort(peopleSum);
		result = Math.min(result, peopleSum[4] - peopleSum[0]);
	}
}