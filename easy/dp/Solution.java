package easy.dp;

public class Solution {
    /**
     * 最大子数组之和121
     * @data 2019/3/25
     * @param nums
     * @return
     */
    public int MaxSubArray(int[] nums){
        int res = Integer.MIN_VALUE,curSum = 0;
        for(int num:nums){
            curSum = Math.max(curSum + num,num);
            res = Math.max(res,curSum);
        }
        return res;
    }

    /**
     * 爬楼梯的最小损失746
     * 动态规划
     * @data 2019/3/25
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n+1];
        for(int i = 2;i < n+1;i++){
            dp[i] = Math.min(dp[i-1] + cost[i-1],dp[i-2] + cost[i-2]);
        }
        return dp[n];
    }

    /**
     * 买卖股票的最佳时机
     * @data 2019/3/27
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int buy = Integer.MAX_VALUE,res = 0;
        for(int price:prices){
            buy = Math.min(buy,price);
            res = Math.max(res,price-buy);
        }
        return res;
    }
}
