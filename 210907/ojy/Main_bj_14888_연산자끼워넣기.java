package hw0907;
import java.io.*;
import java.util.*;
public class Main_bj_14888_연산자끼워넣기 {

	static int n,total,max=-987654321,min=987654321;
    static int []arr;
    static int []visit;
    static int [] operators = new int[4];
    private static int calc(){
        int sum = arr[0];
        for(int i=0;i<n-1;i++){
            if(visit[i]==0){
                sum+=arr[i+1];
            }else if(visit[i]==1){
                sum-=arr[i+1];
            }else if(visit[i]==2){
                sum*=arr[i+1];
            }else if(visit[i]==3){
                sum/=arr[i+1];
            }
        }
        return sum;
    }
    private static void permutation(int cnt){
        if(cnt==n-1){
            int result = calc();
            max = Math.max(max,result);
            min = Math.min(min,result);
            return;
        }
        for(int i=0;i<4;i++){
            if(operators[i]>=1){
                operators[i]--;
                visit[cnt]=i;
                permutation(cnt+1);
                operators[i]++;
                visit[cnt]=-1;
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        visit = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            visit[i]=-1;
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++){
            operators[i]=Integer.parseInt(st.nextToken());
            total = operators[i];
        }
        permutation(0);
        System.out.println(max);
        System.out.println(min);

    }
}
