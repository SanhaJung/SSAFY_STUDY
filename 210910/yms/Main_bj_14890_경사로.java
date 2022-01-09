package bj14890;

import java.util.*;
import java.io.*;

public class Main_bj_14890_경사로 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][N];
		
		int answer = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		label:for(int i=0; i<N; i++) {
			int cur = arr[i][0];
			boolean[] check = new boolean[N];
			for(int j=1; j<N; j++) {
				if(cur == arr[i][j]) continue;
				else {
					if(cur-arr[i][j] == 1) {
						for(int k=0; k<L; k++) {
							if(j+k>=N) continue label;
							else if(check[j+k] || arr[i][j+k]!=arr[i][j])
								continue label;
						}
						
						for(int k=0; k<L; k++)
							check[j+k] = true;
						cur = arr[i][j];
						j+=L-1;
					}
					else if (cur-arr[i][j] == -1) {
						for(int k=1; k<L+1; k++) {
							if(j-k<0) continue label;
							else if(check[j-k] || arr[i][j-k]!=arr[i][j-1])
								continue label;
						}						
						for(int k=1; k<L+1; k++)
							check[j-k] = true;
						cur = arr[i][j];
					}
					else {
						continue label;
					}
				}
			}
			answer++;
		}
		
		label:for(int i=0; i<N; i++) {
			int cur = arr[0][i];
			boolean[] check = new boolean[N];
			for(int j=1; j<N; j++) {
				if(cur == arr[j][i]) continue;
				else {
					if(cur-arr[j][i] == 1) {
						for(int k=0; k<L; k++) {
							if(j+k>=N) continue label;
							else if(check[j+k] || arr[j+k][i]!=arr[j][i])
								continue label;
						}
						
						for(int k=0; k<L; k++)
							check[j+k] = true;
						cur = arr[j][i];
						j+=L-1;
					}
					else if (cur-arr[j][i] == -1) {
						for(int k=1; k<L+1; k++) {
							if(j-k<0) continue label;
							else if(check[j-k] || arr[j-k][i]!=arr[j-1][i])
								continue label;
						}						
						for(int k=1; k<L+1; k++)
							check[j-k] = true;
						cur = arr[j][i];
					}
					else {
						continue label;
					}
				}
			}
			answer++;
		}
		
		System.out.println(answer);
		
	}
}
