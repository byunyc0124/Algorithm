import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> mapList = new HashMap<>();
        for(int i=0; i<n; i++){
            mapList.put(nums[i], mapList.getOrDefault(nums[i], 0)+1);
        }
        int answer = 0;
        for(int i=0; i<mapList.size(); i++){
            if(answer == n/2) break;
            answer++;
        }
        return answer;
    }
}