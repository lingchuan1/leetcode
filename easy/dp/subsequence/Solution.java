package easy.dp.subsequence;

public class Solution {

    /**
     * 最长递增子序列的个数673
     *
     * @param nums
     * @return
     * @data 2019/6/29
     */
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int max = 1;
        if (n == 0)
            return 0;
        //dp数组存储当前最长字符串长度，cnt数组存储最长字符串个数，初始化值都为1
        int[] dp = new int[n];
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++)
            dp[i] = 1;
        for (int i = 0; i < n; i++)
            cnt[i] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] <= nums[j])//如果nums[i] <= nums[j]时不作处理
                    continue;
                if (dp[i] == dp[j] + 1)//说明有新的最长字符串，把两者的个数相加
                    cnt[i] +=cnt[j];
                else if (dp[i] < dp[j] + 1) {//有更长的字符串
                    dp[i] = dp[j] + 1;
                    cnt[i] = cnt[j];
                }
            }
             max = Math.max(max,dp[i]);//每次循环用max保存最长字符串的长度
        }
        int res = 0;
        for(int i = 0;i < nums.length;i ++ ){//遍历将长度为最长字符串长度的个数相加可得结果
            if(dp[i] == max)
                res += cnt[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2,1};
        System.out.println(new Solution().findNumberOfLIS(nums));
    }
}
