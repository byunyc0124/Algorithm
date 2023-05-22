import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static char[][] brd;
	static int[][] check;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 크기
		brd = new char[n][n]; // 그림판
		for (int i = 0; i < n; i++) {
			String st = br.readLine();
			for (int j = 0; j < n; j++) {
				brd[i][j] = st.charAt(j);
			}
		}
		
		check = new int[n][n]; // 방문 체크 배열
		
		int cnt1 = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(check[i][j] == 0) {
					cnt1++;
					dfs(i, j);
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(brd[i][j] == 'R') {
					brd[i][j] = 'G';
				}
				if(check[i][j] == 1) {
					check[i][j] = 0;
				}
			}
		}
		
		int cnt2 = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(check[i][j] == 0) {
					cnt2++;
					dfs(i, j);
				}
			}
		}
		
		System.out.println(cnt1+" "+cnt2);
		
	}
	
	static void dfs(int si, int sj) {
		
		check[si][sj] = 1;
		
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		
		for(int k=0; k<4; k++) {
			int ni = si + dx[k];
			int nj = sj + dy[k];
			if(0 <= ni && ni < n && 0 <= nj && nj < n && brd[ni][nj] == brd[si][sj] && check[ni][nj] == 0) {
				check[ni][nj] = 1;
				dfs(ni, nj);
			}
		}
	}

}