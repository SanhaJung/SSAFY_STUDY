package hw0916;
import java.io.*;
import java.util.*;
public class Main_bj_16234_인구이동 {

	static int n,L,R,sum;
    static List<int[]>list = new ArrayList<>();
    static int [][]arr;
    static boolean [][]visit;
    static int[]dx = {-1,0,1,0};
    static int []dy = {0,1,0,-1};
    private static void DFS(int x, int y){
        visit[x][y] = true;
        for(int i=0;i<4;i++){
            int cx = x+dx[i];
            int cy = y +dy[i];
            if(0<=cx&&cx<n&&0<=cy&&cy<n){
                if(L<=Math.abs(arr[x][y]-arr[cx][cy])&&Math.abs(arr[x][y]-arr[cx][cy])<=R){
                    if(!visit[cx][cy]){
                        sum+=arr[cx][cy];
                        list.add(new int[]{cx,cy});
                        DFS(cx,cy);
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        visit = new boolean[n][n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer=0;
        exit:
        while (true) {
        boolean stop = true;
        visit = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visit[i][j]) {
                        list = new ArrayList<>();
                        sum = arr[i][j];
                        list.add(new int[]{i,j});
                        DFS(i, j);
                        if(list.size()>1) {
                            stop = false;
                            for (int p = 0; p < list.size(); p++) {
                                arr[list.get(p)[0]][list.get(p)[1]] = sum / list.size();
                            }
                        }
                    }
                }
            }
//            for(int i=0;i<n;i++)
//                System.out.println(Arrays.toString(arr[i]));
//            System.out.println();
            if(stop) break exit;
            answer++;
        }
        System.out.println(answer);
    }
}
