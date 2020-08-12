package com.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1260. DFS와 BFS
 * JAVA_서울_6반_엄재웅
 */

/* TODO
 * 1. N,M,V 값 입력받기
 * 2. 간선 표시를 하는 input배열 생성
 * 3. 배열을 1, 2 순서로 정렬시킴
 * 4. dfs
 *   1. 방문했다고 표시 후
 *   2. 간선이 나타나면 재귀로 들어감
 * 5. bfs
 *   1. 큐에 시작부분 넣음
 *   2. 큐가 빌 때 까지
 *     1. 맞는 간선이 나타나면 queue에 넣음
 */

public class bj1260_DFSAndBFS_엄재웅 {
	private static int N, M, V;
	private static int[][] input;
	private static boolean[] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		input = new int[M][2];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			if(start < end) {
				input[i][0] = start;
				input[i][1] = end;
			} else {
				input[i][0] = end;
				input[i][1] = start;
			}
		}
		
		Arrays.sort(input, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0])
					return Integer.compare(o1[1], o2[1]);
				else
					return Integer.compare(o1[0], o2[0]);
			}
		});
		
		visited = new boolean[N+1];
		dfs(V);
		System.out.println();
		visited = new boolean[N+1];
		bfs(V);
	}
	
	private static void dfs(int start) {
		System.out.print(start + " ");
		visited[start] = true;
		
		for(int i = 0; i < M; i++) {
			if(input[i][0] == start && !visited[input[i][1]])
				dfs(input[i][1]);
			else if(input[i][1] == start && !visited[input[i][0]])
				dfs(input[i][0]);
				
		}
	}
	
	private static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			System.out.print(current + " ");
			
			for(int j = 0; j < M; j++) {
				if(input[j][0] == current && !visited[input[j][1]]) {	
					q.offer(input[j][1]);
					visited[input[j][1]] = true;
				} else if(input[j][1] == current && !visited[input[j][0]]) {	
					q.offer(input[j][0]);
					visited[input[j][0]] = true;
				}
			}
		}
	}
}
