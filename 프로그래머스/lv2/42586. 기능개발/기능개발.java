import java.util.*;

// (100-93)/1 = 7
// (100-30)/30 = 70/30 = 2 -> 나머지 존재 -> 3
// (100-55)/5 = 9
// 7 3 9
// 73 9
// 5 10 1 1 20 1
// 5 1011 201
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new ArrayDeque<>(); // 걸리는 시간 저장
        for(int i=0; i<progresses.length; i++){
            if((100-progresses[i])%speeds[i] == 0) q.add((100-progresses[i])/speeds[i]);
            else q.add((100-progresses[i])/speeds[i] + 1);
        }
        int cnt = 1, max_time = q.poll(), cur;
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            cur = q.poll();
            if(cur > max_time){
                sb.append(cnt+" ");
                cnt = 1;
                max_time = cur;
            }else{
                cnt++;
            }
        }
        sb.append(cnt);
        String[] strArr = sb.toString().split(" ");
        int[] answer = new int[strArr.length];
        for(int i=0; i<strArr.length; i++){
            answer[i] = Integer.parseInt(strArr[i]);
        }
        return answer;
    }
}