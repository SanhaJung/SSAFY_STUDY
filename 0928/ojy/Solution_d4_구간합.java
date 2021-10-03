package hw0928;

import java.util.Scanner;

public class Solution_d4_구간합 {

	static long ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1;tc<=T;tc++) {
			long A = sc.nextLong();
			long B = sc.nextLong();
			ans = 0;
			long mul = 1;
			while(A<=B) {
				while(B%10!=9&&A<=B) {	//(21,1)=3 (20,1)=5 //(1,10)=77
					calc(B,mul);
					B--;
				}//19
				if(A>B) break; 
				while(A%10!=0 && A<=B) {
					calc(A,mul);	//(8,1)=13 (9,1)=22
					A++;
				}//10
				A/=10;
				B/=10;
				long m = (B-A+1)*mul; // 1= (1-1+1)*1
				for(int i=0; i<10 ;i++) ans+=m*i;  //45
				mul*=10;
			}
			System.out.println("#"+tc+" "+ans);
		}
	}
	//중요 - 원리기억
	private static void calc(long n, long t) {
		while(n>0) {
			ans+=(n%10)*t;
			n/=10;
		}
	}

}
