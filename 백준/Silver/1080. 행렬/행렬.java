import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] a = new int[n][m];
		int[][] b = new int[n][m];
		for(int i=0; i<n; i++) {
			char[] ch = br.readLine().toCharArray();
			for(int j=0; j<m; j++) {
				a[i][j] = ch[j]-'0';
			}
		}
		for(int i=0; i<n; i++) {
			char[] ch = br.readLine().toCharArray();
			for(int j=0; j<m; j++) {
				b[i][j] = ch[j]-'0';
			}
		}
		
		int ans = 0;
		for(int i=0; i<n-2; i++) {
			for(int j=0; j<m-2; j++) {
				if(a[i][j] != b[i][j]) {
					change(a, i, j);
					ans++;
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(a[i][j] != b[i][j]) {
					System.out.println(-1);
					return;
				}
			}
		}
		System.out.println(ans);
	}
	
	private static void change(int[][] input, int x, int y) {
		for(int i=x; i<x+3; i++) {
			for(int j=y; j<y+3; j++) {
				input[i][j] = 1 - input[i][j];
			}
		}
	}

}