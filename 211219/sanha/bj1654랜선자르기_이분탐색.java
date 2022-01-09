package a12algo;

import java.util.*;
import java.io.*;

public class bj1654랜선자르기_이분탐색{
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        
        int[] lanArray = new int[K];
        long len = 0;
        for(int i=0;i<K;i++){
            int l = Integer.parseInt(br.readLine());
            len = Math.max(len, l);
            lanArray[i] = l;
        }
        
        long start = 1;
        long end = len;
        long n = 0;
        long n2 = 0;
        
        if(N==K) {
        	for(int i=0;i<K;i++) {
        		len = Math.min(len, lanArray[i]);
        	}
        } else if(N==1) {}
        else {
        	while(!(n2<N && n>=N)){
        		System.out.println(start+" "+end);
        		len = (long) ((start+end)/2);

        		n = 0;
        		n2=0;
        		for(int i=0;i<K;i++){
        			n2 += lanArray[i]/(len+1);
        		}
        		for(int i=0;i<K;i++){
        			n += lanArray[i]/(len);
        		}
        		if(n2<N && n<N) {
        			end = len+1;
        		}else if(n2>=N && n>=N) {
        			start = len-1;
        		}
//        		System.out.println(n+" "+n2);
//        		System.out.println(start+" "+end);
        	}
        }

        
        System.out.println(len);
    }
}