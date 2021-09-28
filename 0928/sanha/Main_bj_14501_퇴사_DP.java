package a0927.study;

import java.io.*;
import java.util.*;

// DP
// n번째 일자에 상담이 끝난다면 
// n-1일자 까지 상담중 최대로 진행하여
// 일자에 상담이 끝나는 경우의 이익을 구하는 것과 같다.
// --> 최적 부분 문제
// 부분문제가 반복된다
// --> 중복 부분 문제
// 그러므로 DP로 풀 수 있다

// 하지만 나는 0-1 knapsack문제 느낌이나서 DP구나라고 생각함

public class Main_bj_14501_퇴사_DP {
	static int N;		// 상담을 할 날 수, 잡혀있는 상담의 수
	static int[] T;		// 상담에 걸리는 시간의 배열
	static int[] P;		// 상담의 금액
	static int result_p;// 최대 금액

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14501_퇴사.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine()); // 상담을 할 날 수, 상담의 수 
		T = new int[17];	// 각 상담에 걸리는 배열
		
		P = new int[17];	// 각 상담의 금액을 저장하는 배열
		
		for(int i=1;i<=N;i++) {  // 상담에 걸리는 시간, 금액을 인덱스를 맞춰 저장
			st = new StringTokenizer(br.readLine(), " ");
			T[i] = Integer.parseInt(st.nextToken());	// 시간 (선물의 무게)
			P[i] = Integer.parseInt(st.nextToken());	// 금액	(선물의 금액)
		}
		
		// dp
		for(int i=N;i>=1;i--) {  // 마지막 날부터 상담 고려
			//     현재 날짜 + 상담 걸리는 일수가 주어진 날을 초과하면
			if(i+T[i]>N+1) P[i] = P[i+1];
			else {
				P[i] = Math.max(P[i+1], P[i]+P[i+T[i]]);
				result_p = Math.max(result_p, P[i]);
			}
		}
		
		System.out.println(result_p);
		
	}



}
