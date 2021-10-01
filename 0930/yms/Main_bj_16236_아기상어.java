package bj16236;

import java.util.*;
import java.io.*;

public class Main_bj_16236_아기상어2 {
	static int N;
	static int[][] map;
	static PriorityQueue<Fish> fish = new PriorityQueue<>();						// 먹을 수 있는 물고기들을 저장할 우선순위 큐
	static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
	
	static class Fish implements Comparable<Fish>{
		int r;
		int c;
		int distance;
		
		public Fish(int r, int c, int distance) {
			super();
			this.r = r;
			this.c = c;
			this.distance = distance;
		}

		@Override
		public int compareTo(Fish o) {
			int d_value = Integer.compare(this.distance, o.distance);
			int r_value = Integer.compare(this.r, o.r);
			int c_value = Integer.compare(this.c, o.c);
			if(d_value == 0) {														// 1. 거리가 가까울수 (distance가 작을수록)
				if(r_value == 0)													// 2. 위쪽에 있을수록 (r이 작을수록)
					return c_value;													// 3. 왼쪽에 있을수록 (c가 작을수록)
				return r_value;
			}
			return d_value;
		}
	}
	
	static class Shark {
		int r;																		//상어의 좌표 r c, 크기 size, 크기가 커지기 까지 필요한 경험치 exp
		int c;
		int size;
		int exp;
		
		public Shark(int r, int c, int size, int exp) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
			this.exp = exp;
		}
	}
	
	static void bfs(Shark shark) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		int[][] visited = new int[N][N];
		q.offer(new int[] {shark.r,shark.c});
		visited[shark.r][shark.c] = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			for(int d=0; d<4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				// 인덱스 범위 안에 들어오고, 아직 방문한적이 없고, 상어의 크기보다 물고기의 크기가 작으면서 물고기의 크기가 9 아래인경우
				// 상어가 물고기를 많이먹어서 크기가 9 이상이 될 경우, map 배열에서 상어가 9로 표시 되어 있고 초기 위치의 visited가 0이기 때무넹
				// 계속 자기자신을 먹는 걸로 처리되서 map[nr][nc]<9 이게 필요함
				if(0<=nr && nr<N && 0<=nc && nc<N && visited[nr][nc]==0 && map[nr][nc]<=shark.size && map[nr][nc]<9) {
					visited[nr][nc] = visited[r][c]+1;
					q.offer(new int[] {nr,nc});
					if(0<map[nr][nc] && map[nr][nc]<shark.size) {														// 먹을수 있는 물고기라면
						fish.add(new Fish(nr,nc,visited[nr][nc]));														// 우선순위 큐에 추가
					}
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		Shark shark = new Shark(0,0,2,0);
		int answer = 0;
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark.r = i;
					shark.c = j;
				}
			}
		}
		
		while(true) {
			fish.clear();												// 이전 반복에서 남은 물고기가 있을 수도 있으므로 초기화
			bfs(shark);
			if(fish.isEmpty())											// 더이상 먹을 물고기가 없으면 break
				break;
			
			Fish feed = fish.poll();
			map[shark.r][shark.c] = 0;									// 기존 상어의 위치를 0으로 바꾸고
			map[feed.r][feed.c] = 9;									// 먹은 위치를 9(상어)로 변경
			shark.r = feed.r;
			shark.c = feed.c;											// 상어의 좌표값도 바꿔줌
			answer += feed.distance;									// 먹으러 움직인 거리만큼 추가
			shark.exp++;												// 먹은 물고기의 갯수 증가
			if(shark.exp == shark.size) {								// 만약 먹은 물고기 수와 상어의 크기가 같다면
				shark.size++;											// 크기를 늘리고
				shark.exp = 0;											// 먹은 물고기 수 초기화
			}
		}
		
		System.out.println(answer);
	}
}
