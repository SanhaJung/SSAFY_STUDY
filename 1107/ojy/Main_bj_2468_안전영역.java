package hw1107;

import java.io.*;
import java.util.*;
public class Main_bj_2468_안전영역 {
	//높이의 범위 1~100까지
	//높이가 n이하인 지점을 모두 잠기게했을때, 남아있는 안전영역의 최대 개수 구하기
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int [][]arr;
	static int N,tmax,MAX=1;
	/*
	 * 3
		1 1 1
		1 1 1
		1 1 1 =>1 =>0
		와 같은 결과 방지하기 위해 MAX 1로 해놓음
	 */
	static int[]dx= {-1,0,1,0};
	static int[]dy= {0,1,0,-1};
	private static void init(int n) {
		int copy[][] = new int[N][N];
		boolean visit[][]= new boolean[N][N];
		int count = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(arr[i][j]<=n) {		//비안전영역은 1
					copy[i][j]=1;
				}else					//안전영역은 0
					copy[i][j]=0;
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(copy[i][j]==0&&!visit[i][j]) {
					dfs(i,j,visit,copy);
					count++;
				}
			}
		}
		MAX = Math.max(count, MAX);
		
	}
	private static void dfs(int i,int j,boolean [][]visit,int[][]copy) {
		visit[i][j] = true;
		for(int p=0;p<4;p++) {
			int cx = i+dx[p];
			int cy = j+dy[p];
			if(0<=cx&&cx<N&&0<=cy&&cy<N) {
				if(copy[cx][cy]==0&&!visit[cx][cy]) {	//방문하지 않고 안전영역일때
					dfs(cx,cy,visit,copy);
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		tmax=1;
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				tmax = Math.max(tmax, arr[i][j]);
			}
		}
		for(int i=1;i<=tmax;i++) {	//높이
			init(i);
		}
		System.out.println(MAX);
	}
}
