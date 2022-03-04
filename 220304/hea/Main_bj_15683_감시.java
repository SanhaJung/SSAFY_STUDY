package baekjoon;

import java.io.*;
import java.util.*;

public class Main_bj_15683_감시 {
	static int N, M;
	static List<CCTV> cctvList;
	static int minResult;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class CCTV {
		int r, c, num;

		public CCTV(int r, int c, int num) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] office = new int[N][M];
		cctvList = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				office[r][c] = Integer.parseInt(st.nextToken());
				if (office[r][c] > 0 && office[r][c] < 6)
					cctvList.add(new CCTV(r, c, office[r][c]));
			}
		}

		minResult = Integer.MAX_VALUE;
		monitor(0, office);
		System.out.println(minResult);

		br.close();
	}

	private static void monitor(int n, int[][] office) {
		// 모든 cctv 확인이 끝나면
		if (n == cctvList.size()) {
			int cnt = 0;
			
			// 사각지대 크기 측정
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (office[r][c] == 0) cnt++; 
				}
			}
			
			// 결과 갱신
			minResult = Math.min(cnt, minResult);  
			return;
		}

		// 현재 CCTV
		CCTV cctv = cctvList.get(n);
		// 5번 CCTV는 네 방향 모두 확인하므로
		if (cctv.num == 5) {
			// 사무실 복사
			int[][] tempOffice = copyOffice(office);
			for (int d = 0; d < 4; d++) {
				check(cctv.r, cctv.c, d, tempOffice);
			}
			monitor(n + 1, tempOffice);
		}

		int cnt = (cctv.num == 2 ? 2 : 4); // cctv 2번만 2방향
		for (int d = 0; d < cnt; d++) {
			// 사무실 복사
			int[][] tempOffice = copyOffice(office);
			num(cctv.num, cctv.r, cctv.c, d, tempOffice);
			monitor(n + 1, tempOffice);
		}

	}

	// DFS를 사용하기 때문에 맵을 복사해서 매개변수로 넣어줘야 함.
	// 맵 복사 함수
	private static int[][] copyOffice(int[][] office) {
		int[][] copy = new int[N][M];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				copy[r][c] = office[r][c];
			}
		}

		return copy;
	}

	// cctv 감시 영역 체크
	private static void check(int r, int c, int d, int[][] office) {
		// 해당 방향으로 진행하면서 감시할 수 있는 영역인지 확인
		while (true) {
			r += dr[d];
			c += dc[d];
			if (0 <= r && r < N && 0 <= c && c < M) {
				if (office[r][c] == 6) break; 						// 벽이면 통과 못 함
				if (office[r][c] == 0) office[r][c] = -1;			// 빈 곳이면 감시@
				if(office[r][c] > 0 && office[r][c] < 6) continue;  // cctv 통과 가능
			} else break;
		}
	}

	// CCTV 종류에 따른 감시 방향, 영역
	private static void num(int n, int r, int c, int d, int[][] office) {
		switch (n) {
		
		// →
		case 1: {
			check(r, c, d, office);
			break;
		}

		// ← → 
		case 2: {
			if (d == 0) {
				check(r, c, d, office);
				check(r, c, 2 - d, office);
			} else if (d == 1) {
				check(r, c, d, office);
				check(r, c, 4 - d, office);
			}
			break;
		}
		
		// ↑ → 
		case 3: {
			check(r, c, d, office);
			check(r, c, (d + 1) % 4, office);
			break;
		}

		// ← ↑ →
		case 4: {
			check(r, c, d, office);
			check(r, c, (d + 1) % 4, office);
			check(r, c, (d + 2) % 4, office);
			break;
		}

		default:
			break;
		}
	}
}
