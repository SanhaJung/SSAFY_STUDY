package ct0928;

import java.io.*;
import java.util.*;
import java.math.*;

public class Main_d4_6026_성수의비밀번호공격 {
	// BigInteger 사용
//	static BigInteger nCr(int n,int r,long p){
//        long[] fac=new long[n+1];
//        fac[0]=1;
//        for(int i=1; i<=n; i++) fac[i]=fac[i-1]*i%p;
//        
//        BigInteger P=BigInteger.valueOf(p);
//        BigInteger A=BigInteger.valueOf(fac[n]);
//        BigInteger B=BigInteger.valueOf(fac[r]).modInverse(P).remainder(P);
//        BigInteger C=BigInteger.valueOf(fac[n-r]).modInverse(P).remainder(P);
//        return A.multiply(B).multiply(C).remainder(P);
//    }
	
	static long nCr(int n, int r, long p) {
		if(r==0) return 1L;
		
		long[] fac = new long[n+1];
		fac[0] = 1;
		
		for(int i=1;i<=n;i++) fac[i] = fac[i-1] * i % p;
		
		return (fac[n]*modInverse(fac[r], p)%p*modInverse(fac[n-r], p)%p)%p;
	}
	
	private static long power(long x, long y, long p) {
		long res = 1L;
		x = x % p;
		while(y>0) {
			if(y%2==1) res = (res*x)%p;
			y = y>>1; // y = y/2
			x = (x*x)%p;
		}
		return res%p;
	}
	
	static long modInverse(long n, long p) {
		return power(n, p-2, p);
	}
	


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d4_6026_성수의비밀번호공격.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		
		long Q = 1_000_000_007;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());	
			int N = Integer.parseInt(st.nextToken());  // N: 비밀번호 자리수
			long res = surjection(M, N, Q);
			
			
			
			sb.append("#").append(t).append(" ").append(res>=0? res:(res+Q)).append("\n");
		}

		System.out.println(sb);
		
		br.close();

	}
	// 전사함수 - 문제에서 m,n 바뀌어있음
	// 
	private static long surjection(int m, int n, long p) {
		long tot = 0L;
		for(int i=0;i<m;i++) {
			long t1 = (i%2==0)? 1L:-1L;
			long t2 = nCr(m, i, p)%p;
			long t3 = power(m-i, n, p)%p;
			tot = (tot+ (t1*t2*t3)%p)%p;
		}
		return tot%p;
	}

}
