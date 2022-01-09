package bj11404;

import java.util.*;
import java.io.*;

public class Main_bj_11404_플로이드 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(in.readLine());
		int m = Integer.parseInt(in.readLine());
		
		int[][] map = new int[n+1][n+1];
		int INF = Integer.MAX_VALUE/2;
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			if(map[r][c]==0)
				map[r][c] = v;
			else 
				map[r][c] = Math.min(map[r][c], v);
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(i!=j && map[i][j]==0) {
					map[i][j] = INF;
				}
			}
		}
		
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
				}
			}
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(map[i][j] == INF)
					map[i][j] = 0;
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
