import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, home[][], dp[][][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 집의 크기
		StringTokenizer st = null;
		home = new int[n][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				home[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[3][n][n]; // 가로 세로 대각선
		dp[0][0][1] = 1; // 초기값 -> 처음에는 가로로 차지
		for(int i=2; i<n; i++) { // 첫 행 초기화
			if(home[0][i] == 0) { // 벽이 아니면
				dp[0][0][i] = dp[0][0][i-1]; // 다 가로로 한가지 방법씩 있음
			}
		}
		
		for(int i=1; i<n; i++) {
			for (int j = 2; j < n; j++) {
				// 대각선으로 이동할 수 있는 경우의 수(대각선으로 이동 시 세칸을 차지)
				if(home[i][j] == 0 && home[i][j-1] == 0 && home[i-1][j] == 0) {
					// 가로 -> 대각선, 세로 -> 대각선, 대각선 -> 대각선 가능
					dp[2][i][j] = dp[0][i-1][j-1] + dp[1][i-1][j-1] + dp[2][i-1][j-1];
				}
				// 가로, 세로로 이동할 수 있는 경우의 수
				if(home[i][j] == 0) {
					// 가로 -> 가로, 대각선 -> 가로 가능
					dp[0][i][j] = dp[0][i][j-1] + dp[2][i][j-1];
					// 세로 -> 세로, 대각선 -> 세로 가능
					dp[1][i][j] = dp[1][i-1][j] + dp[2][i-1][j];
				}
			}
		}
		
		int ans = dp[0][n-1][n-1] + dp[1][n-1][n-1] + dp[2][n-1][n-1];
		System.out.println(ans);
	}
}