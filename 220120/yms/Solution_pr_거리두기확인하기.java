import java.util.*;

class Solution {
    static int dr[] = {-1,0,1,0}, dc[] = {0,1,0,-1};
    
    static boolean bfs(int r, int c, String[] p) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[5][5];
        q.offer(new int[] {r,c,0});
        visited[r][c] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            r = cur[0];
            c = cur[1];
            int distance = cur[2];
            
            if(distance>=2) continue;
            
            for (int d=0; d<4; d++) {
                int nr = r+dr[d];
                int nc = c+dc[d];

                if(0<=nr && nr<5 && 0<=nc && nc<5 && !visited[nr][nc] && p[nr].charAt(nc)!='X') {
                    if (p[nr].charAt(nc) == 'P')
                        return false;
                    
                    else if (p[nr].charAt(nc) == 'O')
                        visited[nr][nc]=true;
                        q.offer(new int[] {nr,nc,distance+1});
                }
            }
        }

        return true;
    }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
         label:for (int i = 0; i < places.length; i++) {
            String[] p = places[i];

            for (int r=0; r<5; r++) {
                for (int c=0; c<5; c++) {
                    if (p[r].charAt(c) == 'P') {
                        if(!bfs(r, c, p)) {
                            answer[i]=0;
                            continue label;
                        };
                    }
                }
            }
            answer[i] = 1;
        }

        return answer;
    }

}