class Solution {
    
    static double getStart(String line) {
        String[] s = line.split(" ");           //s[0] = 날짜 s[1] = 시간 s[2] = 처리시간
        String[] times = s[1].split(":");
        int end_hour = Integer.parseInt(times[0]);
        int end_minute = Integer.parseInt(times[1]);
        double end_second = Double.parseDouble(times[2]);

        double end_time = end_hour*3600 + end_minute*60 + end_second;
        double proctime = Double.parseDouble(s[2].replace("s",""))-0.001;   // 시작시간 0.001초 처리
        double start_time = end_time-proctime;
        
        return start_time;
    }
    
    public int solution(String[] lines) {
        int answer = 0;
        
        for(int i=0; i<lines.length; i++) {
            String[] s = lines[i].split(" ");               //s[0] = 날짜 s[1] = 시간 s[2] = 처리시간
            String[] times = s[1].split(":");
            int end_hour = Integer.parseInt(times[0]);
            int end_minute = Integer.parseInt(times[1]);
            double end_second = Double.parseDouble(times[2]);
            
            double end_time = end_hour*3600 + end_minute*60 + end_second;   // 초로 환산
            int cnt = 0;
            
            for(int j=i; j<lines.length; j++) {
                double start_time = getStart(lines[j]);
                // 현재 작업이 끝난 시간부터 1초 동안 끝난 작업의 개수를 세준다.
                //      => 입력값이 끝난 시간을 기준으로 정렬되어 있기 때문에 끝난 시간보다 시작 시간이 빠르면 포함됨
                if(end_time+1 > start_time) cnt++;
            }
            
            answer = Math.max(answer, cnt);
            
        }
        
        return answer;
    }
}