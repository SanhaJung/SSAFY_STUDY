package a0917.algo;

import java.util.*;
import java.io.*;

public class Main_bj_16234_인구이동 {

	static int n;
    static int L;
    static int R;
    static int map[][];
    static boolean visited[][];
    // 인접 국가 인구 범위에 있는 국가 담는 리스트
    static List<Node> range_country_list;
    
    static int dx[] = { 0, 1, 0,-1}; // 상우하좌
    static int dy[] = {-1, 0, 1, 0};
    
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        int day = 0;
        map = new int[n][n];
        
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                map[i][j]=sc.nextInt();
            }
        }
        // 인구이동이 필요한만큼 반복
        while(true){
        	// 방문 초기화
            visited = new boolean[n][n];  
            if(!mapcheck()){ // 인구이동 필요
                day++;    
            } else {      // 인구이동 끝
                break; 
            }
        }
        // 
        System.out.println(day);
        
    }
    
    // 인구 이동 필요한지 전체 지도 확인.
    public static boolean mapcheck(){
    	// 이동이 더이상 필요하지 않을 경우 true. 필요한 경우 false
        boolean isDone = true;  
        
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                // 방문하지 않은 경우
                if(!visited[i][j]){
                	// range_country_list 초기화: 현재 day에 
                    range_country_list = new LinkedList<>();
                    // 방문처리 & list 추가
                    visited[i][j] =true;
                    range_country_list.add(new Node(i,j));
                    
                    int sum = dfs(i,j,range_country_list,0);  // sum - 리스트에 저장된 값의 합.
                    if(range_country_list.size() > 1){        // 리스트 크기가 1 이상일 경우
                    										  // (= 인구이동이 필요한 데이터가 있을경우)
                        change(sum,range_country_list);       // 평균값 계산해서 map 갱신.
                        isDone= false; 
                    }
                }
            }
        }
        return isDone;
    }
    
    // 평균값으로 map 갱신.
    public static void change(int sum,List<Node> range_country_list){
        int avg = sum/range_country_list.size();
        for(Node node:range_country_list){
            map[node.x][node.y] = avg;
        }
    }
    
    
    public static int dfs(int i, int j, List<Node> range_country_list, int sum){
//    	visited[i][j] =true;
        sum= map[i][j]; 		// 현재 day에 평균내야할 인구의 합 누적 시작
        
        for(int d=0;d<4;d++){
            int ni = i+dx[d]; 
            int nj = j+dy[d];
            // ni, nj 인덱스 범위 밖이면 지나감
            if(0<=ni&& ni<n && 0<=nj && nj<n && !visited[ni][nj]) {
                int diff = Math.abs(map[i][j] - map[ni][nj]);   // 인구 차이가
                if(diff >= L && diff <= R){						// 범위 내에 있다면
                	visited[ni][nj] = true;
                    range_country_list.add(new Node(ni,nj));	// 인구 평균낼 국가 리스트 추가
                    sum+= dfs(ni,nj,range_country_list,sum);    // 인구 총합
                }
            }
        }
        return sum;
    }    
    
}
class Node{
    int x;
    int y;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }



}
