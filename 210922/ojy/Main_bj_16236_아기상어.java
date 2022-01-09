package hw0922;
import java.io.*;
import java.util.*;
public class Main_bj_16236_아기상어 {
	static class Fish {
        int x;
        int y;
        int dist;

        public Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

    }
    static int n;
    static int [][]arr;
    static int[]dx ={0,-1,0,1};
    static int[]dy ={1,0,-1,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        Queue<Fish>queue = new LinkedList<>();  //상어가 담길 큐
        PriorityQueue<Fish>fish = new PriorityQueue<>(new Comparator<Fish>() {  //피시가 담길 우선순위 큐
            @Override
            public int compare(Fish o1, Fish o2) {
                int len = o1.dist-o2.dist;
                if(len==0){                     //거리가 동일하다면
                    int up = o1.x-o2.x;         // 가장 위
                    if(up==0){                  // 그것도 여러개면
                        return o1.y-o2.y;       // 가장 왼쪽
                    }else
                        return up;
                }else
                    return len;
            }
        });
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
               if(arr[i][j]==9) {
                    queue.offer(new Fish(i, j, 0));
                    arr[i][j] = 0;
                }
            }
        }
        int sharkSize=2;
        int time=0;
        int count=0;
        while(true){
            boolean[][]visit = new boolean[n][n];
            //상어가 갈 수 있는 곳을 다 탐색
            while(!queue.isEmpty()){
                Fish shark =queue.poll();
                visit[shark.x][shark.y] = true;                                 //상어가 갈 수 있는 곳 방문처리
                for(int i=0;i<4;i++){                                           //사방 탐색
                    int cx = shark.x+dx[i];
                    int cy = shark.y+dy[i];
                    if(0<=cx&&cx<n&&0<=cy&&cy<n&&!visit[cx][cy]){               //아직 탐색하지 않은 곳이 있다면
                        if(arr[cx][cy]<=sharkSize) {                            //sharSize보다 작다면 탐색가능
                            if(arr[cx][cy]!=0&&arr[cx][cy]<sharkSize)           // 물고기가 있고 sharkSize보다 작으면
                                fish.offer(new Fish(cx,cy,shark.dist+1));   //물고기 queue에 넣어주고 shark가 몇칸 이동할 수 있는지 넣어줌
                            visit[cx][cy] = true;                               //해당 부분 탐색완료
                            queue.offer(new Fish(cx, cy, shark.dist + 1));  //빈칸이든 물고기가size와 같거나 작으면 이동할 수 있으므로 그부분은 queue에 넣어줌
                        }
                    }
                }
            }
            if(!fish.isEmpty()){            //먹을 수 있는 물고기가 있다면
                Fish f = fish.poll();       //우선순위큐로 이미 우선순위가 정해진 큐에서 제일 높은 우선순위를 가진 물고기를 poll
                count++;                    //먹은 갯수 증가
                if(count == sharkSize){     //만약 먹은 갯수가 상어크기와 같아지면
                    sharkSize++;            //상어크기 1증가
                    count=0;                //횟수 초기화
                }
                queue.offer(new Fish(f.x,f.y,0));   //먹은 물고기자리에 상어를 배치
                time+=f.dist;                           //시간은 상어가 이동한만큼의 거리
                arr[f.x][f.y] = 0;                      //그자리는 이제 빈칸
                fish.clear();                           //상어의 자리가 바뀌었으므로 먹을 수 있는 fish 다시 생각해야함
            }else                           //먹을 수 있는 물고기 없으면
                break;                      //아기상어할일 끝남
        }
        System.out.println(time);
    }
}
