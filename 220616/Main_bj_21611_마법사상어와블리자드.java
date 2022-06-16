package bj21611;

import java.io.*;
import java.util.*;

public class Main_bj_21611_마법사상어와블리자드 {
	
	static int N,M,sr,sc,answer;
	static int[][] board;
	static int[] dr = {0,-1,1,0,0}, dc = {0,0,0,-1,1};	// 1: 상, 2: 하, 3: 좌, 4: 우
	static int[] mr = {0,1,0,-1}, mc = {-1,0,1,0}; // 0: 좌, 1: 하, 2: 우, 3: 상
	static Queue<Integer> q = new ArrayDeque<>();
	
	static void blizard(int d, int s) {
		for(int i=1; i<=s; i++) {
			board[sr+(dr[d])*i][sc+(dc[d])*i] = 0;
		}
		make_queue();
	}
	
	static void reset() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				board[i][j] = 0;
			}
		}
	}
	
	static void make_queue() {
		int r=sr, c=sc, d=0, time=1;
		
		while(r!=1 || c!=1) {
			for(int i=0; i<time; i++) {
				if(r==1 && c==1) return;
				r+=mr[d];
				c+=mc[d];
				if(board[r][c]!=0) q.offer(board[r][c]);
			}
			d = (d+1)%4;
			
			for(int i=0; i<time; i++) {
				if(r==1 && c==1) return;
				r+=mr[d];
				c+=mc[d];
				if(board[r][c]!=0) q.offer(board[r][c]);
			}
			d = (d+1)%4;
			time++;
		}
	}
	
	static void move() {
		reset();
		int r=sr, c=sc, d=0, time = 1;
		
		while(!q.isEmpty()) {
			for(int i=0; i<time; i++) {
				if(q.isEmpty() || (r==1 && c==1)) return;
				r+=mr[d];
				c+=mc[d];
				board[r][c] = q.poll();
			}
			d = (d+1)%4;
			
			for(int i=0; i<time; i++) {
				if(q.isEmpty() || (r==1 && c==1)) return;
				r+=mr[d];
				c+=mc[d];
				board[r][c] = q.poll();
			}
			d = (d+1)%4;
			time++;
		}	
	}
	
	static void destroy() {
		make_queue();		
		boolean flag = true;
		Queue<Integer> temp = new ArrayDeque<>();		
		while(flag) {
			if(q.isEmpty()) return;
			int num = q.poll(), cnt = 1, cur = 0;
			temp = new ArrayDeque<>();		
			flag = false;
			while(!q.isEmpty()) {
				cur = q.poll();
				if(cur==num) {
					cnt++;
				}
				else {
					if(cnt<4) {
						for(int i=0; i<cnt; i++) {
							temp.offer(num);
						}
					}
					else {
						flag = true;
						answer += cnt*num;
					}
					num = cur;
					cnt = 1;
				}
			}
			if(cnt>=4) {
				answer += cnt*num;
			}
			else {
				for(int i=0; i<cnt; i++) temp.offer(cur);
			}
			
			q = new ArrayDeque<>();
			
			while(!temp.isEmpty()) {
				q.offer(temp.poll());
			}
		}
	}
	
	static void change() {
		make_queue();
		Queue<Integer> temp = new ArrayDeque<>();
		if(q.isEmpty()) return;
		int cnt = 1, num = q.poll();
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur == num) cnt ++;
			else {
				temp.offer(cnt);
				temp.offer(num);
				cnt = 1;
				num = cur;
			}
		}
		temp.offer(cnt);
		temp.offer(num);
		q = new ArrayDeque<>();
		
		while(!temp.isEmpty()) {
			q.offer(temp.poll());
			if(q.size()==N*N-1) break;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N+1][N+1];
		sr = sc = (N+1)/2;
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=1; j<=N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			blizard(d,s);
			move();
			destroy();
			move();
			change();
			move();
			
		}
		
		System.out.println(answer);
		
	}
}
