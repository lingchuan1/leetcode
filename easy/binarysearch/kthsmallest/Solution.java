package easy.binarysearch.kthsmallest;

public class Solution {
    /**
     * 乘法表第k小668(二分查找)
     *
     * @param m
     * @param n
     * @param k
     * @return
     * @data 2019/7/4
     */
    public int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n;
        while (left < right) {
            int cnt = 0;//记录小于或等于mid的数字个数
            int mid = left + (right - left) / 2;
            for (int i = 1; i <= m; i++) {
                cnt += (mid > n * i) ? n : (mid / i);
            }
            if (cnt < k)
                left = mid + 1;
            else right = mid;
        }
        return right;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findKthNumber(3, 3, 5));
    }
}
