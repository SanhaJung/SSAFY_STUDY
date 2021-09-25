package hw0905;
import java.io.*;
import java.util.*;
public class Main_bj_3190_뱀 {

	static int n,k,L;
    static int [][]arr;
    static int[]dx = {0,1,0,-1};    //동남서북
    static int[]dy = {1,0,-1,0};
    static class Snake{
        int x;
        int y;
        int dir;
        public Snake(int x, int y,int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        StringTokenizer st;
        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            arr[x][y]=2;
        }
        int cnt = Integer.parseInt(br.readLine());
        ArrayDeque<Snake>deque = new ArrayDeque<>();
        Map<Integer,Character>map = new HashMap<>();
        deque.offer(new Snake(0,0,0));
        for(int i=0;i<cnt;i++){
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            char ch = st.nextToken().charAt(0);
            map.put(key,ch);
        }
        int count=0;
        int dir=0,cx,cy;
        while(true){
            count++;
            Snake snake = deque.peekLast();
            cx = snake.x+dx[dir];
            cy = snake.y+dy[dir];
            if(map.containsKey(count)){
                if(map.get(count).equals('D'))
                    dir = (dir+1)%4;
                else
                    dir = (dir+3)%4;
                map.remove(count);
            }
            if(cx<0||cx>=n||cy<0||cy>=n) break;
            if(arr[cx][cy]==1)break;
            if(arr[cx][cy]==2){
                arr[cx][cy]=1;
                deque.offer(new Snake(cx,cy,dir));
            }else{
                deque.offer(new Snake(cx,cy,dir));
                arr[cx][cy]=1;
                Snake s = deque.pollFirst();
                arr[s.x][s.y] = 0;
            }
        }
        System.out.println(count);
    }
}
