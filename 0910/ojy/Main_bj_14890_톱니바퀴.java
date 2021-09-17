package hw0913;
import java.io.*;
import java.util.*;
public class Main_bj_14890_톱니바퀴 {

	static List<Character>[] list = new ArrayList[4];
    private static void rotation(int index,boolean isfirst){
        if(!isfirst) {
            char ch = list[index].get(7);
            list[index].remove(7);
            list[index].add(0,ch);
        }else{
            char ch = list[index].get(0);
            list[index].remove(0);
            list[index].add(ch);
        }
    }
    private static void spin(int index, int dir){
        if(index==1){
            char cur = list[0].get(2);
            //일단 spin
            if(dir==1){
                rotation(0,false);
            }else if(dir==-1){
                rotation(0,true);
            }
            //두번째거
            if(cur!=list[1].get(6)){
                //다르면
                cur = list[1].get(2);
                if(dir==1){
                    rotation(1,true);
                }else{
                    rotation(1,false);
                }
                if(cur!=list[2].get(6)) {
                    //세번째거
                    cur = list[2].get(2);
                    if(dir==1){
                        rotation(2,false);
                    }else if(dir==-1){
                        rotation(2,true);
                    }
                    if(cur!=list[3].get(6)){
                        if(dir==1){
                            rotation(3,true);
                        }else{
                            rotation(3,false);
                        }
                    }
                }
            }
        }else if(index==4){
            char cur = list[3].get(6);
            //일단 spin
            if(dir==1){
                rotation(3,false);
            }else if(dir==-1){
                rotation(3,true);
            }
            if(cur!=list[2].get(2)) {
                //다르면
                cur = list[2].get(6);
                if (dir == 1) {
                    rotation(2,true);
                } else {
                    rotation(2,false);
                }
                if(cur!=list[1].get(2)) {
                    //세번째거
                    cur = list[1].get(6);
                    if(dir==1){
                        rotation(1,false);
                    }else if(dir==-1){
                        rotation(1,true);
                    }
                    if(cur!=list[0].get(2)){
                        if(dir==1){
                            rotation(0,true);
                        }else{
                           rotation(0,false);
                        }
                    }
                }
            }
        }
        else if(index==2){
            char cur = list[1].get(2);
            char cur_back = list[1].get(6);
            if(dir==1){
                rotation(1,false);
            }else{
                rotation(1,true);
            }
            if(cur_back!=list[0].get(2)) {
                if(dir==1){
                    rotation(0,true);
                }else{
                    rotation(0,false);
                }
            }
            if(cur!=list[2].get(6)) {
                //세번째거
                cur = list[2].get(2);
                if(dir==1){
                    rotation(2,true);
                }else{
                    rotation(2,false);
                }
                if(cur!=list[3].get(6)){
                    if(dir==1){
                        rotation(3,false);
                    }else{
                        rotation(3,true);
                    }
                }
            }

        }else if(index==3){
            char cur = list[2].get(2);
            char cur_back = list[2].get(6);
            if(dir==1){
                rotation(2,false);
            }else if(dir==-1){
                rotation(2,true);
            }
            if(cur_back!=list[1].get(2)) {
                cur_back = list[1].get(6);
                if(dir==1){
                    rotation(1,true);
                }else{
                    rotation(1,false);
                }
                if(cur_back!=list[0].get(2)){
                    if(dir==1){
                        rotation(0,false);
                    }else if(dir==-1){
                        rotation(0,true);
                    }
                }
            }
            if(cur!=list[3].get(6)){
                if(dir==1){
                    rotation(3,true);
                }else{
                    rotation(3,false);
                }
            }

        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int [][]arr = new int[4][8];
        for(int i=0;i<4;i++){
            String s = br.readLine();
            list[i] = new ArrayList<>();
            for(int j=0;j<8;j++){
                list[i].add(s.charAt(j));
            }
        }
        int k = Integer.parseInt(br.readLine());
        //2,6
        for(int i=0;i<k;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            spin(index,dir);
        }
        int sum=0;
        for(int i=0;i<4;i++){
            if(list[i].get(0)=='1')
                sum+=Math.pow(2,i);
        }
        System.out.println(sum);
    }

}
