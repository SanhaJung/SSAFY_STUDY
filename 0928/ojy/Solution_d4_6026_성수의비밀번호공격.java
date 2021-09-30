package hw0928;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_d4_6026_성수의비밀번호공격 {
	static int MOD = 1_000_000_007;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			long total = function(M,N);
			sb.append("#").append(tc).append(" ").append(total).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	private static long function(int M,int N) {
		long total = 0;
		for(int i=0;i<=N;i++) {
			long l1 = i%2==0?1:-1;
			long l2 = nCr(N,i,MOD);
			long l3 = power(N-i,M,MOD);
			long result = ((l1*l2)%MOD*l3)%MOD;
			total = (total+result+MOD)%MOD;
		}
		return total;
	}
	private static long nCr(int n,int r,int p) {
		if(r==0) return 1L;
		
		long[]fac = new long[n+1];
		fac[0]=1;
		for (int i = 1; i <= n; i++) {
			fac[i] = fac[i-1]*i%p;
		}
		return (fac[n]*power(fac[r],p-2,p)%p
				* power(fac[n-r],p-2,p)%p)%p;
	}
	private static long power(long x,long y, int p) {
		long res = 1L;
		x = x%p;
		while(y>0) {
			if(y%2==1)
				res = (res*x)%p;
			y = y>>1;
			x = (x*x)%p;
		}
		return res;
	}

}
