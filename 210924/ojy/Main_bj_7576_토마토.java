package hw0924;

import java.io.*;
import java.util.*;
public class Main_bj_7576_토마토 {
	static class Node{
		int x;
		int y;
		int t;
		public Node(int x, int y, int t) {
			this.x = x;
			this.y = y;
			this.t = t;
		}
		
	}
	static int m,n,total,result=-1;
	static int [][]arr;
	static Queue<Node>queue = new LinkedList<>();
	static boolean [][] visit;
	static int[]dx = {0,-1,0,1};
	static int[]dy = {1,0,-1,0};
	private static void BFS() {
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			if(total == 0) { 
				result = Math.max(node.t,result);
			}
			for(int i=0;i<4;i++) {
				int cx = node.x+dx[i];
				int cy = node.y+dy[i];
				if(0<=cx&&cx<n&&0<=cy&&cy<m) {
					if(!visit[cx][cy]&&arr[cx][cy]==0) {
						visit[cx][cy] = true;
						arr[cx][cy] =1;
						total--;
						queue.offer(new Node(cx,cy,node.t+1));
					}
				}
			}
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visit = new boolean[n][m];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==0)
					total++;
				else if(arr[i][j]==1) {
					queue.offer(new Node(i,j,0));
					visit[i][j] = true;
				}
			}
		}
		BFS();
		System.out.println(result);
	}

}
