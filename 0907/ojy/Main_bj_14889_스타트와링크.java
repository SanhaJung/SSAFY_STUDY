package hw0907;
import java.io.*;
import java.util.*;
public class Main_bj_14889_스타트와링크 {

	static int n,min;
    static int [][]arr;
    static int []numbers;
    private static void comb(int cnt,int start) {
        if(cnt==n/2) {
            int sumA=0,sumB=0;
            List<Integer>list = new ArrayList<>();
            for(int i=0;i<cnt;i++) {
                list.add(numbers[i]);
            }
            for(int i=0;i<n-1;i++) {
                for(int j=i+1;j<n;j++) {
                    if(!list.contains(i)&&!list.contains(j)) {
                        sumB+=arr[i][j]+arr[j][i];
                    }
                    else if(list.contains(i)&&list.contains(j)) {
                        sumA +=arr[i][j]+arr[j][i];
                    }
                }
            }
            min = Math.min(Math.abs(sumA-sumB),min);
            return;
        }
        for(int i=start;i<n;i++) {
            numbers[cnt]=i;
            comb(cnt+1,i+1);
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
            n = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            numbers = new int[n];
            min=987654321;
            for(int i=0;i<n;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            comb(0,0);
        System.out.println(min);
    }
}
