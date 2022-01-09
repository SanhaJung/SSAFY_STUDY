package bj15683;

import java.util.*;
import java.io.*;

public class Main_bj_15683_감시2 {		// 메모리 43000KB, 실행시간 460ms
	
	static int[] dr = {-1,0,1,0}; // 상 우 하 좌
	static int[] dc = {0,1,0,-1};
	static int N,M;
	static int[][] arr;
	static int[][] copyarr;
	static int answer = Integer.MAX_VALUE/2;
	static ArrayList<CCTV> list = new ArrayList<>();
	
	static class CCTV {
		int r;
		int c;
		int num;
		
		public CCTV(int r, int c, int num) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
		}
		
	}
	
	static void up(int r,int c) {
		for(int i=r-1; i>=0; i--) {
			if(copyarr[i][c]==6) break;
			else if (copyarr[i][c]==0) copyarr[i][c]=-1;
		}
	}
	static void right(int r,int c) {
		for(int i=c+1; i<M; i++) {
			if(copyarr[r][i]==6) break;
			else if (copyarr[r][i]==0) copyarr[r][i]=-1;
		}
	}
	static void left(int r,int c) {
		for(int i=c-1; i>=0; i--) {
			if(copyarr[r][i]==6) break;
			else if (copyarr[r][i]==0) copyarr[r][i]=-1;
		}
	}
	static void down(int r,int c) {
		for(int i=r+1; i<N; i++) {
			if(copyarr[i][c]==6) break;
			else if (copyarr[i][c]==0) copyarr[i][c]=-1;
		}
	}
	
	static int[] numbers;	// 리스트에 들어있는 CCTV들의 순서에 맞춰 각각의 CCTV들이 볼 방향을 중복순열로 저장
	
	static void permutation(int cnt) {
		
		if(cnt==list.size()) {
			make();
			for(int i =0; i<numbers.length; i++) {
				int r = list.get(i).r;
				int c = list.get(i).c;
				int num = list.get(i).num;
				int d = numbers[i];
				setting(r,c,num,d);
			}
			
			calculate();
			return;
		}
		
		for(int i=0; i<4; i++) {
			numbers[cnt] = i;
			permutation(cnt+1);
		}
		
	}
	
	static void setting(int r, int c, int num, int d) {
		if(num == 1) {
			if(d==0)
				up(r,c);
			else if (d==1)
				right(r,c);
			else if (d==2)
				down(r,c);
			else if (d==3)
				left(r,c);
		}
		else if (num == 2) {
			if(d==0 || d== 1) {
				up(r,c);
				down(r,c);
			}
			else {
				right(r,c);
				left(r,c);
			}
		}
		else if (num == 3) {
			if(d==0) {
				up(r,c);
				right(r,c);
			}
			else if(d==1) {
				right(r,c);
				down(r,c);
			}
			else if(d==2) {
				down(r,c);
				left(r,c);
			}
			else if(d==3) {
				left(r,c);
				up(r,c);
			}
		}
		else if (num == 4) {
			if(d==0) {
				left(r,c);
				up(r,c);
				right(r,c);
			}
			else if(d==1) {
				up(r,c);
				right(r,c);
				down(r,c);
			}
			else if(d==2) {
				right(r,c);
				down(r,c);
				left(r,c);
			}
			else if(d==3) {
				down(r,c);
				left(r,c);
				up(r,c);
			}
		}
		else if (num == 5) {
			left(r,c);
			up(r,c);
			right(r,c);
			down(r,c);
		}
		
	}
	
	static void make() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				copyarr[i][j] = arr[i][j];
			}
		}
	}
	
	static void calculate() {
		int temp = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(copyarr[i][j] == 0) 
					temp++;
				
			}
		}
		answer = Math.min(answer, temp);
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];		
		copyarr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]>0 && arr[i][j]<6)
					list.add(new CCTV(i,j,arr[i][j]));
			}
		}
		
		numbers = new int[list.size()];
		
		permutation(0);
		
		System.out.println(answer);
	}
}
