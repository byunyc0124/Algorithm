import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main{
	
	static int m, n, h;
	static int[] dx = {-1, 1, 0, 0, 0, 0}; // 위아래 왼오 앞뒤
	static int[] dy = {0, 0, -1, 1, 0, 0};
	static int[] dz = {0, 0, 0, 0, -1, 1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken()); // 가로
		n = Integer.parseInt(st.nextToken()); // 세로
		h = Integer.parseInt(st.nextToken()); // 높이
		int[][][] box = new int[h][n][m];
		Deque<Pos> dq = new ArrayDeque<>();
		for(int i=0; i<h; i++) {
			for(int j=0; j<n; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0; k<m; k++) {
					box[i][j][k] = Integer.parseInt(st.nextToken());
					if(box[i][j][k] == 1) {
						dq.add(new Pos(i, j, k));
					}
				}
			}
		}
		
		while(!dq.isEmpty()) {
			Pos pos = dq.poll();
			int x = pos.x; // 높이
			int y = pos.y; // 가로
			int z = pos.z; // 세로
			for(int k=0; k<6; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				int nz = z + dz[k];
				if(nx >= 0 && nx < h && ny >= 0 && ny < n && nz >= 0 && nz < m && box[nx][ny][nz] == 0) {
					box[nx][ny][nz] =  box[x][y][z] + 1;
					dq.add(new Pos(nx, ny, nz));
				}
			}
		}
		
		boolean isOkay = true;
		for(int i=0; i<h; i++) {
			for(int j=0; j<n; j++) {
				for(int k=0; k<m; k++) {
					if(box[i][j][k] == 0) {
						isOkay = false;
					}
				}
			}
		}
		
		if(isOkay) {
			int ans = 0;
			for(int i=0; i<h; i++) {
				for(int j=0; j<n; j++) {
					for(int k=0; k<m; k++) {
						ans = Math.max(ans, box[i][j][k]);
					}
				}
			}
			System.out.println(ans-1);
		}else {
			System.out.println(-1);
		}
	}
	
	static class Pos{
		int x, y, z;
		public Pos(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

}