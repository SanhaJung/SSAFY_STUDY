import java.util.;

class Solution {
    
    static boolean visited[][][];
    static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
    
    static int move(int r, int c, int d, String[] grid) {
        int cnt = 0;
 
        while (!visited[r][c][d]) { 
            visited[r][c][d] = true;
 
            if (grid[r].charAt(c) == 'L')
                d = (d-1+4)%4;
            else if (grid[r].charAt(c) == 'R')
                d = (d+1)%4;
 
            r = (r+dr[d]+grid.length) % grid.length;
            c = (c+dc[d]+grid[0].length()) % grid[0].length();
            
            cnt++;
        }
 
        return cnt;
    }
    
    public int[] solution(String[] grid) {
        int[] answer = {};
        visited = new boolean[grid.length][grid[0].length()][4];
        ArrayListInteger list = new ArrayList();
        
        for(int i=0; igrid.length; i++) {
            for(int j=0; jgrid[i].length(); j++) {
                for(int d=0; d4; d++) {
                    if(!visited[i][j][d]) {
                       list.add(move(i,j,d,grid));
                    }
                }
            }
        }
        
        Collections.sort(list);
        
        answer = new int[list.size()];
        for(int i=0; ilist.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}