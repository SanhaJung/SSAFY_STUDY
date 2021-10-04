package a1001.hw;

import java.util.*;
import java.io.*;

public class Main_bj_16235_나무재테크_우선순위큐 {
	static int N, M, K;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static PriorityQueue<Tree> trees;
	static ArrayDeque<Tree> die;
	static int[][] map, A;

	static class Tree implements Comparable<Tree>{
		int r, c;	// 나무 위치 (r, c)
		int age;	// 나무 나이 

		public Tree(int r, int c, int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // N*N
		M = Integer.parseInt(st.nextToken()); // M개의 나무
		K = Integer.parseInt(st.nextToken()); // K년

		map = new int[N][N]; // 양분
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 5);
		}

		A = new int[N][N]; // 추가되는 양분 양
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 나무들 정보 저장
		trees = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());

			trees.offer(new Tree(r, c, age));
		}
		
		
		// K년 반복 - (봄, 여름, 가을, 겨울)
		for (int year = 0; year < K; year++) {
			die = new ArrayDeque<>();				     // 해마다 죽은 나무 저장할 큐
			PriorityQueue<Tree> pq = new PriorityQueue<>();	
			// 원래의 우선순위 큐 trees에 넣으면 나이가 작은 게 자동으로 앞으로 가서 꼬인다.
			// 임시로 담을 큐 
			
														 // *봄* - 양분 먹기
			int size = trees.size();					 // 살아있는 나무만큼 돌기
			for (int i = 0; i < size; i++) {
				Tree tree = trees.poll();				
				if (map[tree.r][tree.c] >= tree.age) {   // 양분이 나무의 나이보다 많다면
					map[tree.r][tree.c] -= tree.age;	 // 양분을 먹고 
					tree.age++; 						 // 나무 나이 +1				
					pq.offer(tree);						 // 큐에서 뺐으니까 다시 넣기
				} else die.offer(tree);					 // (양분 < 나무 나이) -> 나무 죽음
			}
			trees = pq;									 // 복사
			
			
			size = die.size();							 // *여름* - 죽은 나무들이 양분이 된다.
			for (int i = 0; i < size; i++) {				
				Tree tree = die.poll();					
				map[tree.r][tree.c] += tree.age / 2;	// 죽은 나무의 1/2만큼 양분이 된다.	
			}

			
			
			ArrayList<Tree> list = new ArrayList<>();	// *가을* - 나무 나이 % 5 == 0이면 8방으로 번식 
			for (Tree tree : trees) {					// 나무의 나이가 5로 나누어 떨어지는 나무들
				if (tree.age % 5 == 0)					// 나무들이 큐에 담겨 있어서
					list.add(tree);
			}
			for (int i = 0; i < list.size(); i++) {
				Tree tree = list.get(i);
				for (int k = 0; k < 8; k++) {
					int nr = tree.r + dr[k];
					int nc = tree.c + dc[k];
					if (0 <= nr && nr < N && 0 <= nc && nc < N) { // 범위 안에 들어오면
						trees.offer(new Tree(nr, nc, 1));	 	  // 나이가 1인 나무 생성
					}											  
				}												  
			}													 
			
														
			for (int r = 0; r < N; r++) {				 // *겨울* - 입력에서 주어진 양분만큼 각 땅에 추가됨
				for (int c = 0; c < N; c++) {
					map[r][c] += A[r][c];
				}
			}
		}
		
		System.out.println(trees.size());				// 종료 - K년 후 남의 나무의 수 출력
	}
}
