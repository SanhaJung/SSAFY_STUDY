package s0928;

import java.io.*;
import java.util.*;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Main_17140_이차원배열과연산 {
	
	static int r, c, k;
	static int[][] arr;
	static ArrayList<Info> rowList;
	static ArrayList<Info> colList;
	static boolean[] v;
	
	static class Info implements Comparable<Info>{
		int value;
		int count;
		public Info(int value, int count) {
			super();
			this.value = value;
			this.count = count;
		}
		@Override
		public int compareTo(Info o) {
			int r = Integer.compare(this.count, o.count);
			if(r==0) r= Integer.compare(this.value, o.value);
			return r;
		}
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken())-1;
		c = Integer.parseInt(st.nextToken())-1;
		k = Integer.parseInt(st.nextToken());
		arr = new int[3][3];
		
		for(int i=0; i<arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<arr[0].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int time=0; time<=100; time++) {
			if(r < arr.length && c < arr[0].length) {									// arr의 형태가 바뀌면서 r,c 좌표가 존재하지 못하는 경우가 있으므로, r,c 범위체크 
				if(arr[r][c] == k) {													// 만약 100초안에 arr[r][c]==k 가 된다면,
					System.out.println(time);											// time 출력하고 
					return;																// 프로그램 종료 
				}
			}
			
			int rLen = arr.length;
			int cLen = arr[0].length;
			int[][] tmp = new int[101][101];
			if(rLen >= cLen) {
				// R 연산 
				int maxLen = -987654321;												// 행 중에서 길이가 제일 긴 행의 길이를 저장할 변수 
				for(int r=0; r<rLen; r++) {												// 각 행마다, 
					int[] count = new int[101];											// 요소들의 등장횟수를 카운트할 count 배열 초기화 
					for(int c=0; c<cLen; c++) count[arr[r][c]]++;						// 각 행의 각 요소의 등장횟수 카운트 
					
					rowList = new ArrayList<>();										// (값, 등장횟수)를 저장한 info 객체를 담을 리스트 초기화 
					for(int i=1; i<=100; i++) {											// count 배열의 1~100번 인덱스까지 순회하면서, (0은 제외하므로)
						if(count[i]>0) rowList.add(new Info(i, count[i]));				// count값이 0보다 크면, 리스트에 i,count[i] 순으로 객체를 생성하여 추가 
					}
					Collections.sort(rowList);											// 등장횟수 오름차순 정렬 후, 숫자 오름차순으로 정렬 
					int idx=0;
					for(int j=0; j<rowList.size(); j++) {								// rowList에서 Info 객체를 하나씩 순회하면서 
						tmp[r][idx++] = rowList.get(j).value;							// 값 
						tmp[r][idx++] = rowList.get(j).count;							// 등장횟수 순서로 temp 배열에 저장  
					}
					maxLen = maxLen <= rowList.size()*2 ? rowList.size()*2 : maxLen;	// 가장 긴 행을 찾기 위해 maxLen 값 업데이트 (rowlist객체에 값, 등장횟수가 한쌍으로 들어가있으므로, x2 처리해서 비교)
				}
				
				maxLen = maxLen > 100 ? 100 : maxLen;									// 최대 100개까지만 저장하므로, maxLen값이 100 초과로 초기화되었다면 최대값인 100으로 초기화 
				arr = new int[rLen][maxLen];											// R연산이므로, r x maxLen 만큼의 배열로 초기화한 뒤, 
				for(int i=0; i<rLen; i++) {
					for(int j=0; j<maxLen; j++) {
						arr[i][j] = tmp[i][j];											// tmp에 저장된 값들을 arr에 초기화 
					}
				}
			} else {
				// C 연산 
				int maxLen = -987654321;												// 열 중에서 길이가 제일 긴 열의 길이를 저장할 변수 
				for(int c=0; c<cLen; c++) {												// 각 열마다, 
					int[] count = new int[101];											// 요소들의 등장횟수를 카운트할 count 배열 초기화 
					for(int r=0; r<rLen; r++) count[arr[r][c]]++;						// 각 열마다 각 요소의 등장횟수 카운트 
					
					colList = new ArrayList<>();										// (값, 등장횟수)를 저장한 info 객체를 담을 리스트 초기화 
					for(int j=1; j<=100; j++) {											// count 배열의 1~100번 인덱스까지 순회하면서, (0은 제외하므로)
						if(count[j]>0) colList.add(new Info(j, count[j]));				// count값이 0보다 크면, 리스트에 i,count[i] 순으로 객체를 생성하여 추가 
					}
					Collections.sort(colList);											// 등장횟수 오름차순 정렬 후, 숫자 오름차순으로 정렬 
					int idx=0;
					for(int j=0; j<colList.size(); j++) {								// colList에서 Info 객체를 하나씩 순회하면서 
						tmp[idx++][c] = colList.get(j).value;							// 값 
						tmp[idx++][c] = colList.get(j).count;							// 등장횟수 순서로 temp 배열에 저장  
					}
					maxLen = maxLen <= colList.size()*2 ? colList.size()*2 : maxLen;	// 가장 긴 행을 찾기 위해 maxLen 값 업데이트 (rowlist객체에 값, 등장횟수가 한쌍으로 들어가있으므로, x2 처리해서 비교)
				}
				
				maxLen = maxLen > 100 ? 100 : maxLen;									// 최대 100개까지만 저장하므로, maxLen값이 100 초과로 초기화되었다면 최대값인 100으로 초기화 
				arr = new int[maxLen][cLen];											// R연산이므로, r x maxLen 만큼의 배열로 초기화한 뒤, 
				for(int i=0; i<maxLen; i++) {
					for(int j=0; j<cLen; j++) {
						arr[i][j] = tmp[i][j];											// tmp에 저장된 값들을 arr에 초기화 
					}
				}
			}
		}
		System.out.println(-1);															// for문 안에서 프로그램이 종료되지 못했다면, arr[r][c]==k 인 부분을 100초안에 못찾은 것이므로, -1 출력 
	
	}
}
