import java.util.*;
import java.io.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        for(int i=1; i<=n; i++){
            if(plus(i, n)){
                answer++;
            }
        }
        return answer;
    }
    
    public boolean plus(int start, int number){
        int sum = 0;
        for(int i=start; i<=number; i++){
            sum += i;
            if(sum == number) return true;
            else if(sum > number) return false;
        }
        return false;
    }
}