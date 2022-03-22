import java.util.*;

class Solution {
    
    static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};  // 상우하좌
    static int answer;
    static int[][] cost;    // 메모이제이션
    
    static void dfs(int[][] board, boolean[][] visited, int r, int c, int b, int sum) {
        
        if(sum>=answer) return;
        
        if(sum>cost[r][c]) return;
        
        if(r==board.length-1 && c==board.length-1) {
            answer = Math.min(answer, sum);
            return;
        }
        
        for(int d=0; d<4; d++) {
            int nr = r+dr[d];
            int nc = c+dc[d];
            if(0<=nr & nr<board.length && 0<=nc && nc<board.length && board[nr][nc]==0 && !visited[nr][nc]) {
                visited[nr][nc] = true;
                if(b==-1 || b%2 == d%2) {
                    cost[nr][nc] = Math.min(cost[nr][nc], sum+100);
                    dfs(board, visited, nr, nc, d, sum+100);
                }
                else {
                    cost[nr][nc] = Math.min(cost[nr][nc], sum+600);
                    dfs(board, visited, nr, nc, d, sum+600);
                }
                visited[nr][nc] = false;
            }
        }
    }
/*    25번 테스트 케이스 반례

           23 21
    26 (27 | 29)      <--- 26에서 꺾어 들어온 값이 더 작음
       (33 | 30)      <--- 다음 값을 계산하니 21에서 들어온 값이 더 작음
       
      cost를 3차원 배열로 각각 [r][c][d] 경우마다 고려해서 풀면 된다고함
*/
    static void bfs(int[][] board) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[board.length][board.length];
        q.offer(new int[] {0,0,-1,0});
        visited[0][0] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            int r = cur[0];
            int c = cur[1];
            int b = cur[2];
            int sum = cur[3];
            
            if(r==board.length-1 && c==board.length-1) {
                answer = Math.min(answer, sum);
                continue;
            }
            
            
            for(int d=0; d<4; d++) {
                int nr = r+dr[d];
                int nc = c+dc[d];
                int nsum = 0;
                if(0<=nr && nr<board.length && 0<=nc && nc<board.length && board[nr][nc]==0) {                 
                    if(b==-1 || b%2 == d%2) nsum = sum+100;
                    else nsum = sum+600;
                    
                    // cost가 같을 때도 포함해줘야함 -> 다음 분기에 직선도로가 될 수도있고 코너가 될 수도 있기 때문에
                    if(!visited[nr][nc] || cost[nr][nc]>=nsum) {    
                        visited[nr][nc] = true;
                        cost[nr][nc] = nsum;
                        q.offer(new int[] {nr,nc,d,nsum});
                    }
                }
            }
        }
    }
    
    public int solution(int[][] board) {
        answer = Integer.MAX_VALUE/2;
        boolean[][] visited = new boolean[board.length][board.length];
        cost = new int [board.length][board.length];
        
        for(int i=0; i<board.length; i++) {
            Arrays.fill(cost[i],Integer.MAX_VALUE/2);
        }
        
        dfs(board,visited,0,0,-1,0);
        // bfs(board);
        return answer;
    }
}