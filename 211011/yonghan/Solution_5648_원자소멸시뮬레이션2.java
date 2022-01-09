package a1011;

import java.io.*;
import java.util.*;

public class Solution_5648_원자소멸시뮬레이션2 {
	static int N, Energy;
	
	static List<Atom> list = new ArrayList<>();
	
	//이동된 모든 원자를 관리하기 위한 맵
	static Map<Integer, List<Atom>> moveMap = new HashMap<>();
	
	static List<Atom> delList = new ArrayList<>(); //충돌 체크후 제거할 원자들 담을 리스트

	static class Atom {
		
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
				
				////원자의 입력이 -1000 ~ 1000이므로
				//원자가 이동하다가 범위를 벗어나면 그밖에서 충돌할리없음
				//즉 범위를 벗어나면 에너지 방출없이 없애주면 됨

				moveMap.clear(); //각각의 텀 마다 움직인 모든원소들의 맵이므로 텀마다 초기화
				delList.clear(); //각각의 텀마다 충돌이 일어나고 삭제할 원소들을 저장할 리스트 초기화
				for(Atom a : list) {
					a.move();
					if(a.x<-2000 || a.x>2000 || a.y<-2000 || a.y >2000) {
						delList.add(a);
					}else {
						//범위 안에 원자들을 map에서 관리
						putToMoveMap(a);
					}
				} //0.5초 뒤 원자들 모두 움직임 완료
				checkCrash();
				
				list.removeAll(delList); //소멸될 원자들 제거 
				
			}
			System.out.println("#"+tc+" "+Energy);

		}
	}
	private static void checkCrash() {
		Set<Integer> keys = moveMap.keySet(); //맵에 있는 키 set을 가져와서
		for(Integer key : keys) { //
			List<Atom> list = moveMap.get(key); //해당 키셋의 Atom 리스트를 만든다
			if(list.size() > 1) { //만든 list의 크기가 1이상이면 충돌이 나는 atom이 리스트에 담아졌으므로
				for(Atom a : list ) {
					Energy +=a.k; //방출 에너지 합산하고
					delList.add(a); //소멸될 리스트에 담는다
				}
			}
		}
	}
	private static void putToMoveMap(Atom a) {
		//좌표를 Integer로 처리하기 위한 식
		Integer key = a.x*10000+a.y; //앞의 네자리는 x 좌표 뒤 네자리는 y 좌표로 만들어줌
		
		if(moveMap.containsKey(key)) { //현재 a의 키가 맵에 존재한다면 
			moveMap.get(key).add(a); //키에 해당하는 맵에 원자를 리스트에 넣기만 하면 됨 
		}else { //처음 좌표진입 
			List<Atom> atoms = new ArrayList<>();
			atoms.add(a);
			moveMap.put(key, atoms);
		}
		
	}

}
