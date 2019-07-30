package easy.binarysearch.matrix;

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0)
            return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0,right = m * n - 1;
        int x,y;
        while(left <= right){
            int mid = left + (right - left)/2;
            y = mid % n;
            x = mid / n;
            if(matrix[x][y] == target)
                return true;
            else if(matrix[x][y] < target)
                left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{0,1}};
        System.out.println(new Solution().searchMatrix(matrix,1));
    }
}
