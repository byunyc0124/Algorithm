import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int m, ans;
	static int cs, hs; // 치킨집의 개수, 집의 개수
	static int[][] dis; // 치킨거리 배열
	static int[] remove; // 조합에 사용할 치킨집 인덱스 배열
	static int[] nums; // 조합을 저장할 임시 배열
	
	
	public static void main(String[] args) throws IOException{
		ans = 0; // 결과
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // n * n 도시
		m = Integer.parseInt(st.nextToken()); // 살릴 치킨집의 개수
		int[][] city = new int[n][n]; // 도시 정보 저장할 배열
		List<Pos> house = new ArrayList<Pos>(); // 집 정보 저장
		List<Pos> chicken = new ArrayList<Pos>(); // 치킨집 정보 저장
		hs = 0; // 집 개수
		cs = 0; // 치킨집 개수
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				if(city[i][j] == 1) {
					house.add(new Pos(i, j)); // 집 위치 정보 저장
					hs++;
				}
				if(city[i][j] == 2) {
					chicken.add(new Pos(i, j)); // 치킨집 위치 정보 저장
					cs++;
				}
			}
		}
		nums = new int[cs-m];
		
		dis = new int[hs][cs]; // 각 집에 대해서 치킨 거리 저장
		for(int i=0; i<hs; i++) {
			for(int j=0; j<cs; j++) {
				int d = Math.abs(house.get(i).x - chicken.get(j).x) + Math.abs(house.get(i).y - chicken.get(j).y);
				dis[i][j] = d;
			}
		}
		
		remove = new int[cs]; // 제거할 치킨집을 고르기 위한 인덱스 배열
		for(int i=0; i<cs; i++) {
			remove[i] = i;
		}
		for(int i=0; i<hs; i++) { // 치킨거리의 최대합 구하기
			int max = Integer.MIN_VALUE;
			for(int j=0; j<cs; j++) {
				max = Math.max(max, dis[i][j]);
			}
			ans += max;
		}
		
		comb(0, 0); // 제거할 치킨집 조합 뽑기
		
		
		System.out.println(ans);
	}
	
	private static void comb(int cnt, int start) {
		if(cnt == (cs-m)) { // (cs-m)개 뽑으면 = 제거할만큼 뽑으면
			int d = 0; 
			int[][] temp = new int[hs][cs]; // 치킨거리 배열 복사
			for(int i=0; i<hs; i++) { 
				temp[i] = dis[i].clone();
			}
			for(int i=0; i<dis.length; i++) {
				for(int j=(cs-m-1); j>=0; j--) {
					temp[i][nums[j]] = Integer.MAX_VALUE; // 제거한 치킨집이므로 최소값 갱신될 수 없게 max_value로 바꿔주기
				}
			}
			for(int i=0; i<hs; i++) {
				int min = Integer.MAX_VALUE;
				for(int j=0; j<cs; j++) {
					min = Math.min(min, temp[i][j]);
				}
				d += min;
			}
			
			ans = Math.min(ans, d); // 최소값으로 갱신
			return;
		}
		
		for(int i=start; i<cs; i++) {
			nums[cnt] = remove[i];
			comb(cnt+1, i+1);
		}
	}
	
	private static class Pos { // 집 혹은 치킨집의 위치 정보 저장
		int x, y; // 행과 열

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}

}