package hw1009;
import java.io.*;
import java.util.*;
class Node{
    int x;
    int y;
    int d;

    public Node(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}
public class Main_bj_17837_새로운게임2 {

	 static int N,K;
	    static int[][]arr;
	    static Node[]nodes;
	    static List<Integer>[][]list;
	    static int []dx = {0,0,-1,1};
	    static int[]dy={1,-1,0,0};
	    private static void turn(){
	        for(int tc=1;tc<=1000;tc++){
	            for(int i=1;i<=K;i++){   //K번말까지 순서대로 이동
	                Node node =nodes[i];
	                int x =node.x;
	                int y =node.y;
	                int cx = x +dx[node.d];
	                int cy = y+dy[node.d];

	                if(cx<1||cx>N||cy<1||cy>N||arr[cx][cy]==2){ //파랑색
	                    if(node.d==0)node.d=1;
	                    else if(node.d==1)node.d=0;
	                    else if(node.d==2)node.d=3;
	                    else if(node.d==3)node.d=2;
	                    cx = node.x+dx[node.d];
	                    cy = node.y+dy[node.d];
	                }
	                if(cx<=0||cx>N||cy<=0||cy>N||arr[cx][cy]==2) continue;
	                if(1<=cx&&cx<=N&&1<=cy&&cy<=N){
	                    if (arr[cx][cy] == 1) { //빨간색
	                        int idx = list[x][y].indexOf(i);
	                        for(int j = list[x][y].size()-1;j>=idx;j--){
	                            int tmp = list[x][y].get(j);
	                            list[cx][cy].add(tmp);
	                            nodes[tmp].x =cx;
	                            nodes[tmp].y = cy;
	                            list[x][y].remove(j);
	                        }
	                    } else if (arr[cx][cy] == 0) {  //하얀색
	                        int idx = list[x][y].indexOf(i);
	                        for(int j = idx;j<list[x][y].size();j++){
	                            int tmp = list[x][y].get(j);
	                            list[cx][cy].add(tmp);
	                            nodes[tmp].x =cx;
	                            nodes[tmp].y = cy;
	                            list[x][y].remove(j--);
	                        }
	                    }
	                    if (list[node.x][node.y].size() >= 4) {
	                            System.out.println(tc);
	                            return;
	                    }
	                }
	            }
	        }
	        System.out.println(-1);
	        return;
	    }
	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        N = Integer.parseInt(st.nextToken());
	        K = Integer.parseInt(st.nextToken());
	        arr = new int[N+1][N+1];
	        list = new ArrayList[N+1][N+1];
	        nodes = new Node[K+1];
	        for(int i=1;i<=N;i++){
	            st = new StringTokenizer(br.readLine());
	            for(int j=1;j<=N;j++) {
	                arr[i][j] = Integer.parseInt(st.nextToken());
	                list[i][j] = new ArrayList<Integer>();
	            }
	        }
	        for(int i=1;i<=K;i++){
	            st = new StringTokenizer(br.readLine());
	            int x = Integer.parseInt(st.nextToken());
	            int y = Integer.parseInt(st.nextToken());
	            int d = Integer.parseInt(st.nextToken())-1;
	            nodes[i] = new Node(x,y,d);
	            list[x][y].add(i);
	        }
	        turn();
	    }

}
