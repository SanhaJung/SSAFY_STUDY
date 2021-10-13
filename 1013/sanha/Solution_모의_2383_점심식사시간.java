package a1013.study;

import java.io.*; 
import java.util.*; 

// 1. 부분집합으로 사람마다 사용할 계단 선택
// 2. 우선순위 큐로 계단까지 거리가 짧은 것을 기준으로 계산
// 3. 각 사람이 계단을 지나가는 시점을 기준으로 계단을 지나는 인원수관리
public class Solution_모의_2383_점심식사시간 { 
	static int T, N, map[][], perIdx, min; 
	static Person per[]; 
	static int stair[][]; 
	static class Person 
	
	implements Comparable<Person> { 
		int r, c, d, t; 
		public Person(int r, int c) {
			this.r = r; 
			this.c = c; 
			} 
		@Override public int compareTo(Person o) { 
			return this.d - o.d; 
			} 
		} 
	
	public static void main(String[] args) throws IOException { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine(), " "); 
		
		T = Integer.valueOf(st.nextToken()); 
		
		for (int tc = 1; tc <= T; tc++) { 
			st = new StringTokenizer(br.readLine(), " "); 
			N = Integer.valueOf(st.nextToken()); 
			
			per = new Person[N * N]; 
			
			int idx = 0;
			perIdx = 0; 
			stair = new int[2][3];	// 행, 열(계단의 위치), 길이 
			map = new int[N][N]; 
			
			for (int i = 0; i < N; i++) { 
				st = new StringTokenizer(br.readLine(), " "); 
				for (int j = 0; j < N; j++) { 
					map[i][j] = Integer.valueOf(st.nextToken()); 
					if (map[i][j] == 1) {						// 사람일때 
						per[perIdx++] = new Person(i, j); } 	// 사람배열에 위치저장, 저장 인덱스 증가
					if (map[i][j] >= 2) { 					 	// 계단일때
						stair[idx][0] = i;						// 계단의 위치 - 행
						stair[idx][1] = j; 						// 계단의 위치 - 열
						stair[idx++][2] = map[i][j]; 			// 계단의 길이
					} 
				} 
			} 
			min = Integer.MAX_VALUE; 
			powerset(0); 
			System.out.println("#" + tc + " " + min); 
			} 
		} 
	
	// 어떤계단으로 내려갈지 사람을 두 집합으로 나눔 -> 부분집합
	static void powerset(int idx) {	
		if (idx == perIdx) { 										// 부분집합을 뽑을때 모든 사람을 고려했다면
			int max = 0;  											// 
			for (int i = 0; i < 2; i++) {  							// 0, 1번인덱스 계단마다 계산
				PriorityQueue<Person> pq = new PriorityQueue<>(); 	// 계단입구 - 사람 가까운 순으로 계산하기위해 pq사용
				int time[] = new int[100]; 							// 타임테이블(각 시간대별로 )
				for (int j = 0; j < perIdx; j++) { 					// 각 사람이 어떤 계단으로 가는지 
					if (per[j].t == i) { 							// 현재 즉, i번 인덱스 계단으로 가는 사람이라면
						pq.offer(per[j]); 							// 우선순위 큐에 저장
					} 
				} 
				int end = 0; 								// 전체 소요시간:마지막사람이 계단을 내려가는 시간 저장
				while (!pq.isEmpty()) { 
					Person cur = pq.poll(); 				// 가까운 순서대로 사람 poll
					int start = cur.d; 						// 현재 사람이 출발하는시간 = 계단까지 이동시간 = 사람~계단 거리
					end = start + stair[cur.t][2]; 			// 현재사람이 내려가는 최단 시점
					for (int j = start; j < end; j++) { 	// 매 분마다 계단을 내려갈때 대기가 있는지 확인
						if (time[j] == 3) { 				// 3명이 계단에 있다면
							end++; 							// 계단 내려가는 시간 +1
							continue; 						
						} 
						time[j]++; 							// j라는 시점에 그계단에 있다는 것을 표시(인원 +1)
					} 
					if (max < end) { 						// 각 사람중에 가장마지막에 내려간 시점이 마지막 시점
						max = end; 							
					} 
				} 
			} 
			if (min > max) { 								// 총 걸린 시간이 짧은것을 return하기위해 update
				min = max;
				} 
			return; 
			} 
		// 계단 선택하고, 길이 구하기 
		per[idx].d = Math.abs(per[idx].r - stair[0][0]) + Math.abs(per[idx].c - stair[0][1])+1; 
		per[idx].t = 0; 	// 0번 인덱스의 계단선택
		powerset(idx + 1); 	// 다음 뽑기
		per[idx].d = Math.abs(per[idx].r - stair[1][0]) + Math.abs(per[idx].c - stair[1][1])+1; 
		per[idx].t = 1; 	// 0번 인덱스의 계단선택
		powerset(idx + 1); 	// 다음 뽑기
		} 
	}

