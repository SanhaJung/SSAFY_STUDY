import java.util.*;
import java.io.*;


public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] secret = new int[M];
        st = new StringTokenizer(in.readLine());
        for(int i=0; i<M; i++) {
            secret[i] = Integer.parseInt(st.nextToken());
        }

        int[] input = new int[N];
        st = new StringTokenizer(in.readLine());
        for(int i=0; i<N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        label:for(int i=0; i<=input.length-secret.length; i++) {
            if(input[i]==secret[0]) {
                for(int j=1; j<secret.length; j++) {
                    if(input[i+j]!=secret[j]) continue label;
                }
                System.out.println("secret");
                return;
            }
        }
        System.out.println("normal");
    }
}