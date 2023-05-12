import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m, map[][], index[];
	static List<Pos> virus, space;
	static List<int[]> combiList;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 세로 크기
		m = Integer.parseInt(st.nextToken()); // 가로 크기
		map = new int[n][m]; // 지도 정보
		virus = new ArrayList<>();
		space = new ArrayList<>();
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virus.add(new Pos(i, j));
				}
				if(map[i][j] == 0) {
					space.add(new Pos(i, j));
				}
			}
		}
		
		index = new int[3];
		combiList = new ArrayList<>();
		combi(0, 0);
		int ans = Integer.MAX_VALUE;
		for(int i=0; i<combiList.size(); i++) {
			ans = Math.min(countVirus(combiList.get(i)), ans);
		}
		ans = space.size() - 3 - (ans - virus.size());
		System.out.println(ans);
		
	}
	
	private static void combi(int cnt, int start) {
		if(cnt == 3) {
			combiList.add(Arrays.copyOf(index, 3));
			return;
		}
		
		for(int i=start; i<space.size(); i++) {
			index[cnt] = i;
			combi(cnt+1, i+1);
		}
		
	}
	
	private static class Pos{
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	private static int countVirus(int[] createWall) {
		int cnt = 0;
		for(int i=0; i<3; i++) {
			map[space.get(createWall[i]).x][space.get(createWall[i]).y] = 1;
		}
		
		Queue<Pos> q = new ArrayDeque<>();
		int[][] visited = new int[n][m];
		for(int i=0; i<virus.size(); i++) {
			q.add(virus.get(i));
			cnt++;
			visited[virus.get(i).x][virus.get(i).y] = 1; 
		}
		int x = 0; int y = 0;
		int nx = 0; int ny = 0;
		while(!q.isEmpty()) {
			Pos pos = q.poll();
			x = pos.x;
			y = pos.y;
			for(int k=0; k<4; k++) {
				nx = x + dx[k];
				ny = y + dy[k];
				if(nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == 0 && visited[nx][ny] == 0) {
					cnt++;
					visited[nx][ny] = 1;
					q.add(new Pos(nx, ny));
				}
			}
		}

		for(int i=0; i<3; i++) {
			map[space.get(createWall[i]).x][space.get(createWall[i]).y] = 0;
		}
		return cnt;
	}

}