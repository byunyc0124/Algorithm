import java.util.*;
import java.io.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int minIndex = 0;
        Arrays.sort(people);
        for(int i=people.length-1; i>=minIndex; i--){
            if(people[i] + people[minIndex] > limit){ // 둘 합쳐서 제한을 넘으면
                // 젤 뒤에 한명만 따로 보냄
                answer++;
            }else{ // 같이 보낼 수 있으면
                answer++;
                minIndex++;
            }
        }
        return answer;
    }
}