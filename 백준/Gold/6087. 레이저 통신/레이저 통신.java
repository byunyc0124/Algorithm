import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// G3. 레이저 통신
/**
 * 레이저 방향이 오른쪽이었다면 거울 설치 혹은 미설치 시 갈 수 있는 곳은 위, 아래, 오른쪽
 * 상(0) -> 좌우(2, 3) 상
 * 하(1) -> 좌우(2, 3) 하
 * 좌(2) -> 상하(0, 1) 좌
 * 우(3) -> 상하(0, 1) 우
 */
public class Main {

    static int w, h, ans;
    static char map[][];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken()); // 가로 길이
        h = Integer.parseInt(st.nextToken()); // 세로 길이
        map = new char[h][w]; // 지도 정보 저장
        int startRow = -1, startCol = -1, endRow = -1, endCol = -1;
        String str = " ";
        for(int i=0; i<h; i++){
            str = br.readLine();
            for(int j=0; j<w; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'C') {
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

    static void move(int sx, int sy, int ex, int ey){
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[3] - o2[3]);
        pq.add(new int[]{sx, sy, -1, 0});
        int[][][] mirror = new int[h][w][4];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                Arrays.fill(mirror[i][j], Integer.MAX_VALUE);
            }
        }
        mirror[sx][sy][0] = 0;
        mirror[sx][sy][1] = 0;
        mirror[sx][sy][2] = 0;
        mirror[sx][sy][3] = 0;
        int x, y, d, cnt;
        int nx, ny, ncnt = 0; // 다음위치와 방향
        while (!pq.isEmpty()){
            int[] info = pq.poll();
            x = info[0];
            y = info[1];
            d = info[2];
            cnt = info[3];

            if(x == ex && y == ey) {
                ans = cnt;
                break;
            }

            for(int k=0; k<4; k++){
                nx = x + dx[k];
                ny = y + dy[k];
                if(nx < 0 || nx >= h || ny < 0 || ny >= w || map[nx][ny] == '*') continue;

                ncnt = cnt;
                if(d != -1 && d != k) ncnt++;

                if(mirror[nx][ny][k] <= ncnt) continue;
                mirror[nx][ny][k] = ncnt;
                pq.add(new int[]{nx, ny, k, ncnt});
            }
        }
    }
}