package a0922.study;

import java.io.*;
import java.util.*;

public class Main_bj_16235_나무재테크 {
	// N: 땅의 크기
	// M: 입력되는 나무의 수
	// K: 수행 년 수
	static int N, M, K;
	static int[][] land;	   // 현재 땅의 양분
	static int[][] winterAdd;  // 겨울에 추가되는 양분
	static Deque<Tree> tree_q = new ArrayDeque<>();  // 나무의 좌표와 나이 저장
													 // 나무를 추가할때 앞으로 추가해서 age가 작은 나무 먼저 poll()
	// 가을 - 나무 번식시 사용
	// 좌상, 상, 우상, 우, 우하, 하, 좌하, 좌
	static int[] dr = {-1,-1,-1, 0, 1, 1, 1, 0};
	static int[] dc = {-1, 0, 1, 1, 1, 0,-1,-1};
	
	// 클래스 static으로 정의
	static class Tree implements Comparable<Tree>{
		// r  : 나무의 행의 좌표
		// c  : 나무의 열의 좌표
		// age: 나무의 나이
		int r, c;
		int age;
		public Tree(int r,int c,int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
		}
		// 나이로 비교할 필요가 있을 것같아서 정의
		// 하지만 쓰이지 않았음
		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_16235_나무재테크.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		land = new int[N][N];
		winterAdd = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				winterAdd[i][j] = Integer.parseInt(st.nextToken()); // 겨울에 추가할 양분 저장
				land[i][j] = 5;  									// 땅의 양분 초기화 
			}
		}
		// 나무 리스트에 추가
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			// (1,1) 부터 r,c 입력이 들어와서 인덱스와 맞춰주기위해 -1
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int age = Integer.parseInt(st.nextToken());
			tree_q.offer(new Tree(r, c, age));
		}
		// K년 반복
		for(int k=0;k<K;k++) {
			// 죽은 나무 저장 큐
			Deque<Tree> die_tree_q = new ArrayDeque<>();
			
			// 봄: 나무 성장
			for(int i=tree_q.size()-1;i>=0;i--) {		// 큐의 size가 변하기때문에 감소하면서 반복문 수행
				Tree temp = tree_q.poll();   			// 가장 먼저 저장된(같은 [r,c]에서 age가 적은) 나무 poll
				if(land[temp.r][temp.c] >= temp.age) {  // 땅의 양분 >= 나무의 나이라면
				   land[temp.r][temp.c] -= temp.age;    // 땅의 양분을 나무의 나이만큼 줄이고
				   temp.age++;							// 나무 나이 +1
				   tree_q.offer(temp);					// 큐에 나무 저장(나이가 많은 나무이므로 뒤쪽으로 저장)
				} else {								// 땅의 양분 < 나무의 나이라면
					die_tree_q.offer(temp);				// 나무는 죽고 죽은 나무 리스트에 추가
				}
			}
			// 여름: 죽은 나무 양분이됨
			for(int i=die_tree_q.size()-1;i>=0;i--) {   // 큐의 size가 변하기때문에 감소하면서 반복문 수행
				Tree temp = die_tree_q.poll();			// 죽은 나무 큐에서 poll
				land[temp.r][temp.c] += temp.age/2;		// 죽은 나무가 있는 위치에 나이/2만큼 양분 추가
			}
			// 가을: 나무 번식
			// 번식할 나무 저장
			Deque<Tree> temp_q = new ArrayDeque<>();   // 번식할 나무 저장 큐
			for(Tree t: tree_q) {					   // 살아있는 나무 큐 모두 조회(수정X: for each)
				if(t.age%5==0) {					   // 나무의 나이가 5의 배수라면
					temp_q.offer(t);				   // 번식할 나무 큐에 추가
				}
			}
			// 나무 번식
			for(int i=temp_q.size()-1;i>=0;i--) {      			  // 큐의 size가 변하기때문에 감소하면서 반복문 수행
				Tree temp = temp_q.poll();			   		  	  // 번식할 나무 저장
					for(int d=0;d<8;d++) {			 			  // 8방탐색
						int nr = temp.r + dr[d];			      // nr: 다음 r의 좌표 
						int nc = temp.c + dc[d];			      // nc: 다음 c의 좌표
						if(0<=nr && nr<N && 0<=nc && nc<N) {      // nr, nc가 인덱스 범위내에 있으면
							tree_q.offerFirst(new Tree(nr,nc,1)); // 나이가 1인나무 이므로 앞쪽에 저장
						}
					}
			}
			// 겨울: 양분 추가
			for(int i=0;i<N;i++) {					// 모든 땅에 접근하여
				for(int j=0;j<N;j++) {
					land[i][j] += winterAdd[i][j];  // 양분 추가
				}
			}
			
		}
		// K년 후 살아있는 나무의 수
		// = 살아있는 나무 큐의 크기
		System.out.println(tree_q.size());
		br.close();
	}

}
