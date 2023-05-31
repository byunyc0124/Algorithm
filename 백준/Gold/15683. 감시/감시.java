import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int ans, n, m;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static List<Info> cctv;
    // cctv 종류에 따라 바라보는 방향
    static int[][][] tp = {{},
                            {{0}, {1}, {2}, {3}}, // 1번
                            {{0, 2}, {1, 3}}, // 2번
                            {{0, 1}, {1, 2}, {2, 3}, {0, 3}}, // 3번
            {{0, 1, 2}, {0, 1, 3}, {1, 2, 3}, {0, 2, 3}}, // 4번
            {{0, 1, 2, 3}}}; // 5번

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ans = Integer.MAX_VALUE;
        n = Integer.parseInt(st.nextToken()); // 세로크기
        m = Integer.parseInt(st.nextToken()); // 가로크기
        int[][] brd = new int[n][m];
        cctv = new ArrayList<>();
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                brd[i][j] = Integer.parseInt(st.nextToken());
                if(Arrays.asList(1,2,3,4,5).contains(brd[i][j])){
                    cctv.add(new Info(i, j, brd[i][j]));
                }
            }
        }

        dfs(0, brd);
        System.out.println(ans);
    }

    private static void dfs(int cnt, int[][] brd) {
        if(cnt == cctv.size()){ // cctv의 개수만큼 돌면서 볼 수 있는 곳을 채우면
            int blind_cnt = 0;
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(brd[i][j] == 0) blind_cnt++; // 볼 수 없었던 곳을 저장
                }
            }
            ans = Math.min(ans, blind_cnt); // 최소값으로 저장
            return;
        }

        int x = cctv.get(cnt).x;
        int y = cctv.get(cnt).y;
        int type = cctv.get(cnt).type;
        for(int[] t : tp[type]){ // cctv 종류에 해당하는 방향으로 전부 바라봄
            int[][] temp = new int[n][m];
            for(int i=0; i<n; i++){
                temp[i] = brd[i].clone();
            }
            for(int i : t){ // cctv 종류별로 회전하면서 볼 수 있는 곳 확인
                int nx = x;
                int ny = y;
                while(true){ // 한 행 혹은 한 열을 모두 볼 수 있으므로 무한 루프로
                    nx += dx[i];
                    ny += dy[i];
                    /* 벽이 아니거나 범위 안이면 */
                    if(nx >= 0 && nx < n && ny >= 0 && ny < m && temp[nx][ny] != 6){
                        if(temp[nx][ny] == 0){ // 볼 수 있는 곳이면 #으로 채움
                            temp[nx][ny] = '#';
                        }
                    }else{ // 벽이 나오거나 범위 밖이면 종료
                        break;
                    }
                }
            }
            dfs(cnt+1, temp);
        }
    }


    static class Info{
        int x, y; // cctv 위치
        int type; // cctv 종류
        public Info(int x, int y, int type){
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}