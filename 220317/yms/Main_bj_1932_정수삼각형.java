package bj1932;

import java.util.*;
import java.io.*;

public class Main_bj_1932_정수삼각형 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(in.readLine());
		int answer = 0;
		int[][] triangle = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<=i; j++) {
				triangle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1; i<triangle.length; i++) {
            for(int j=0; j<triangle[i].length; j++) {
                // 제일 왼쪽 칸이면 바로 위에 숫자 누적
                if(j==0) triangle[i][j] += triangle[i-1][j];
                // 제일 오른쪽 칸도 바로 위에 숫자 누적
                else if(j==triangle[i].length-1) triangle[i][j] += triangle[i-1][j-1];  
                // 사이에 있는 칸이면 왼쪽 위, 오른쪽 위 중 더 큰 값을 누적
                else triangle[i][j] = Math.max(triangle[i][j],triangle[i][j]
                                               +Math.max(triangle[i-1][j],triangle[i-1][j-1]));
            }
        }
        
        for(int i=0; i<triangle[triangle.length-1].length; i++) {
            answer = Math.max(answer, triangle[triangle.length-1][i]);
        }
		
        System.out.println(answer);
	}
}
