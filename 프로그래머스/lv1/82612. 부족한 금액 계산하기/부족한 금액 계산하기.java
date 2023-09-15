class Solution {
    public long solution(int price, int money, int count) {
        long use = 0;
        for(int i=1; i<=count; i++){
            use += price * i;
        }
        if(use > money) return use-money;
        else return 0;
    }
}