package hw0914;
import java.io.*;
import java.util.*;
public class Main_bj_15683_감시 {

	static int n,m,min =987654321;
    static int [][]arr;
    static int [][]copyArr;
    static List<CCTV>list = new ArrayList<>();
    static int []number;
    static class CCTV{
        int num;
        int x;
        int y;

        public CCTV(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }
    private static void permutation(int cnt){
        if(cnt == list.size()){
            copyArr = new int [n][m];
            int count=0;
            for(int i=0;i<n;i++)
                copyArr[i]= Arrays.copyOf(arr[i],m);

            for(int i=0;i<list.size();i++)
                go(list.get(i),number[i]);

            for(int i=0;i<n;i++) {
                for (int j = 0; j < m; j++)
                    if (copyArr[i][j] == 0)
                        count++;
            }
            min = Math.min(count,min);
            return;
        }
        for(int i=0;i<4;i++){
                number[cnt] = i; 
                permutation(cnt + 1);
        }
    }
    private static void go(CCTV cctv,int d){
        int num = cctv.num;
        int x = cctv.x;
        int y = cctv.y;
        if(num==1){
            if(d==0) up(x,y);
            else if(d==1)  right(x,y);
            else if(d==2) down(x,y);
            else if(d==3) left(x,y);
        }else if(num==2){
            if(d%2==0){
                right(x,y);
                left(x,y);
            }else {
                up(x,y);
                down(x,y);
            }
        }else if(num==3){
            if(d==0){
                up(x,y);
                right(x,y);
            }else if(d==1){
                right(x,y);
                down(x,y);
            }else if(d==2){
                down(x,y);
                left(x,y);
            }else if(d==3){
                left(x,y);
                up(x,y);
            }
        }else if(num==4){
            if(d==0){
               left(x,y);
               up(x,y);
               right(x,y);
            }else if(d==1){
                up(x,y);
                right(x,y);
                down(x,y);
            }else if(d==2){
                right(x,y);
                down(x,y);
                left(x,y);
            }else if(d==3){
                down(x,y);
                left(x,y);
                up(x,y);
            }
        }else if(num==5){
            up(x,y);
            right(x,y);
            down(x,y);
            left(x,y);
        }
    }
    private static void right(int x,int y){
        for(int i=y+1;i<m;i++){
            if(copyArr[x][i]==0)
                copyArr[x][i]=-1;
            else if(copyArr[x][i]==6)
                break;
        }
    }
    private static void left(int x,int y){
        for(int i=y-1;i>=0;i--){
            if(copyArr[x][i]==0)
                copyArr[x][i] = -1;
            else if(copyArr[x][i]==6)
                break;
        }
    }
    private static void down(int x, int y){
        for(int i=x+1;i<n;i++){
            if(copyArr[i][y]==0)
                copyArr[i][y]=-1;
            else if(copyArr[i][y]==6)
                break;
        }
    }
    private static void up(int x,int y){
        for(int i=x-1;i>=0;i--){
            if(copyArr[i][y]==0)
                copyArr[i][y]=-1;
            else if(copyArr[i][y]==6)
                break;
        }
    }
    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int [n][m];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]!=0&&arr[i][j]!=6) {
                    list.add(new CCTV(arr[i][j], i, j));
                }
            }
        }
        number = new int [list.size()];
        permutation(0);
        System.out.println(min);
    }
}
