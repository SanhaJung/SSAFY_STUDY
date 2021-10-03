package a1003.study;

import java.util.*;
import java.io.*;

public class Main_bj_14889_스타트와링크_1003 {
	
	static int N;				// 팀의 개수
	static int res=987654321;	// 팀의 능력치 차이의 최솟값
	static int[][] power;		// 능력치 저장 배열
	static int combCnt;			// 팀의 조합의 경우의 수 cnt
	static int[] startNum;		// 스타트 팀 배열
	static int[] linkNum;		// 링크 팀 배열

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14889_스타트와링크.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// input START
		N = Integer.parseInt(br.readLine());
		power = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0;j<N;j++) {
				power[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// input END
		
		startNum = new int[N/2];	// 각 팀의 인원수는 N/2
		linkNum = new int[N/2];		// 문제조건: N은 짝수

		comb(0, 0);		// 선수 뽑는 조합
		
		System.out.println(res);

	}

	private static void comb(int cnt, int start) {
		
		if(res==0) return; 		// 팀의 능력치 차이가 0일때가 최소
		
//		if(combCnt== (N*(N-1))/4) return;	// 
		
		if(cnt==N/2) {						// 스타트팀을 뽑으면
//			combCnt++;
			int idx =0;
			loop: for(int i=0;i<N;i++) {  	// 링크팀을 만들어주고
				for(int e: startNum) {
					if(e==i) continue loop;
				}
				linkNum[idx++] = i;
			}
			
			// 각팀의 능력치 차이 저장
			res = Math.min(res,  Math.abs(subSum(startNum) -subSum(linkNum))) ;
			
			return;
		}
		
		// 조합 뽑기
		for(int i=start;i<N;i++) {
			startNum[cnt] = i;
			comb( cnt+1, i+1);
		}
	}

	private static int subSum(int[] array) {	// 각팀의 능력치 구하는 함수
		int subSum = 0;
		for(int i=0;i<N/2;i++) {
			for(int j=0;j<N/2;j++) {
				subSum += power[array[i]][array[j]];
			}
		}
		return subSum;
	}

}
