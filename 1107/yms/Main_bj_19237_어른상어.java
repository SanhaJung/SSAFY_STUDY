package bj19237;

import java.io.*;
import java.util.*;

public class Main_bj_19237_어른상어 {

	static int N, M, k, answer;
	static int[][] time;					// 냄새가 없어지기까지 남은 시간
	static int[][] smell;						// 냄새를 뿌린 상어의 번호
	static int[][][] priority;					// 각 상어마다 입력받은 우선순위
	static Shark[] shark;
	static int[] dr = {0,-1,1,0,0}, dc = {0,0,0,-1,1}; 	// 1 : 위, 2 : 아래, 3 : 왼쪽, 4 : 오른쪽
	
	static class Shark {
		int r, c, d;							// 행, 열 , 방향
		Shark(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	
	static void solve() {
		while (true) {
			int count = 0;						// 상어의 숫자
			for (int i = 1; i <= M; i++) {
				if (shark[i] != null)
					count++;
			}
			if (count == 1 && shark[1] != null) { // 1번 상어 혼자 남은 경우
				return;
			}
			if (answer >= 1000) {					  // 시간이 1000초가 넘어가면 -1
				answer = -1;
				return;
			}

			int[][] temp = new int[N+1][N+1];

			for (int i = 1; i <= M; i++) {
				if (shark[i] != null) { 		  // 상어가 경계 안에 있다면
					move(temp, i);
				}
			}
			
			for (int i = 1; i <= N; i++) {		  // 냄새 유효시간 조정
				for (int j = 1; j <= N; j++) {	
					if (time[i][j] > 0)
						time[i][j]--;

					if (time[i][j] == 0)
						smell[i][j] = 0;
				}
			}
			
			for (int i = 1; i <= N; i++) {		  // 새로 이동한 구역에 냄새와 잔여시간 세팅
				for (int j = 1; j <= N; j++) {
					if (temp[i][j] > 0) {
						time[i][j] = k;
						smell[i][j] = temp[i][j];
					}
				}
			}
			answer++;
		}

	}

	static void move(int[][] temp, int num) {
		int nr = 0;
		int nc = 0;
		int d = 0;

		boolean flag = false;						// 냄새가 없는 구역으로 이동했음을 체크해주는 변수

		for (int i = 0; i < 4; i++) {				// 입력받은 우선순위 순서대로 검사
			d = priority[num][shark[num].d][i];
			nr = shark[num].r + dr[d];
			nc = shark[num].c + dc[d];
			if (0<nr && nr<=N && 0<nc && nc<=N && smell[nr][nc] == 0) {	// 인덱스 범위 안이고 해당 위치에 아무런 냄새가 없다면
				flag = true;
				break;
			}
		}
		if (!flag) {								// 주변 모든 구역에 냄새가 뿌려져 있다면
			for (int i = 0; i < 4; i++) {
				d = priority[num][shark[num].d][i];
				nr = shark[num].r + dr[d];
				nc = shark[num].c + dc[d];

				if (0<nr && nr<=N && 0<nc && nc<=N && smell[nr][nc] == num)	// 인덱스 범위 안이고 해당 위치가 자신의 냄새라면
					break;
			}
		}
		
		if (temp[nr][nc] == 0) {	// 이동할 곳이 빈곳이라면 이동
			temp[nr][nc] = num;
			shark[num].r = nr;
			shark[num].c = nc;
			shark[num].d = d;
		}
		else {						// 빈 곳이 아니라면 상어를 내보냄 (작은 번호 상어 부터 움직였으므로 나중에 오는 상어는 무조건 퇴출)
			shark[num] = null;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		time = new int[N + 1][N + 1];		// 잔여 시간 배열
		smell = new int[N + 1][N + 1];		// 냄새를 뿌린 상어의 번호 배열
		priority = new int[M + 1][5][4];	// 상어의 현재 방향에 따른 우선순위 입력
		shark = new Shark[M + 1];			// 각 상어의 정보

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 1; j <= N; j++) {
				int n = Integer.parseInt(st.nextToken());

				if (n > 0) {
					shark[n] = new Shark(i, j, 0);
					time[i][j] = k;
					smell[i][j] = n;
				}
			}
		}
		st = new StringTokenizer(in.readLine());
		for (int i = 1; i <= M; i++)
			shark[i].d = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(in.readLine());
				for (int k = 0; k < 4; k++) {
					priority[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		solve();
		
		System.out.println(answer);
	}
}