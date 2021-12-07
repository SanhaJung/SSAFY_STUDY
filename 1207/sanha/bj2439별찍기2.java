package a12algo;

import java.util.*;
import java.io.*;

public class bj2439별찍기2 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N-i;j++){
                sb.append(" ");
            }
            for(int j=N-i;j<N;j++){
                sb.append("*");
            }
           sb.append("\n");
        }
        System.out.println(sb);
    }
}
