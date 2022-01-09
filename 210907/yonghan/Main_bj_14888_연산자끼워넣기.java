package a0905;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_bj_14888_연산자끼워넣기 {
	static int [] number;
	static int N;
	static int [] oper = new int [4]; //+, -, *, /
	static int max=Integer.MIN_VALUE, min=Integer.MAX_VALUE; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		number = new int [N];
		st = new StringTokenizer(br.readLine());
		for(int i =0; i<N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i =0; i<4; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}
		
		
		dfs(number[0],1);
		System.out.println(max);
		System.out.println(min);
		
	}
	static void dfs(int num, int idx) {
		if(idx==N) {
			max = Math.max(max, num);
			min = Math.min(min, num);
		}
		
		for(int i =0; i<4; i++) {
			if(oper[i]>0) {
				oper[i]--;
				
				if(i==0) {
					dfs(num + number[idx] , idx+1);
				}
				
				else if(i==1) {
					dfs(num - number[idx] , idx+1);
					
				}
				else if(i==2) {
					dfs(num * number[idx] , idx+1);
				}
				
				else {
					dfs(num / number[idx] , idx+1);
				}
				oper[i]++;
			}
		}
		
	}
}
