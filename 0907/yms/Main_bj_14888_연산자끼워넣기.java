package bj14888;

import java.util.*;
import java.io.*;

public class Main_bj_14888_연산자끼워넣기 {
	
	static int[] arr;
	static int[] ops = new int[4];
	static int N;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	static void dfs(int op, int idx, int sum) {
		
		if(op==0) sum += arr[idx++];
		else if (op==1) sum -= arr[idx++];
		else if (op==2) sum *= arr[idx++];
		else if (op==3) sum /= arr[idx++];
		
		for(int i=0; i<4; i++) {
			if(ops[i]!=0) {
				ops[i]--;
				dfs(i,idx,sum);
				ops[i]++;
			}
		}
		
		if(idx==N) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		arr = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine());
		for(int i=0; i<4; i++) {
			ops[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0,0,0);
		
		System.out.println(max+" "+min);
		
	}
}
