import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<int[]> st = new Stack<>();
        st.add(new int[] {prices[0], 0});
        int time = 0, index = 1;
        int[] cur;
        while(index < prices.length){
            cur = st.peek();
            while(cur[0] > prices[index]){
                st.pop();
                answer[cur[1]] = time - cur[1] + 1;
                if(st.isEmpty()) break;
                cur = st.peek();
            }
            st.add(new int[] {prices[index], index++});
            time++;
        }
        while(!st.isEmpty()){
            cur = st.pop();
            answer[cur[1]] = time - cur[1];
        }
        return answer;
    }
}