package a1006;

import java.io.*;
import java.util.*;

public class Main_bj_14501_퇴사_dfs {
	static int N , money;
	static int [] T, P ;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		T = new int [N+1];
		P = new int [N+1];
		
		for(int i =1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		money = 0;
		dfs(1,0);
		System.out.println(money);
		
	}
	private static void dfs(int day, int sum) {
		money = Math.max(sum, money);
		
		if(day > N) return;
		
		int idx = day + T[day]; //현재 날짜에 상담해야할 날짜 더해서 
		if(idx<=N+1) {  //N+1이 넘어가지 않으면
			dfs(idx, sum+P[day]); //상담 금액 sum에 추가해서 dfs
		}
		
		dfs(day+1, sum); //오늘 선택 안하기
		
	}
}
