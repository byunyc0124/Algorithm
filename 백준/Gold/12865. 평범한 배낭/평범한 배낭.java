import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 물품의 수
		int k = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게
		int[] w = new int[n+1];
		int[] v = new int[n+1];
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] d = new int[n+1][k+1];
		
		for(int i=1; i<=n; i++) { // i: 물건
			for(int j=1; j<=k; j++) { // j: 가방의 무게
				if(w[i] > j) {
					d[i][j] = d[i-1][j];
				}else {
					d[i][j] = Math.max(d[i-1][j], v[i]+d[i-1][j-w[i]]);					
				}
			}
		}
		
		System.out.println(d[n][k]);
	}

}