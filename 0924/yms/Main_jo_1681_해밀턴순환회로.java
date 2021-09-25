package jo1681;

import java.util.*;
import java.io.*;

public class Main_jo_1681_해밀턴순환회로 {
	
	static boolean[] visited;
	static int N;
	static int[] numbers;
	static int[][] matrix;
	static int answer = Integer.MAX_VALUE/2;
	
	static void permutation(int cnt, int sum) {
		
		if(sum > answer)
			return;
		
		if(cnt==N) {
			if(matrix[numbers[N-1]][0]!=0) {
				sum += matrix[numbers[N-1]][0];
				answer = Math.min(sum, answer);
			}
			return;
		}
		
		for(int i=1; i<N; i++) {
			if(!visited[i] && matrix[numbers[cnt-1]][i] != 0) {
				visited[i] = true;
				numbers[cnt] = i;
				permutation(cnt+1,sum+matrix[numbers[cnt-1]][numbers[cnt]]);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine().trim());
		
		matrix = new int[N+1][N+1];
		visited = new boolean[N+1];
		numbers = new int[N+1];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		permutation(1,0);
		
		System.out.println(answer);
	}
}
