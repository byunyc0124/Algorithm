import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int INF = 9999999;
	static int n, map[][];
	static Info[] infoArr;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for(int test=0; test<T; test++) {
			n = Integer.parseInt(br.readLine());
			infoArr = new Info[n+2];
			for(int i=0; i<n+2; i++) {
				st = new StringTokenizer(br.readLine());
				infoArr[i] = new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			map = new int[n+2][n+2];
			for(int i=0; i<n+2; i++) {
				for(int j=0; j<n+2; j++) {
					if(i!=j && !canGo(i, j)) { // i에서 j로 바로 갈 수 없다면
						map[i][j] = INF;
					}
				}
			}
			
			for(int k=1; k<n+1; ++k) { // 경유지는 항상 편의점만 가능
				for(int i=0; i<n+2; ++i) {
					if(i==k) continue; // 출발지와 경유지가 같다면 다음 출발지
					for(int j=0; j<n+2; ++j) {
						if(i==j || k==j) continue; // 경유지와 목적지가 같거나 출발지가 곧 목적지라면 패스
						if(map[i][j] > map[i][k]+map[k][j]) {
							map[i][j] = map[i][k]+map[k][j];
						}
					}
				}
			}
			
			if(map[0][n+1] == INF) {
				System.out.println("sad");				
			}else {
				System.out.println("happy");				
			}
		}
	}
	
	private static boolean canGo(int i, int j) { // i에서 j로 갈 수 있는지 판별 
		int d = Math.abs(infoArr[i].x - infoArr[j].x) + Math.abs(infoArr[i].y - infoArr[j].y);
		if(d > 50 * 20) return false;
		else return true;
	}

	static class Info{
		int x, y;

		public Info(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}