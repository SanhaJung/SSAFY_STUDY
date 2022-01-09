package hw0928;

import java.io.*;
import java.util.*;
public class Solution_d4_8458_원점으로집합_서울_12반_오지영 {

	static int min = 987654321;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=t;tc++) {
			int N = Integer.parseInt(br.readLine());
			int max = 0;
			int sum = 0;
			int cnt = 0;
			List<Integer>list = new ArrayList<Integer>();
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int num = Math.abs(x)+Math.abs(y);
				list.add(num);
				max = Math.max(num, max);
			}

			for(int i=1;i<list.size();i++) {
				if(list.get(i)%2!=list.get(i-1)%2)
					cnt=-1;
			}
			
			if(cnt==0) {
				while(true) {
					boolean isE = true;
					if(sum<max||max%2!=sum%2)
						isE = false;
					if(isE) break;
					sum+= ++cnt;
				}
			}
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

}
