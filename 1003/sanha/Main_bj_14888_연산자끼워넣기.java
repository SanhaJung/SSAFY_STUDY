package a1003.study;

import java.util.*;
import java.io.*;

public class Main_bj_14888_연산자끼워넣기 {
	
	static int N;		// 수열의 개수
	// 수열을 저장 하는 큐 (먼저 들어온 수가 먼저 연산)
	static Deque<Integer> numbers_q= new ArrayDeque<Integer>();
	static int[] operators;	// 연산자 저장 배열
	
	static int[] p; 				// 순열의 case저장 배열
	static boolean[] isSelected;	// 순열의 방문처리
	
	// 연산 최소, 최대값
	static int minRes=1000000001, maxRes=-1000000001;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14888_연산자끼워넣기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// input START
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		// 수: 큐에 저장
		for(int i=0;i<N;i++) {
			numbers_q.offer(Integer.parseInt(st.nextToken()));
		}
		
		operators = new int[N-1];
		p = new int[N-1];
		isSelected = new boolean[N-1];
		// 연산자: 배열에 저장
		st = new StringTokenizer(br.readLine());
		int idx = 0;
		for(int i=0;i<4;i++) {
			int n = Integer.parseInt(st.nextToken());
			for(int j=0;j<n;j++) {
				operators[idx++] = i;
			}
		}
		// input END
		
		// 연산자 순열 뽑기
		permu(0);
		
		System.out.println(maxRes);
		System.out.println(minRes);
	}

	private static void permu(int cnt) {
		// 연산자 순열
		if(cnt == N-1) {
			// 연산
			int num1 = numbers_q.poll(); 		// 첫번째 수 poll
			numbers_q.offer(num1);				// 다음 순열 케이스에서 쓰기위해 offer
			for(int i=0;i<N-1;i++) {
				int num2 = numbers_q.poll();	// 다음 수 poll
				numbers_q.offer(num2);			// 다음 순열 케이스에서 쓰기위해 offer
				
				if(p[i]==0) num1 += num2;		// 덧셈
				else if(p[i]==1) num1 -= num2;	// 뺄셈
				else if(p[i]==2) num1 *= num2;	// 곱셈
				else if(p[i]==3) {				// 나눗셈
					if(num1<0) {				
						num1*= -1;				//음수일때 
						num1 /= num2;			// 양수로 바꿔서 나눠주고 몫을 음수로
						num1*= -1;
					}
					else num1 /= num2;
				}
				
			}

			maxRes = Math.max(maxRes, num1);
			minRes = Math.min(minRes, num1);
			return;
		}
		// 순열 뽑기
		 for(int i=0;i<N-1;i++) {
			 // 방문했다면 pass
			 if(isSelected[i]) continue;
			 
			 p[cnt] = operators[i];
			 isSelected[i] = true;
			 permu(cnt+1);
			 
			 isSelected[i] = false;
			 
		 }
		
	}
	
	

}
