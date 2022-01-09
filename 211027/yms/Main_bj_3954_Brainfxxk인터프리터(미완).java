package bj3954;

import java.util.*;
import java.io.*;

public class Main_bj_3954_Brainfxxk인터프리터 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		int T = Integer.parseInt(in.readLine());
		
		label:for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(in.readLine());
			int memory_size = Integer.parseInt(st.nextToken());
			int program_size = Integer.parseInt(st.nextToken());
			int input_size = Integer.parseInt(st.nextToken());
			
			String program = in.readLine();
			String input = in.readLine();
			int[] memory = new int[memory_size];
			
			int pp=0, mp=0, ip=0, cnt=0;
			
			int[] braket_left = new int[program_size];	// [
			int[] braket_right = new int[program_size];	// ]
			
			for(int i=0; i<program_size; i++) {
				if(program.charAt(i)=='[')
					stack.push(i);
				else if (program.charAt(i) == ']') {
					int temp = stack.pop();
					braket_left[temp] = i;
					braket_right[i] = temp;
				}
			}
			
			int min =0, max = 0;
			
			while(pp<program_size) {
				if(cnt==50000000) {
					System.out.println("Loop");
					continue label;
				}
				
				if(program.charAt(pp) == '-') {
					memory[mp]--;
					if(memory[mp]<0)
						memory[mp] = 255;
					pp++;
				}
				else if(program.charAt(pp) == '+') {
					memory[mp]++;
					if(memory[mp]>255)
						memory[mp] = 0;
					pp++;
				}
				else if(program.charAt(pp) == '<') {
					mp--;
					if(mp<0)
						mp = memory_size-1;
					pp++;
				}
				else if(program.charAt(pp) == '>') {
					mp++;
					if(mp>memory_size-1)
						mp =0;
					pp++;
				}
				else if(program.charAt(pp) == '[') {
					if(memory[mp]==0)
						pp = braket_left[pp];
					pp++;
				}
				else if(program.charAt(pp) == ']') {
					if(memory[mp]!=0)
						pp = braket_right[pp];
					pp++;
				}
				else if(program.charAt(pp) == '.') {
					pp++;
				}
				else if(program.charAt(pp) == ',') {
					if(ip<input_size)
						memory[mp] = input.charAt(ip++);
					else if (ip == input_size) {
						memory[mp] = 255;
					}
					pp++;
				}
				cnt++;
			}
			System.out.println("Terminates");
		}
		
	}
}
