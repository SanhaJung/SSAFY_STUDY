package a12algo;
import java.util.*;
import java.io.*;
public class bj1436영화감독숌 {
	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int cnt = 1;
        int answer = 666;
        
        while(cnt<N){
            answer++;
            while(!(answer+"").contains("666")){
                answer++;
            }
            cnt++;
        }
        
        System.out.println (answer);
        
    }
}
