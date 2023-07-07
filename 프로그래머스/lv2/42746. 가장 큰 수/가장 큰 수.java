import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String[] strArr = new String[numbers.length];
        for(int i=0; i<numbers.length; i++){
            strArr[i] = Integer.toString(numbers[i]);
        }
        Arrays.sort(strArr, (o1, o2) -> (o2+o1).compareTo(o1+o2));
        if(strArr[0].equals("0")) return "0";
        StringBuilder answer = new StringBuilder();
        for(int i=0; i<numbers.length; i++){
            answer.append(strArr[i]);
        }
        return answer.toString();
    }
}