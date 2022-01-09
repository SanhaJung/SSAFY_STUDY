package hw0905;
import java.io.*;
import java.util.*;
public class Main_bj_14501_퇴사 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int []dp = new int[n+1];
        int [][] item = new int[n][2];
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            item[i][0] = Integer.parseInt(st.nextToken());
            item[i][1] = Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<n;i++){
            if(item[i][0]+i<=n){
                dp[item[i][0]+i] = Math.max(dp[i+item[i][0]],dp[i]+item[i][1]);
            }
            dp[i+1] = Math.max(dp[i],dp[i+1]);
        }
        System.out.println(dp[n]);
    }
}
