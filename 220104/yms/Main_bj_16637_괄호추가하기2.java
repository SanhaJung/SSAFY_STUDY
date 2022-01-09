package bj16637;

import java.util.*;
import java.io.*;

public class Main_bj_16637_괄호추가하기2 {
	
	static int answer = Integer.MIN_VALUE/2;
	static char[] op;

	static void dfs(int idx, int total) {
		if (idx >= op.length)
		{
			answer = Math.max(answer, total);
			return;
		}
		if(idx+3<op.length) {
			int num = calculate(op[idx+1]-'0',op[idx+3]-'0',op[idx+2]);
			dfs(idx+4,calculate(total,num,op[idx]));
		}
		dfs(idx+2,calculate(total,op[idx+1]-'0',op[idx]));
		
	}
	
	static int calculate(int a, int b, char op) {
		if(op=='+') {
			return a+b;
		}
		else if (op == '-') {
			return a-b;
		}
		else {
			return a*b;
		}		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		in.readLine();
		op = in.readLine().toCharArray();
		
		dfs(1,op[0]-'0');
		
		System.out.println(answer);
	}
}
