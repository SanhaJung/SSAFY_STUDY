package bj16235;

import java.util.*;
import java.io.*;

public class Main_bj_16235_나무재테크 {
	
	static int[][] map;
	static ArrayList<Integer>[][] trees;
	static ArrayList<Integer>[][] dead_trees;
	static int[][] winter_nutrients;
	static int N,M,K;
	static int[] dr = {-1,-1,-1,0,0,1,1,1}, dc = {-1,0,1,-1,1,-1,0,1};
	
	static void spring() {
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				if(!trees[i][j].isEmpty()) {											// 해당 칸에 나무가 있다면
					Collections.sort(trees[i][j]);										// 리스트를 오름차순으로 정렬한다.
					for(int k=0; k<trees[i][j].size(); k++) {							// 해당 칸의 나무 갯수만큼
						if(map[i][j]>=trees[i][j].get(k)) {								// 남은 양분이 나무의 나이 이상이라면
							map[i][j] -= trees[i][j].get(k);							// 나이만큼 양분을 먹는다
							trees[i][j].set(k,trees[i][j].get(k)+1);
						}
						else {															// 나이보다 양분이 적다면
							dead_trees[i][j].add(trees[i][j].get(k));									// 죽은 나무에 추가한다.
							trees[i][j].remove(k--);									// 현재 나무 리스트에서 제거한다
						}
					}
				}
			}
		}
	}
	
	static void summer() {																
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				if(!dead_trees[i][j].isEmpty()) {										// 해당 칸에 죽은 나무가 있다면
					for(int k=0; k<dead_trees[i][j].size(); k++) {	
						map[i][j]+=dead_trees[i][j].get(k)/2;							// 나이를 2로 나눈 값을 양분으로 추가한다.
					}
					dead_trees[i][j].clear();
				}
			}
		}
	}
	
	static void fall() {															
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				if(!trees[i][j].isEmpty()) {											// 해당칸에 나무가 있다면
					for(int k=0; k<trees[i][j].size(); k++) {
						if(trees[i][j].get(k)%5==0) {									// 만약 나무의 나이가 5의 배수라면
							for(int d=0; d<8; d++) {
								int nr = i+dr[d];
								int nc = j+dc[d];
								if(0<nr && nr<=N && 0<nc && nc<=N) {					// 해당 방향이 인덱스 범위를 벗어나지 않으면
									trees[nr][nc].add(1);								// 해당 방향에 나이가 1인 나무를 생성한다.
								}
							}
						}
					}
				}
			}
		}
	}
	
	static void winter() {
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				map[i][j] += winter_nutrients[i][j];									// 입력에서 주어진 A배열을 현재 양분에 추가한다.
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				map[i][j] = 5;
			}
		}
		
		winter_nutrients = new int[N+1][N+1];
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=1; j<N+1; j++) {
				winter_nutrients[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		trees = new ArrayList[N+1][N+1];
		dead_trees = new ArrayList[N+1][N+1];
		
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				trees[i][j] = new ArrayList<>();
				dead_trees[i][j] = new ArrayList<>();
			}
		}
		
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			trees[r][c].add(n);
		}
		
		for(int i=0; i<K; i++) {
			spring();
			summer();
			fall();
			winter();
		}
		
		int answer = 0;
		
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				if(!trees[i][j].isEmpty()) {
					answer += trees[i][j].size();
				}
			}
		}
		
		System.out.println(answer);
	}
}
