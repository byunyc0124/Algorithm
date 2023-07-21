

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static int r, c;
	static char[][] brd;
	static int[][] check;
	static int ans;
	static boolean flag;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()); // 행 크기
		c = Integer.parseInt(st.nextToken()); // 열 크기
		brd = new char[r][c]; // 빵집 근처의 모습
		for(int i=0; i<r; i++) {
			String str = br.readLine();
			for(int j=0; j<c; j++) {
				brd[i][j] = str.charAt(j);
			}
		}
		
		check = new int[r][c]; // 방문 체크 배열
		for(int i=0; i<r; i++) {
			check[i][0] = 1;
			flag = false;
			dfs(i, 0);
		}
		
		System.out.println(ans);
		
	}
	
	private static void dfs(int i, int j) {
		
		if(j == c-1) {
			ans++;
			flag = true;
			return;
		}
		
		/* 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선 */
		int[] dx = {-1, 0, 1};
		int[] dy = {1, 1, 1};
		for(int k=0; k<3&&!flag; k++) {
			int ni = i + dx[k];
			int nj = j + dy[k];
			if(0 <= ni && ni < r && nj < c && check[ni][nj] == 0 && brd[ni][nj] == '.') {
				check[ni][nj] = 1; // 방문 체크하고
				dfs(ni, nj); // 방문 - 재귀 호출
				
			}
		}
	}

}