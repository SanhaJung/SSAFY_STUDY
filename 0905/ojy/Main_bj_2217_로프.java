import java.io.*;
import java.util.*;
public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int []weight = new int[n];
        for(int i=0;i<n;i++)
            weight[i]=Integer.parseInt(br.readLine());
        Arrays.sort(weight);
        int max=0;
        for(int i=0;i<n;i++){
            max = Math.max(max,(i+1)*weight[n-1-i]);
        }
        System.out.println(max);
    }

}