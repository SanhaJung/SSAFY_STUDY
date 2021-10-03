package hw0924;
import java.io.*;
import java.util.*;
public class Main_bj_16236_아기상어 {
	static class Fish{
		int x;
		int y;
		int d;
		public Fish(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
		
	}
	static int[]dx= {-1,0,1,0};
	static int[]dy = {0,1,0,-1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n= Integer.parseInt(br.readLine());
		int [][]arr = new int[n][n];
		PriorityQueue<Fish> fishes = new PriorityQueue<>(new Comparator<Fish>() {

			@Override
			public int compare(Fish o1, Fish o2) {
				int distance = o1.d-o2.d;
				if(distance==0) {
					int up = o1.x-o2.x;
					if(up==0) return o1.y-o2.y;
					else return up;
				}else
					return distance;
			}
		});
		Queue<Fish>shark = new LinkedList<>();
		for(int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==9) {
					shark.offer(new Fish(i,j,0));
					arr[i][j]=0;
				}
			}
		}
		int size = 2;
		int time = 0;
		int count =0;
		while(true) {
			boolean[][]visit = new boolean[n][n];
			while(!shark.isEmpty()) {
				Fish s = shark.poll();
				visit[s.x][s.y] = true;
				for(int i=0;i<4;i++) {
					int cx = s.x+dx[i];
					int cy= s.y+dy[i];
					
					if(0<=cx&&cx<n&&0<=cy&&cy<n) {
						if(!visit[cx][cy]&&arr[cx][cy]<=size) {
							if(arr[cx][cy]!=0&&arr[cx][cy]<size)
								fishes.offer(new Fish(cx,cy,s.d+1));
							visit[cx][cy] = true;
							shark.offer(new Fish(cx,cy,s.d+1));
						}
					}
				}
			}
			if(!fishes.isEmpty()) {	//물고기 있다면
				Fish f = fishes.poll();
				count++;
				if(count == size) {
					size++;
					count=0;
				}
				shark.offer(new Fish(f.x,f.y,0));
				arr[f.x][f.y]=0;
				time+=f.d;
				fishes.clear();
			}else 
				break;
		}
		System.out.println(time);
	}

}
