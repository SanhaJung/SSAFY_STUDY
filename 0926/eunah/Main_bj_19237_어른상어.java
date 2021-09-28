package as0926;

import java.io.*;
import java.util.*;

public class Main_bj_19237_어른상어 {
	// 상어 내쫓기
	// 1번 상어가 가장 강력해서 나머지 모두를 쫓아낼 수 있음
	// 각 상어들이 상하좌우 중 하나로 이동, 냄새 뿌림
	// 냄새는 상어가 k번 이동하고 나면 사라진다
	// 각 상어가 이동 방향을 결정할 때는, 인접 칸 중 냄새 없는 칸 방향으로 잡기
	// 없으면 자신의 냄새가 있는 칸의 방향으로 잡는다.
	// 우선순위는 상어마다 다름
	// 상어가 보고 있는 방향
	// 모든 상어가 이동한 후 한 칸에 여러 마라의 상어가 남아 있으면 가장 작은 번호를 가진 상어를 제외하고 모두 격자 밖으로 쫓겨난다.

	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };

	static int[][] map; // 상어위치
	static int[][] smell; // 냄새
	static int[][] time; // 남은 시간
	static Shark[] sharks;
	static List<int[][]> priority; // 상어의 이동방향 우선순위

	static class Shark {
		int r, c, d;

		public Shark(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()); // 맵 크기
		int M = Integer.parseInt(st.nextToken()); // 상어 마리 수
		int k = Integer.parseInt(st.nextToken()); // k번 이동 후 냄새 사라짐

		map = new int[N][N]; // 상어의 위치
		smell = new int[N][N]; // 냄새
		time = new int[N][N]; // 남은 시간
		sharks = new Shark[M + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				smell[i][j] = n;
				if (map[i][j] > 0) {
					sharks[map[i][j]] = new Shark(i, j, 0);
					time[i][j] = k;
				}
			}
		}

		// 초기 상어의 방향 정보 넣기
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			sharks[i].d = Integer.parseInt(st.nextToken()) - 1;
		}

		// 각 상어들의 방향에 따른 방향 우선순위
		priority = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			int[][] temp = new int[4][4];
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				temp[j] = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
						Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1 };
			}
			priority.add(temp);
		}

		int cnt = M;
		int times = 0;
		while (true) {

			// 1번 상어만 남으면 종료
			// 1000초가 지나도 조건 만족 못 하면 -1 출력
			if (cnt == 1 && sharks[1] != null)
				break;
			if (times >= 1000) {
				times = -1;
				break;
			}

			// 상어가 동시에 이동하니까 map 초기화
			map = new int[N][N];
			// snum 상어 번호
			for (int snum = 1; snum <= M; snum++) {
				if (sharks[snum] == null)
					continue; // 상어가 죽었으면 다음 상어로

				Shark s = sharks[snum];

				// 현재 방향이 s.d니까, 우선순위는
				int[] p = priority.get(snum - 1)[s.d];
				boolean flag = false; // 냄새가 없는 칸으로 이동했으면 true 로
				int nr = 0, nc = 0, d = 0; // 상어가 이동할 곳의 정보를 담을 변수들

				// 냄새가 없는 칸 확인
				for (int j = 0; j < 4; j++) {
					nr = s.r + dr[p[j]];
					nc = s.c + dc[p[j]];
					d = p[j];
					if (0 <= nr && nr < N && 0 <= nc && nc < N && smell[nr][nc] == 0) {
						flag = true; // 이동
						break;
					}
				}

				if (!flag) { // 냄새가 없는 칸이 없다면
					// 자신의 냄새와 같은 칸 확인
					for (int j = 0; j < 4; j++) {
						nr = s.r + dr[p[j]];
						nc = s.c + dc[p[j]];
						d = p[j];

						if (0 <= nr && nr < N && 0 <= nc && nc < N && smell[nr][nc] == snum) {
							break;
						}
					}
				}

				// nr, nc로 이동
				if (map[nr][nc] == 0) { // 상어가 없다면
					map[nr][nc] = snum;
					sharks[snum].r = nr;
					sharks[snum].c = nc;
					sharks[snum].d = d;
				} else {
					sharks[snum] = null;
				}
			}

			// 모든 상어의 이동이 끝난 후
			// - 냄새가 남은 곳의 시간을 1초씩 감소시켜주기
			// - 남은 시간이 0이 되었다면 상어의 흔적이 없어진 것이므로 smell 부분도 0으로 바꿔주기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (time[i][j] > 0) time[i][j]--;

					if (time[i][j] == 0)
						smell[i][j] = 0;
				}
			}

			cnt = 0; // 남은 상어의 마리 수를 세 줄 변수
			// 현재 남은 상어들 정보 업데이트 시키기
			// 앞에서 상어의 정보들만 업데이트 했으니까
			// smell, time 배열에도 업데이트
			for (int i = 1; i <= M; i++) {
				if (sharks[i] == null) continue;

				Shark s = sharks[i];
				smell[s.r][s.c] = i;
				time[s.r][s.c] = k;
				
				cnt++;
			}
			
			times++; // 일련의 과정이 한 번 끝났으므로 시간 1초 증가

		}
		System.out.println(times);
		br.close();
	}
}