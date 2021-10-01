package hw0924;
import java.io.*;
import java.util.*;
public class Main_bj_17144_미세먼지안녕 {
	static class Dust{
		int x;
		int y;
		int size;
		public Dust(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}
		
	}
	static int r,c,t;
	static int[][]arr;
	static int []dx= {0,-1,0,1};
	static int []dy = {1,0,-1,0};
	static Queue<Dust>queue = new LinkedList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		int row = -1;
		arr = new int[r][c];
		for(int i=0;i<r;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<c;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(row==-1&&arr[i][j]==-1) {
					row = i;
				}
			}
		}
		for(int tc=0;tc<t;tc++) {
			queue = new LinkedList<>();
			for(int i=0;i<r;i++) {
				for(int j=0;j<c;j++) {
					if(arr[i][j]>0)
						queue.offer(new Dust(i,j,arr[i][j]));
				}
			}
			spread();
			clean(row);
		}
		int answer = 0;
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				if(arr[i][j] != -1)
					answer+=arr[i][j];
			}
		}
		System.out.println(answer);
		br.close();
	}
	private static void clean(int row) {
		int first = row;
		int second = row+1;
		for (int i = first - 1; i > 0; i--) 
            arr[i][0] = arr[i-1][0];
        for (int i = 0; i < c - 1; i++) 
            arr[0][i] = arr[0][i+1];
        for (int i = 0; i < first; i++) 
            arr[i][c - 1] = arr[i + 1][c - 1];
        for (int i = c - 1; i > 1; i--) 
            arr[first][i] = arr[first][i-1];

        arr[first][1] = 0;

        for (int i = second + 1; i < r - 1; i++) 
            arr[i][0] = arr[i + 1][0];

        for (int i = 0; i < c - 1; i++) 
            arr[r - 1][i] = arr[r - 1][i + 1]; 

        for (int i = r - 1; i > second; i--) 
            arr[i][c - 1] = arr[i - 1][c - 1];

        for (int i = c - 1; i > 1; i--) 
            arr[second][i] = arr[second][i - 1];

        arr[second][1] = 0;
	}
	private static void spread() {
		while(!queue.isEmpty()) {
			Dust d = queue.poll();
			if(d.size<5) continue;
			int cnt=0;
			for(int i=0;i<4;i++) {
				int cx = d.x+dx[i];
				int cy = d.y+dy[i];
				if(0<=cx&&cx<r&&0<=cy&&cy<c) {
					if(arr[cx][cy]!=-1) {
						cnt++;
						arr[cx][cy]+=d.size/5;
					}
				}
			}
			arr[d.x][d.y]-=(d.size/5)*cnt;
		}
	}

}
