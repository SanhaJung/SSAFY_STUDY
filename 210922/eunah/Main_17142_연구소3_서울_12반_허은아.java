package as0922;

import java.io.*;
import java.util.*;

public class Main_17142_연구소3_서울_12반_허은아 {
	static int N, M;
	static int[][] lab; // 연구소
	static List<int[]> allVirus; // 초기 바이러스의 위치를 담을 리스트
	static int[] active;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int result = Integer.MAX_VALUE;
	static int emptyCnt = 0; // 초기 빈 칸의 개수

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 연구소의 크기
		M = Integer.parseInt(st.nextToken()); // 처음에 활성시킬 바이러스의 개수

		lab = new int[N][N]; // 연구소
		allVirus = new ArrayList<>(); // 모든 바이러스를 담아 둘 리스트
		// 0 : 빈 칸, 1 : 벽, 2 : 바이러스
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				if (lab[i][j] == 2) allVirus.add(new int[] { i, j });
				if (lab[i][j] == 0) emptyCnt++; // 초기 빈 칸의 개수
			}
		}
		if (emptyCnt == 0) {
			System.out.println(0);
		} else {
			active = new int[M];
			select(0, 0);
			result = (result == Integer.MAX_VALUE) ? -1 : result;
			System.out.println(result);
		}
		br.close();
	}

	// 활성시킬 바이러스를 뽑는 조합 함수
	static void select(int cnt, int start) {
		if (cnt == M) {
			int[][] lab_copy = new int[N][N];
			// 깊은 복사
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					lab_copy[i][j] = lab[i][j];
				}
			}

			ArrayDeque<int[]> q = new ArrayDeque<>();
			for (int i = 0; i < M; i++) {
				int[] virus = allVirus.get(active[i]);
				lab_copy[virus[0]][virus[1]] = 3; // 활성 상태 바이러스를 3으로 주자
				q.offer(virus);
			}
			int count = emptyCnt; // 조합 별로 확인해야 하기 때문에 매개변수로 넘겨주기
			bfs(lab_copy, q, count);
			return;
		}

		// 바이러스 뽑기
		for (int i = start; i < allVirus.size(); i++) {
			active[cnt] = i;
			select(cnt + 1, i + 1);
		}
	}

	static void bfs(int[][] lab, ArrayDeque<int[]> q, int count) {
		boolean[][] visited = new boolean[N][N];

		int time = 0;
		while (true) {
			List<int[]> temp = new ArrayList<>();
			end: while (!q.isEmpty()) {
				int[] virus = q.poll();
				for (int i = 0; i < 4; i++) { // 인접한 곳 탐색
					int nr = virus[0] + dr[i];
					int nc = virus[1] + dc[i];
					if (0 <= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc]
							&& (lab[nr][nc] == 0 || lab[nr][nc] == 2)) { // 범위 안에 들어오고
						if (lab[nr][nc] == 0) count--;
						if (count == 0) break end;
						
						visited[nr][nc] = true;
						lab[nr][nc] = 3;
						temp.add(new int[] { nr, nc });
					}
				}
			}
			time++;
			// 시간 줄이기, 백트래킹??
			if (count == 0) break; // 빈 칸이 없으면 끝
			if (temp.size() == 0) break; // 퍼뜨릴 바이러스가 없으면 끝
			if (result != Integer.MAX_VALUE && time > result) return; // 현재 최소값보다 커지면 그만

			for (int i = 0; i < temp.size(); i++) {
				q.add(temp.get(i));
			}
		}

		if (count != 0) return; // 빈 칸이 남은 경우
		result = Math.min(result, time);
	}
}