package bj17822;

import java.util.*;
import java.io.*;

public class Main_bj_17822_원판돌리기 {
	
	static int[][] arr,copyarr;
	static int[] p;										//12시를 가르키는 포인터 변수 배열
	static int N,M,T;
	
	static void rotate(int x, int d, int k) {			// 회전
		if(d==0) d = -1;								// 0이면 시계방향, 1이면 반시계방향
		for(int i=x; i<=N; i+=x) {						// x의 배수에 해당하는 원판을 d방향으로 k칸 만큼 이동
			p[i] = (M+p[i]+(d*k))%M;					// i번원판의 12시를 가르키는 포인터 = 포인터+(d방향*k칸) % M
		}
	}
	
	static void copy() {								// 연산에 사용할 배열 복사
		for(int i=1; i<=N; i++) {
			for(int j=0; j<M; j++) {
				copyarr[i][j] = arr[i][j];
			}
		}
	}
	
	static void check() {
		copy();
		boolean flag = false;
		
		for(int i=1; i<=N; i++) {														// i : 원판의 번호
			for(int j=0; j<M; j++) {													// j : i번 원판에 새겨진 번호의 인덱스
				if(copyarr[i][(p[i]+j)%M]>0) {											// i번 원판에 해당 수가 없어진 수가 아니라면 
					if(i<N && copyarr[i][(p[i]+j)%M] == copyarr[i+1][(p[i+1]+j)%M]) {	// 제일 바깥쪽 원판이 아니고 다음 원판과 인접한 수가 같다면
						flag = true;
						arr[i][(p[i]+j)%M] = 0;											// 원본 배열에 0으로 설정 (숫자 없애기)
						arr[i+1][(p[i+1]+j)%M] = 0;
					}
					if(copyarr[i][(p[i]+j)%M] == copyarr[i][(p[i]+j+1)%M]) {			// 현재 원판에서 인접한 두 수가 같다면
						flag = true;
						arr[i][(p[i]+j)%M] = 0;											// 원본 배열에 0으로 설정 (숫자 없애기)
						arr[i][(p[i]+j+1)%M] = 0;
					}
				}
			}
		}
		if(!flag) {																		// 위의 경우가 없을 경우 평균을 구하고 연산
			double avg = 0;																// 평균보다 크거나 작은 경우를 소숫점까지 체크 
			int cnt = 0;																// 현재 원판에 존재하는 숫자 개수 변수
			for(int i=1; i<=N; i++) {
				for(int j=0; j<M; j++) {
					if(copyarr[i][j]>0) {												// 사라진 숫자가 아니라면
						avg += copyarr[i][j];
						cnt++;
					}
				}
			}
			avg /= cnt;																	// 총합 / 숫자의 개수 = 평균
			for(int i=1; i<=N; i++) {
				for(int j=0; j<M; j++) {
					if(copyarr[i][j]>0) {
						double temp = copyarr[i][j];
						if(temp>avg)													// 평균보다 크면 -1, 평균보다 작으면 +1
							arr[i][j]--;
						else if (temp<avg)
							arr[i][j]++;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 원판의 개수
		M = Integer.parseInt(st.nextToken());	// 정수의 개수
		T = Integer.parseInt(st.nextToken());	// 회전 횟수
		
		arr = new int[N+1][M];
		copyarr = new int[N+1][M];
		p = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());	// 번호가 x 배수인 원판을
			int d = Integer.parseInt(st.nextToken());	// d 방향으로 (0 = 시계방향, 1 = 반시계방향)
			int k = Integer.parseInt(st.nextToken());	// k 칸 회전 시킨다.
			
			rotate(x,d,k);
			check();
		}
		
		int answer = 0;
		
		for(int i=1; i<=N; i++) {
			for(int j=0; j<M; j++) {
				answer += arr[i][j];
			}
		}
		
		System.out.println(answer);
		
	}
}
