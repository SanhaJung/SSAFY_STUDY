package a12algo;

import java.util.*;
import java.io.*;

public class bj2475검증수 {
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int[] numbers = new int[5];
        int sum = 0;
        
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<5;i++){
            sum += Math.pow(Integer.parseInt(st.nextToken()), 2);
        }
        System.out.println(sum%10);
        
    }
}
