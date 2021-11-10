package hw1029;
import java.io.*;
import java.util.*;
public class Main_bj_14888_연산자끼워넣기 {
	static int N,MAX=-987654321,MIN=987654321;
	static int[]operand;
	static int[]A,oper;
	private static int calc() {
		int sum = A[0];
		for(int i=0;i<N-1;i++) {
			if(operand[i]==0) {	///+
				sum+=A[i+1];
			}else if(operand[i]==1) {
				sum-=A[i+1];
			}else if(operand[i]==2) {
				sum*=A[i+1];
			}else if(operand[i]==3) {
				sum/=A[i+1];
			}
		}
		return sum;
	}
	private static void permutation(int cnt) {
		if(cnt==N-1) {					//연산자를 선택하니 N-1개
			int result = calc();		
			MAX = Math.max(MAX, result);
			MIN = Math.min(MIN, result);
			return;
		}
		for(int i=0;i<4;i++) {
			if(oper[i]>0) {				//해당연산자를 쓸 수 있는 기회가 남았다면
				oper[i]--;				//그 연산자를 쓰고
				operand[cnt] = i;		//그 연산자에 해당되는 인덱스번호를 operand에 넣어줌
				permutation(cnt+1);		
				oper[i]++;				//해당 연산자 쓰는 거 취소
			}
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = new int[N];
		operand = new int[N];
		for(int i=0;i<N;i++)
			A[i] = Integer.parseInt(st.nextToken());
		
		oper = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++)
			oper[i] = Integer.parseInt(st.nextToken());
		
		permutation(0);
		System.out.println(MAX);
		System.out.println(MIN);
	}

}
