import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int n, villagers[], dp[][], visited[];
	static List<Integer>[] village;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		villagers = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			villagers[i] = Integer.parseInt(st.nextToken());
		}
		village = new ArrayList[n];
		for(int i=0; i<n; i++) {
			village[i] = new ArrayList<>();
		}
		int a, b;
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			village[a].add(b);
			village[b].add(a);
		}
		
		dp = new int[n][2];
		visited = new int[n];
		select(0); // 우수마을 선정 시작
		
		int answer = Math.max(dp[0][0], dp[0][1]);
		System.out.println(answer);
	}

	private static void select(int start) {
		if(visited[start] == 1) return; // 이미 방문했으면 종료
		
		visited[start] = 1; // 방문체크
		dp[start][0] = 0; // 해당 마을 우수마을 선정X
		dp[start][1] = villagers[start]; // 해당 마을 우수마을 선정O
		
		for(int next : village[start]) { // 인접한 마을 탐색
			if(visited[next] == 1) continue; // 방문한 적 있으면 다음 마을 탐색
			
			select(next); // 인접 마을의 경우의 수 탐색 -> 인접 마을의 선정된 경우와 선정되지 않은 경우의 최대 주민 수의 합을 저장해옴

			dp[start][0] = dp[start][0] + Math.max(dp[next][0], dp[next][1]);
			dp[start][1] = dp[start][1] + dp[next][0];
		}
	}
}