import java.util.*;

class Solution {
    static boolean[] menu;                          // A~Z 중 메뉴 표시
    static char[] select;                           // 조합으로 뽑은 코스
    static ArrayList<String> list;                  // 답
    static PriorityQueue<Menu> pq;                  // 순열로 뽑은 코스를 담아 최대 주문횟수를 가진 코스를 뽑기 위한 큐
    static int max;                                 // 순열로 뽑은 코스으 최대 주문 횟수
    
    static class Menu implements Comparable<Menu> {
        String menu;
        int cnt;
        
        public Menu(String menu, int cnt) {
            this.menu = menu;
            this.cnt = cnt;
        }
        
        @Override
        public int compareTo(Menu o) {              // 주문횟수가 많은 순으로 정렬
            return -(this.cnt-o.cnt);
        }
    }
    
    static void combination(String[] orders, int size, int cnt, int start) {        
        if(cnt==size) {
            String s = "";
            for(int i=0; i<select.length; i++) {
                s += select[i];
            }
            int num = check(orders);
            max = Math.max(max, num);
            pq.offer(new Menu(s,num));
            return;
        }
        
        for(int i=start; i<26; i++) {
            if(menu[i]) {                                   // A~Z 중 있는 메뉴라면
                select[cnt] = (char)('A'+i);
                combination(orders, size, cnt+1, i+1);
            }
        }
    }
    
    static int check(String[] orders) {
        int cnt = 0;
        
        label:for(int i=0; i<orders.length; i++) {
            for(int j=0; j<select.length; j++) {
                if(!orders[i].contains(select[j]+"")) continue label;
            }
            cnt++;                                           // 해당 코스가 주문내역에 있으면 cnt 증가
        }
        return cnt;
    }
    
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        menu = new boolean[26];
        list = new ArrayList<>();
        
        for(int i=0; i<orders.length; i++) {
            for(int j=0; j<orders[i].length(); j++) {
                if(!menu[orders[i].charAt(j)-'A']) menu[orders[i].charAt(j)-'A'] = true;
            }
        }
        
        for(int i=0; i<course.length; i++) {
            select = new char[course[i]];
            pq = new PriorityQueue<>();
            max = Integer.MIN_VALUE/2;
            combination(orders, course[i], 0, 0);
            
            while(!pq.isEmpty()) {
                Menu menu = pq.poll();
                if(menu.cnt<max) break;                // 해당 메뉴의 주문횟수가 최대 주문 횟수와 같을때만 list에 add
                if(menu.cnt>=2) list.add(menu.menu);   // 문제에서 주어진 최소 주문횟수 2회
                
            }
        }
        
        Collections.sort(list);          
        answer = new String[list.size()];
        int idx = 0;
        
        for(String menu : list) {
            answer[idx++] = menu;
        }
        
        return answer;
    }
}