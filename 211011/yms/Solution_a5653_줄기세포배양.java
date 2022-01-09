package a5653;

import java.util.*;
import java.io.*;

public class Solution_a5653_줄기세포배양 {
	static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
	static PriorityQueue<Cell> pq = new PriorityQueue<>();
	static boolean[][] visited;
	static int N,M,K,answer;
	
	static class Cell implements Comparable<Cell> {
		int r;
		int c;
		int life;
		int time;
		
		public Cell(int r, int c, int life, int time) {
			super();
			this.r = r;
			this.c = c;
			this.life = life;
			this.time = time;
		}

		@Override
		public int compareTo(Cell o) {
			return -Integer.compare(this.life, o.life);
		}
	}
	
	static void bfs() {
		ArrayDeque<Cell> q = new ArrayDeque<>();
		int time = 0;
		
		while(!pq.isEmpty()) {
			int size = pq.size();
			for(int i=0; i<size; i++) {
				Cell cell = pq.poll();
				cell.time--;
				if(cell.life>cell.time) {						// 활성 상태
					for(int d=0; d<4; d++) {
						int nr = cell.r+dr[d];
						int nc = cell.c+dc[d];
						if(!visited[nr][nc]) {
							q.offer(new Cell(nr,nc,cell.life,cell.life*2));
							visited[nr][nc] = true;
						}
					}
				}
				if(cell.time>0){				// 세포가 살아있다면
					q.offer(cell);
				}
			}
			
			if(++time==K) {
				answer = q.size();
				break;
			}
			else {
				while(!q.isEmpty()) {
					pq.offer(q.poll());
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/a5653/input_모의_5653.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			visited = new boolean[N+K][M+K];
			
			for(int i=K/2; i<K/2+N; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=K/2; j<K/2+M; j++) {
					int temp =Integer.parseInt(st.nextToken());
					if(temp>0) {
						pq.offer(new Cell(i,j,temp,temp*2));
						visited[i][j] = true;
					}
				}
			}
			
			bfs();
			
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.println(sb.toString());
		in.close();
	}
}
