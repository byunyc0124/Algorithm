import java.util.*;

class Solution {
    public int solution(int num) {
        if(num == 1) return 0;
        int answer = 0;
        long n = num;
        while(answer < 500){
            if(n == 1) break;
            if(n % 2 == 0) n = n / 2;
            else n = n * 3 + 1;
            answer++;
        }
        return answer < 500 ? answer : -1;
    }
}