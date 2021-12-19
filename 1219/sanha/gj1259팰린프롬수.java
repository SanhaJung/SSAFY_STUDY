package a12algo;
import java.util.*;
import java.io.*;
public class gj1259팰린프롬수 {
	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        
        while(!line.equals("0")){
        	if(line.length()==1) {
        		sb.append("yes").append("\n");
        	}else {
        		for(int i=0;i<line.length()/2;i++){
        			if(line.charAt(i)!=line.charAt(line.length()-i-1)){
        				sb.append("no").append("\n");
        				break;
        			}
        			if(i==line.length()/2-1) sb.append("yes").append("\n");
        		}
        	}

            line = br.readLine();
        }
        System.out.println(sb);
    }
}
