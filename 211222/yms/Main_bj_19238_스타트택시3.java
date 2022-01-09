package bj19238;

import java.util.*;
import java.io.*;

public class Main_bj_19238_스타트택시3 {
	static int N,M;
	static int[][] map;
	static Passenger[] passengers;
	static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
	
	static class Taxi {
		int r;
		int c;
		int fuel;
		
		public Taxi(int r, int c, int fuel) {
			super();
			this.r = r;
			this.c = c;
			this.fuel = fuel;
		}
		
	}
	
	static class Passenger implements Comparable<Passenger> {
		int sr;
		int sc;
		int er;
		int ec;
		int distance;
		
		public Passenger(int sr, int sc, int er, int ec, int distance) {
			super();
			this.sr = sr;
			this.sc = sc;
			this.er = er;
			this.ec = ec;
			this.distance = distance;
		}

		@Override
		public int compareTo(Passenger o) {
			if(this.distance!=o.distance) return Integer.compare(this.distance, o.distance);
			else if (this.sr!=o.sr) return Integer.compare(this.sr, o.sr);
			else return Integer.compare(this.sc, o.sc);
		}
		
	}
	
	static int getDistance(int sr, int sc, int er, int ec) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		q.offer(new int[] {sr,sc,0});
		visited[sr][sc] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int distance = cur[2];
			
			if(r==er && c==ec)
				return distance;
			
			for(int d=0; d<4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(0<=nr && nr<N&& 0<=nc && nc<N && !visited[nr][nc] && map[nr][nc]>=0) {
					q.offer(new int[] {nr,nc,distance+1});
					visited[nr][nc] = true;
				}
			}
		}
		
		return -1;
	}
	
	static Passenger find_passenger(Taxi taxi) {
		Queue<int[]> q = new ArrayDeque<>();
		PriorityQueue<Passenger> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[N][N];
		q.offer(new int[] {taxi.r,taxi.c,0});
		visited[taxi.r][taxi.c] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0; i<size; i++) {
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];
				int distance = cur[2];
				
				if(map[r][c]>0) {
					passengers[map[r][c]].distance = distance;
					pq.offer(passengers[map[r][c]]);
				}
				
				for(int d=0; d<4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					if(0<=nr && nr<N&& 0<=nc && nc<N && !visited[nr][nc] && map[nr][nc]>=0) {
						q.offer(new int[] {nr,nc,distance+1});
						visited[nr][nc] = true;
					}
				}
			}
			if(!pq.isEmpty())
				break;
		}
		
		return pq.poll();
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int fuel = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		passengers = new Passenger[M+1];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) map[i][j]=-1;
			}
		}
		
		st = new StringTokenizer(in.readLine());
		int r = Integer.parseInt(st.nextToken())-1;
		int c = Integer.parseInt(st.nextToken())-1;
		
		Taxi taxi = new Taxi(r,c,fuel);
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(in.readLine());
			int sr = Integer.parseInt(st.nextToken())-1;
			int sc = Integer.parseInt(st.nextToken())-1;
			int er = Integer.parseInt(st.nextToken())-1;
			int ec = Integer.parseInt(st.nextToken())-1;
			passengers[i]=new Passenger(sr,sc,er,ec,0);
			map[sr][sc]=i;
		}		
		
		int cnt = 0;
		
		while(cnt<M) {
			Passenger cur = find_passenger(taxi);
			if(cur==null) {
				taxi.fuel = -1;
				break;
			}
			
			taxi.fuel -= cur.distance;
			if(taxi.fuel<0) {
				taxi.fuel = -1;
				break;
			}
			
			int driving_distance = getDistance(cur.sr, cur.sc, cur.er, cur.ec);
			if(driving_distance<0) {
				taxi.fuel = -1;
				break;
			}
			taxi.fuel -= driving_distance;
			if(taxi.fuel<0) {
				taxi.fuel = -1;
				break;
			}
			else {
				taxi.r = cur.er;
				taxi.c = cur.ec;
				map[cur.sr][cur.sc]= 0;
				taxi.fuel += (driving_distance*2);
				cnt++;
			}
		}
		
		System.out.println(taxi.fuel);
	}
}
