package bj17135;

import java.util.*;
import java.io.*;

public class Main_bj_17135_캐슬디펜스2 {
	
	static int N,M,D,answer;
	static int[] archers = new int[3];
	static ArrayList<int[]> original_monsters = new ArrayList<>();
	static ArrayList<int[]> monsters = new ArrayList<>();
	
	static void copy_monsters() {
		for(int[] monster : original_monsters) {
			monsters.add(new int[] {monster[0], monster[1]});
		}
	}
	
	static void combination(int start, int cnt) {
		if(cnt == 3) {
			copy_monsters();
			int result = 0;
			int[][] target = {{-1,-1}, {-1,-1}, {-1,-1}};
			
			while(!monsters.isEmpty()) {
				// 각 궁수마다 쏠 대상 선정
				for(int i=0; i<3; i++) {		
					int distance = D+1;
					for(int[] monster : monsters) {
						int temp = Math.abs(N-monster[0])+Math.abs(archers[i]-monster[1]);
						if(temp<=D) {
							if(temp<distance) {
								distance = temp;
								target[i][0] = monster[0];
								target[i][1] = monster[1];
							}
							else if (temp==distance && target[i][1]>monster[1]) {
								distance = temp;
								target[i][0] = monster[0];
								target[i][1] = monster[1];
							}
						}
					}
				}
				// 해당되는 타겟 제거
				for(int i=0; i<3; i++) {		
					for(int j=0; j<monsters.size(); j++) {
						int[] monster = monsters.get(j);
						if(target[i][0] == monster[0] && target[i][1] == monster[1]) {
							monsters.remove(j);
							result++;
							break;
						}
					}
				}
				// 화살을 쏜 뒤 몬스터들이 한 칸씩 아래로 이동
				for(int i=0; i<monsters.size(); i++) {		
					int[] monster = monsters.get(i);
					if(++monster[0]>=N) {
						monsters.remove(i--);
					}
				}
			}
			answer = Math.max(answer, result);
			return;
		}
		
		for(int i=start; i<M; i++) {
			archers[cnt] = i;
			combination(i+1, cnt+1);
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<M; j++) {
				if(Integer.parseInt(st.nextToken())==1)
					original_monsters.add(new int[] {i,j});
			}
		}
		
		combination(0,0);
		System.out.println(answer);
		
	}
}
