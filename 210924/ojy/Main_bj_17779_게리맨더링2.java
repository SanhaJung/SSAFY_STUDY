package hw0923;
import java.io.*;
import java.util.*;

//구역은 5개 선거구로 나누고 선거구는 구역을 적어도 한개 이상 포함
public class Main_bj_17779_게리맨더링2 {
	static int n,total;
    static int [][]arr;
    
    private static int calc(int x, int y,int d1, int d2){
        List<Integer>list = new ArrayList<>();
        boolean[][]visit = new boolean[n+1][n+1];
        //5번 선거구에 대한 영역
        //경계값 정하기 
        //칸의 경계선을 보고 하면 됨
        for(int i=0;i<=d1;i++){
            visit[x+i][y-i] = true; //북 -> 서(1)
            visit[x+d2+i][y+d2-i]= true;    // 동 -> 남(4)
        }
        for(int i=0;i<=d2;i++){
            visit[x+i][y+i]= true;  //북 -> 동(2)
            visit[x+d1+i][y-d1+i] = true;// 서 -> 남(3)
        }
        
        //안에 값 채우기
        boolean start = false;
        for(int i =x+1; i<x+d1+d2 ; i++) {
            for (int j = 1; j<=n ;j++) {
                if(start){	//경계선 안으로
                    if(visit[i][j]){	//만약에 방문한 곳이라면 -> 경계선이라면
                        start = false;	//flag를 false로
                        break;
                    }
                    visit[i][j] = true;	//안부분 방문처리
                }
                if(visit[i][j])	//경계선을 만나면
                    start = true;	//flag를 true
            }
        }
        //여기까지
        
        //각각의 선거구를 돌면서 인구수를 저장
        //1번 선거구
        int cnt=0;
        int t=0;	//t에 1~4까지의 인구수를 저장
        for(int i=1;i<x+d1;i++){
            for(int j=1;j<=y;j++){
                if(!visit[i][j])	//5인구수가 아니라면
                    cnt+=arr[i][j];
            }
        }
        t+=cnt;	
        list.add(cnt);	//인구수 저장
        cnt=0;
        
        //2번선거구
        for(int i=1;i<=x+d2;i++){
            for(int j=y+1;j<=n;j++){
                if(!visit[i][j])
                 cnt+=arr[i][j];
            }
        }

        t+=cnt;
        list.add(cnt);
        cnt=0;
        //3번 선거구
        for(int i=x+d1;i<=n;i++){
            for(int j=1;j<y-d1+d2;j++){
                if(!visit[i][j])
                    cnt+=arr[i][j];
            }
        }

        t+=cnt;
        list.add(cnt);
        cnt=0;
        //4번선거구
        for(int i=x+d2+1;i<=n;i++){
            for(int j=y-d1+d2;j<=n;j++){
                if(!visit[i][j])
                    cnt+=arr[i][j];
            }
        }
        t+=cnt;
        list.add(cnt);
        
        list.add(total-t);//마지막 총인구수에서 1~4인구수를 빼서 5인구수를 저장
        Collections.sort(list);	// sort
        return list.get(4)-list.get(0);	//가장 많은 인구수 - 가장 적은 인구수
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1][n+1];
        int min=987654321;
        for(int i=1;i<=n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                total+=arr[i][j];
            }
        }
        //행은 1~n, 열도 1~n
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                for(int d1=1;d1<=n;d1++){	//d1,d2또한 1부터 시작
                    for(int d2=1;d2<=n;d2++){
                        if(i+d1+d2<=n&&1<=j-d1&&j+d2<=n) {	// 경계값 안에 포함되면
                            min = Math.min(calc(i, j, d1, d2), min);	//계산
                        }
                    }
                }
            }
        }
        System.out.println(min);
    }
}
