import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] brd = new int[n+1][n+1];
		int[][] sums = new int[n+1][n+1];
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=n; j++) {
				brd[i][j] = Integer.parseInt(st.nextToken());
				if(i==1 && j==1) sums[i][j] = brd[i][j];
				else sums[i][j] = sums[i-1][j] + sums[i][j-1] - sums[i-1][j-1] + brd[i][j];
			}
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			System.out.println(sums[x2][y2] - sums[x1-1][y2] - sums[x2][y1-1] + sums[x1-1][y1-1]);
		}
	}
}