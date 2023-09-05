import java.util.*;
import java.io.*;
class Solution {
    public int solution(int[] numbers) {
        boolean[] visited = new boolean[10];
        for(int i=0; i<numbers.length; i++){
            visited[numbers[i]] = true;
        }
        int answer = 0;
        for(int i=0; i<visited.length; i++){
            if(!visited[i]) answer += i;
        }
        return answer;
    }
}