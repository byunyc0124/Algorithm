import java.util.*;
import java.io.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> mapList = new HashMap<>();
        for(int i=0; i<participant.length; i++){
            if(mapList.containsKey(participant[i])) mapList.put(participant[i], mapList.get(participant[i])+1);
            else mapList.put(participant[i], 1);
        }
        
        for(int i=0; i<completion.length; i++){
            if(mapList.containsKey(completion[i])) {
                if(mapList.get(completion[i]) == 1) mapList.remove(completion[i]);
                else mapList.put(completion[i], mapList.get(completion[i])-1);
            }
        }
        
        String answer = "";
        for(String k : mapList.keySet()){
            answer = k;
        }
        return answer;
    }
}