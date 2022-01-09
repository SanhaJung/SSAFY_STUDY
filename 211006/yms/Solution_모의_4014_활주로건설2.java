package a4014;

import java.util.*;
import java.io.*;

public class Solution_모의_4014_활주로건설2 {						//백준 14890 경사로
	
	static int N,L;
	static int[][] arr;
	
	static boolean check(int n, boolean flag) {
		int[] line = new int[N];
		boolean[] check = new boolean[N];
		
		for(int i=0; i<N; i++) {
			if(flag) line[i] = arr[n][i];	// flag = true >> 행 검사
			else line[i] = arr[i][n];		// flag = false >> 열 검사
		}
		
		for(int i=1; i<N; i++) {
			if(line[i-1] == line[i]) continue;
			else if (line[i-1]+1 == line[i]) {	// 오르막길
				for(int j=1; j<=L; j++) {
					if(i-j<0 || check[i-j] || line[i-j] != line[i-1]) return false; 
					check[i-j] = true;
				}
			}
			else if (line[i-1]-1 == line[i]) { // 내리막길
				for(int j=0; j<L; j++) {
					if(i+j>=N || check[i+j] || line[i+j] != line[i]) return false; 
					check[i+j] = true;
				}
				i += L-1;
			}
			else
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/a4014/input_모의_4014.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			arr = new int[N][N];
			
			int answer = 0;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<N; i++) {
				if(check(i,true)) answer++;
				if(check(i,false)) answer++;
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
		in.close();
	}
}
