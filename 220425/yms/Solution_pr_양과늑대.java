import java.util.*;

class Solution {
    
    static ArrayList<Integer>[] edge;
    static boolean[] visited;
    static int result;
    
    static void dfs(ArrayList<Integer> list, int sheep, int wolf, int[] info) {
        result = Math.max(result, sheep);
        
        for(int cur : list) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.addAll(list);
            for(int num : edge[cur]) {
                if(!visited[num]) {
                    if(info[num]==1 && wolf+1>=sheep) continue;
                    temp.add(num);
                    visited[num] = true;
                    
                    if(info[num]==0) dfs(temp, sheep+1, wolf, info);
                    else dfs(temp, sheep, wolf+1, info);
                    
                    visited[num] = false;
                    temp.remove(Integer.valueOf(num));
                }
            }
        }
        
    }
    
    public int solution(int[] info, int[][] edges) {
        int answer = 1;
        result = 1;
        edge = new ArrayList[info.length]; 
        for(int i=0; i<info.length; i++) {
            edge[i] = new ArrayList<>();
        }
        
        for(int i=0; i<edges.length; i++) {
            edge[edges[i][0]].add(edges[i][1]);
            edge[edges[i][1]].add(edges[i][0]);
        }
        
        visited = new boolean[edge.length];
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        dfs(list,0,0,info);
        
        answer = result;
        
        return answer;
    }
}