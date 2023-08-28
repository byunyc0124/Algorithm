import java.util.*;
class Solution {
    public long solution(int a, int b) {
        if(a == b) return a;
        else if(a > b) return sum(b, a);
        else return sum(a, b);
    }
    
    public long sum(int start, int end){
        long result = 0;
        for(int i=start; i<=end; i++){
            result += i;
        }
        return result;
    }
}