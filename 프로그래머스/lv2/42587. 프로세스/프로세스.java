import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<int[]> q = new ArrayDeque<>();
        int[] priArr = new int[10]; // 1이상 9이하
        for(int i=0; i<priorities.length; i++){
            q.add(new int[] {priorities[i], i==location ? 1 : 0}); // 찾아야하는건 1
            priArr[priorities[i]]++; // 우선순위 개수 파악용
        }
        int answer = 0, max_pri = findMax(priArr), cnt = 1;
        int[] cur;
        while(!q.isEmpty()){
            cur = q.poll();
            if(cur[0] == max_pri){ // 실행해야하면
                if(cur[1] == 1) return cnt; // 찾는거였다면 바로 return
                priArr[cur[0]]--; // 찾는게 아니였다면 빼기만 함
                max_pri = findMax(priArr);
                cnt++;
            }
            else q.add(cur); // 아직 실행할 수 없다면 큐에 다시 추가
        }
        return answer;
    }
    
    int findMax(int[] priArr){
        for(int i=9; i>=0; i--){
            if(priArr[i] > 0) return i;
        }
        return 0;
    }
}