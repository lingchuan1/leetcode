package easy.dp.game;

import java.util.HashMap;
import java.util.HashSet;

public class Solution {
    /**
     * 猜数字大小II 375
     * @data 2019/11/8
     * @param n
     * @return
     */
    public int getMoneyAmount(int n) {
        //使用数组存放结果，dp[i][j]表示从i数字到j数字之间猜中一个数字最少需要花费
        int[][] dp = new int[n + 1][n + 1];
        for(int i = 2;i < n;i ++){
            for(int j = i - 1;j > 0;j --){
                int min = Integer.MAX_VALUE;
                for(int k = j + 1;k < i;k ++){
                    int max = k + Math.max(dp[j][k - 1],dp[k + 1][i]);
                    min = Math.min(min,max);
                }
                dp[j][i] = i - j == 1 ? j : min;
            }
        }
        return dp[1][n];
    }

    /**
     * 我能赢吗 464
     * @data 2019/11/8
     * @param maxChoosableInteger
     * @param desiredTotal
     * @return
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(maxChoosableInteger >= desiredTotal)
            return true;
        if(maxChoosableInteger * (maxChoosableInteger + 1)/2 < desiredTotal)
            return false;
        HashMap<Integer,Boolean> map = new HashMap<>();
        return canWin(maxChoosableInteger,desiredTotal,0,map);
    }
    private boolean canWin(int length,int total,int used,HashMap<Integer,Boolean> map){
        if(map.containsKey(used))
            return map.get(used);
        for(int i = 0;i < length;i ++){
            //用一个整型数按位来记录某个数字是否被使用过
            int cur = (1 << i);
            //相与为0说明该数字没有被使用过
            if((cur & used) == 0){
                if(total <= i + 1 || !canWin(length,total - (i + 1),cur|used,map)){
                    map.put(used,true);
                    return true;
                }
            }
        }
        map.put(used,false);
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getMoneyAmount(10));
    }
}
