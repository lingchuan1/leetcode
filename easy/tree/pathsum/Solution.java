package easy.tree.pathsum;

import sun.text.resources.cldr.hy.FormatData_hy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 路径总和I  112
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null && root.val == sum)
            return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /**
     * 路径总和II  113
     *
     * @param root
     * @param sum
     * @return
     */
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<Integer> out = new ArrayList<>();
        helper1(root, sum, out);
        return res;
    }

    private void helper1(TreeNode root, int sum, List<Integer> out) {
        if (root == null)
            return;
        out.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            res.add(new ArrayList<>(out));
            return;
        }
        helper1(root.left, sum - root.val, out);
        helper1(root.right, sum - root.val, out);
        out.remove(out.size() - 1);
    }

    /**
     * 路径总和III 437（头节点可以不是根，尾节点可以不是叶子）
     *
     * @param root
     * @param sum
     * @return
     */
    /*错误？
    public int pathSumIII(TreeNode root,int sum){
        List<Integer> out = new LinkedList<>();
        return helper(root,sum,out);
    }

    private int helper(TreeNode root,int sum,List<Integer> out){
        int res = 0;
        if(root == null)
            return 0;
        out.add(root.val);
        if(root.left == null && root.right == null && root.val == sum)
            res ++;
        int t = res;
        for(int i = 0;i < out.size() ;i ++){
            t = t - out.get(i);
            if(t == sum)
                res ++;
        }
        res = helper(root.left,sum - root.val,out) + helper(root.right,sum - root.val,out) + res;
        out.remove(out.size() - 1);
        return res;
    }
*/
    //前n项和 - sum = 前m项和 说明 前n项和 - 前m项和 = sum
    public int pathSumIII(TreeNode root, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return helper(root, sum, 0, map);
    }

    private int helper(TreeNode root, int sum, int curSum, HashMap<Integer, Integer> map) {
        int res = 0;
        if (root == null)
            return 0;
        curSum += root.val;
        res += map.getOrDefault(curSum - sum, 0);
        map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        res = helper(root.left, sum, curSum, map) + helper(root.right, sum, curSum, map) + res;
        map.put(curSum, map.get(curSum) - 1);//回溯
        return res;
    }

    /**
     * 二叉树的直径543
     *
     * @param root
     * @return
     * @data 2019/7/14
     */
    //很暴力，转换，直径是左右子树最大深度之和，两个递归求
    /*
    private HashMap<TreeNode,Integer> map = new HashMap<>();
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null)
            return 0;
        int res = getHeight(root.left) + getHeight(root.right);
        return Math.max(res,Math.max(diameterOfBinaryTree(root.left),diameterOfBinaryTree(root.right)));
    }

    private int getHeight(TreeNode node){
        if(node == null)
            return 0;
        if(map.containsKey(node))
            return map.get(node);
        int h = 1 + Math.max(getHeight(node.left),getHeight(node.right));
        map.put(node,h);
        return h;
    }
    */

    //用一个递归
    private int res1 = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        maxDepth(root);
        return res1;
    }

    private int maxDepth(TreeNode node) {
        if (node == null)
            return 0;
        int left = maxDepth(node.left);
        int right = maxDepth(node.right);
        res1 = Math.max(res1, left + right);
        return 1 + Math.max(left, right);
    }

    /**
     * 最长同值路径687
     *
     * @param root
     * @return
     * @data 2019/7/14
     */
    /*
    private int res2;
    public int longestUnivaluePath(TreeNode root) {
        if(root == null)
            return 0;
        int res = maxDepth(root.left,root.val) + maxDepth(root.right,root.val);
        return Math.max(res,Math.max(longestUnivaluePath(root.left),longestUnivaluePath(root.right)));
    }
    private int maxDepth(TreeNode node,int val){
        if(node == null || node.val != val)
            return 0;
        int left = maxDepth(node.left,val);
        int right = maxDepth(node.right,val);
        res2 = Math.max(res2,left + right);
        return Math.max(left,right) + 1;
    }
    */

    private int res2;
    public int longestUnivaluePath(TreeNode root){
        if(root == null)
            return 0;
        maxDepth2(root);
        return res2;
    }
    private int maxDepth2(TreeNode node){
        if(node == null)
            return 0;
        int left = maxDepth2(node.left);
        int right = maxDepth2(node.right);
        left = (node.left != null && node.val == node.left.val) ? left + 1 : 0;
        right = (node.right != null && node.val == node.right.val) ? right + 1 : 0;
        res2 = Math.max(res2,left + right);
        return Math.max(left,right);
    }
}
