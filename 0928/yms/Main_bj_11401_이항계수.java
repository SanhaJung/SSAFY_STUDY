package bj11401;

import java.util.*;
import java.io.*;
import java.math.*;
public class Main_bj_11401_이항계수 {
	
	final static long p = 1000000007;

	static long factorial(long n) {	//n! mod p 가 리턴
		long temp = 1L;
		
		while(n>1) {
			temp = (temp*n)%p;
			n--;
		}
		return temp;
	}
	
	static long pow_mod(long a, long b) { // a^b mod p 가 리턴
		long result = 1;
		
		while(b>0) {
			if(b%2==1) {
				result *= a;
				result %= p;
			}
			a = (a*a)%p;
			b /= 2;
		}
		
		return result;
	}
	
//	static BigInteger nCr(int n,int r,int p){
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
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		long N = Long.parseLong(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long numerator = factorial(N);		//N! mod p
		long denominator = pow_mod(factorial(N-K)*factorial(K)%p,p-2); // (((N-K)!*K!) mod p)^p-2
		long answer = numerator*denominator%p;
		System.out.println(answer);
		
	}
}
