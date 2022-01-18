class Solution {
    public int solution(String s) {
        int answer = 0;
        String[] nums = {"zero", "one", "two", "three", "four", "five", "six",
                            "seven", "eight", "nine"};
        
        for(int i=0; i<nums.length; i++) {
            s = s.replace(nums[i], Integer.toString(i));
        }
        
        answer = Integer.parseInt(s);
        return answer;
    }
}