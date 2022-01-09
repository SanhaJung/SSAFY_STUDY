package a0907;

import java.io.*;
import java.util.*;

public class Main_bj_14890_경사로 {
	static int [][] map;
	static int N, L;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int [N][N];
		for(int i =0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int horizontalCount=0;
		int verticalCount=0;
		
		for(int i =0; i<N; i++) {
			if(go(map[i])) horizontalCount++;
		}
		
		for(int i =0; i<N; i++) {
			int [] verticalArr = new int [N];
			for(int j =0; j<N; j++) {
				verticalArr[j] =  map[j][i];
			}
			
			if(go(verticalArr)) verticalCount++;
		}
		
		System.out.println(horizontalCount+verticalCount);
		
	}
	private static boolean go(int[] height) {
		boolean [] check = new boolean[N];
		
		  for (int i=0; i<N-1; i++) {
	            if (height[i] == height[i+1])	continue;
	            
	            if (Math.abs(height[i] - height[i+1]) > 1)	return false;
	            
	            if (height[i] - 1 == height[i+1]) {
	                for (int j=i+1; j<=i+L; j++) {
	                    if (j >= N || height[i+1] != height[j] || check[j] == true) {
	                        return false;
	                    } 
	                    check[j] = true;
	                }
	            }
	            else if (height[i] + 1 == height[i+1]) {
	                for (int j=i; j>i-L; j--) {
	                    if (j < 0 || height[i] != height[j] || check[j] == true) {
	                        return false;
	                    }
	                    check[j] = true;
	                }
	            }            
	        }
	 
		return true;
	}
}
