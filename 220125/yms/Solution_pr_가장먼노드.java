import java.util.*;

class Solution {
    
    static int[] visited;
    static int[][] matrix;
    static ArrayList<Integer>[] list;
    static int max;
    
//     static void bfs(int n) {
//         Queue<Integer> q = new ArrayDeque<>();
//         visited = new int[n+1];
//         q.offer(1);
//         visited[1] = 0;
        
//         while(!q.isEmpty()) {
//             int cur = q.poll();
//             for(int i=2; i<=n; i++) {
//                 if(matrix[cur][i]==1 && visited[i]==0) {
//                     visited[i] = visited[cur]+1;
//                     q.offer(i);
//                     max = Math.max(max, visited[i]);
//                 }
//             }
//         }
//     }
    
    static void bfs(int n) {
        Queue<Integer> q = new ArrayDeque<>();
        visited = new int[n+1];
        q.offer(1);
        visited[1] = 0;
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            for (Integer num : list[cur]) {
                if (num!= 1 && visited[num]==0) {
                    q.offer(num);
                    visited[num] = visited[cur]+1;
                    max = Math.max(max, visited[num]);
                }
        }
        }
    }
    
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
//         matrix = new int[n+1][n+1];
      
//         for(int i=0; i<edge.length; i++) {
//             matrix[edge[i][0]][edge[i][1]] = 1;
//             matrix[edge[i][1]][edge[i][0]] = 1;
//         }
     
//         bfs(n);
   
        ArrayList<Integer>[] list = new ArrayList[n+1];
        
        for(int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int i=0; i<edge.length; i++) {
            list[edge[i][0]].add(edge[i][1]);
            list[edge[i][1]].add(edge[i][0]);
        }
        
        bfs(n);
        
        for(int i=1; i<=n; i++) {
            if(visited[i]==max) answer++;
        }
        
        return answer;
    }
}