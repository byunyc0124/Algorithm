import java.util.*;
import java.io.*;

class Solution {
    public boolean solution(String[] phone_book) {
        HashMap<String, Integer> mapList = new HashMap<>();
        for(int i=0; i<phone_book.length; i++){
            mapList.put(phone_book[i], 0);
        }
        for(int i=0; i<phone_book.length; i++){
            for(int j=1; j<phone_book[i].length(); j++){
                if(mapList.containsKey(phone_book[i].substring(0, j))) return false;
            }
        }
        return true;
    }
}