package a12algo;

import java.util.*;
import java.io.*;

public class bj1654랜선자르기_완탐{
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        
        int[] lanArray = new int[K];
        ArrayList<Integer> lans = new ArrayList<>();
        int len = -987654321;
        for(int i=0;i<K;i++){
            int l = Integer.parseInt(br.readLine());
//            len = Math.max(len, l);
            lanArray[i] = l;
            lans.add(l);
        }
        Collections.sort(lans);
        len = lans.get(K-2);
//        System.out.println(lans.get(K-2));
        int n = 0;
        while(n<N){
             n = 0;
            for(int i=0;i<K;i++){
                n += lanArray[i]/len;
            }
            len--;
        }
        
        System.out.println(++len);
    }
}