package bj19236;

import java.util.*;
import java.io.*;

public class Main_bj_19236_청소년상어2 {
	
	static int[] dr= {-1,-1,0,1,1,1,0,-1};	//↑, ↖, ←, ↙, ↓, ↘, →, ↗
	static int[] dc= {0,-1,-1,-1,0,1,1,1};
	static int answer;
	
	static class Fish{
		int r;
		int c;
		int dir;
		int sum;
		
		public Fish(int y, int x, int dir,int sum) {
			super();
			this.r = y;
			this.c = x;
			this.dir = dir;
			this.sum=sum;
		}
	}
	
	static ArrayList<Fish> getFishList(int[][] map, Fish shark, Fish[] fishes){		// 먹을 수 있는 물고기들을 담는 리스트 반환
		ArrayList<Fish> list = new ArrayList<>();
		Queue<Fish> q = new ArrayDeque<>();
		q.add(shark);
		
		while(!q.isEmpty()) {
			Fish cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int d = cur.dir;
			
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(0<=nr && nr<4 && 0<=nc && nc<4) {
				if(map[nr][nc]==0) {					// 빈칸이면 그냥 이동
					q.add(new Fish(nr,nc,d,0));
				} else {
					int num = map[nr][nc];
					q.add(new Fish(nr,nc,d,0));
					list.add(new Fish(nr,nc,fishes[num].dir,0));	// 반환할 리스트에 물고기 넣어줌
				}
			}
		
		}
		return list;
	}

	static void moveShark(int[][] map, Fish shark, Fish[] fishes) {
		if(answer < shark.sum) answer = shark.sum;

		moveFish(map,shark,fishes);	// 물고기 움직임
		
		ArrayList<Fish> list = getFishList(map, shark, fishes);	// 물고기가 움직인 상태에서 상어가 먹을수잇는 모든 물고기

		for (Fish fish : list) {
			int[][] copy_map = copyMap(map);
			Fish[] copy_fishes = copyFish(fishes);
			
			int num = copy_map[fish.r][fish.c];

			copy_map[fish.r][fish.c] = 0;
			
			Fish newShark=new Fish(fish.r,fish.c,fish.dir,shark.sum+num);
			copy_fishes[num].dir = -1;
			moveShark(copy_map,newShark,copy_fishes);
		}
	}

	static void moveFish(int[][] map,Fish shark,Fish[] fishes) {
		for (int num = 1; num <= 16; num++) {
			
			if(fishes[num].dir == -1)	continue;	// 죽은 물고기라면 스킵
			
			int d = fishes[num].dir;
			int r = fishes[num].r;
			int c = fishes[num].c;
			
			while(true) {
				int nr = r+dr[d];
				int nc = c+dc[d];

				if((nc<0 || nr<0 || nc>=4 || nr>=4) || (nc==shark.c && nr==shark.r)) {	// 인덱스를 벗어나거나 상어가 있는 칸이라면
					d=(d+1)%8;
					if(d==fishes[num].dir)	break;	// 모든 방향 탐색하면 처음 방향으로 돌아오므로
					continue;
				}
				
				if(map[nr][nc]==0) {				// 빈칸이라면
					map[nr][nc]=num;
					map[r][c]=0;
					fishes[num]=new Fish(nr,nc,d,0);
					break;
					
				}
				else {								//물고기 있는 칸이라면 서로 위치를 바꿔줌
					int temp = map[nr][nc];
					map[nr][nc] = num;
					map[r][c] = temp;
					
					fishes[num].r = nr;
					fishes[num].c = nc;
					fishes[num].dir = d;
					
					fishes[temp].r = r;
					fishes[temp].c = c;
					break;
				}
				
			}
			
		}

	}
	
	static int[][] copyMap(int[][] map){
		int[][] copy=new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copy[i][j]=map[i][j];
			}
		}
		return copy;
	}
	
	static Fish[] copyFish(Fish[] fishes) {
		Fish[] copy=new Fish[17];
		for (int i = 1; i <=16; i++) {
			Fish cur=fishes[i];
			copy[i]=new Fish(cur.r,cur.c,cur.dir,0);
		}
		return copy;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int[][] map = new int[4][4];				// 물고기의 위치 
		Fish shark = null; 							// 상어
		Fish[] fishes = new Fish[17];

		for (int i = 0; i < 4; i++) {
			st= new StringTokenizer(in.readLine());
			for (int j = 0; j < 4; j++) {
				int num= Integer.parseInt(st.nextToken());
				int dir= Integer.parseInt(st.nextToken());
				map[i][j] = num;
				fishes[num] = new Fish(i,j,dir-1,0);
			}
		}
		
		int num = map[0][0];
		shark = new Fish(0,0,fishes[num].dir,num);
		fishes[num].dir=-1;							//잡아 먹힘 표시
		map[0][0]=0;								//물고기 먹음
		
		
		moveShark(map,shark,fishes);
		
		System.out.println(answer);
	}
}