package a0924;

import java.io.*;
import java.util.*;

public class Main_bj_17837_새로운게임2 {
	static int [][] map;
	static int N,K;
	static int [] di = {0,0,0,-1,1};  //0, 동 서 북 남
	static int [] dj = {0,1,-1,0,0}; 
	
	static Horse[] horses;
	static ArrayList<Integer>[][] chip;
	
	static class Horse {
		int id;
		int i,j;
		int dir;
		public Horse(int id, int i, int j, int dir) {
			this.id = id;
			this.i = i;
			this.j = j;
			this.dir = dir;
		}
		public void changeDir() {
			// 방향  1 2 3 4 동 서 북 남
			// 1 -> 2 , 2->1 , 3->4, 4->3
			if (dir == 1)
				dir = 2;
			else if (dir == 2)
				dir = 1;
			else if (dir == 3)
				dir = 4;
			else if(dir ==4)
				dir = 3;
		}
	
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int [N+1][N+1];
		chip = new ArrayList[N+1][N+1];
		horses = new Horse[K+1];
		
		//0은 흰색, 1은 빨간색, 2는 파란색
		for(int i =1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =1; j<=N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				chip[i][j] = new ArrayList<>(); //칩 순서 담을 배열 초기화
			}
		}
		//for(int [] a : map ) System.out.println(Arrays.toString(a));
		//이동 방향은 4보다 작거나 같은 자연수이고 1부터 순서대로 →, ←, ↑, ↓의 의미
		
		for(int k =1; k<=K; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			horses[k] = new Horse(k, i, j, dir);
			chip[i][j].add(k);  //1번 부터 k번 까지의 말 chip 배열에 입력
			
		}
		
		for(int time =1; time<=1000 ; time++) {
			for(int idx=1; idx<=K; idx++) {
				if(move(idx)) {
					System.out.println(time);
					return;
				}
			}
			
		}
		System.out.println(-1);
		
	}
	private static boolean move(int idx) {
		Horse cur = horses[idx];
		int ni = cur.i + di[cur.dir];
		int nj = cur.j + dj[cur.dir];
		
		//범위 밖 이거나 파랑색
		if (!isRangeValid(ni, nj)) {
			cur.changeDir();
			ni = cur.i + di[cur.dir];
			nj = cur.j + dj[cur.dir];
		}
		
//		if (ni < 0 || ni >= N || nj < 0 || nj >= N || map[ni][nj] == 2) {
//			//바뀐 방향에서 다시 거리 밖이거나 파란색 움직이지 않음
//			//cur.changeDir();
//		}
//		else {
//			action(cur, ni, nj);
//			if(chip[ni][nj].size()>=4) return true;
//		}
		
		if(isRangeValid(ni, nj)) {
			action(cur, ni, nj);
			if(chip[ni][nj].size()>=4) return true;
		}
		return false;
	}
	
	private static void action(Horse cur, int ni, int nj) {
		ArrayList<Integer> from = chip[cur.i][cur.j];
		ArrayList<Integer> to = chip[ni][nj];
		
		int height = from.indexOf(cur.id); //말 높이
		int fromSize = from.size();
		
		switch(map[ni][nj]) {
		case 0: //흰
		    // 말 위 말들 같이 이동
		    for(int k = height; k < fromSize; k++){
		    	//리스트 삭제 
		        int horseID = from.remove(height);
		        // 말 좌표 변경
		        horses[horseID].i = ni;
		        horses[horseID].j = nj;
		        to.add(horseID);
		    }
		    break;
		    
		case 1: //빨
			// 말 위 순서 뒤집어서  이동 
			for(int k = height; k < fromSize; k++){
		    	// 리스트 끝자리에서 삭제 (뒤집기)
		    	int horseID = from.remove(from.size()-1);
		        // 말 좌표 변경
		        horses[horseID].i = ni;
		        horses[horseID].j = nj;
		        to.add(horseID);
		    }
		    break;
		}
		
	}
	
	private static boolean isRangeValid(int nX, int nY) {
		if( nX > N || nX < 1 || nY > N || nY < 1 || map[nX][nY] == 2) {
			// 체스판을 벗어나거나 파란색 칸인 경우
			return false;
		}
		return true;
	}
	
}
