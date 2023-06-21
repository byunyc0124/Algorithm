import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int w, h, ans;
    static char map[][];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new char[h][w];
        int startRow = -1, startCol = -1, endRow = -1, endCol = -1;
        String str;
        for (int i = 0; i < h; i++) {
            str = br.readLine();
            for (int j = 0; j < w; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'C') {
                    if (startRow == -1) {
                        startRow = i;
                        startCol = j;
                    } else {
                        endRow = i;
                        endCol = j;
                    }
                }
            }
        }
        ans = Integer.MAX_VALUE;
        move(startRow, startCol, endRow, endCol);
        System.out.println(ans);
    }

    static void move(int sx, int sy, int ex, int ey) {
        PriorityQueue<State> pq = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);
        pq.add(new State(sx, sy, -1, 0));
        int[][][] visited = new int[h][w][4];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }
        visited[sx][sy][0] = visited[sx][sy][1] = visited[sx][sy][2] = visited[sx][sy][3] = 0;
        int x, y, d, cnt;
        int nx, ny, ncnt = 0;
        while (!pq.isEmpty()) {
            State state = pq.poll();
            x = state.x;
            y = state.y;
            d = state.d;
            cnt = state.cnt;

            if (x == ex && y == ey) {
                ans = cnt;
                break;
            }

            for (int k = 0; k < 4; k++) {
                nx = x + dx[k];
                ny = y + dy[k];
                if (nx < 0 || nx >= h || ny < 0 || ny >= w || map[nx][ny] == '*') continue;

                ncnt = cnt;
                if (d != -1 && d != k) ncnt++;

                if (visited[nx][ny][k] <= ncnt) continue;
                visited[nx][ny][k] = ncnt;
                pq.add(new State(nx, ny, k, ncnt));
            }
        }
    }

    static class State {
        int x, y, d, cnt;

        public State(int x, int y, int d, int cnt) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.cnt = cnt;
        }
    }
}