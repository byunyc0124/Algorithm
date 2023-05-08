import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	static int n, answer; // 한변의 길이와 결과값
	static char[][] map; // 평지 정보
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 평지의 한변의 길이
		map = new char[n][n]; // 지형의 정보
		for(int i=0; i<n; i++) { // 총 n줄의 정보를 받아옴
			char[] ch = br.readLine().toCharArray(); // 한줄로 주어진 string을 char배열로 나눠서 받아옴
			for(int j=0; j<n; j++) { // n열로 되어있는 char배열 정보를 map 배열에 저장함
				map[i][j] = ch[j];
			}
		}
		answer = Integer.MAX_VALUE; // 최소값으로 갱신할 것이므로 최대로 초기화
		move(); // 기둥 움직임 시작
		if(answer == Integer.MAX_VALUE) answer = 0; // 못도착하면 0으로 출력
		System.out.println(answer); // 결과 출력
	}

	private static void move() {
		Queue<Info> q = new ArrayDeque<>(); // 최소 동작 횟수를 찾아줄 것이므로 큐 이용
		int[][][] visited = new int[n][n][2]; // 방문 체크 배열
		int[] start = start(); // 시작 위치 구해옴
		int sx = start[0], sy = start[1]; // 구해온 시작 위치를 변수에 저장
		int center_x = 0, center_y = 0; // 중점 좌표
		boolean isGaro = true; // 가로인지
		if(map[sx+1][sy] == 'B') { // 세로 형태라면
			isGaro = false; // 세로 표기
			center_x = sx+1; // 초기 중점 좌표 저장
			center_y = sy; // 초기 중점 좌표 저장
			visited[center_x][center_y][0] = 1;
			visited[center_x-1][center_y][0] = 1;
			visited[center_x+1][center_y][0] = 1;
		}else {
			center_x = sx; // 초기 중점 좌표 저장
			center_y = sy+1; // 초기 중점 좌표 저장		
			visited[center_x][center_y][1] = 1;
			visited[center_x][center_y-1][1] = 1;
			visited[center_x][center_y+1][1] = 1;
		}
		int cnt = 0; // 이동 횟수
		q.add(new Info(center_x, center_y, isGaro, 0)); // 큐에 시작 정보 넣어줌
		while(!q.isEmpty()) { // 큐가 빌 때까지
			Info info = q.poll(); // 큐에 있는 정보를 하나 빼옴
			center_x = info.center_x; // 중점 좌표 - 행
			center_y = info.center_y; // 중점 좌표 - 열
			isGaro = info.isGaro; // 가로인지
			cnt = info.cnt; // 이동한 횟수
			if(isGaro) { // 가로 모양일 때
				// 최종 위치에 도착했다면
				if(map[center_x][center_y] == 'E' && map[center_x][center_y-1] == 'E' && map[center_x][center_y+1] == 'E'){
					answer = Math.min(cnt, answer); // 최소값 갱신 
					break; // 종료
				}
				// 위로 이동할 수 있다면 이동
				if(center_x >= 1 && map[center_x-1][center_y] != '1' && map[center_x-1][center_y-1] != '1' && map[center_x-1][center_y+1] != '1') {
					if(visited[center_x-1][center_y][1] != 1 && visited[center_x-1][center_y-1][1] != 1 && visited[center_x-1][center_y+1][1] != 1) {
						visited[center_x-1][center_y][1] = 1;
						visited[center_x-1][center_y-1][1] = 1;
						visited[center_x-1][center_y+1][1] = 1;
						q.add(new Info(center_x - 1, center_y, isGaro, cnt+1));						
					}
				}
				// 아래로 이동할 수 있다면 이동
				if(center_x < n-1 && map[center_x+1][center_y] != '1' && map[center_x+1][center_y-1] != '1' && map[center_x+1][center_y+1] != '1') {
					if(visited[center_x+1][center_y][1] != 1 && visited[center_x+1][center_y-1][1] != 1 && visited[center_x+1][center_y+1][1] != 1) {
						visited[center_x+1][center_y][1] = 1;
						visited[center_x+1][center_y-1][1] = 1;
						visited[center_x+1][center_y+1][1] = 1;
						q.add(new Info(center_x + 1, center_y, isGaro, cnt+1));
					}
				}
				// 오른쪽으로 이동할 수 있다면 이동
				if(center_y >= 2 && map[center_x][center_y-2] != '1') {
					if(visited[center_x][center_y-2][1] != 1) {
						visited[center_x][center_y-2][1] = 1;
						q.add(new Info(center_x, center_y-1, isGaro, cnt+1));						
					}
				}
				// 왼쪽으로 이동할 수 있다면 이동
				if(center_y < n-2 && map[center_x][center_y+2] != '1') {
					if(visited[center_x][center_y+2][1] != 1) {
						visited[center_x][center_y+2][1] = 1;
						q.add(new Info(center_x, center_y+1, isGaro, cnt+1));						
					}
				}
				// 회전할 수 있다면 회전
				if(center_x >= 1 && center_x < n-1 && center_y >= 1 && center_y < n-1 
						&& map[center_x-1][center_y] != '1' && map[center_x-1][center_y-1] != '1' && map[center_x-1][center_y+1] != '1'
						&& map[center_x+1][center_y] != '1' && map[center_x+1][center_y-1] != '1' && map[center_x+1][center_y+1] != '1') {
					if(visited[center_x][center_y][0] != 1 && visited[center_x-1][center_y][0] != 1 && visited[center_x+1][center_y][0] != 1) {
						visited[center_x][center_y][0] = 1;
						visited[center_x-1][center_y][0] = 1;
						visited[center_x+1][center_y][0] = 1;
						q.add(new Info(center_x, center_y, false, cnt+1));						
					}
				}
			}else { // 세로 모양일 때
				// 최종 위치에 도착했다면
				if(map[center_x][center_y] == 'E' && map[center_x-1][center_y] == 'E' && map[center_x+1][center_y] == 'E'){
					answer = Math.min(cnt, answer);
					break;
				}
				// 왼쪽으로 이동할 수 있다면 이동
				if(center_y >= 1 && map[center_x][center_y-1] != '1' && map[center_x-1][center_y-1] != '1' && map[center_x+1][center_y-1] != '1') {
					if(visited[center_x][center_y-1][0] != 1 && visited[center_x-1][center_y-1][0] != 1 && visited[center_x+1][center_y-1][0] != 1) {
						visited[center_x][center_y-1][0] = 1;
						visited[center_x-1][center_y-1][0] = 1;
						visited[center_x+1][center_y-1][0] = 1;
						q.add(new Info(center_x, center_y-1, isGaro, cnt+1));						
					}
				}
				// 오른쪽으로 이동할 수 있다면 이동
				if(center_y < n-1 && map[center_x][center_y+1] != '1' && map[center_x-1][center_y+1] != '1' && map[center_x+1][center_y+1] != '1') {
					if(visited[center_x][center_y+1][0] != 1 && visited[center_x-1][center_y+1][0] != 1 && visited[center_x+1][center_y+1][0] != 1) {
						visited[center_x][center_y+1][0] = 1;
						visited[center_x-1][center_y+1][0] = 1;
						visited[center_x+1][center_y+1][0] = 1;
						q.add(new Info(center_x, center_y+1, isGaro, cnt+1));						
					}
				}
				// 위쪽으로 이동할 수 있다면 이동
				if(center_x >= 2 && map[center_x-2][center_y] != '1') {
					if(visited[center_x-2][center_y][0] != 1) {
						visited[center_x-2][center_y][0] = 1;
						q.add(new Info(center_x-1, center_y, isGaro, cnt+1));						
					}
				}
				// 아래쪽으로 이동할 수 있다면 이동
				if(center_x < n-2 && map[center_x+2][center_y] != '1') {
					if(visited[center_x+2][center_y][0] != 1) {
						visited[center_x+2][center_y][0] = 1;
						q.add(new Info(center_x+1, center_y, isGaro, cnt+1));						
					}
				}
				// 회전할 수 있다면 회전
				if(center_x >= 1 && center_x < n-1 && center_y >= 1 && center_y < n-1 
						&& map[center_x][center_y-1] != '1' && map[center_x-1][center_y-1] != '1' && map[center_x+1][center_y-1] != '1'
						&& map[center_x][center_y+1] != '1' && map[center_x-1][center_y+1] != '1' && map[center_x+1][center_y+1] != '1') {
					if(visited[center_x][center_y][1] != 1 && visited[center_x][center_y-1][1] != 1 && visited[center_x][center_y+1][1] != 1) {
						visited[center_x][center_y][1] = 1;
						visited[center_x][center_y-1][1] = 1;
						visited[center_x][center_y+1][1] = 1;
						q.add(new Info(center_x, center_y, true, cnt+1));
					}
				}
			}
		}
	}
	
	private static int[] start() { // 시작 좌표 찾기
		int[] result = new int[2]; // 반환할 결과 배열
		for(int i=0; i<n; i++) { // n행을 모두 찾아봄
			for(int j=0; j<n; j++) { // n열을 모두 찾아봄
				if(map[i][j] == 'B') { // B가 나왔다면
					result[0] = i; // 행 저장
					result[1] = j; // 열 저장
					return result; // 반환
				}
			}
		}
		return null;
	}

	private static class Info{
		int center_x, center_y; // 중심좌표
		boolean isGaro; // 가로인지
		int cnt; // 얼마나 동작했는지
		public Info(int center_x, int center_y, boolean isGaro, int cnt) {
			super();
			this.center_x = center_x;
			this.center_y = center_y;
			this.isGaro = isGaro;
			this.cnt = cnt;
		}
	}
}