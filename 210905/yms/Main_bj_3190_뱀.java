package bj3190;

import java.util.*;
import java.io.*;

public class Main_bj_3190_뱀 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[] dr = {0,1,0,-1};
		int[] dc = {1,0,-1,0};
		int answer = 0;
		
		int N = Integer.parseInt(in.readLine());
		int[][] arr = new int[N][N];
		
		int K = Integer.parseInt(in.readLine());
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(in.readLine());
			int ar = Integer.parseInt(st.nextToken())-1;
			int ac = Integer.parseInt(st.nextToken())-1;
			arr[ar][ac] = 1;
		}
		
		int L = Integer.parseInt(in.readLine());
		int[][] command = new int[L][2];
		
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(in.readLine());
			command[i][0] = Integer.parseInt(st.nextToken());
			command[i][1] = (int)(st.nextToken().charAt(0));
		}
		///// 입력 끝
		
		ArrayDeque<int[]> snake = new ArrayDeque<>();
		snake.offerFirst(new int[] {0,0});
		int d = 0;
		arr[0][0] = 2;
		
		for(int i=0; i<=L; i++) {	
			int time = 0;
			int cnt = 0;
			if(i==0) {
				time = command[0][0];
			}
			else if (i==L)
				time = 100;
			else {
				time = command[i][0] - command[i-1][0];
			}
			
			while(time>cnt) {
				answer++;
				if(i<L)
					cnt++;
				int nr = snake.peekFirst()[0]+dr[d];
				int nc = snake.peekFirst()[1]+dc[d];
				
				if(0<=nr && nr<N  && 0<=nc && nc<N && arr[nr][nc]!=2) {
					snake.offerFirst(new int[] {nr,nc});
					if(arr[nr][nc] == 0) {
						int[] tail = snake.pollLast();
						arr[tail[0]][tail[1]] = 0;
						arr[nr][nc] = 2;
					}
					else {
						arr[nr][nc] = 2;
					}
				}
				else {
					System.out.println(answer);
					return;
				}
			}

			if(command[i][1] == 'L') d = (4+d-1)%4;
			else d = (d+1)%4;	
		}
		
		System.out.println(answer);
		
	}
}
