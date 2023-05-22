import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	
	static int n, m;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for(int test=1; test<=t; test++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			int[][] B = new int[m + 1][n + 1];
	        
	        for (int i = 0; i <= m; i++) {
	            for (int j = 0, end = Math.min(i, n); j <= end; j++) { // 뽑으려는 개수(K)보다 i개수가 작으면 i까지 뽑기
	                if (j == 0 || i == j) B[i][j] = 1;
	                else B[i][j] = B[i - 1][j - 1] + B[i - 1][j];
	            }
	        }
	        
	        System.out.println(B[m][n]);
		}
	}

}