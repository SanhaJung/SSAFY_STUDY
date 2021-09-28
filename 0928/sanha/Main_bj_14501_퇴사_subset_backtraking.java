package a0927.study;

import java.io.*;
import java.util.*;

// 부분집합 & 백트래킹

public class Main_bj_14501_퇴사_subset_backtraking {
	static int N;		// 상담을 할 날 수, 잡혀있는 상담의 수
	static int[] T;		// 상담에 걸리는 시간의 배열
	static int[] P;		// 상담의 금액
	static boolean[] v; // 부분집합 방문처리
	static int result_p;// 최대 금액

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14501_퇴사.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		T = new int[N];
		v = new boolean[N];
		
		P = new int[N];
		
		for(int i=0;i<N;i++) {  // 상담에 걸리는 시간, 금액을 인덱스를 맞춰 저장
			st = new StringTokenizer(br.readLine(), " ");
			T[i] = Integer.parseInt(st.nextToken());	// 시간
			P[i] = Integer.parseInt(st.nextToken());	// 금액
		}
		
		// 부분집합
		subset(0);
		
		System.out.println(result_p);
		
	}

	private static void subset(int cnt) {
		// N개의 원소를 모두 고려한 상황(기저 조건)
		if(cnt==N) {	
			boolean[] tv = new boolean[N];	// 근무하는 동안의 날짜별 상담 여부
			int temp_p = 0;				  	// 현재 부분집합 case의 금액의 합
			for(int i=0;i<N;i++) {  
				// 부분 집합에 뽑혔다면(v방문 되어 있다면)
				if(v[i]) {					
					for(int j=0;j<T[i];j++) {	// 부분집합의 원소의 시작점부터 상담 소요시간까지 true
						if(i+j<N && !tv[i+j]) { // 범위 내에 있고 방문처리 되어 있지 않으면
												//                (이전상담과 겹치지 않으면)	
							tv[i+j] = true;		// 상담하는 날: true
						}else return;			// 근무 일수를 넘겼거나 이미 상담이 잡혀있으면 return(백트래킹)
					}
					temp_p += P[i];				// 금액 누적
				}
			}
			// 최대누적 금액 저장
			result_p = Math.max(result_p, temp_p);
			return;
		}

		// 현재 원소 뽑음
		v[cnt]=true;
		subset(cnt+1);
		
		// 현재 원소 뽑지 않음
		v[cnt] = false;
		subset(cnt+1);
		
		
	}

}
