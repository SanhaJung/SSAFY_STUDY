package a0930;

import java.io.*;
import java.util.*;

public class Main_bj_14889_스타트와링크 {
	static int N, result;
	static int [][] map;
	static boolean [] visit;
	static int [] startTeam;
	static int [] linkTeam;
	
	public static void main(String[] args) throws Exception  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int [N][N];
		visit = new boolean[N];
		for(int i =0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0; j<N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//팀 나누기
		result = Integer.MAX_VALUE;
		comb(0,0);
		
		System.out.println(result);
		
	}
	private static void comb(int idx, int cnt) {
		if(result ==0) return;
		
		if(cnt==N/2) {
			//방문처리된 팀과 처리되지 않은 팀으로 나눔
			//System.out.println(Arrays.toString(visit));
			//ex T(1) T(2) T(3) T(4) F F F F
			//시너지 S12 21 S13 31 S14 41 S23 32 S24 42 S34 43 
			int start =0;
			int link =0;
			for(int i=0; i<N-1; i++) {
				for(int j =i+1; j<N; j++) {
					if(visit[i] && visit[j] ) {
						start +=map[i][j];
						start +=map[j][i];
					}
					if(!visit[i] && !visit[j]) {
						link +=map[i][j];
						link +=map[j][i];
					}
				}
			}
			int sub = Math.abs(start - link);
			result = Math.min(sub, result);
			
			return;
		}
		
		for(int i =idx ; i<N; i++) {
			if(!visit[i]) {
				visit[i] = true;
				comb(i+1,cnt+1);
				visit[i] = false;
			}
		}
	}
}
