package hw1022;

import java.io.*;
import java.util.*;

public class Main_bj_17135_캐슬디펜스 {
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N,M,D,MAX=Integer.MIN_VALUE;
	static int [][]arr;
	static int numbers[];
	private static void comb(int start,int cnt) {									//궁수 있는 자리를 조합으로 뽑음
		if(cnt==3) {																//세명 다 뽑았다면
			game();																	//본격적인 게임 시작
			return;
		}
		for(int i=start;i<M;i++) {
			numbers[cnt]=i;
			comb(i+1,cnt+1);
		}
	}
	private static void game() {					
		int [][]copy = new int[N][M];												//게임을 진행하면서 많이 달라질테니 copy배열 선언
		for(int i=0;i<N;i++)							
			copy[i] = Arrays.copyOf(arr[i], arr[i].length);							//원래 배열을 copy
		int total=0;																//궁수가 죽인 병사 수
		int x=N;																	//x:병사가 한턴마다 내려가야하는데 이를 배열 행을 밑에서부터 지우는 것으로 대체
		while(x>0) {
			List<Node>list = new ArrayList<Node>();									//1궁수마다 죽일 병사의 위치를 저장할 리스트
			for(int i=0;i<3;i++) {													//궁수3명이니까 궁수마다
				int minDist = Integer.MAX_VALUE;									//가장가까운 거리를 구하기위한 minDist
				boolean flag = false;												//죽일 적이 있는지 확인할 flag
				int cx=-1,cy=-1;													//적의 위치
				for(int p=0;p<x;p++) {
					int y = numbers[i];												//열정보 저장(행은 N으로 정해져있음)
					for(int q=0;q<M;q++) {
						if(copy[p][q]==1) {											//1인걸 만나면
							int len = Math.abs(p-x)+Math.abs(q-y);					//거리 재기
							if(len>D) continue;										//D보다 큰경우 out
							flag = true;											//D보다 작은 경우가 있다면 죽일 적이 있다는 뜻이니 true
							if(minDist>len) {										//최소 거리보다 현재가 작은 거리라면
								minDist = len;										//최소거리 바꿈	
								cx=p;												//현재 적의 위치로 바꿈
								cy=q;
							}else if(minDist==len) {								//최소거리와 현재 거리가 같다면
								if(cy>q) {											//현재가 더 왼쪽에 있다면
									cx=p;											//현재 적의 위치로 바꿈
									cy=q;
								}
							}
						}
					}
				}
				if(flag) {															//죽일 적이 있다면
					list.add(new Node(cx,cy));										//그 적의 위치를 list에 저장
				}
			}																		//3명이 동시에 공격이니 3명이 각자 자신이 죽일 수 있는 적을 찾은 뒤에 시작
			for(int i=0;i<list.size();i++) {										//죽일 적이 담긴 list size만큼 돌면서
				if(copy[list.get(i).x][list.get(i).y]==1) {							//(죽여야할 적이 중복될 수도 있으니까if문을 씀) 적이 아직안죽었다면
					copy[list.get(i).x][list.get(i).y]=0;							//죽임
					total++;														//죽은 적+1
				}
			}
			x--;																	//적 밑으로 한칸 이동=마지막행 사라짐(행을 하나씩 줄임)
		}
		MAX = Math.max(MAX, total);													//3명의 위치 경우마다 죽인 적의 수중 최대로 많이 죽인 수를 갱신
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());										//행
		M = Integer.parseInt(st.nextToken());										//열
		D = Integer.parseInt(st.nextToken());										//공격할 수 있는 최대 거리
		arr = new int[N][M];														//원본 배열
		numbers=new int[M];															//조합으로 뽑은 3명의 열자리 배열
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		comb(0,0);
		System.out.println(MAX);
		
	}
}
