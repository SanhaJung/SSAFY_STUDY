package a0903;

import java.io.*;
import java.util.*;

public class Main_bj_3190_뱀_서울_12반_허은아 {
	// 사과를 먹으면 뱀 길이 늘어남
	// 벽 또는 자기 자신의 몸과 부딪히면 게임 끝
	// 상하좌우 끝 : 벽
	// 뱀 시작 위치 : 0, 0 / 길이 : 1
	// 처음에 오른쪽을 향한다.
	// 몸 길이를 늘려 다음 칸테 위치 시킴,
	// 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리 그대로
	// 사과 없으면 몸길이 줄여서 꼬리 칸 비워둠
	// 몇 초에 끝나는지 계산
	static int currD = 1; // 처음 시작방향 오른쪽
	static int[] dx = { -1, 0, 1, 0 }; // 상우하좌 바라보고 있는 방향
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); // N*N 보드판
		int K = Integer.parseInt(br.readLine()); // K개의 사과

		boolean[][] board = new boolean[N][N]; // 보드판

		// K개 사과 입력
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			board[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
		}

		int time = 0;
		List<int[]> snake = new ArrayList<int[]>(); // 뱀 초기화
		snake.add(new int[] { 0, 0 });

		int L = Integer.parseInt(br.readLine());
		int bx = 0;
		String dir="";
		end: for (int i = 0; i <= L; i++) {
			int x = 10000;
			if (i < L) {
				st = new StringTokenizer(br.readLine(), " ");
				x = Integer.parseInt(st.nextToken()); // x초 후 방향 바꿀 것
				 dir = st.nextToken(); // 방향
			} else {
				bx = 0;
			}

			int cnt = 0;
			here: while (cnt++ < x - bx) {
				time++;
				// 바라보고 있는 방향으로 전진
				int[] cur = snake.get(snake.size() - 1); // 현재 뱀의 머리 위치
				int nx = cur[0] + dx[currD];
				int ny = cur[1] + dy[currD];
				if (0 <= nx && nx < N && 0 <= ny && ny < N && checkNoHit(nx, ny, snake)) { // 범위 안에 들어오고 몸에 안 부딪히면
					snake.add(new int[] { nx, ny }); // 머리 다음 칸에 넣기
					if (!board[nx][ny]) { // 사과가 없다면
						snake.remove(0); // 꼬리 없애기
					} else { // 사과가 있다면
						board[nx][ny] = false; // 사과 먹기
					}
				} else { // 범위를 벗어나면 끝내기
					break end;
				}
			}
			bx = x;

			selectDirection(dir);

		}
		System.out.println(time);
	}

	static int selectDirection(String d) {
		if (d.equals("L")) { // 왼쪽으로 꺾을 거면
			currD -= 1;
		} else if (d.equals("D")) { // 오른쪽으로 꺾을 거면
			currD += 1;
		}

		if (currD == -1) {
			currD = 3;
		} else if (currD == 4) {
			currD = 0;
		}
		return currD;
	}

	static boolean checkNoHit(int nx, int ny, List<int[]> snake) {// 뱀 몸이랑 부딪히는지 확인
		for (int i = 0; i < snake.size(); i++) {
			int[] s = snake.get(i);
			if (nx == s[0] && ny == s[1])
				return false;
		}
		return true; // 안 부딪힘
	}
}