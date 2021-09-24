package hw0922;
import java.io.*;
import java.util.*;
public class Main_bj_17142_연구소3 {
	static int n,m,total,min=987654321;
    static class Virus {
        int x;
        int y;
        int cnt;
        public Virus(int x, int y,int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    static Virus[]numbers;
    static List<Virus>input = new ArrayList<>();
    static int [][]arr;
    static boolean [][]visit;
    static int[] dx={0,-1,0,1};
    static int[] dy ={1,0,-1,0};
    static Queue<Virus>queue = new LinkedList<>();
    private static void bfs(){
        int num=0;
        while (!queue.isEmpty()){
            Virus in = queue.poll();
            for(int i=0;i<4;i++){
                int cx = in.x+dx[i];
                int cy = in.y+dy[i];
                if(0<=cx&&cx<n&&0<=cy&&cy<n){
                    if(!visit[cx][cy]&&arr[cx][cy]!=1){
                        if(arr[cx][cy]==0)
                            num++;
                        visit[cx][cy]=true;
                        queue.offer(new Virus(cx,cy,in.cnt+1));
                    }
                }
            }
            if(total==num) {
                min = Math.min(in.cnt+1,min);
                return;
            }
        }
    }
    private static void comb(int start, int cnt){
        if(cnt==m){
            queue.clear();
            visit = new boolean[n][n];
            for(int i=0;i<m;i++) {
                queue.offer(new Virus(numbers[i].x,numbers[i].y, 0));
                visit[numbers[i].x][numbers[i].y] = true;
            }
            bfs();
            return;
        }
        for(int i=start;i< input.size();i++){
            numbers[cnt] = input.get(i);
            comb(i+1,cnt+1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        visit = new boolean[n][n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]==0)
                    total++;
                if(arr[i][j]==2) {
                    input.add(new Virus(i,j,0));
                }
            }
        }
        numbers = new Virus[m];
        comb(0,0);
        if(total==0)
            System.out.println(0);
        else {
            if(min==987654321)
                System.out.println(-1);
            else
                System.out.println(min);
        }
    }
}
