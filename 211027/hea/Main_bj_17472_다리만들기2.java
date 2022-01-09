package studyAlgo;

import java.io.*;
import java.util.*;

public class Main_bj_17472_다리만들기2 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] map;
	static int[] parents; // 부모원소를 관리(트리처럼 사용)

	static class Bridge implements Comparable<Bridge> {
		int from, to, len;

		public Bridge(int from, int to, int len) {
			super();
			this.from = from;
			this.to = to;
			this.len = len;
		}

		@Override
		public int compareTo(Bridge o) {
			return Integer.compare(this.len, o.len);
		}
		@Override
		public String toString() {
			return "Bridge [from=" + from + ", to=" + to + ", len=" + len + "]";
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		
		// bfs로 섬(구역) 구분
		ArrayDeque<int[]> q = new ArrayDeque<>();
		boolean[][] v = new boolean[N][M];
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 1 && !v[r][c]) {
					cnt++;
					v[r][c] = true;
					map[r][c] = cnt;
					q.offer(new int[] { r, c });
					while (!q.isEmpty()) {
						int[] cur = q.poll();

						for (int k = 0; k < 4; k++) {
							int nr = cur[0] + dr[k];
							int nc = cur[1] + dc[k];
							if (0 <= nr && nr < N && 0 <= nc && nc < M && !v[nr][nc] && map[nr][nc] == 1) {
								q.offer(new int[] { nr, nc });
								v[nr][nc] = true;
								map[nr][nc] = cnt;
							}
						}
					}
				}
			}
		}
		System.out.println("구역 지정");
		for (int[] m : map) {
			System.out.println(Arrays.toString(m));
		}
		System.out.println();

		
		PriorityQueue<Bridge> bridges = new PriorityQueue<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] > 0) {
					int num = map[r][c];
					next: for (int k = 0; k < 4; k++) {
						int nr = r;
						int nc = c;
						int len = 0;
						while (true) {
							nr += dr[k];
							nc += dc[k];

							if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue next;

							// 범위 안에 들어오면
							if (map[nr][nc] == num)
								continue next; // 현재 섬과 만나면
							if (map[nr][nc] == 0) {
								len++;
								continue;
							}
							
							if (map[nr][nc] > 0) {
								if (len > 1) {
									bridges.offer(new Bridge(num, map[nr][nc], len));
								}
								continue next;
							}
						}
					}

				}
			}
		}
		
		// 크루스칼 //
		parents = new int[cnt + 1];
		// 모든 원소를 자신을 대표자로 만듦
		for (int i = 1; i <= cnt; i++) {
			parents[i] = i;
		}

		int bridgeNum = 0, result = 0;
		int size = bridges.size();
		for (int i = 0; i < size; i++) {
			Bridge bridge = bridges.poll();
			if (union(bridge.from, bridge.to)) {
				result += bridge.len;
				bridgeNum++;
			}
		}
		
		if (result == 0 || bridgeNum != cnt - 1) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
		br.close();
	}

	// a가 속한 집합의 대표자 찾기
	private static int find(int a) {
		if (a == parents[a])
			return a; // 자신이 대표자
		return parents[a] = find(parents[a]); // 자신이 속한 집합의 대표자를 자신의 부모로n
	}

	// 두 원소를 하나의 집합으로 합치기(대표자를 이용해서 합침)
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false; // 이미 같은 집합으로 합치지 않음

		parents[bRoot] = aRoot;
		return true;
	}
}