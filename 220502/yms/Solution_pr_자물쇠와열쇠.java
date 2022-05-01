class Solution {
    
    static int[][] rotate(int[][] key) {
        int[][] temp = new int[key.length][key.length];
        for(int i=0; i<key.length; i++) {
            for(int j=0; j<key.length; j++) {
                temp[j][key.length-1-i] = key[i][j];
            }
        }
        return temp;
    }
    
    static int[][] copy(int[][] arr) {
        int[][] temp = new int[arr.length][arr.length];
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr.length; j++) {
                temp[i][j] = arr[i][j];
            }
        }
        return temp;
    }
    
    static int[][] match(int[][] key, int[][] board, int r, int c) {
        int[][] temp = copy(board);
        for(int i=0; i<key.length; i++) {
            for(int j=0; j<key.length; j++) {
                temp[i+r][j+c] += key[i][j];
            }
        }
        return temp;
    }
    
    static boolean check(int[][] key, int[][] board) {
        for(int i=0; i<board.length/3; i++) {
            for(int j=0; j<board.length/3; j++) {
                    if(board[board.length/3+i][board.length/3+j] != 1) return false;                    
                }
            }
        return true;
    }
    
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        int[][] board = new int[lock.length*3][lock.length*3];
        
        for(int i=0; i<lock.length; i++) {
            for(int j=0; j<lock.length; j++) {
                board[lock.length+i][lock.length+j] = lock[i][j];
            }
        }
        
        int len = board.length;
        
        for(int i=0; i<4; i++) {
            for(int r=1; r<len-lock.length; r++) {
                for(int c=1; c<len-lock.length; c++) {
                    int[][] temp = match(key,board,r,c);
                    if(check(key,temp)) return true;
                }
            }
            key = rotate(key);
        }
        
        return answer;
    }
}