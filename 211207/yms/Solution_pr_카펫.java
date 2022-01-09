class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        
        for(int r=1; r<=yellow; r++) {
            if(yellow%r>0) continue;
            int c = yellow/r;
            if(2*(r+c)+4 == brown) {
                answer = new int[] {c+2, r+2};
                break;
            }
            
        }
        
        return answer;
    }
}