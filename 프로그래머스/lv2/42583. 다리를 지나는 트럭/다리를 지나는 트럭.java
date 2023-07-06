import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<int[]> q = new ArrayDeque<>(); // int[]: 트럭무게, 들어간 시간, 도착할 시간
        int time = 1, cur_w = 0, index = 1; // 현재 시간, 현재 다리에 올라간 무게
        int[] cur;
        q.add(new int[] {truck_weights[0], time, bridge_length+time});
        cur_w += truck_weights[0];
        time++;
        while(index < truck_weights.length){
            if(q.isEmpty()){
                q.add(new int[] {truck_weights[index], time, bridge_length+time});
                cur_w += truck_weights[index++];
                time++;
                continue;
            }
            cur = q.peek();
            if(time >= cur[2]) {
                q.poll();
                cur_w -= cur[0];
            }
            if(cur_w + truck_weights[index] > weight){
                time++;
                continue;
            }
            q.add(new int[] {truck_weights[index], time, bridge_length+time});
            cur_w += truck_weights[index++];
            time++;
        }
        while(!q.isEmpty()){
            cur = q.poll();
            time = cur[2];
        }
        return time;
    }
}