import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> mapList = new HashMap<>();
        for(int i=0; i<clothes.length; i++){
            mapList.put(clothes[i][1], mapList.getOrDefault(clothes[i][1], 0)+1);
        }
        int answer = 1;
        for(int cnt : mapList.values()){
            answer *= cnt+1;
        }
        return answer-1;
    }
}