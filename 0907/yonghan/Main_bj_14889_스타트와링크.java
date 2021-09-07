package a0905;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_bj_14889_스타트와링크 {
	static int N, min=Integer.MAX_VALUE;
	static int [][] map;
	static boolean[] visit;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int [N][N];
		visit = new boolean[N];
		
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j =0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0,0);
		
		System.out.println(min);
	}

	 static void dfs(int idx, int count) {
		end : if(count == N/2) { //n/2개 뽑으면 안뽑힌 n/2 가 자동으로 정해짐
			int start=0, link =0;
			for(int i=0; i<N-1; i++) {
				for(int j=i+1; j<N; j++) {
					if(visit[i]&& visit[j] ) {
						start +=map[i][j];
						start +=map[j][i];
					}
					else if(!visit[i] && !visit[j] ) {
						link +=map[i][j];
						link +=map[j][i];
					}
				}
			}
			int result = Math.abs(start-link);
			
			if(result==0) {
				min = Math.min(result, min);
				break end;
			}
			min = Math.min(result, min);
			
			return;
		}
		
		for(int i=idx; i<N; i++) {
			if(!visit[i]) { 
				visit[i]=true; //start 팀
				dfs(i+1, count+1);
				visit[i]=false; //link팀 
			}
		}
		
	}
}
