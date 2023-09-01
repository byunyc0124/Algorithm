import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        Arrays.sort(arr);
        List<Integer> answerTemp = new ArrayList<>();
        for(int i=0; i<arr.length; i++){
            if(arr[i] % divisor == 0){
                answerTemp.add(arr[i]);
            }
        }
        if(answerTemp.size() == 0) return new int[] {-1};
        int[] answer = new int[answerTemp.size()];
        for(int i=0; i<answerTemp.size(); i++){
            answer[i] = answerTemp.get(i);
        }
        return answer;
    }
}