package bj17136;

import java.util.*;
import java.io.*;

//bfs
public class Main_bj_17136_색종이붙이기 {
	
	static int answer = -1;
	
	static class Info {
		int[][] map;
		int[] paper;
		int cnt;
		
		public Info(int[][] map, int[] paper, int cnt) {
			this.map = map;
			this.paper = paper;
			this.cnt = cnt;
		}
	}
	
	static void bfs(int[][] arr) {
		Queue<Info> q = new ArrayDeque<>();
		int[] papers = {5,5,5,5,5};
		q.offer(new Info(arr,papers,0));
		
		while(!q.isEmpty()) {
			Info info = q.poll();
			
			int r=-1, c=-1;			
			label:for(int i=0; i<10; i++) {
					for(int j=0; j<10; j++) {
						if(info.map[i][j]==1) {
							r = i;
							c = j;
							break label;
						}
					}
			}
			
			if(r==-1 && c==-1) {
				answer = info.cnt;
				return;
			}
			
			for(int size=4; size>=0; size--) {
				if(info.paper[size]>0 && check(r,c,size,info.map)) {
					int[][] map = new int[10][10];
					for(int i=0; i<10; i++) {
						for(int j=0; j<10; j++) {
							map[i][j] = info.map[i][j];
						}
					}
					for(int i=r; i<=r+size; i++) {
						for(int j=c; j<=c+size; j++) {
							map[i][j]=0;
						}
					}
					int[] paper = new int[6];
					for(int i=0; i<5; i++) {
						paper[i] = info.paper[i];
					}
					paper[size]--;
					q.offer(new Info(map,paper,info.cnt+1));
				}
			}
		}
	}
	
	static boolean check(int r, int c, int size, int[][] map) {
		if(r+size>=10 || c+size>=10) return false;
		for(int i=r; i<=r+size; i++) {
			for(int j=c; j<=c+size; j++) {
				if(map[i][j]==0) return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int[][] map = new int[10][10];
		
		for(int i=0; i<10; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs(map);
		
		System.out.println(answer);
	}
}
