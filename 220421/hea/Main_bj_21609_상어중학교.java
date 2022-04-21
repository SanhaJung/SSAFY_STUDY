package baekjoon;

import java.io.*;
import java.util.*;

public class Main_bj_21609_상어중학교 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int N, M;
	static int[][] map;
	static boolean[][] groupCheck;
	static ArrayList<int[]> groupList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // N * N, 맵의 크기
		M = Integer.parseInt(st.nextToken()); // 색상의 개수

		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int result = 0;
		groupList = new ArrayList<>();
		while (true) {
			boolean[][] checked = new boolean[N][N];
			groupCheck = new boolean[N][N];
			
			// 가장 큰 블록 그룹 정보
			int[] group = { N, N, -1, -1 }; // r, c, groupSize, rainbowCount
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!checked[r][c] && map[r][c] >= 1) {
						findBlockGroup(group, checked, r, c, map[r][c]);
					}
				}
			}

			// 블록 그룹 찾음
			int size = groupList.size();
			
			// 그룹 크기가 1 이하이면 종료
			if (size <= 1) break;

			// 점수 획득
			result += size * size;
			
			// 블록 제거
			for (int[] block : groupList) {
				int r = block[0];
				int c = block[1];

				map[r][c] = -2; 
			}
			groupList.clear();

			// 중력
			gravity(map);

			// 회전
			map = rotate(map);

			// 중력
			gravity(map);

		}
		System.out.println(result);
		br.close();
	}

	// 반시계 방향으로 90도 회전
	private static int[][] rotate(int[][] map) {
		int[][] temp = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				temp[N - 1 - c][r] = map[r][c];
			}
		}
		return temp;
	}

	private static void gravity(int[][] map) {
		for (int c = 0; c < N; c++) {
			for (int r = N - 1; r >= 0; r--) {
				
				// 무지개블록, 색깔 블록이면 내리기
				if (map[r][c] >= 0) { 
					for (int i = r + 1; i <= N; i++) {
						if (i == N || map[i][c] >= -1) {
							map[i - 1][c] = map[r][c];
							if (r != i - 1) map[r][c] = -2;
							break;
						}
					}
				}
				
			}
		}
	}

	static void findBlockGroup(int[] group, boolean[][] checked, int r, int c, int color) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		ArrayList<int[]> blockList = new ArrayList<>();
		dq.add(new int[] { r, c });
		checked[r][c] = true;

		int size = 1;
		int rainbow = 0;
		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			blockList.add(cur);
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				
				// 맵 벗어나거나, 방문했거나, 빈칸, 검정블록이면
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || checked[nr][nc] || map[nr][nc] <= -1) continue;
				
				// 색깔이 다른 블록이면
				if (map[nr][nc] >= 1 && map[nr][nc] != color) continue;

				// 무지개 블록이거나 같은 색깔 블록이면
				// 무지개블록 개수 세주기
				if (map[nr][nc] == 0) rainbow++;
				size++;
				checked[nr][nc] = true;
				dq.add(new int[] { nr, nc });
			}
		}

		// 무지개블록 방문처리 해제
		for (int[] bl : blockList) {
			if (map[bl[0]][bl[1]] == 0) {
				checked[bl[0]][bl[1]] = false;
			}
		}

		// 블록 수가 같은데 무지개 블록 수가 더 많거나
		// 그룹 크기가 더 크면 갱신
		if ((size == group[2] && rainbow >= group[3]) || size > group[2]) {
			group[0] = r;
			group[1] = c;
			group[2] = size;
			group[3] = rainbow;

			groupList.clear();
			groupList = blockList;
		}
	}

}