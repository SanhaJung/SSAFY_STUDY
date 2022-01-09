package a0923.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// KMP 알고리즘(Knuth–Morris–Pratt Algorithm) 
// O(N+M)

public class Main_bj_1786_찾기 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[] text = in.readLine().toCharArray();
		char[] pattern = in.readLine().toCharArray();
		
		int tLength = text.length, pLength = pattern.length;
		
//		// 실패함수 만들기 : KMP의 아이디어를 똑같이 적용, W에서 W를 찾는 듯한 행위를 해서...
//		int[] pi = new int[pLength];
//	    for(int i=1, j=0; i<pLength; i++){// i:접미사 포인터(i=1부터 시작: 우리는 실패함수를 만드는게 목적이므로 첫글자 틀리면 0위치로 가야하므로.), j:접두사 포인터
//	        while(j > 0 && pattern[i] != pattern[j]) {
//	        	// j > 0 = j-1>=0
//	        	// 배턴이 다르면 인덱스 비교할 유의미한 자리로 이동
//	        	j = pi[j-1];  
//	        }
//	        // 같으면 j++, for문 올라가서 i++
//	        if(pattern[i] == pattern[j]) pi[i] = ++j;
//	    }
	    
    	// 실패함수(배열) 만들기 줄임
 		int[] pi = new int[pLength];
 	    for(int i=1, j=0; i<pLength; i++){// i:접미사 포인터(i=1부터 시작: 우리는 실패함수를 만드는게 목적이므로 첫글자 틀리면 0위치로 가야하므로.), j:접두사 포인터
 	        while(j > 0 && pattern[i] != pattern[j]) j=pi[j-1];  
 	        if(pattern[i] == pattern[j]) pi[i] = ++j;
 	    }
		
		int cnt = 0;  // 일치하는 개수
		ArrayList<Integer> list = new ArrayList<Integer>();  // 일치하는 위치를 리스트에 담음
		// i : 텍스트 포인터 , j: 패턴 포인터 
		// 같은 글자 찾기
		for(int i=0,j=0; i<tLength; i++) { 
			while(j>0 && text[i] != pattern[j]) j=pi[j-1]; 
			if(text[i]==pattern[j]) { //두 글자 일치
				if(j == pLength-1) {  // j가 패턴의 마지막 인덱스라면 -> 일치하는 문자열 찾음
					cnt++;            // 카운트 증가 -> 필요한 문제 조건 코드 위치
					list.add((i+1)-pLength); 
					j=pi[j]; 
				}else { 
					++j;  // 실패배열을 만드는 것이 아니라서 대입은 안해도됨
				}
			}
		}
		
		sb.append(cnt).append("\n");
		if(cnt>0) {
			for(int e: list) {
				sb.append(e+1).append(" ");
			}
		}
		System.out.println(sb);
		in.close();
	}
}