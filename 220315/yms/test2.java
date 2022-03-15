package 모의역량테스트;

import java.util.*;
import java.io.*;

public class test2_2 {
	
	static int[][] map;
	static boolean[] check;
	static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
	static int N, hr, hc, num, answer;
	static ArrayList<Node> list;
	static int[] numbers;
	static boolean[] visited;
	
	static class Node{
		int r;
		int c;
		int type;
		
		public Node(int r, int c, int type) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
		}
	}
	
	static void permutation(int cnt) {
		
		if(cnt == list.size()) {
			hr = 1;
			hc = 1;
			int sum = 0;
			check = new boolean[num+1];
			for(int i=0; i<list.size(); i++) {
				Node node = list.get(numbers[i]);
				if(node.type<0 && !check[Math.abs(node.type)]) return;
				if(node.type>0) check[node.type] = true;
				sum += Math.abs(hr-node.r)+Math.abs(hc-node.c);
				hr = node.r;
				hc = node.c;
			}
			
			if(answer>sum) {
				System.out.println(sum);
				for(int i=0; i<list.size(); i++) {
					Node node = list.get(numbers[i]);
//					System.out.print("("+node.r+","+node.c+") ");
					System.out.print(node.type+",");
				}
				System.out.println();
			}
			
			answer = Math.min(answer, sum);
			return;
		}
		
		for(int i=0; i<list.size(); i++) {
			if(!visited[i]) {
				visited[i] = true;
				numbers[cnt] = i;
				permutation(cnt+1);
				visited[i] = false;
			}
		}
	}
	
	static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N+1][N+1];
		q.offer(new int[] {hr,hc});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			for(int d=0; d<4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(0<nr && nr<=N && 0<nc && nc<=N && !visited[nr][nc]) {
					if(map[nr][nc]!=0) {
						list.add(new Node(nr,nc,map[nr][nc]));
					}
					visited[nr][nc] = true;
					q.offer(new int[] {nr,nc});
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("src/모의역량테스트/sample_input1.txt")));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(in.readLine());
		
		for(int tc=1; tc<=5; tc++) {
			N = Integer.parseInt(in.readLine());
			map = new int[N+1][N+1];
			list = new ArrayList<>();
			answer = Integer.MAX_VALUE/2;
			num = 0;
			
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=1; j<=N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]>0) num++;
				}
			}
			
			numbers = new int[num*2];
			visited = new boolean[num*2];
			
			check = new boolean[num+1];
			hr = 1;
			hc = 1;
			
			bfs();
			for(Node node : list) {
				System.out.println(node.r+","+node.c+","+node.type);
			}
			permutation(0);
			
			sb.append("#").append(tc).append(" ").append(answer).append('\n');
			
//			for(int i=1; i<=N; i++) {
//				for(int j=1; j<=N; j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
		}
		
		System.out.println(sb.toString());
	}
}
