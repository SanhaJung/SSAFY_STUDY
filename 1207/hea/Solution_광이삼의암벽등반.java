package swTestSample;

import java.io.*;
import java.util.*;

public class Solution_광이삼의암벽등반 {

	static int M, N, L, result;
	static int[][] map;
	static int[] user;

	static List<Ring> rings;
	static class Ring {
		int r, c;

		public Ring(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken()); // 행
			N = Integer.parseInt(st.nextToken()); // 열
			L = Integer.parseInt(st.nextToken()); // 거리

			map = new int[M][N];
			rings = new ArrayList<>();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						rings.add(new Ring(i, j));
					}
				}
			}

			result = Integer.MAX_VALUE;
			// 출발지점 M-1,0 도착지점 0,N-1
			dfs(rings, 0, M - 1, 0);

			if (result == Integer.MAX_VALUE)
				result = -1;
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

	static void dfs(List<Ring> list, int cnt, int i, int j) {
		// 현재 위치와 도착점의 거리가 L 이하이면 종료
		if (i + Math.abs(N - 1 - j) <= L) {
			result = Math.min(cnt, result);
			return;
		}

		for (int k = 0; k < list.size(); k++) {
			Ring ring = list.get(k);
			if (Math.abs(ring.r - i) + Math.abs(ring.c - j) <= L) {
				List<Ring> temp = new ArrayList<>();
				for (Ring r : list) {
					if (r != ring)
						temp.add(r);
				}
				dfs(temp, cnt + 1, ring.r, ring.c);
			}
		}
	}

}