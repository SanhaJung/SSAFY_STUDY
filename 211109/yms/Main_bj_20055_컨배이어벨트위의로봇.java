package bj20055;

import java.util.*;
import java.io.*;

public class Main_bj_20055_컨배이어벨트위의로봇 {
	
	static int[] belt;
	static int[] robot;
	static int N,K,cnt;
	
	static void rotate() {
		int belt_temp = belt[2*N];
		int robot_temp = robot[N];
		
		for(int i=2*N; i>0; i--) {
			belt[i] = belt[i-1];
		}
		belt[1] = belt_temp;
		
		for(int i=N; i>0; i--) {
			robot[i] = robot[i-1];
		}
		robot[1] = robot_temp;
		
		if(robot[N] == 1) {
			robot[N] = 0;
		}
		
	}
	
	static void move() {
		for(int i=N-1; i>0; i--) {
			if(robot[i]==1 && robot[i+1]==0 && belt[i+1]>0) {
				robot[i+1] = 1;
				robot[i] = 0;
				if(i+1==N) {
					robot[i+1] = 0;
				}
				if(--belt[i+1]==0)
					cnt++;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 컨베이어 벨트의 길이
		K = Integer.parseInt(st.nextToken());	// 종료 조건
		
		belt = new int[2*N+1];
		robot = new int[N+1];
		
		st = new StringTokenizer(in.readLine());
		for(int i=1; i<=2*N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());		// 벨트의 내구도
		}
		
		int time = 0;
		
		while(cnt<K) {
			rotate();
			move();
			if(belt[1]>0) {
				robot[1] = 1;
				if(--belt[1]==0)
					cnt++;
			}
			time++;
		}
		
		System.out.println(time);
		
	}
}
