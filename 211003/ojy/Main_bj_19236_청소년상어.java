package hw1003;
import java.io.*;
import java.util.*;
class Fish{
    int x;  //위치
    int y;
    int num;    //물고기번호
    int dir;    //방향
    int alive;  //죽었는지 살았는지 판별
    public Fish(int x, int y, int num, int dir,int alive) {
        this.x = x;
        this.y = y;
        this.num = num;
        this.dir = dir;
        this.alive = alive;
    }
}
public class Main_bj_19236_청소년상어 {
	static int [][]arr;
    static Fish[] fish; //물고기 정보 저장
    public static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}; //상, 상좌, 좌, 좌하, 하, 하우, 우, 상우
    public static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    public static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[4][4];
        fish = new Fish[17];
        for(int i=0;i<4;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<4;j++){
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken())-1;
                fish[num] = new Fish(i,j,num,dir,1);
                arr[i][j] = num;    //물고기 저장
            }
        }
        //상어이동 과정
        //1. 시작 -> 물고기 먹음
        //2. 물고기 이동
        //3. 상어이동
        int cx =0,cy=0; //상어 위치
        int cd = fish[arr[0][0]].dir; //초기 상어 방향
        int eat = arr[0][0]; //먹은 물고기 번호 합 저장 변수 - (0,0)물고기 먹음

        fish[arr[0][0]].alive = 0; //(0,0)물고기 죽음
        arr[0][0] = -1; //상어가 있는 위치 -1

        dfs(cx,cy,cd,eat);
        System.out.println(ans);
        br.close();
    }
    private static void moveFish(){
        //물고기 번호가 작은 것부터 이동
        //한칸 이동: 이동O- 빈칸, 다른 물고기가 있는 칸
        //         이동x- 상어가 있음, 공간 경계나감
        //각 물고기는 방향이 이동할 수 있는 칸을 향할때까지 45도 반시계회전
        //이동할 수 있는 칸 x->이동 x
        //다른 물고기가 있는 칸으로 이동시 서로의 위치 바꿈

        for(int i=1;i<17;i++){
            if(fish[i].alive == 0) continue;    //죽은 물고기 패스

            int cnt = 0;
            int dir = fish[i].dir;  //현재 물고기 방향
            int cx=0,cy=0;
            while(cnt<8){
                dir%=8; //방향+1로 범위 넘어가는 것 방지
                fish[i].dir = dir;

                cx = fish[i].x+dx[dir];
                cy = fish[i].y+dy[dir];
//                System.out.println(cx+" "+cy);
                if(cx>=0&&cx<4&&cy>=0&&cy<4&&arr[cx][cy]!=-1){
                    if(arr[cx][cy]==0){ //해당위치에 물고기 가 없을 경우
                        arr[fish[i].x][fish[i].y] = 0; //기존 위치 빈칸
                        fish[i].x = cx;
                        fish[i].y = cy;
                        arr[cx][cy] = i;
                    }else{  //이동할 위치에 다른 물고기가 있을 경우
                        int change = fish[arr[cx][cy]].num;
                        fish[change].x = fish[i].x;
                        fish[change].y = fish[i].y;
                        arr[fish[change].x][fish[change].y] = change;

                        //현재 물고기 위치 변경
                        fish[i].x = cx;
                        fish[i].y = cy;
                        arr[cx][cy] = i;
                    }
                    break;
                }else{
                    dir++;
                    cnt++;
                }
            }
        }
    }
    //모든 상어의 경로를 구해보며 물고기를 먹음
    //1번물고기를 먹었다면 상어가 물고기 먹고 -> 물고기 이동 -> 상어 이동 반복
    //1번 물고기를 먹은 경우의 수를 재귀를 통해 훑어본뒤, 2번 물고리르 먹은 경우의 수를 진행
    private static void dfs(int cx,int cy,int cd, int eat){
        ans = Math.max(ans,eat);    //이전에 먹은 물고기 번호 합 max 비교해 ans 저장

        //물고기를 먹고 진행할때 물고기들이 다 이동하므로
        //다른 물고기를 먹을땐 맨 처음 상태의 물고기 상태가 아닌 죽은 물고기가 있는채로 이동
        //상어가 물고기를 먹기전의 맵상태, 물고기 상태를 기억
        int[][] copyArr = new int[arr.length][arr.length];
        for(int i=0;i<arr.length;i++)
            System.arraycopy(arr[i],0,copyArr[i],0,arr.length);

        Fish[] copyFish = new Fish[fish.length];
        for(int i=1;i<17;i++)
            copyFish[i] = new Fish(fish[i].x,fish[i].y,fish[i].num,fish[i].dir,fish[i].alive);

        moveFish();

        //상어이동
        //상어가 이동하면서 어떤 물고기를 먹고
        //또 그 물고기의 자리에서 이동하면서 먹으며
        //여러 경우의 수가 나오므로 dfs를 돌면서 물고기의 상태를 변경해주고
        //다시 물고기 상태, 상어 위치를 원래대로 해준다.
        //상어가 어느 칸을 선택하느냐에 따라 여러 가지 경우의 수가 발생
        for(int i=1;i<4;i++){   //4*4행렬로 1칸,2칸,3칸 까지 최대 이동
            int rx = cx+dx[cd]*i;
            int ry = cy+dy[cd]*i;
//            System.out.println(rx+" "+ry);
            if(0<=rx&&rx<4&&0<=ry&&ry<4&&arr[rx][ry]!=0){

                int eatFish = arr[rx][ry];
                int rd = fish[eatFish].dir;
                arr[cx][cy]=0;
                arr[rx][ry] = -1;
                fish[eatFish].alive=0;

                dfs(rx,ry,rd,eat+eatFish);

                fish[eatFish].alive=1;// 물고기 상태, 상어 위치 원래대로 되돌리기
                arr[cx][cy]=-1;
                arr[rx][ry] = eatFish;
            }

        }
        //물고기를 움직이므로 그로 인해 물고기들의 좌표가 바뀌니까 다시 되돌리는 과정 진행
        for(int j=0;j<arr.length;j++)
        	System.arraycopy(copyArr[j],0,arr[j],0,arr.length);
        
        for(int j=1;j<17;j++)
        	fish[j] = new Fish(copyFish[j].x,copyFish[j].y,copyFish[j].num,copyFish[j].dir,copyFish[j].alive);
    }
	
}
