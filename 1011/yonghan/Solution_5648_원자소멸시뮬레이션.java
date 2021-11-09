package a1011;

import java.io.*;
import java.util.*;

public class Solution_5648_원자소멸시뮬레이션 {
	static int N, Energy;
	
	static List<Atom> list = new ArrayList<>();
	
//	static int [] di = {-1, 1, 0, 0 }; //상 하 좌 우
//	static int [] dj = {0, 0, -1, 1 }; //상 하 좌 우
	
	static class Atom {
		//원자의 입력이 -1000 ~ 1000이므로
		//원자가 이동하다가 범위를 벗어나면 그밖에서 충돌할리없음
		//즉 범위를 벗어나면 에너지 방출없이 없애주면 됨
		
		int x,y, dir, k;
		public Atom(int x, int y, int dir, int k) {
			this.x = x; //y
			this.y = y; //x
			this.dir = dir; //0, 1, 2, 3 - 상 하 좌 우
			this.k = k;
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Atom [x=").append(x).append(", y=").append(y).append(", dir=").append(dir).append(", k=")
					.append(k).append("]");
			return builder.toString();
		}

		public void move() {
			if(dir == 0) {
				y++;
			}
			else if(dir==1) {
				y--;
			}
			else if(dir==2) {
				x--;
			}
			else { //dir==3
				x++;
			}
		}
	}
	
	public static void main(String[] args) throws Exception  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<= T ; tc++) {
			N = Integer.parseInt(br.readLine());
			list.clear();
			for(int n=0; n<N; n++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				
				list.add(new Atom(x*2, y*2, dir, k));
			}
			Energy = 0;
			while (true) {
				if (list.size() <= 1)
					break;
				
				for (int i = list.size() - 1; i >= 0; i--) {
					list.get(i).move();
					
					if (list.get(i).x <-2000 || list.get(i).x > 2000
					||	list.get(i).y <-2000 || list.get(i).y > 2000) { //움직인 원자가 범위밖이라면
						list.remove(i);
					}
				}
				
				//충돌될 원자 list
				List<Atom> crashedList = checkCrash();
				//방출하는 에너지 총합 구하고
				for(Atom a : crashedList) {
					System.out.println(a);
					Energy += a.k;
				}
				//소멸
				list.removeAll(crashedList);
			}
			System.out.println("#"+tc+" "+Energy);

		}
	}
	//제한시간 초과
	private static List<Atom> checkCrash() {
		//list에 담겨진 atom들 충돌 하는지 체크
		List<Atom> crashedList = new ArrayList<>();
		for(int i =0; i<list.size()-1; i++) {
			for(int j =i+1; j<list.size(); j++) {
				if(	list.get(i).x == list.get(j).x &&
					list.get(i).y == list.get(j).y) {
					if(! crashedList.contains(list.get(i)) && ! crashedList.contains(list.get(j))) {
						//둘다 포함되어 있지 않다면
						crashedList.add(list.get(i));
						crashedList.add(list.get(j));
					}
					else if (!crashedList.contains(list.get(i))) {
						//첫번째 것만 포함되어있지 않다면
						//System.out.println("첫번째 요소 포함안됨");
						crashedList.add(list.get(i));
					}
					else if (!crashedList.contains(list.get(j))) {
						//왜 else가 아닌지 (?)
						//System.out.println("두번째 요소 포함안됨");
						crashedList.add(list.get(j));
					}
				}
			}
		}
		
		return crashedList;
	} 
	
	
	private static void checkCrash(boolean [] check) {
		//list에 담겨진 atom들 충돌 하는지 체크
		int [] coordinate = new int [list.size()];
		for(int i =0; i<list.size(); i++) {
			// x좌표y좌표 8자리로 만든 값을 1차원 배열에 저장
			coordinate[i] = list.get(i).x * 10000 + list.get(i).y;
		}
		//
	}

}
