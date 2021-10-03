package bj17142;

import java.io.*;
import java.util.*;

public class Main_bj_17142_연구소3 {
	static int N, M;
	static int[] numbers; // 뽑은 조합을 기록
	static ArrayList<Virus> virus = new ArrayList<>();
	
	static int[][] map;
	static boolean[][] visited;
	static int blank;		// 빈칸 기록
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	static int min = Integer.MAX_VALUE/2;
	
	static class Virus {
		int r;
		int c;
		int time;
		
		public Virus(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	
	static void combination(int cnt, int start) {
		if(cnt == M) {
			bfs();
			return;
		}
		
		for (int i = start; i < virus.size(); i++) {
			numbers[cnt] = i;
			combination(cnt+1, i+1);
		}
	}

	static void bfs() {
		ArrayDeque<Virus> q = new ArrayDeque<>();
		visited = new boolean[N][N];
		
		for (int i = 0; i < M; i++) {
			q.add(virus.get(numbers[i]));
			visited[virus.get(numbers[i]).r][virus.get(numbers[i]).c] = true;
		}
		
		int time = 0;
		int count = 0;		// 몇 칸을 확산 했는지 기록할 변수
		
		while(!q.isEmpty()) {
			Virus virus = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = virus.r + dr[d];
				int nc = virus.c + dc[d];
			
				time = virus.time;
				
				if(0<=nr && nr<N && 0<=nc && nc<N && !visited[nr][nc]) {
					if(map[nr][nc] == 0) {										// 빈칸이라면
						visited[nr][nc] = true;
						count++;
						q.add(new Virus(nr,nc,virus.time+1));
					}
					if(map[nr][nc] == 2) {										// 비활성바이러스라면
						visited[nr][nc] = true;
						q.add(new Virus(nr,nc,virus.time+1));
					}
				}
			}
			
			if(count == blank) {
				time++;	// 이경우, 새로 뽑은 시간값 할당이 안되므로, 임의로 1 증가
				break;
			}
		};
	
		if(count != blank) return;	// 다 못채우면 그냥 끝내기
		
		// time-1의 이유 : 마지막에 +1한 상태를 q에 날리기 때문
		min = Math.min(min, time-1);
	}	

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); // 연구소 크기
		M = Integer.parseInt(st.nextToken()); // 바이러스 개수
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virus.add(new Virus(i,j,1));
				}else if(map[i][j] == 0) {
					blank++;
				}
			}
		}
		
		if(blank == 0) {
			System.out.println(0);
			return;
		}
		
		numbers = new int[M];
		
		combination(0, 0);
		
		if(min == Integer.MAX_VALUE/2) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(min);
	}
}