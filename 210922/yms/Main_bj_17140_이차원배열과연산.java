package bj17140;

import java.util.*;
import java.io.*;

public class Main_bj_17140_이차원배열과연산 {
	
	static class Number implements Comparable<Number>{									// 해당 숫자와 숫자가 몇번 나왔는지를 저장할 클래스
		int num;
		int count;
	
		public Number(int num, int count) {
			super();
			this.num = num;
			this.count = count;
		}

		@Override
		public int compareTo(Number o) {												// 횟수를 우선으로 정렬하고, 횟수가 같다면 숫자 크기로 정렬
			if(this.count == o.count) return Integer.compare(this.num, o.num);
			return Integer.compare(this.count, o.count);
		}	
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] arr= new int[100][100];
		int answer = 0;
		
		int r_max = 3;
		int c_max = 3;
		for(int i=0; i<3; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int t=0; t<100; t++) {
			if(arr[r-1][c-1]==k)
				break;
			if(r_max >= c_max) {	//R연산
				for(int i=0; i<r_max; i++) {								// 행마다 실행
					ArrayList<Number> temp = new ArrayList<>();
					int[] cnt = new int[101];
					boolean[] check = new boolean[101];
					
					for(int j=0; j<c_max; j++) {							// 숫자의 빈도를 cnt 배열에 저장
						cnt[arr[i][j]]++;
					}
					
					for(int j=0; j<c_max; j++) {
						if(!check[arr[i][j]] && cnt[arr[i][j]]>0 && arr[i][j]!=0) {		//아직 처리되지 않은 숫자이고 빈도가 0초과이며 숫자가 0이 아니라면
							check[arr[i][j]] = true;
							temp.add(new Number(arr[i][j], cnt[arr[i][j]]));
						}
						arr[i][j] = 0;													//새로 넣을때 길이가 짧아지는 경우를 대비해 기존 값들을 0으로 초기화
					}
					
					Collections.sort(temp);
					
					int idx = 0;
					for(Number number : temp) {											// 인덱스를 더하면서 숫자와 횟수를 순서대로 넣고, 100 이상이면 멈춤
						if(idx>=100) break;
						arr[i][idx++] = number.num;
						arr[i][idx++] = number.count;
					}
					
					c_max = Math.max(c_max, idx);										// 행열의 최대 크기를 계산해둠
				}
			}
			else {		//C연산
//				System.out.println("C연산");
				for(int i=0; i<c_max; i++) {
					ArrayList<Number> temp = new ArrayList<>();
					int[] cnt = new int[101];
					boolean[] check = new boolean[101];
					
					for(int j=0; j<r_max; j++) {
						cnt[arr[j][i]]++;
					}
					for(int j=0; j<r_max; j++) {
						if(!check[arr[j][i]] && cnt[arr[j][i]]>0 && arr[j][i]!=0) {
							check[arr[j][i]] = true;
							temp.add(new Number(arr[j][i], cnt[arr[j][i]]));
						}
						arr[j][i] = 0;
					}
					Collections.sort(temp);
					
					int idx = 0;
					for(Number number : temp) {
						if(idx>=100) break;
						arr[idx++][i] = number.num;
						arr[idx++][i] = number.count;
					}
					
					r_max = Math.max(r_max, idx);
				}
			}
			answer++;																		// 연산이 끝나면 answer+1
		}
		
		
//		for(int i=0; i<r_max; i++) {
//			for(int j=0; j<c_max; j++) {
//				System.out.print(arr[i][j]);
//			}
//			System.out.println();
//		}
		
		if(arr[r-1][c-1] != k) answer = -1;													// 100번의 for문이 끝나고 났을때 원하는 값이 안나오면 -1
		System.out.println(answer);		
	}
}
