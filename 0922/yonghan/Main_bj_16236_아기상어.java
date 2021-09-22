package a0917;

import java.io.*;
import java.util.*;

public class Main_bj_16236_아기상어 {
	
	static class info{
	    int i, j;

	    info(int i, int j){
	        this.i = i;
	        this.j = j;
	    }
	}
	
	    public static int n, shark_i, shark_j, min_dist, min_i, min_j, result, eat_cnt = 0, shark_size = 2;
	    public static int [][] map, check;
	    public static int [] dx = {0, 0, 1, -1}, dy = {-1, 1, 0, 0};
	    public static void main(String args[]) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	        n = Integer.parseInt(br.readLine());
	        map = new int[n + 1][n + 1];
	        check = new int[n+1][n+1];

	        for(int i=1; i<=n; i++){
	            StringTokenizer st = new StringTokenizer(br.readLine());
	            for(int j=1; j<=n; j++){
	                map[i][j] = Integer.parseInt(st.nextToken());

	                if(map[i][j] == 9){
	                    shark_i = i;
	                    shark_j = j;
	                    map[i][j] = 0;
	                }
	            }
	        }

	        while(true){
	        	
	            init_check(); //bfs 수행을 위한 초기화

	            bfs(shark_i, shark_j); // 먹을수 있는 물고기 찾음
	            

	            if(min_i != 21 && min_j != 21){
	            	//이동시간 더해주고
	                result += check[min_i][min_j];
	                //먹은수 증가
	                eat_cnt++;
	                //먹은 물고기 수가 상어사이즈와 같다면 상어사이즈 증가 (먹은수 리셋)
	                if(eat_cnt == shark_size){
	                    shark_size++;
	                    eat_cnt = 0;
	                }
	                //상어자리 갱신
	                map[min_i][min_j] = 0;
	                
	                shark_i = min_i;
	                shark_j = min_j;
	            }
	            
	            //먹을수 있는 물고기가 없는 경우
	            else{
	                break;
	            }
	        }

	        System.out.println(result);
	    }

	    public static void init_check(){
	        min_dist = 401; 
	        
	        min_i = 21; 
	        min_j = 21;

	        for(int i=1; i<=n; i++){
	            for(int j=1; j<=n; j++){
	                check[i][j] = -1; //이동거리 저장 배열 -1로 초기화
	            }
	        }
	    }

	    public static void bfs(int i, int j){
	        Queue<info> q = new LinkedList<info>();
	        check[i][j] = 0; //i , j 아기상어 좌표
	        
	        q.add(new info(i, j));

	        while(!q.isEmpty()){
	            info cur = q.poll();
	            i = cur.i;
	            j = cur.j;

	            for(int d=0; d<4; d++){ //4방탐색
	                int ni = i + dx[d];
	                int nj = j + dy[d];
	                
	                //범위 밖
	                if(ni < 1 || ni > n || nj < 1 || nj > n) continue;
	                //이미 방문했거나, 아기상어보다 큰 물고기인 경우 
	                if(check[ni][nj] != -1 || map[ni][nj] > shark_size) continue;
	                
	                //물고기 좌표 ni nj 까지 거리 갱신
	                check[ni][nj] = check[i][j] + 1;
	                
	                //먹을수 있는 물고기
	                if(map[ni][nj] != 0 && map[ni][nj] < shark_size){

	                    if(min_dist > check[ni][nj]){ //현재 물고기 까지 거리가 짧은 경우
	                        min_i = ni;
	                        min_j = nj;
	                        min_dist = check[ni][nj];
	                    }
	                    else if(min_dist == check[ni][nj]){ //같은 이동시간의 물고기 찾은 경우
	                        if(min_i == ni){ 
	                            if(min_j > nj){ //위쪽
	                                min_i = ni;
	                                min_j = nj;
	                            }
	                        }else if(min_i > ni){ //왼쪽
	                            min_i = ni;
	                            min_j = nj;
	                        }
	                    }
	                }

	                q.add(new info(ni, nj));
	            }
	        }

	    }
	}
