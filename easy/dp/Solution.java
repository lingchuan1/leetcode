package easy.dp;

public class Solution {
    /**
     * 最大子数组之和121
     *
     * @param nums
     * @return
     * @data 2019/3/25
     */
    public int MaxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE, curSum = 0;
        for (int num : nums) {
            curSum = Math.max(curSum + num, num);
            res = Math.max(res, curSum);
        }
        return res;
    }

    /**
     * 爬楼梯70
     *
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

    /**
     * 买卖股票的最佳时机121
     *
     * @param prices
     * @return
     * @data 2019/3/27
     */
    public int maxProfit(int[] prices) {
        int buy = Integer.MAX_VALUE, res = 0;
        for (int price : prices) {
            buy = Math.min(buy, price);
            res = Math.max(res, price - buy);
        }
        return res;
    }

    /**
     * 整数拆分343
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
                    dp[i] = Math.max(dp[i],Math.max(j*(i - j),j*integer(i - j)));
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
        dp[1] = Math.max(nums[0],nums[1]);
        for(int i = 2;i < n;i++)
            dp[i] = Math.max(dp[i - 1],nums[i] + dp[i - 2]);
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
        return Math.max(rob2(nums,0,n-1),rob2(nums,1,n));
    }

    private int rob2(int[] nums,int left,int right){
        if(right - left <= 0)
            return 0;
        if(right - left == 1)
            return nums[left];
        int n = right - left;
        int[] dp = new int[n];
        dp[0] = nums[left];
        dp[1] = Math.max(nums[left],nums[left + 1]);
        for(int i = 2;i < right - left;i ++)
            dp[i] = Math.max(dp[i-1],dp[i-2] + nums[i+left]);
        return dp[n - 1];
    }
}
