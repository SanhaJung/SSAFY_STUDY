package bj15685;

import java.util.*;
import java.io.*;

public class Main_bj_15685_드래곤커브 {
	
	static int[] dr = {0,-1,0,1}; // 0 우 1 상 2 좌 3 하
	static int[] dc = {1,0,-1,0};
	static boolean[][] arr = new boolean[101][101];
	static ArrayList<Integer> curve = new ArrayList<>();
	
	static void make(int g) {
		for(int i=0; i<g; i++) {
			int size = curve.size();
			for(int j=0; j<size; j++) {
				int d = curve.get(size-1-j);
				curve.add((d+1)%4);
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(in.readLine());
		int answer = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());

			curve.add(d);
			make(g);
			arr[x][y] = true;
			for(int c : curve) {
				x+=dr[c];
				y+=dc[c];
				arr[x][y] = true;
			}
			curve.clear();
			
		}
		
		
		
		for(int i=1; i<100;i++) {
			for(int j=1; j<100; j++) {
				if(arr[i][j] && arr[i+1][j] && arr[i][j+1] && arr[i+1][j+1])
					answer++;
			}
		}
		
		System.out.println(answer);
	}
}
