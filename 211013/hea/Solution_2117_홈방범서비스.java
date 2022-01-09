package swTestSample;

import java.io.*;
import java.util.*;

public class Solution_2117_홈방범서비스 {
	// 운영 비용 = K * K + (K - 1) * (K - 1)
	// 집 하나당 수익 M

	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };

	static boolean[][] map;
	static int N, M, homeCnt, result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // N*N Map
			M = Integer.parseInt(st.nextToken()); // 하나의 집이 지불할 수 있는 비용 M

			map = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					int n = Integer.parseInt(st.nextToken());
					if (n == 1) {
						map[i][j] = true;
						homeCnt++; // 초기 집의 개수
					}
				}
			}
			result = 1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(map[i][j] ? 1 : 0, i, j);
				}
			}
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
//			if (homeCnt == 1) { // 집이 한 개면 답은 1
//				result = 1;
//			} else {
//				result = Integer.MIN_VALUE;
//				for (int i = 0; i < N; i++) {
//					for (int j = 0; j < N; j++) {
//						bfs(map[i][j] ? 1 : 0, i, j);
//					}
//				}
//			}
//			// 돌고 나서 1개인 경우가 최대이면 갱신이 안 됨
//			sb.append("#").append(test_case).append(" ").append(result == Integer.MIN_VALUE ? 1 : result).append("\n");
		}
		System.out.println(sb);
	}

	static void bfs(int cnt, int i, int j) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { i, j });

		int K = 1;
		boolean[][] v = new boolean[N][N];
		v[i][j] = true;

		while (!q.isEmpty()) {
			int size = q.size();
			here: for (int s = 0; s < size; s++) {
				int[] cur = q.poll();
				for (int k = 0; k < 4; k++) {
					int nr = cur[0] + dr[k];
					int nc = cur[1] + dc[k];
					if (0 <= nr && nr < N && 0 <= nc && nc < N && !v[nr][nc]) {
						v[nr][nc] = true;
						q.offer(new int[] { nr, nc });
						if (map[nr][nc])
							cnt++;
						if (cnt == homeCnt) {
							q.clear();
							break here;
						}
					}
				}
			}
			K++;
			if (cnt * M >= K * K + (K - 1) * (K - 1) && result < cnt) { // 손해를 안 보고, 집의 개수가 더 많으면 갱신
				result = cnt;
			}
		}
	}

}