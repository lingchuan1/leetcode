package easy.binarysearch.kthsmallest;

import java.util.PriorityQueue;

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

    /**
     * 有序矩阵第k小378
     *
     * @param matrix
     * @param k
     * @return
     * @data 2019/7/8
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        int left = matrix[0][0], right = matrix[n - 1][m - 1];
        while (left < right) {
            int cnt = 0;
            int j = m - 1;
            int mid = left + (right - left) / 2;
            for (int i = 0; i < n; i++) {
                if (matrix[i][m - 1] <= mid)
                    cnt += m;
                else {
                    while (j >= 0 && matrix[i][j] > mid)
                        j--;
                    cnt += j + 1;
                }
            }
            if (cnt < k)
                left = mid + 1;
            else right = mid;
        }
        return right;
    }

    /**
     * 吃香蕉875
     *
     * @param piles
     * @param H
     * @return
     */
    public int minEatingSpeed(int[] piles, int H) {
        int left = 1, right = 100;
        int mid = 0, cnt;
        while (left < right) {
            mid = left + (right - left) / 2;
            cnt = 0;
            for (int pile : piles)
                cnt += (pile - 1) / mid + 1;
            if (cnt > H)
                left = mid + 1;
            else right = mid;
        }
        return left;
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2}, {3, 3}};
        int res = new Solution().kthSmallest(arr, 2);
        System.out.println(res);
    }

    /**
     * 第k个最小的分数786
     *
     * @param A
     * @param K
     * @return
     * @data 2019/7/7
     */
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        double left = 0, right = 1;
        int p = 0, q = 1;
        int cnt = 0;
        int n = A.length;
        while (true) {
            cnt = 0;
            p = 0;
            double mid = left + (right - left) * 0.5;
            for (int i = 0, j = i + 1; i < n; i++) {
                while (j < n && A[i] > mid * A[j])
                    j++;
                cnt += n - j;
                if (j < n && p * A[j] < q * A[i]) {//判断是否是种子结果
                    p = A[i];
                    q = A[j];
                }
            }
            if (cnt == K)//小于mid的个数刚好等于K,返回结果
                return new int[]{p, q};
            else if (cnt < K)
                left = mid;
            else right = mid;
        }
    }
}
