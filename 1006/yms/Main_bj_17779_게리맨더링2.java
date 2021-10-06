package bj17779;

import java.util.*;
import java.io.*;

public class Main_bj_17779_게리맨더링2 {
	
	static int[][] map;
	static int N,total;
	static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
	static int answer = Integer.MAX_VALUE/2;
	static int[] sum;
	
	static void seperate(int x, int y) {
		for(int d1=1; d1<=N; d1++) {
			for(int d2=1; d2<=N; d2++) {
				if(x+d1+d2<=N && y-d1>=0 && y+d2<=N) {
					boolean[][] border = new boolean[N+1][N+1];
					sum = new int[5];
					
					//경계선 그리는 부분
					for(int i=0; i<=d1; i++) {
						border[x+i][y-i] = true;
						border[x+d2+i][y+d2-i] = true;
					}
					for(int i=0; i<=d2; i++) {
						border[x+i][y+i] = true;
						border[x+d1+i][y-d1+i] = true;
					}
					//1구역
					for(int i=1; i<x+d1; i++) {
						for(int j=1; j<=y; j++) {
							if(border[i][j]) break;
							sum[0]+=map[i][j];
						}
					}
					
					//2구역
					for(int i=1; i<=x+d2; i++) {						// 5구역 내부를 채우는 방법 대신 진행 방향을 오른쪽에서 왼쪽으로 바꿔줘서
						for(int j=N; j>y; j--) {						// 1,3구역이랑 똑같이 경계선에 부딪히면 break하도록 변경
							if(border[i][j]) break;
							sum[1]+=map[i][j];
						}
					}
					
					//3구역
					for(int i=x+d1; i<=N; i++) {
						for(int j=1; j<y-d1+d2; j++) {
							if(border[i][j]) break;
							sum[2]+=map[i][j];
						}
					}
					//4구역
					for(int i=x+d2+1; i<=N; i++) {						// 2구역과 같음
						for(int j=N; j>=y-d1+d2; j--) {
							if(border[i][j])break;
							sum[3]+=map[i][j];
						}
					}
					
					sum[4] = total-(sum[0]+sum[1]+sum[2]+sum[3]);
					
					Arrays.sort(sum);
					
					answer = Math.min(answer, sum[4]-sum[0]);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		map = new int[N+1][N+1];
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=1; j<N+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j];
			}
		}
		
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				seperate(i, j);
			}
		}
		System.out.println(answer);
		
	}
}
