package a0930.hw;

import java.util.*;
import java.io.*;

public class Main_bj_17822_원판돌리기 {
	static int N, M;
	static List[] pans;	// 원판 담을 배열 
	static boolean[][] check;
	static int[] dr = { -1, 0, 1, 0 }; // 상우하좌
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); 		// 원판 수
		M = Integer.parseInt(st.nextToken()); 		// 원판에 적힌 수 개수
		int T = Integer.parseInt(st.nextToken());   // T번 회전

		pans = new List[N];
		for (int i = 0; i < N; i++) {
			pans[i] = new ArrayList<Integer>();		// 각 원판을 ArrayList로 관리
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				pans[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		// 입력 받기 끝

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()); // 원판 번호
			int d = Integer.parseInt(st.nextToken()); // 회전 방향, 0:시계 1: 반시계
			int k = Integer.parseInt(st.nextToken()); // k칸 회전

			
			for (int i = x; i <= N; i += x)  rotate(i - 1, d, k); // 회전시키기
			
			
			// 인접한 원소 확인
			check = new boolean[N][M];
			int cnt = 0;				// -1로 바꿔 준 숫자 세기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if ((int) pans[i].get(j) == -1) continue;
					int n = (int) pans[i].get(j);
					cnt += bfs(i, j, n);
				}
			}
			
			
			if (cnt == 0) { // cnt == 0이면 같은 것이 없다
							// 평균 구해서
				double mean = 0;
				int size = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						int n = (int) pans[i].get(j);
						if (n != -1) {
							mean += n;
							size++;
						}
					}
				}
				mean /= size;

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						int n = (int) pans[i].get(j);
						if (n > mean && n != -1) {						// 평균보다 크면   -1
							pans[i].set(j, (int) pans[i].get(j) - 1);
						}else if(n < mean && n != -1){					// 평균보다 작으면 +1
							pans[i].set(j, (int) pans[i].get(j) + 1);
						}
					}
				}
			} 
		}
		
		// 남은 원소들의 합 구하기
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int n = (int) pans[i].get(j);
				if(n!=-1) result += n;
			}
		}
		System.out.println(result);
	}

	/**
	 * 원판 회전시키는 함수
	 * 
	 * @param x = 원판 번호
	 * @param d = 시계/반시계 방향
	 * @param k = k칸 회전 
	 * */
	private static void rotate(int x, int d, int k) {
		List pan = pans[x];									// 해당 원판

		if (d == 0) { 										// 시계 방향
			while (k-- > 0) {								// k칸 회전
				int last = (int) pan.get(pan.size() - 1);	// 맨 끝 원소 맨 앞에 넣기
				pan.remove(pan.size() - 1);
				pan.add(0, last);
			}

		} else if (d == 1) { // 반시계 방향
			while (k-- > 0) {
				int first = (int) pan.get(0);				// 첫 번째 원소 맨 끝에 넣기
				pan.remove(0);
				pan.add(first);
			}
		}
	}

	// 인접한 수 확인
	private static int bfs(int i, int j, int num) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { i, j });		

		int cnt = 0;	// 이웃하면서 숫자가 같은 수 세기
		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int k = 0; k < 4; k++) {
				int nr = cur[0] + dr[k];
				int nc = cur[1] + dc[k];
				
				if (nc == -1) nc = M - 1;		// 열의 경우,  원 형태로 인덱스 0과, 인덱스 M-1이 이웃하므로
				if (nc == M) nc = 0;			// 배열의 범위를 넘어가는 경우 처리

				if (0 <= nr && nr < N) {
					int n = (int) pans[nr].get(nc);		// 이웃한 원소
					if (n == -1) continue;				// 없앤 숫자이면 다음 확인
					if (n == num && !check[nr][nc] ) {	// 현재 원소와 이웃한 원소가 같고, 방문하지 않았다면
						check[nr][nc] = true;	
						q.add(new int[] {nr,nc});		// q에 넣고
						pans[nr].set(nc,-1);			// 해당 원소 -1로 바꾸기
						cnt++;							// 이웃한 같은 숫자의 개수 세주기 
					}
				}
			}

		}
		if(cnt > 0) {            // 이웃하면서 같은 숫자가 존재한다면
			check[i][j] = true;	 // 시작점도  처리해주기
			pans[i].set(j,-1);	 // 시작점 처리
		}
		return cnt;
	}
}
