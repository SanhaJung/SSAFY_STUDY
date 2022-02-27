class Solution {
    
    static char[] select;
    static boolean[] visited;
    static char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static int answer;
    
    static void permutation(String[] data, int cnt) {      
        if(cnt==8) {
            if(check(data)) answer++;
            return;
        }
        
        for(int i=0; i<friends.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                select[cnt] =  friends[i];
                permutation(data, cnt+1);
                visited[i] = false;
            }
        }
    }
    
    static boolean check(String[] data) {
        for(int i=0; i<data.length; i++) {
            char friend1 = data[i].charAt(0);
            char friend2 = data[i].charAt(2);
            char condition = data[i].charAt(3);
            int num = data[i].charAt(4)-'0';
            
            int friend1_idx = 0;
            int friend2_idx = 0;
            
            for(int j=0; j<select.length; j++) {
                if(select[j]==friend1) friend1_idx = j;
                else if(select[j]==friend2) friend2_idx = j; 
            }
            
            if(condition=='=') {
                if(Math.abs(friend1_idx - friend2_idx) != num+1) return false; 
            }
            else if (condition=='>') {
                if(Math.abs(friend1_idx - friend2_idx) <= num+1) return false; 
            }
            else {
                if(Math.abs(friend1_idx - friend2_idx) >= num+1) return false; 
            }
        }
        
        return true;
    }
    
    public int solution(int n, String[] data) {
        select = new char[8];
        visited = new boolean[8];
        answer = 0;
        permutation(data,0);
        
        return answer;
    }
}