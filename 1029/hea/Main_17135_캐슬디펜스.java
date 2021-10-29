package studyAlgo;

import java.io.*;
import java.util.*;

public class Main_17135_캐슬디펜스 {
	static int[][] map;
	static int N, M, D, result;
	static int[] pick;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		pick = new int[3];
		result = Integer.MIN_VALUE;
		comb(0, 0);

		System.out.println(result);
		br.close();
	}

	static void comb(int cnt, int start) {
		if (cnt == 3) {
			
			// 조합 경우 별로 확인해야하니까 맵 복사
			int[][] copy_map = new int[N][M];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					copy_map[r][c] = map[r][c];
				}
			}
			
			// 뽑은 궁수들의 위치
			int[][] archers = { { N, pick[0] }, { N, pick[1] }, { N, pick[2] } };
			int count = 0; // 현재 경우에 처치하는 적의 수 
			while (true) {
				// 궁수 세 명 쏘기
				int[][] shoot = { { -1, -1 }, { -1, -1 }, { -1, -1 } };
				for (int n = 0; n < 3; n++) {
					int[] archer = archers[n]; 
					int mind = Integer.MAX_VALUE; // 최소 거리
					
					for (int r = archer[0] - 1; r >= 0; r--) { // 궁수의 윗행부터
						for (int c = 0; c < M; c++) {
							if (copy_map[r][c] == 1) { // 적이면
								int distance = Math.abs(r - archer[0]) + Math.abs(c - archer[1]); // 거리 계산
								if (distance > D) continue;
								if (distance < mind) {
									shoot[n][0] = r;
									shoot[n][1] = c;
									mind = distance;
								} else if (distance == mind && c < shoot[n][1]) { // 최소거리가 같은데 왼쪽에 위치
									shoot[n][0] = r;
									shoot[n][1] = c;
								}
							}
						}
					}
					archer[0]--;
				}
				
				//적 쏘기
				for (int i = 0; i < 3; i++) {
					int[] sh = shoot[i];
					if (sh[0] != -1 && copy_map[sh[0]][sh[1]] == 1) {
						count++;
						copy_map[sh[0]][sh[1]] = 0;
					}
				}
				if (archers[0][0] == 0) break; // 더 이상 볼 곳이 없으므로 종료
			}
			result = Math.max(result, count);
			return;
		}

		for (int i = start; i < M; i++) {
			pick[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}
}