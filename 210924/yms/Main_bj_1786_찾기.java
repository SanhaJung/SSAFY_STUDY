package bj1786;

import java.util.*;
import java.io.*;

public class Main_bj_1786_찾기 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] text = in.readLine().toCharArray();
		char[] pattern = in.readLine().toCharArray();
				
		int tLength = text.length, pLength = pattern.length;
		ArrayList<Integer> answer = new ArrayList<>();
		int cnt = 0;
		
		int[] pi = new int[pLength];
		for(int i=1, j=0; i<pLength; i++) {
			while(j>0 && pattern[i] != pattern[j]) {
				j = pi[j-1];
			}
			if(pattern[i] == pattern[j]) pi[i] = ++j;
		}
	
		for(int i=0, j=0; i<tLength; i++) {
			while(j>0 && text[i] != pattern[j]) {
				j = pi[j-1];
			}
			if(text[i] == pattern[j]) {
				if(j == pLength-1) {
					cnt++;
					answer.add(i+1 - j);
					j = pi[j];
				}
				else
					j++;
			}
		}
		
		System.out.println(cnt);
		for(int n : answer) {
			System.out.print(n+" ");
		}
	}
}
