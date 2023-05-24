import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C;
	static int ans;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int size = (int) Math.pow(2, N);
		
		visit(0, 0, size, 0);
		
		System.out.println(ans);
	}
	
	static void visit(int r, int c, int size, int cnt) {
		
		if(r == R && c == C) {
			ans = cnt;
			return;
		}
		
		int half = size/2;
		int sum = half * half;
		if(r <= R && R < r+half && c <= C && C < c+half) {
			visit(r, c, half, cnt);
		}else if(r <= R && R < r+half && c+half <= C && C < c+size) {
			visit(r, c+half, half, cnt+sum);
		}else if(r+half <= R && R < r+size && c <= C && C < c+half) {
			visit(r+half, c, half, cnt+sum*2);
		}else if(r+half <= R && R < r+size && c+half <= C && C < c+size) {
			visit(r+half, c+half, half, cnt+sum*3);			
		}
	}

}