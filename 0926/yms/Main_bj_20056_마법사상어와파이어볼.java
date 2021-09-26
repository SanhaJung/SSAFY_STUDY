package bj20056;

import java.util.*;
import java.io.*;

public class Main_bj_20056_마법사상어와파이어볼 {
	
	static int[] dr = {-1,-1,0,1,1,1,0,-1}, dc = {0,1,1,1,0,-1,-1,-1};
	static int N,M,K;
	static ArrayList<Fireball>[][] map;
	static ArrayList<Fireball>[][] copy_map;
	static int answer = 0;
	
	static class Fireball {
		int r,c,m,s,d;

		public Fireball(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}		
	}
	
	static void move(int r, int c) {
		
		for(int t=0; t<map[r][c].size(); t++) {												//map[r][c]에 있는 모든 파이어볼의 수 만큼 반복
			Fireball fireball = map[r][c].get(t);
			int nr = r, nc = c;
			for(int i=0; i<fireball.s; i++) {												// 속력만큼 움직임
				nr += dr[fireball.d];
				nc += dc[fireball.d];
				
				if(nr == 0)																	// 범위가 넘어갈 경우 반대쪽으로 나오게 해줌
					nr = N;
				else if(nr == N+1)
					nr = 1;
				if(nc==0)
					nc = N;
				else if(nc == N+1)
					nc = 1;
			}			
			copy_map[nr][nc].add(new Fireball(nr,nc,fireball.m,fireball.s,fireball.d));		// copy_map에 움직인 파이어볼을 저장해줌
		}
		map[r][c].clear();																	// map[r][c]에 있는 모든 파이어볼을 움직인 후 클리어
	}
	
	static void divide(int r, int c) {				
			boolean status = true;															// 방향 조건을 맞추기 위해 생성한 변수
			if(copy_map[r][c].size()>1) {													// 파이어볼이 2개 이상 합쳐진 상태라면
				int nm = 0, ns =0, nd = (copy_map[r][c].get(0).d)%2;
				for(int i=0; i<copy_map[r][c].size(); i++) {
					nm += copy_map[r][c].get(i).m;
					ns += copy_map[r][c].get(i).s;

					if(status) {															// 처음에 입력받은 방향의 나머지(홀수,짝수 상태)가 지금 입력받은 방향과 다르다면
						if(nd != (copy_map[r][c].get(i).d)%2)
							status = false;	
					}
					
				}
				nm /= 5;
				ns /= copy_map[r][c].size();
				
				if(nm>0) {																	// 질량이 0일 경우 소멸해야하므로 0 이상일때만
					if(status) nd = 0;														// 모든 파이어볼의 방향의 나머지(홀수,짝수)가 같다면 0으로 설정
					else nd = 1;
					for(int d= nd%2; d<8; d+=2) {											// 문제의 조건에 맞춰 0,2,4,6과 1,3,5,7 방향으로 파이어볼을 나눠줌
						map[r][c].add(new Fireball(r,c,nm,ns,d));					
					}
				}
			}
			else {																			// 파이어볼이 합쳐진 상태가 아니라면 그대로 map에 저장
				map[r][c].add(copy_map[r][c].get(0));
			}
			copy_map[r][c].clear();															// 이동을 마친 뒤 copy_map을 클리어
		
	}	
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N+1][N+1];														// 2차원 배열에 파이어볼의 정보가 들어간 ArrayList가 들어가있음												
		copy_map = new ArrayList[N+1][N+1];													// 파이어볼을 이동시킬때 사용할 임시 배열

		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				map[i][j] = new ArrayList<>();
				copy_map[i][j] = new ArrayList<>();
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c].add(new Fireball(r,c,m,s,d));
		}
		
		for(int tc=0; tc<K; tc++) {																// 문제에서 주어진 횟수만큼
		
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(map[i][j].size()>0)														// 현재 위치에 파이어볼이 존재하면
						move(i,j);
				}
			}
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(copy_map[i][j].size()>0)													// 움직인 배열에서 현재 위치에 파이어볼이 존재하면
						divide(i,j);
				}
			}
		}
		
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				for(int k=0; k<map[i][j].size(); k++) {
					answer+=map[i][j].get(k).m;
				}
			}
		}
		System.out.println(answer);		
	}
}
