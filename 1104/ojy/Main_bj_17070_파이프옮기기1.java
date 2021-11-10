package hw1104;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
class Node{
	int x;
	int y;
	int d; //가로 대각선 세로 012
	public Node(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
}
public class Main_bj_17070_파이프옮기기1 {
	static int N,cnt;
	static int[][]arr;
	public static void bfs() {
		Queue<Node>queue = new LinkedList<Node>();
		queue.add(new Node(0,1,0));
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int x = node.x;
			int y = node.y;
			int dir = node.d;
			if(x==N-1&&y==N-1) {
				cnt++;
				continue;
			}
			if(dir==0) {//가로면은
				if(y+1<N&&arr[x][y+1]!=1) {//가로로 갈수있음
					queue.add(new Node(x,y+1,0));
				}
				if(x+1<N&&y+1<N&&arr[x+1][y+1]!=1&&arr[x][y+1]!=1&&arr[x+1][y]!=1) {//대각선
					queue.add(new Node(x+1,y+1,1));
				}
			}
			else if(dir==2) {	//세로
				if(x+1<N&&arr[x+1][y]!=1) {	//세로
					queue.add(new Node(x+1,y,2));
				}if(x+1<N&&y+1<N&&arr[x+1][y+1]!=1&&arr[x][y+1]!=1&&arr[x+1][y]!=1) {	//대각선
					queue.add(new Node(x+1,y+1,1));
				}
			}else if(dir==1) {	//대각선
				if(y+1<N&&arr[x][y+1]!=1) {//가로로 갈수있음
					queue.add(new Node(x,y+1,0));
				}if(x+1<N&&arr[x+1][y]!=1) {	//세로
					queue.add(new Node(x+1,y,2));
				}
				if(x+1<N&&y+1<N&&arr[x+1][y+1]!=1&&arr[x][y+1]!=1&&arr[x+1][y]!=1) {	//대각선
					queue.add(new Node(x+1,y+1,1));
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if(arr[N-1][N-1]==1) {		//시간초과 방지
			System.out.println(0);
			return;
		}
		bfs();
		System.out.println(cnt);
		br.close();
	}
}
