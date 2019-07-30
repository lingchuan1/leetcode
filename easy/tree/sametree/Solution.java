package easy.tree.sametree;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 对称二叉树101
     *
     * @param root
     * @return
     * @data 2019/7/10
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left != null && right != null) {
            if (left.val != right.val)
                return false;
            return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
        }
        return false;
    }

    /**
     * 是否平衡110
     *
     * @param root
     * @return
     * @data 2019/7/10
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null)
            return true;
        int leftHeight = treeHeight(root.left);
        int rightHeight = treeHeight(root.right);
        if (Math.abs(leftHeight - rightHeight) > 1)
            return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }
    public int treeHeight(TreeNode root){
        if(root == null)
            return 0;
        return Math.max(treeHeight(root.left),treeHeight(root.right)) + 1;
    }

    /**
     * 一棵树是否是另一棵树的子树
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null)
            return false;
        if(isSame(s,t))
            return true;
        return isSubtree(s.left,t) || isSubtree(s.right,t);
    }
    private boolean isSame(TreeNode x,TreeNode y){
        if(x == null && y == null)
            return true;
        if(x == null || y == null)
            return false;
        if(x.val != y.val)
            return false;
        return isSame(x.left,y.left) && isSame(x.right,y.right);
    }

    /**
     * 叶子相似的树872
     * @param root1
     * @param root2
     * @data 2019/7/12
     * @return
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaf1 = new ArrayList<>();
        List<Integer> leaf2 = new ArrayList<>();
        helper(root1,leaf1);
        helper(root2,leaf2);
        if(leaf1.size() != leaf2.size())//找出所有的叶子结然后比较
            return false;
        for(int i = 0;i < leaf1.size();i ++){
            if(leaf1.get(i) != leaf2.get(i))
                return false;
        }
        return true;
    }

    private void helper(TreeNode root, List<Integer> leaf){
        if(root == null)
            return;
        if(root.left == null && root.right == null)
            leaf.add(root.val);
        helper(root.left,leaf);
        helper(root.right,leaf);
    }
}
