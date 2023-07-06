import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int index = 0;
        for(int c=0; c<commands.length; c++){
            int[] sub = new int[commands[c][1]-commands[c][0]+1];
            int sub_index = 0;
            for(int i=commands[c][0]-1; i<commands[c][1]; i++){
                sub[sub_index++] = array[i];
            }
            Arrays.sort(sub);
            answer[index++] = sub[commands[c][2]-1];
        }
        return answer;
    }
}