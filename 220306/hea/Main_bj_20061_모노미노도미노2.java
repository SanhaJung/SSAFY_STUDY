package baekjoon;

import java.io.*;
import java.util.*;

public class Main_bj_20061_모노미노도미노2 {
	static int dr[] = { 0, 0, -1, 1 };
	static int dc[] = { 1, -1, 0, 0 };
	static int N, score;
	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new boolean[10][10];
		int N = Integer.parseInt(br.readLine());
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			// 블록 내리기
			downGreen(t, x, y); // 초록색 영역
			downBlue(t, x, y); // 파란색 영역

			// 점수 획득
			getScoreGreen();
			getScroeBlue();
			

			// 특별한 칸
			checkSpecialGreen();
			checkSpecialBlue();
		}
		System.out.println(score);
		System.out.println(countTile());
		br.close();
	}

	// 남은 타일 수 세기
	private static int countTile() {
		int cnt = 0;
		for (int r = 4; r < 10; r++) {
			for (int c = 0; c < 4; c++) {
				if (map[r][c]) cnt++;  // 초록색 영역
				if (map[c][r]) cnt++;  // 파란색 영역
			}
		}
		return cnt;
	}

	private static void checkSpecialGreen() {
		int cnt = 0; // 특별한 행 개수
		next: for (int r = 4; r <= 5; r++) {
			for (int c = 0; c < 4; c++) {
				if (map[r][c] == true) {
					cnt++;
					continue next;
				}
			}
		}
		if(cnt == 0) return;

		// 이동
		for (int r = 9; r > 5; r--) {
			for (int c = 0; c < 4; c++) {
				map[r][c] = map[r - cnt][c];
			}
			Arrays.fill(map[r-cnt], false);
		}

	}

	private static void checkSpecialBlue() {
		int cnt = 0; // 특별한 열 개수
		next: for (int c = 4; c <= 5; c++) {
			for (int r = 0; r < 4; r++) {
				if (map[r][c] == true) {
					cnt++;
					continue next;
				}
			}
		}
		if(cnt == 0) return;

		// 이동
		for (int c = 9; c > 5; c--) {
			for (int r = 0; r < 4; r++) {
				map[r][c] = map[r][c - cnt]; 
				map[r][c - cnt] = false;
			}
			
		}
	}

	private static void getScoreGreen() {
		ArrayList<Integer> remainRow = new ArrayList<>(Arrays.asList(9, 8, 7, 6, 5, 4));
		// 초록색 영역 : 4행~9행
		next: for (int r = 4; r < 10; r++) {
			for (int c = 0; c < 4; c++) {
				if (map[r][c] != true) continue next;
			}
			// 한 행이 꽉 찼을 경우
			score++; // 1점 획득
			remainRow.remove((Integer) r); // 행 삭제
			Arrays.fill(map[r], false); // 행 초기화
		}
		
		if(remainRow.size() == 6) return;
		int n = 9;
		for (Integer r : remainRow) {
			if (n == r) {
				n--;
				continue;
			}
			for (int c = 0; c < 4; c++) {
				map[n][c] = map[r][c];
			}
			Arrays.fill(map[r], false);
			n--;
		}
	}

	private static void getScroeBlue() {
		ArrayList<Integer> remainCol =  new ArrayList<>(Arrays.asList(9, 8, 7, 6, 5, 4));
		// 파란색 영역 : 4열~9열
		next: for (int c = 4; c < 10; c++) {
			for (int r = 0; r < 4; r++) {
				if (map[r][c] != true)
					continue next;
			}
			// 한 행이 꽉 찼을 경우
			score++; // 1점 획득
			remainCol.remove((Integer) c); // 삭제 행에 추가
			for (int k = 0; k < 4; k++) {
				map[k][c] = false;
			}
		}
		if(remainCol.size() == 6) return;
		
		int n = 9;
		for (Integer c : remainCol) {
			if (n == c) {
				n--;
				continue;
			}
			for (int r = 0; r < 4; r++) {
				map[r][n] = map[r][c];
				map[r][c] = false;
			}
			n--;
		}
	}

	private static int[][] selectBlock(int t, int x, int y) {
		int[][] block = {};
		// 블록
		if (t == 1) {
			block = new int[][] { { x, y } };
		} else if (t == 2) {
			block = new int[][] { { x, y }, { x, y + 1 } };
		} else if (t == 3) {
			block = new int[][] { { x, y }, { x + 1, y } };
		}
		return block;
	}

	private static void downGreen(int t, int x, int y) {
		int[][] block = selectBlock(t, x, y);
		end: while (true) {
			boolean flag = true;
			for (int i = 0; i < block.length; i++) {
				int[] b = block[i];
				if (b[0] + 1 < 0 || b[0] + 1 >= 10 || map[b[0] + 1][b[1]]) {
					flag = false; // 전진 불가능
					break end;
				}
			}
			// 블록 이동
			if (flag) {
				for (int i = 0; i < block.length; i++) {
					block[i][0]++;
				}
			}
		}
		for (int i = 0; i < block.length; i++) {
			map[block[i][0]][block[i][1]] = true;
		}
	}

	private static void downBlue(int t, int x, int y) {
		int[][] block = selectBlock(t, x, y);
		end: while (true) {
			boolean flag = true;
			for (int i = 0; i < block.length; i++) {
				int[] b = block[i];
				if (b[1] + 1 < 0 || b[1] + 1 >= 10 || map[b[0]][b[1] + 1]) {
					flag = false; // 전진 불가능
					break end;
				}
			}
			if (flag) {
				for (int i = 0; i < block.length; i++) {
					block[i][1]++;
				}
			}
		}
		for (int i = 0; i < block.length; i++) {
			map[block[i][0]][block[i][1]] = true;
		}
	}
}
