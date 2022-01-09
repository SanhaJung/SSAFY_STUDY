package hw0916;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main_bj_15685_드래곤커브 {

	static int n,x,y,d,g;
    static int[] dx = {1,0,-1,0};
    static int[]dy ={0,-1,0,1};
    static int [][]arr= new int[101][101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            List<Integer>list = new ArrayList<>();
            list.add(d);
            arr[y][x]=1;
            
            for(int p=1;p<=g;p++){ //세대
                for(int q=list.size()-1;q>=0;q--){
                    list.add((list.get(q)+1)%4);
                }
            }
            //list -> 방향
            for(int j=0;j< list.size();j++){
                int cy = y+dy[list.get(j)];
                int cx = x+dx[list.get(j)];
                arr[cy][cx]=1;
                y=cy;
                x=cx;
            }
        }
        int result=0;
        for(int i=1;i<101;i++){
            for(int j=1;j<101;j++){
                if(arr[i-1][j]==1&&arr[i][j]==1&&arr[i][j-1]==1&&arr[i-1][j-1]==1){
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}
