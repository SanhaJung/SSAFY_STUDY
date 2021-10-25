package bj17281;

import java.util.*;
import java.io.*;

public class Main_bj_17281_야구 {
	
	static boolean[] visited = new boolean[10];
	static int[] numbers = new int[10];
	static int N;
	static int[][] input;
	static int answer = Integer.MIN_VALUE/2;
	
	static void permutation(int cnt) {
		if(cnt==10) {
			int result = game();
			answer = Math.max(answer,result);
			return;
		}
		
		if(cnt==4) {												// 1번 선수는 항상 4번 타자이므로 고정
			numbers[cnt] = 1;
			permutation(cnt+1);
		}
		else {
			for(int i=2; i<=9; i++) {
				if(!visited[i]) {
					visited[i] = true;
					numbers[cnt] = i;
					permutation(cnt+1);
					visited[i] = false;
				}
			}
		}
	}
	
	static int game() {
		int cur = 1;										// 현재 타석에 들어설 선수 번호
		int score = 0;										// 합산 점수
		boolean[] base;										// 베이스 (0 = 1루, 1 = 2루, 2 = 3루)
		
		for(int i=0; i<N; i++) {							// 이닝
			int out = 0;									// 아웃카운트 초기화
			base = new boolean[3];							// 베이스 초기화 
			
			while(out<3) {
				if(input[i][numbers[cur]]==1) {				// 1루타
					for(int j=2; j>=0; j--) {
						if(base[j]) {
							if(j==2) {						// 3루 주자만 홈인
								base[j] = false;
								score++;
							}
							else {							// 나머지 주자는 1루씩 이동
								base[j+1] = true;
								base[j]= false;
							}
						}
					}
					base[0] = true;							// 타자는 1루로 이동
				}
				else if(input[i][numbers[cur]]==2) {		// 2루타
					for(int j=2; j>=0; j--) {			
						if(base[j]) {
							if(j>=1) {						// 2,3루 주자만 홈인
								base[j] = false;
								score++;
							}
							else {							// 1루 주자는 3루로 이동
								base[j+2] = true;
								base[j]= false;
							}
						}
					}
					base[1] = true;							// 타자는 2루로 이동
				}
				else if(input[i][numbers[cur]]==3) {		// 3루타
					for(int j=2; j>=0; j--) {
						if(base[j]) {						// 출루해 있는 주자들 모두 홈인
							base[j]=false;
							score++;
						}
					}
					base[2] = true;							// 타자는 3루로 이동
				}
				else if(input[i][numbers[cur]]==4) {		// 홈런
					for(int j=2; j>=0; j--) {
						if(base[j]) {						// 진루해 있는 주자들 모두 홈인
							base[j]=false;
							score++;
						}
					}
					score++;									// 친 타자도 홈인
				}
				else {										// 아웃
					if(++out==3) {							// 아웃카운트가 3개이면
						cur = (cur%9)+1;					// 다음 선수 번호를 1 증가 시키고 break
						break;
					}
				}
				
				cur = (cur%9)+1;							// 다음 선수 번호 1 증가
			}
		}
		return score;
	}

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		input = new int[N][10];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=1; j<=9; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		permutation(1);
		System.out.println(answer);
	}
}
