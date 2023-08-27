import java.util.*;
class Solution {
    public long solution(long n) {
        if(Math.sqrt(n) % 1 == 0.0){ // 제곱근의 값이 정수면
            return (long)Math.pow(Math.sqrt(n) + 1, 2);
        }else{
            return -1;
        }
    }
}