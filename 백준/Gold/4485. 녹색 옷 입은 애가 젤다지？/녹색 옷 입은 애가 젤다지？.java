import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, cave[][], ans, rupee[][];
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = 1;
		StringTokenizer st = null;
		while(true) {
			n = Integer.parseInt(br.readLine());
			if(n == 0) break;
			ans = Integer.MAX_VALUE;
			cave = new int[n][n];
			rupee = new int[n][n];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<n; i++) {
				Arrays.fill(rupee[i], Integer.MAX_VALUE);
			}
			rupee[0][0] = cave[0][0];
			move();
			
			System.out.printf("Problem %d: %d\n", test++, rupee[n-1][n-1]);
		}
	}

	private static void move() {
		Queue<Info> q = new ArrayDeque<>();
		q.add(new Info(0, 0, cave[0][0]));
		int x = 0, y = 0, sum = 0; // 현재
		int nx = 0, ny = 0, nsum = 0; // 다음
		while(!q.isEmpty()) {
			Info info = q.poll();
			x = info.x;
			y = info.y;
			sum = info.sum;
			
			if(rupee[x][y] < sum) {
				continue;
			}
			
			for(int k=0; k<4; k++) {
				nx = x + dx[k];
				ny = y + dy[k];
				if(nx >= 0 && nx < n && ny >= 0 && ny < n) {
					nsum = sum + cave[nx][ny];
					if(nsum < rupee[nx][ny]) {
						rupee[nx][ny] = nsum;
						q.add(new Info(nx, ny, nsum));
					}
				}
			}
		}
	}
	
	private static class Info{
		int x, y; // 위치
		int sum; // 잃은 루피의 크기
		public Info(int x, int y, int sum) {
			super();
			this.x = x;
			this.y = y;
			this.sum = sum;
		}
	}

}