package a6026;

import java.util.*;
import java.io.*;

public class Solution_d4_6026_성수의비밀번호공격 {
	
	final static int P = 1000000007;
	static long[] factorial = new long[101];
	static int M,N;
	
	static void factorial_calculate() {			//n! mod p 저장
		factorial[0] = factorial[1] = 1;
		for(int i=2; i<factorial.length; i++) {
			factorial[i] = (factorial[i-1]*i)%P;
		}
	}
	// nCr = (n! / ((n-r)!*r!))%P = (n! * ((n-r)! * r!)^P-2)%P
	static long nCr(int r) {
		if(r==0)
			return 1;
		long nf = factorial[M];
		long n_rf = pow(factorial[M-r],P-2);
		long rf = pow(factorial[r],P-2);
		return ((nf*n_rf)%P*rf)%P;
	}
	
	static long pow(long a, long b) {
		long temp = 1;
		while(b>0) {
			if(b%2==1) {
				temp = (temp*a)%P;
			}
			a = (a*a)%P;
			b /= 2;
		}
		return temp;
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/a6026/input_d4_6026.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		factorial_calculate();
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			long answer = 0;
			// 함수의 갯수 = (-1)^i * kCi * (k-i)^n
			for(int i=0; i<=M; i++) {
				long l1 = (i%2)==0 ? 1 : -1;
				long l2 = nCr(i);
				long l3 = pow(M-i,N);
				answer = (answer + ((l1*l2)%P*l3)%P + P)%P;
			}
			
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
			
		}
		System.out.println(sb.toString());
	}

}
