import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static class Info{
		int to; // 도착정점
		int weight; // 가중치
		public Info(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수
		int K = Integer.parseInt(br.readLine())-1; // 시작 정점의 번호
		boolean[] visited = new boolean[V];
		ArrayList<Info>[] adjList = new ArrayList[V];
		for(int i=0; i<V; i++) {
			adjList[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken())-1;
			int v = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			adjList[u].add(new Info(v, w));
		}
		
		final int INF = Integer.MAX_VALUE;
		int[] distance = new int[V]; // 출발 정점에서 자신까지 오는 최소 비용
		
		Arrays.fill(distance, INF); // 최소값 갱신 로직은 반영해야 하므로 큰값으로 초기화
		distance[K] = 0;
		
		int min, current;
		for (int c = 0; c < V; c++) {
			// step 1. 경유지로 처리되지 않은 정점 중 출발지에서 가장 가까운 정점 선택
			current = -1;
			min = INF;
			
			for (int i = 0; i < V; i++) {
				if(!visited[i] && min > distance[i]) {
					min = distance[i];
					current = i;
				}
			}
			
			if(current == -1) break;
			visited[current] = true;
			
			// step 2. 위에서 선택된 정점을 경유지로 해서 갈 수 있는 다른 미방문 인접정점과의 비용 최소값 갱신
			for (int j = 0; j < adjList[current].size(); j++) {
				if(!visited[adjList[current].get(j).to] && adjList[current].get(j).weight != 0 
						&& distance[adjList[current].get(j).to] > min+adjList[current].get(j).weight) {
					distance[adjList[current].get(j).to] = min+adjList[current].get(j).weight;
				}
			}
		}

		for(int i=0; i<V; i++) {
			System.out.println(distance[i] != INF ? distance[i] : "INF");			
		}
	}

}