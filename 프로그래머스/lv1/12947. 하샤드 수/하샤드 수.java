import java.util.*;
import java.io.*;

class Solution {
    public boolean solution(int x) {
        int sum = 0;
        int sp = 10;
        while(x >= sp){
            sum += x % sp / (sp / 10);
            sp *= 10;
        }
        sum += x % sp / (sp / 10);
        return true ? x % sum == 0 : false;
    }
}