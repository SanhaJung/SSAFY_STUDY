package bj1956;

import java.util.*;
import java.io.*;

public class Main_bj_1956_운동 {
	
	static int INF = Integer.MAX_VALUE/2;
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int answer = INF;
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[][] matrix = new int [V+1][V+1];
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			matrix[start][end] = distance;
		}
		
		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				if(i!=j && matrix[i][j]==0) matrix[i][j] = INF;
			}
		}
		
		for(int k=1; k<=V; k++) {
			for(int i=1; i<=V; i++) {
				for(int j=1; j<=V; j++) {
					matrix[i][j] = Math.min(matrix[i][j], matrix[i][k]+matrix[k][j]);
				}
			}
		}
		
		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				if(i!=j && matrix[i][j]!=INF && matrix[j][i]!=INF) answer = Math.min(answer, matrix[i][j]+matrix[j][i]);
			}
		}
		
		if(answer == INF) answer = -1;
		System.out.println(answer);
		
	}
}
