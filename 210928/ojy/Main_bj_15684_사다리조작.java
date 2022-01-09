package hw0928;

import java.io.*;
import java.util.*;
public class Main_bj_15684_사다리조작 {
	static int N,M,H; //세로선,갯수, 가로선
	static int [][]arr;
	static boolean finish = false;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//세로선 열
		M = Integer.parseInt(st.nextToken());	//줄갯수
		H = Integer.parseInt(st.nextToken());	//가로선 행
		arr = new int[H+1][N+1];	//1~가로선, 1~세로선
		for(int i=0;i<M;i++) {	
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());	//a 행에
			int b = Integer.parseInt(st.nextToken());	//b 열
			arr[a][b]= 1;	//a행의 b열 
			arr[a][b+1]=-1;	//a행의 b+1열
		}
		int result=-1;
		for(int i=0;i<=3;i++) {
			dfs(0,i);
			if(finish) {
				result=i;
				break;
			}
		}
		System.out.println(result);
		br.close();
	}
	private static void dfs(int cnt,int result) {
		if(cnt==result) {	
			if(isCheck()) {
				finish = true;
			}
			return;
		}
		for(int i=1;i<=H;i++) {
			for(int j=1; j<N;j++) { 
				if(arr[i][j]==0&&arr[i][j+1]==0) {
					arr[i][j]= 1; //오른쪽으로 이동
					arr[i][j+1]=-1; //왼쪽으로 이동
					dfs(cnt+1,result);
					arr[i][j]=0;	//취소
					arr[i][j+1]=0;	//취소
				}
			}
		}
	}
	private static boolean isCheck() {
		for(int i=1;i<=N;i++) {
			int cx=1; //행
			int cy=i;	//열
			while(cx<=H) {
				if(arr[cx][cy]==1) cy++;	//오른쪽으로 갈 수 있으면,이동 ->
				else if(arr[cx][cy]==-1)cy--;	//왼쪽으로 갈 수 있으면, 이동 <-
				cx++;	// 밑으로 이동
			}
			if(cy!=i)return false;	//마지막 열이 처음 열과 다르면
		}
		return true;
	}
}
