package easy.dp;


import static java.lang.Math.max;

public class Solution {
    /**
     * 最大子序和53
     *找到一个具有最大和的连续子数组，返回其最大和
     * @param nums
     * @return
     * @data 2019/3/25
     */
    public int MaxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE, curSum = 0;
        for (int num : nums) {
            curSum = max(curSum + num, num);
            res = max(res, curSum);
        }
        return res;
    }

    /**
     * 爬楼梯70
     *爬楼梯一次可以爬一层或两层，n层楼梯由几种不同的爬法？
     * @param n
     * @return
     * @data 2019/4/14
     */
    public int climbStairs(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n + 1; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }

    /**
     * 爬楼梯的最小损失746
     * 动态规划
     *
     * @param cost
     * @return
     * @data 2019/3/25
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        for (int i = 2; i < n + 1; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }
    //可以只保留最新的两个结果
    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        int p0 = 0;
        int p1 = 0;
        int tem = 0;
        for(int i = 2;i <= n;i ++){
            tem = Math.min(p0 + cost[i - 2],p1 + cost[i - 1]);
            p0 = p1;
            p1 = tem;
        }
        return p1;
    }

    /**
     * 买卖股票的最佳时机121
     *买入和卖出一次，求最大利益。买入后才能卖出
     * @param prices
     * @return
     * @data 2019/3/27
     */
    public int maxProfit(int[] prices) {
        int buy = Integer.MAX_VALUE, res = 0;
        for (int price : prices) {
            buy = Math.min(buy, price);
            res = max(res, price - buy);
        }
        return res;
    }

    /**
     * 买卖股票的最佳时机二122
     * 可以多次买卖同一支股票，但在再次买入之前必须先卖出
     * @data 2019/4/20
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int res = 0;
        for(int i = 0;i < n -1;i++){
            if(prices[i] < prices[i + 1])
                res += (prices[i + 1] - prices[i]);
        }
        return res;
    }

    /**
     * 买卖股票的最佳时期（含冻结期）309
     * @param prices
     * @return
     */
    public int maxProfit5(int[] prices) {
        int buy = Integer.MIN_VALUE, pre_buy = 0, sell = 0, pre_sell = 0;
        for (int price : prices) {
            pre_buy = buy;
            buy = max(pre_sell - price, pre_buy);
            pre_sell = sell;
            sell = max(pre_buy + price, pre_sell);
        }
        return sell;
    }

    /**
     * 整数拆分343
     * 将一个正整数拆分为多个整数的和，并使这些整数乘积最大，返回最大乘积
     * @data 2019/4/14
     * @param n
     * @return
    **/
    int[] dp;
    public int integerBreak(int n) {
        dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 1;
        return integer(n);
        }
        private int integer(int n){
        if(dp[n] != 0)
            return dp[n];
            for(int i = 3;i < n+1;i++)
                for(int j = 1;j < i-1;j++)
                    dp[i] = max(dp[i],max(j*(i - j),j*integer(i - j)));
            return dp[n];
        }

    /**
     * 偷窃一198
     * @data 2019/4/15
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if(nums.length <= 1)
            return nums.length == 0?0:nums[0];
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = max(nums[0],nums[1]);
        for(int i = 2;i < n;i++)
            dp[i] = max(dp[i - 1],nums[i] + dp[i - 2]);
        return dp[n - 1];
    }

    /**
     * 偷窃二213
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        if(nums.length <= 1)
            return nums.length == 0?0:nums[0];
        int n = nums.length;
        return max(rob2(nums,0,n-1),rob2(nums,1,n));
    }

    private int rob2(int[] nums,int left,int right){
        if(right - left <= 0)
            return 0;
        if(right - left == 1)
            return nums[left];
        int n = right - left;
        int[] dp = new int[n];
        dp[0] = nums[left];
        dp[1] = max(nums[left],nums[left + 1]);
        for(int i = 2;i < right - left;i ++)
            dp[i] = max(dp[i-1],dp[i-2] + nums[i+left]);
        return dp[n - 1];
    }

    /**
     * 删除与获得点数740
     * @param nums
     * @return
     */
    public int deleteAndEarn(int[] nums) {
        int[] sum = new int[10001];
        for(int num:nums)
            sum[num] +=num;
        for(int i = 2;i < 10001;i ++)
            sum[i] = Math.max(sum[i] + sum[i -2],sum[i - 1]);
        return sum[10000];
    }
}
