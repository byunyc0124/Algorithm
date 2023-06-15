import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int r, c, t;
	static int cleaner_x1, cleaner_x2; // 클리너 위치 저장 - 행만 저장, 열은 항상 0
	static int[][] room;
	static List<Pos> dust;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()); // 행 크기
		c = Integer.parseInt(st.nextToken()); // 열 크기
		t = Integer.parseInt(st.nextToken()); // 시간
		room = new int[r][c]; // 방 정보
		cleaner_x1 = -1;
		for(int i=0; i<r; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<c; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if(room[i][j] == -1 && cleaner_x1 == -1) { // 첫번째 위치가 아직 갱신이 안됐으면 처음 들어온 것이므로 저장해주기
					cleaner_x1 = i;
					cleaner_x2 = i+1;
				}
			}
		}
		
		for(int time=0; time<t; time++) { // t초의 시간만큼 진행
			dust = new ArrayList<>();
			for(int i=0; i<r; i++) {
				for(int j=0; j<c; j++) {
					if(room[i][j] > 0) dust.add(new Pos(i, j));
				}
			}
			diffusion(); // 미세먼지 확산
			purity(); // 청정
		}
		
		int ans = 0;
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(room[i][j] != -1) ans += room[i][j];
			}
		}
		System.out.println(ans);
	}

	static int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌
	static int[] dy = {0, 1, 0, -1};
	private static void purity() {
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		// 공청기 위
		int si = cleaner_x1 - 1;
		int sj = 0;
		int d = 0;
		q.add(0); // 맨 처음 넣는 것은 청정기로 빨려들어가므로 0으로
		while(true) { // 상우하좌로 이동하면서 큐에 넣기: 0123
			if(si == cleaner_x1 && sj == 1) break;
			si += dx[d];
			sj += dy[d];
			if(si < 0 || si > cleaner_x1 || sj < 0 || sj >= c) { // 범위 벗어났으면
				si -= dx[d];
				sj -= dy[d];
				d++;
			}else {
				q.add(room[si][sj]);
			}
		}
		q.add(q.poll());
		si = cleaner_x1 - 1;
		sj = 0;
		d = 0;
		room[si][sj] = q.poll();
		while(!q.isEmpty()) {
			si += dx[d];
			sj += dy[d];
			if(si < 0 || si > cleaner_x1 || sj < 0 || sj >= c) { // 범위 벗어났으면
				si -= dx[d];
				sj -= dy[d];
				d++;
			}else {
				room[si][sj] = q.poll();
			}
		}

		// 공청기 아래
		si = cleaner_x2 + 1;
		sj = 0;
		d = 2;
		q.add(0); // 맨 처음 넣는 것은 청정기로 빨려들어가므로 0으로
		while(true) { // 하우상좌로 이동하면서 큐에 넣기: 2103
			if(si == cleaner_x2 && sj == 1) break;
			si += dx[d];
			sj += dy[d];
			if(si < cleaner_x2 || si >= r || sj < 0 || sj >= c) { // 범위 벗어났으면
				si -= dx[d];
				sj -= dy[d];
				d--;
				if(d == -1) d=3;
			}else {
				q.add(room[si][sj]);
			}
		}
		q.add(q.poll());
		si = cleaner_x2 + 1;
		sj = 0;
		d = 2;
		room[si][sj] = q.poll();
		while(!q.isEmpty()) { // 우하좌상으로 이동하면서 배열에 넣기: 1230
			si += dx[d];
			sj += dy[d];
			if(si < cleaner_x2 || si >= r || sj < 0 || sj >= c) { // 범위 벗어났으면
				si -= dx[d];
				sj -= dy[d];
				d--;
				if(d == -1) d=3;
			}else {
				room[si][sj] = q.poll();
			}
		}
	}

	private static void diffusion() {
		// 변경될 미세먼지 저장할 임시 배열
		int[][] temp = new int[r][c];
		for(int i=0; i<dust.size(); i++) {
			Pos pos = dust.get(i);
			int x = pos.x;
			int y = pos.y;
			int cnt = 0; // 확산할 방향
			for(int d=0; d<4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx >= 0 && nx < r && ny >= 0 && ny < c && room[nx][ny] != -1) {
					cnt++;
					temp[nx][ny] += room[x][y] / 5;
				}
			}
			temp[x][y] += room[x][y] - ((room[x][y] / 5) * cnt);
		}
		
		// 원래 배열로 다시 옮기기
		for(int i=0; i<r; i++) {
			room[i] = temp[i].clone();
		}
		room[cleaner_x1][0] = -1; // 청정기 위치 저장
		room[cleaner_x2][0] = -1;
	}
	
	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}