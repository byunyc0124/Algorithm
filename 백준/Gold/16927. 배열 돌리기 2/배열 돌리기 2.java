import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] arr;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		int[][] result = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = Math.min(n, m) / 2; // 생성할 큐의 개수
		for(int i=0; i<cnt; i++) {
			turn(0+i, 0+i, n-i, m-i, r);
			
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				sb.append(arr[i][j]+" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}

	private static void turn(int sx, int sy, int lx, int ly, int cnt) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		for(int j=sy; j<ly; j++) { // 00 01 02 03
			q.offer(arr[sx][j]);
		}
		for(int i=sx+1; i<lx; i++) { // 13 23 33
			q.offer(arr[i][ly-1]);
		}
		for(int j=ly-2; j>=sy; j--) { // 32 31 30
			q.offer(arr[lx-1][j]);
		}
		for(int i=lx-2; i>=sx+1; i--) { // 20 10
			q.offer(arr[i][sy]);
		}
        
		int s = q.size();
		cnt = cnt % s;
        
		while(cnt > 0) {
			int a = q.poll();
			q.offer(a);
			cnt--;
		}
		
		for(int j=sy; j<ly; j++) { // 00 01 02 03
			arr[sx][j] = q.poll();
		}
		for(int i=sx+1; i<lx; i++) { // 13 23 33
			arr[i][ly-1] = q.poll();
		}
		for(int j=ly-2; j>=sy; j--) { // 32 31 30
			arr[lx-1][j] = q.poll();
		}
		for(int i=lx-2; i>=sx+1; i--) { // 20 10
			arr[i][sy] = q.poll();
		}
		
	}

}