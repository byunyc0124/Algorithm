import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, fish_cnt;
	static int[][] space;
	static List<Pos> fish;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int time, size, eat_cnt;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 공간의 크기
		space = new int[n][n]; // 공간
		fish = new ArrayList<>(); // 물고기 위치 저장
		fish_cnt = 0; // 물고기의 수
		time = 0; // 시간
		size = 2; // 초기 크기 = 2
		eat_cnt = 0; // 먹은 물고기의 수
		int sx = 0, sy = 0; // 처음 상어 위치 저장
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				space[i][j] = Integer.parseInt(st.nextToken());
				if(space[i][j] == 9) { // 상어 위치
					sx = i; // 초기 위치 설정
					sy = j;
					space[i][j] = 0; // 상어 위치 비워두기
				}else if(space[i][j] > 0 && space[i][j] <= 6) { // 물고기가 있으면
					fish.add(new Pos(i, j)); // 물고기 위치 저장
					fish_cnt++; // 물고기의 수 증가
				}
			}
		}
		
		Info info;
		for(int i=0; i<fish_cnt;) { // 물고기의 개수만큼 움직임
			info = bfs(sx, sy); // 상어 위치에서 탐색 시작
			
			if(info == null) { // 움직일 곳이 없다면 종료
				break;
			}
			
			time += info.dist; // 움직인 거리만큼 시간 증가
			sx = info.x; // 상어의 위치 갱신
			sy = info.y;
			eat_cnt++; // 먹은 개수 증가
			fish_cnt--; // 물고기 수 감소
			if(size == eat_cnt) { // 상어의 크기만큼 먹었다면 크기 증가
				size++;
				eat_cnt = 0; // 먹은 개수 초기화 - 다시 카운트 시작해야 함
			}
			space[sx][sy] = 0; // 현재 상어의 위치 초기화
		}
		
		System.out.println(time); // 시간 출력
	}
	
	static Info bfs(int sx, int sy) {
		int min_dist = Integer.MAX_VALUE; // 최소의 거리만큼 움직일 예정
		List<Info> dist_list = new ArrayList<>(); // 가장 가까운 -> 가장 위에 -> 가장 왼쪽에 있는 물고기로 이동하기 위한 리스트
		int[][] visited = new int[n][n]; // 방문 체크 배열
		visited[sx][sy] = 1; // 시작 위치 방문 체크
		Queue<Info> q = new ArrayDeque<>();
		q.add(new Info(sx, sy, 0));
		while(!q.isEmpty()) {
			Info info = q.poll();
			int x = info.x; // 행
			int y = info.y; // 열
			int dist = info.dist; // 거리
			for(int k=0; k<4; k++) { // 상하좌우 확인
				int nx = x + dx[k];
				int ny = y + dy[k];
				/* 범위 안에 있고 방문한 적 없으면 */
				if(nx >= 0 && nx < n && ny >= 0 && ny < n && visited[nx][ny] == 0) {
					if(space[nx][ny] <= size) { // 먹을 수 있거나 이동할 수 있다면
						visited[nx][ny] = 1; // 방문 체크
						if(space[nx][ny] > 0 && space[nx][ny] < size) { // 먹을 수 있다면
							min_dist = dist; // 거리 저장
							dist_list.add(new Info(nx, ny, dist+1));
						}
						if((dist + 1) <= min_dist) {
							q.add(new Info(nx, ny, dist+1));
						}
					}
				}
			}
		}
		
		if(dist_list.size() > 0) { // 저장된 게 있다면
			Collections.sort(dist_list, new InfoDistComparator()); // 정렬
			return dist_list.get(0); // 맨 처음만 리턴
		}else { // 저장된 게 없다면 이동할 수 있는 곳이 없다는 뜻
			return null;
		}
		
	}
	
	static class InfoDistComparator implements Comparator<Info>{
		@Override
		public int compare(Info o1, Info o2) {
			if(o1.dist == o2.dist) {
				return o1.x-o2.x; // 행: 오름차순 작 -> 큰
			}else if(o1.dist == o2.dist && o1.x == o2.x) {
				return o1.y-o2.y; // 열: 오름차순 작 -> 큰
			}
			return o1.dist - o2.dist; // 거리: 오름차순 작 -> 큰
		}
		
	}
	
	static class Info{ // 정보 저장
		int x, y, dist;
		public Info(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	
	static class Pos{ // 위치 저장
		int x, y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}