package easy.tree.inordertraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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
     * 前序遍历树
     *
     * @param root
     * @return
     * @data 2019/7/9
     */
    private List<Integer> res = new ArrayList<>();
    /*递归
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root != null) {
            res.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
        return res;
    }
    */

    //使用栈
    public List<Integer> preorderTraversal1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if (root == null)
            return res;
        stack.push(root);
        while (!stack.empty()) {
            TreeNode t = stack.pop();
            res.add(t.val);
            if (t.right != null)
                stack.push(t.right);
            if (t.left != null)
                stack.push(t.left);
        }
        return res;
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        if (root == null)
            return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (!stack.empty() || p != null) {
            if (p != null) {
                res.add(p.val);
                stack.push(p);
                p = p.left;
            } else {
                TreeNode t = stack.pop();
                p = t.right;
            }
        }
        return res;
    }

    /**
     * 二叉树的后序遍历
     *
     * @param root
     * @return
     * @data 2019/7/10
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res2 = new LinkedList<>();
        if (root == null)
            return res2;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode t = stack.pop();
            res2.addFirst(t.val);//每次加到结果的最前面
            if (t.left != null)
                stack.push(t.left);
            if (t.right != null)
                stack.push(t.right);
        }
        return res2;
    }
}
