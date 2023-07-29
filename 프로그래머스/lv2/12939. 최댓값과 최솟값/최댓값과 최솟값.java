import java.util.*;
import java.io.*;

class Solution {
    public String solution(String s) {
        String[] nums = s.split(" ");
        StringBuilder answer = new StringBuilder();
        int min = Integer.MAX_VALUE; int max = Integer.MIN_VALUE;
        for(String num : nums){
            int n = Integer.parseInt(num);
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        answer.append(min+" "+max);
        return answer.toString();
    }
}