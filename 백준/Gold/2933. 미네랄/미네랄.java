import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

// G1. 미네랄
// 0. 미네랄 클러스터 파악
// 1. 막대기 던지기(짝수 인덱스: 왼, 홀수 인덱스: 오)
// 2. 미네랄 파괴 혹은 없음
// 3. 미네랄 클러스터 파악(분리된 클러스터 있는지 파악)
// 4. 분리된 클러스터 떨어짐
public class Main {
    static int r, c, n, height[], cluster[][], clusterCnt;
    static char map[][];
    static int[] dx = {0, 0, 1, -1}; // 상하좌우 확인
    static int[] dy = {1, -1, 0, 0}; // 왼오로 움직임

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()); // 행 크기
        c = Integer.parseInt(st.nextToken()); // 열 크기
        map = new char[r][c]; // 동굴 정보 저장
        String str;
        for(int i=0; i<r; i++){
            str = br.readLine();
            for(int j=0; j<c; j++){
                map[i][j] = str.charAt(j);
            }
        }
        n = Integer.parseInt(br.readLine()); // 막대를 던진 횟수
        height = new int[n]; // 막대를 던진 높이 저장
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            height[i] = Integer.parseInt(st.nextToken());
        }

        clusterCnt = findCluster();
        throwStick();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                sb.append(map[i][j]);
            }
            if(i != r-1) sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int findCluster() { // 미네랄 클러스터 파악
        cluster = new int[r][c];
        int result = 0;
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(map[i][j] == 'x' && cluster[i][j] == 0){
                    result++;
                    cluster[i][j] = result;
                    checkCluster(i, j, result);
                }
            }
        }
        return result;
    }

    private static void checkCluster(int sx, int sy, int result) {
        Stack<int[]> st = new Stack<>();
        st.add(new int[]{sx, sy});
        int nx, ny;
        while(!st.isEmpty()){
            int[] position = st.pop();
            for(int k=0; k<4; k++){
                nx = position[0] + dx[k];
                ny = position[1] + dy[k];
                if(nx < 0 || nx >= r || ny < 0 || ny >= c || map[nx][ny] == '.' || cluster[nx][ny] != 0) continue;
                cluster[nx][ny] = result;
                st.add(new int[]{nx, ny});
            }
        }
    }

    private static void throwStick() { // 막대기 던지기
        for(int i=0; i<n; i++){ // 각 던진 막대기 별로 미네랄 파괴
            boolean isDestroyed = destroy(i, height[i]);
            if(!isDestroyed) continue; // 파괴되지 않았다면 변경 없이 또 던지기

            // 미네랄 클러스터 파악(분리된 클러스터 있는지 파악)
            int tempCnt = findCluster();

            // 공중에 떠있는 클러스터 떨어짐
            fallCluster(tempCnt);
            // 클러스터 다시 파악(합쳐질 수 있음)
            clusterCnt = findCluster();
        }
    }

    private static void fallCluster(int cc) {
        int[] floor;
        for(int cnt=cc; cnt>=1; cnt--){ // 각 클러스터의 개수만큼 확인
            // 1. 각 클러스터의 밑면 파악
            floor = new int[c];
            Arrays.fill(floor, -1);
            for(int i=r-1; i>=0; i--){
                for(int j=c-1; j>=0; j--){
                    if(cluster[i][j] != cnt) continue;
                    if(floor[j] != -1) continue;
                    floor[j] = i;
                }
            }

            // 2. 바닥과 붙어있지 않고 다른 클러스터와도 붙어있지 않다면 떨어짐
            int moveMinCnt = Integer.MAX_VALUE; // 최대로 움직일 수 있는 횟수
            for(int j=0; j<c; j++){
                if(floor[j] == -1) continue;
                int x = floor[j];
                int temp = 0;
                while(true){
                    x++;
                    if(x >= r || cluster[x][j] != 0) break;
                    temp++;
                }
                moveMinCnt = Math.min(moveMinCnt, temp);
            }

            if(moveMinCnt == 0 || moveMinCnt == Integer.MAX_VALUE) continue; // 떨어질 필요 없으면 다음 클러스터로 넘어감
            for(int i=r-1; i>=0; i--){
                for(int j=c-1; j>=0; j--){
                    if(cluster[i][j] != cnt) continue;
                    if(floor[j] == -1) continue;
                    cluster[i+moveMinCnt][j] = cnt;
                    cluster[i][j] = 0;
                    map[i+moveMinCnt][j] = 'x';
                    map[i][j] = '.';
                }
            }
        }
    }

    private static boolean destroy(int index, int height) { // 미네랄 파괴
        int d = index % 2 == 0 ? 0 : 1; // 짝수면 왼, 홀수면 오
        int x = r - height;
        int y = d == 0 ? -1 : c;
        int ny = y;
        while(true){
            ny += dy[d];
            if(ny < 0 || ny >= c) return false;
            if(map[x][ny] == 'x'){
                map[x][ny] = '.';
                return true;
            }
        }
    }
}