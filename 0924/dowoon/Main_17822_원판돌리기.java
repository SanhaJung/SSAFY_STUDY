package s0924;

import java.io.*;
import java.util.*;

public class Main_17822_원판돌리기 {

	static int N, M, T, x, d, k;
	static int[][] pan;
	static boolean[][] v;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int answer;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		pan = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				pan[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int idx = 0;
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());		// x의 배수에 해당되는 원판 돌리기 ( 2인 경우, 2, 4, 6... 번째 원판 돌리기 ) 
			d = Integer.parseInt(st.nextToken());		// d=0 : 시계방향(행을 오른쪽으로 쉬프트), d=1 : 반시계 방향(행을 왼쪽으로 쉬프트)
			k = Integer.parseInt(st.nextToken());		// k 만큼 회전 시키기 
			
			rotate(x, d, k);							// 1. 먼저 x의 배수의 원판을 d방향으로 k만큼 회전시키고, 
			if(!remove()) udpate();						// 2. 인접하면서 동일한 수들을 모두 -1로 제거해보고, 
														// 3. 만약 2번에서 제거된 숫자가 없으면, 원판에 적힌 숫자들의 평균을 구해, 원판의 수가 평균보다 크면 -1, 작으면 +1 처리를 해줌 
		}
		
		answer = 0;										// 4. 1~3번 과정을 T번 수행한 다음, 원판에 남아있는 숫자들의 합을 구한다. 
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(pan[i][j] != -1) answer += pan[i][j];
			}
		}
		System.out.println(answer);
	}

	private static void udpate() {
		int sum = 0;
		int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (pan[i][j] != -1) {
                    sum += pan[i][j];					// 평균값을 구하기 위해 sum 계산 
                    cnt++;								// 원판에 적힌 숫자들의 개수를 카운트할 변수 
                }
            }
        }
        double avg = sum / (double) cnt;				// 평균값 계산 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (pan[i][j] != -1) {					// 만약 원판에 적혀있는 숫자이면서 
                    if ((double) pan[i][j] > avg) pan[i][j]--;		// 해당 숫자가 평균보다 크다면 -1 
                    else if ((double) pan[i][j] < avg) pan[i][j]++;	// 해당 숫자가 평균보다 작다면 +1 
                }
            }
        }
	}

	private static boolean remove() {
		boolean removed = false;						// 인접하면서 동일한 숫자를 제거했는지 여부를 체크할 플래그 변수 
		boolean [][] v = new boolean[N][M];				// 방문처리할 배열 
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				
				if(pan[i][j] == -1 || v[i][j]) continue; // 만약 원판에서 숫자가 아니거나(이미 제거된 경우) 이미 방문처리된 경우 스킵 
				
				Queue<int[]> q = new ArrayDeque<>();	// 배열의 모든 원소마다 bfs를 진행하기 위해 매번 큐 초기화 
				q.offer(new int[] {i, j, pan[i][j]});	// 배열의 현재 요소를 큐에 추가  
				v[i][j] = true;							// 현재 요소 방문처리 
				
				while(!q.isEmpty()) {
					int[] cur = q.poll();
					int y = cur[0];						// 행 
					int x = cur[1];						// 열 
					int target = cur[2];				// 값 
					
					for(int d=0; d<4; d++) {
						int ny = y + dy[d];
						int nx = x + dx[d];
						
						if(ny<0||ny>=N) continue;		// 행의 경우 범위를 초과한다면 스킵 
						
						if(nx == -1) nx = M-1;			// 열의 경우 원형큐를 구성하기 때문에 범위를 초과하더라도 초기화 해줘야됨 
						else if(nx == M) nx = 0;
						
						if(v[ny][nx]) continue;			// 만약 이동한 곳이 이미 방문된(인접해서 제거되었다면) 스킵 
						
						if (pan[ny][nx] == target) {	// 만약 이동한 곳이 시작점의 값과 동일하다면 
							
							v[ny][nx] = true;			// 방문처리 해주고 
							q.offer(new int[] {ny, nx, target});	// 이동한 위치를 큐에 넣어주고 
							pan[y][x] = -1;				// 기준이 되는 곳의 값을 제거(-1로 초기화) 해주고 
							pan[ny][nx] = -1;			// 인접하면서 동일한 값을 갖는 곳을 제거 해주고 
							removed = true;				// 플래그 변수를 true 처리해줌 
						}
					}
				}
			}
		}
		return removed;
	}

	private static void rotate(int x, int d, int k) {
		for(int circle=0; circle<N; circle++) {
			if((circle+1)%x == 0) {						// x의 배수인 원판일 때만 아래 코드 실행 
				if(d==0) {								// 시계방향 (오른쪽으로 쉬프트) 
					for (int move=0; move<k; move++) {	// 오른쪽으로 1만큼 k번 이동시키면 총 k번 이동시키는 것과 동일 
						int[] tmp = pan[circle].clone();// 먼저 해당 행을 복사한 다음, 원래의 배열값을 초기화 (동일한 행의 열 값들이 자신보다 왼쪽에 있는 값으로 초기화되기 때문에, 이를 저장해둬야됨 )
						pan[circle][0] = tmp[M-1];		// 맨 마지막 요소가 오른쪽으로 쉬프트하면 배열의 맨 첫 번째로 오기 때문에 먼저 초기화 해줌 
						for (int i=1; i<M; i++) {		// 1번째 인덱스부터 배열의 끝까지 자신의 왼쪽(i-1) 인덱스의 값으로 초기화 
							pan[circle][i] = tmp[i-1];
						}
					}
				}else if(d==1) {						// 반시계 방향(왼쪽으로 쉬프트)
					for (int move=0; move<k; move++) {	// 시계방향과 동일 
						int[] tmp = pan[circle].clone();
						pan[circle][M-1] = tmp[0];		// 시계방향과 반대 
						for (int i=0; i<M-1; i++) {
							pan[circle][i] = tmp[i+1];
						}
					}
				}
			}
		}
	}
}
