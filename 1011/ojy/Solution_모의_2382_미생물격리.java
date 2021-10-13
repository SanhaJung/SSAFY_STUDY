package hw1011;
import java.util.*;
import java.io.*;
class Micro{
	int x;
	int y;
	int num;
	int d;

	public Micro(int x, int y, int num, int d) {
		super();
		this.x = x;
		this.y = y;
		this.num = num;
		this.d = d;
	}

	@Override
	public String toString() {
		return "Micro [x=" + x + ", y=" + y + ", num=" + num + ", d=" + d + "]";
	}
	
}
public class Solution_모의_2382_미생물격리 {
	static int N,M,K;
	static List<Micro>list = new ArrayList<>();
	static int[]dx = {0,-1,1,0,0};
	static int[]dy = {0,0,0,-1,1};
	private static void move() {
		for(int i=0;i<list.size();i++) {
			Micro m = list.get(i);
			int cx = m.x+dx[m.d];
			int cy = m.y+dy[m.d];
			m.x=cx;m.y=cy;
		}
	}
	private static void isCheck() {
		for(int i=0;i<list.size();i++) {
			Micro m = list.get(i);
			if(m.x<=0||m.y>=N-1||m.x>=N-1||m.y<=0) {
				list.get(i).num/=2;
				if(list.get(i).num==0) {
					list.remove(i--);
					continue;
				}
				if(m.d==1)m.d=2;
				else if(m.d==2)m.d=1;
				else if(m.d==3)m.d=4;
				else if(m.d==4)m.d=3;
			}
		}
	}
	private static void isLarge() {
		Collections.sort(list,new Comparator<Micro>() {

			@Override
			public int compare(Micro o1, Micro o2) {
				// TODO Auto-generated method stub
				return o2.num-o1.num;
			}
		});
		for(int i=0;i<list.size();i++) {
			int sum = list.get(i).num;
			for(int j=i+1;j<list.size();j++) {
				if(list.get(i).x==list.get(j).x&&list.get(j).y==list.get(i).y) {
					sum+=list.get(j).num;
					list.remove(j--);
				}
			}
			list.get(i).num = sum;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			list = new ArrayList<Micro>();
			for(int k=0;k<K;k++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				list.add(new Micro(x,y,num,d));
			}
			for(int t=0;t<M;t++) {
				move();
				isCheck();
				isLarge();
			}
			int result=0;
			for(int i=0;i<list.size();i++)
					result+=list.get(i).num;
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
		
	}

}
