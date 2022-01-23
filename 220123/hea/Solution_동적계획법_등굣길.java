package programmers;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Solution_동적계획법_등굣길 {
	static int[] dr = { 0, 1 }; // 오른쪽, 아래
	static int[] dc = { 1, 0 };

	static final int MAX = Integer.MAX_VALUE;
	static int MOD = 1000000007;

	public static void main(String[] args) {
//		int[] result = solution(  );
//		System.out.println(Arrays.toString(result)); 
		int[][] test = new int[][] { { 2, 2 } };
		System.out.println(solution(4, 3, test));
	}

	static int solution(int m, int n, int[][] puddles) {
		int answer = 0;

		int min_distance = m + n - 2;

		int[][] map = new int[n][m];
		int[][] min = new int[n][m];
		for (int[] is : min) {
			Arrays.fill(is, MAX);
		}
		for (int[] pud : puddles) {
			map[pud[1] - 1][pud[0] - 1] = -1; // 물 웅덩이
		}
		min_Bfs(n, m, map, min); // 각 위치까지의 최소 거리

		answer = count_Bfs(n, m, map, min, min_distance);

		return answer;
	}

	// 각 위치까지의 최소 거리 저장하기
	static void min_Bfs(int N, int M, int[][] map, int[][] min) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] { 0, 0, 0 });

		while (!dq.isEmpty()) {
			int[] cur = dq.poll();

			for (int d = 0; d < 2; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == -1) continue;
				if (min[nr][nc] > cur[2] + 1) {
					min[nr][nc] = cur[2] + 1;
				}
				dq.add(new int[] { nr, nc, cur[2] + 1 });
			}
		}

//		for (int[] is : min) {
//			System.out.println(Arrays.toString(is));
//		}
	}

	// 출발지에서 최소 거리 구하기
	static int count_Bfs(int N, int M, int[][] map, int[][] min, int min_distance) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] { 0, 0, 0 });

		int cnt = 0;
		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			if (cur[0] == N - 1 && cur[1] == M - 1) {
				cnt++;
				cnt = cnt % MOD;
				continue;
			}
			for (int d = 0; d < 2; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == -1)
					continue;
				if (cur[2] + 1 > min_distance)
					continue;

				// 현재 이동 경로가 해당 위치까지 오는 최소 거리보다 길면
				if (cur[2] + 1 > min[nr][nc]) continue;

				dq.add(new int[] { nr, nc, cur[2] + 1 });
			}
		}
//		System.out.println(cnt);
		return cnt;
	}
}