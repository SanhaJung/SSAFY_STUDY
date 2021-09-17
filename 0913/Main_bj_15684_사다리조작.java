package hw0914;
import java.io.*;
import java.util.*;
public class Main_bj_15684_사다리조작 {

	static int n,m,h,ans;
    static int [][]arr;
    static boolean isFinish = false;
    static boolean [][]visit;
    static int []dx = {-1,0,1,0};
    static int []dy = {0,1,0,-1};

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        arr = new int [h+1][n+1];

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());

            //a높이에서 b번과 b+1번 세로 선 연결
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b] = 1;  //우측으로 이동
            arr[a][b+1] = 2; //좌측으로 이동
        }
        //추가할 가로선 갯수를 미리 정해놔야 탐색 종료조건으로 걸 수 있음
        // 아래 반복문에서 i는 추가할 가로선의 수
        for(int i=0;i<=3;i++){
            ans = i;
            dfs(1,1,0);
            if(isFinish) break;
        }

        System.out.println(isFinish?ans:-1);
        br.close();
    }
    //line: 추가한 가로선의 갯수(3개가 넘어가면 더이상 탐색 무의미)
    private static void dfs(int x, int y,int line){
        if(isFinish) return;
        if(ans == line){
            if(check()) isFinish = true;
            return;
        }
        for(int i=x;i<=h;i++){
            for(int j=y;j<n;j++){
                if(arr[i][j]==0&&arr[i][j+1]==0){
                    arr[i][j] = 1;
                    arr[i][j+1] =2;
                    dfs(1,1,line+1);
                    arr[i][j]=0;
                    arr[i][j+1] = 0;
                }
            }
        }
    }

    private static boolean check(){
        for(int i=1;i<=n;i++){
            int cx = 1;
            int cy = i;

            while(cx<=h){
                if(arr[cx][cy] == 1) cy++;  //우측이동
                else if(arr[cx][cy] == 2) cy--; //좌측이동
                cx++;   //행+1칸 이동
            }
            if(cy!=i) return false; //i번으로 출발해서 i번으로 도착하지 않는게 하나라도 있다면

        }
        return true;
    }
}
