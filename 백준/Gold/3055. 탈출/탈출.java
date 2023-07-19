import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int r, c, ans;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new int[r][c]; // 1: 시작위치, 2: 도착위치, 0은 빈 공간, 3은 물 시작위치
		int sx=0, sy=0;
		int wx=0, wy=0;
		for(int i=0; i<r; i++) {
			char[] ch = br.readLine().toCharArray();
			for(int j=0; j<c; j++) {
				map[i][j] = ch[j] - 46;
				if(map[i][j] == '*' - 46) map[i][j] = 3;
				else if(map[i][j] == 'S' - 46) {
					map[i][j] = 1;
					sx = i;
					sy = j;
				}else if(map[i][j] == 'D' - 46) map[i][j] = 2;
				else if(map[i][j] == 'X' - 46) map[i][j] = -1;
			}
		}
		
		ans = Integer.MAX_VALUE; // 최소 시간
		water();
		bfs(sx, sy);		
		
		if(ans == Integer.MAX_VALUE) System.out.println("KAKTUS");
		else System.out.println(ans);
	}

	private static void bfs(int sx, int sy) {
		Queue<Pos> q = new ArrayDeque<Pos>();
		int[][] visited = new int[r][c];
		q.add(new Pos(sx, sy));
		visited[sx][sy] = 1;
		int x, y; // 현재 위치
		int nx, ny; // 다음 위치
		while(!q.isEmpty()) {
			Pos pos = q.poll();
			x = pos.x;
			y = pos.y;
			for(int k=0; k<4; k++) {
				nx = x + dx[k];
				ny = y + dy[k];
				if(nx >= 0 && nx < r && ny >= 0 && ny < c && visited[nx][ny] == 0) {
					if(map[nx][ny] == 2) ans = Math.min(visited[x][y], ans);
					else if(map[nx][ny] > 3 + visited[x][y] || map[nx][ny] == 0) {
						q.add(new Pos(nx, ny));
						visited[nx][ny] = visited[x][y] + 1;
					}
				}
			}
		}
	}
	
	private static void water() { // 시간 별로 물이 흐름
		Queue<Pos> q = new ArrayDeque<>();
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(map[i][j] == 3) { // 믈 시작 위치 큐에 넣어줌
					q.add(new Pos(i, j));					
				}
			}
		}

		int x, y; // 현재 위치
		int nx, ny; // 다음 위치
		while(!q.isEmpty()) {
			Pos pos = q.poll();
			x = pos.x;
			y = pos.y;
			for(int k=0; k<4; k++) {
				nx = x + dx[k];
				ny = y + dy[k];
				if(nx >= 0 && nx < r && ny >= 0 && ny < c && map[nx][ny] == 0) {
					map[nx][ny] = map[x][y] + 1;
					q.add(new Pos(nx, ny));
				}
			}
		}
	}
	
	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}