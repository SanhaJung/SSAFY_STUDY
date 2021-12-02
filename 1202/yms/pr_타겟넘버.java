class Solution {
    static int answer;
    
    static void dfs(int[] numbers, int sum, int cnt, int target) {
        
        if(cnt == numbers.length) {
            if(sum == target) {
                answer++;
            }
            return;
        }
        
        dfs(numbers, sum+numbers[cnt], cnt+1, target);
        dfs(numbers, sum-numbers[cnt], cnt+1, target);
        
    }
    
    public int solution(int[] numbers, int target) {        
        dfs(numbers,0,0,target);        
        return answer;
    }
}