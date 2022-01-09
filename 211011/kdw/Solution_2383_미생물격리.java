package s1010;

import java.util.*;
import java.io.*;

public class Solution_2383_미생물격리 {

	static int T;
	static int N, M, K, answer;
	static int[] dr = {0, -1, 1, 0, 0};	// 상 하 좌 우  
	static int[] dc = {0, 0, 0, -1, 1};	
	static List<Bacteria>[][] map, newMap;
	static class Bacteria implements Comparable<Bacteria>{
		int r;
		int c;
		int cnt;
		int d;
		public Bacteria(int r, int c, int cnt, int d) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.d = d;
		}
		@Override
		public int compareTo(Bacteria o) {
			return -Integer.compare(this.cnt, o.cnt);
		}
	}
	
	static List<Bacteria> list;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());			// testcase 입력 
		for(int tc=1; tc<=T; tc++) {
			answer = 0;
			
			st = new StringTokenizer(br.readLine());	// N, M, K 입력 
			N = Integer.parseInt(st.nextToken());		// 셀의 개수 N
			M = Integer.parseInt(st.nextToken());		// 격리 시간 M	
			K = Integer.parseInt(st.nextToken());		// 미생물 군집 수 K
			list = new ArrayList<>();

			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				list.add(new Bacteria(r, c, cnt, d));
			}
			
			for(int time=0; time<M; time++) {
				for(int i=0; i<list.size(); i++) {		// 모든 미생물들을 설정된 방향으로 이동시키기 
					Bacteria cur = list.get(i);
					cur.r += dr[cur.d];
					cur.c += dc[cur.d];
				}	
				
				for(int i=0; i<list.size(); i++) {		// 이동후의 미생물들이 
					Bacteria cur = list.get(i);
					if(cur.r==0||cur.r==N-1 || cur.c==0||cur.c==N-1) {	// 약품 처리된 곳에 있다면 
						cur.cnt /= 2;					// 미생물을 절반으로 감소시키고, 
						if(cur.cnt == 0) {				// 만약 감소된 미생물의 수가 0마리이면, list에서 해당 미생물 제거 
							list.remove(i--);
							continue;
						}
						if(cur.d%2==1) cur.d++;			// 미생물이 남아있다면 방향을 반대로 변경 
						else cur.d--;
					} 
				}
				
				Collections.sort(list);					// 미생물의 수가 많은 순서대로 내림차순 정렬 
				
				for (int i = 0; i <= list.size(); i++) {			// 기준이 되는 list.get(i)번 째 미생물의 수는 list.size()-1 번째부터 i+1번째 미생물보다 더 많기 때문에, 
                    for(int j=list.size()-1; j>=i+1; j--) {
                        if(list.get(i).r == list.get(j).r && list.get(i).c == list.get(j).c) {	// 위치가 같은지만 비교해도, 자동으로 미생물의 수가 많은 쪽을 알 수 있
                            list.get(i).cnt += list.get(j).cnt;		// 위치가 같은 미생물이 있따면, 현재 i번째 미생물에 미생물의 수를 더해주고,
                            list.remove(j);							// j번째 미생물을 제거해줌 
                        }
                    }
                }
			}	
			
			for(int i=0; i<list.size(); i++) {			// 미생물 list에 남아있는 미생물들의 수를 더해줌 
				answer += list.get(i).cnt;
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
