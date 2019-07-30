package easy.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    /**
     * 二叉树的最大深度104
     *
     * @param root
     * @return
     * @data 2019/4/7
     */
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    /**
     * 二叉树的最小深度111
     *
     * @param root
     * @return
     * @data 2019/4/7
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null) return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    /**
     * 反转二叉树226
     *
     * @param root
     * @return
     * @data 2019/4/7
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode node = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(node);
        return root;
    }

    /**
     * 返回二叉树所有从根节点到叶子结点的路径257
     *
     * @param root
     * @return
     * @data 2019/4/7
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        //递归终止条件，当前结点为空，直接返回结果；当前结点没有左右子树，返回当前结点的值
        if (root == null)
            return res;
        if (root.left == null && root.right == null) {
            res.add(root.val + "");
            return res;
        }
        //递归调用函数得到左子树的所有路径，在所有路径前加上当前结点的值，将结果存到res
        List<String> leftS = binaryTreePaths(root.left);
        for (int i = 0; i < leftS.size(); i++)
            res.add(root.val + "->" + leftS.get(i));
        //右子树同理
        List<String> rightS = binaryTreePaths(root.right);
        for (int i = 0; i < rightS.size(); i++)
            res.add(root.val + "->" + rightS.get(i));

        return res;
    }


    /**
     * 修剪二叉搜索树
     *
     * @param root
     * @param L
     * @param R
     * @return
     */
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null)
            return root;
        if (root.val < L)
            return trimBST(root.left, L, R);
        if (root.val > R)
            return trimBST(root.right, L, R);
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 二叉搜索树的最小公共祖先235
     *
     * @param root
     * @param p
     * @param q
     * @return
     * @data 2019/7/15
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        if (root.val > Math.max(p.val, q.val))
            return lowestCommonAncestor(root.left, p, q);
        else if (root.val < Math.min(p.val, q.val))
            return lowestCommonAncestor(root.right, p, q);
        else return root;
    }

    /**
     * 序列化和反序列化二叉搜索树449
     *
     * @param root
     * @return
     * @data 2019/7/15
     */
    private int i;
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder out = new StringBuilder();
        serializeHelper(root,out);
        return out.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        i = 0;
        String[] strings = data.split("_");
        return deserializeHelper(strings);
    }

    private void serializeHelper(TreeNode node,StringBuilder out){
        if(node != null){
            out.append(node.val + "_");
            serializeHelper(node.left,out);
            serializeHelper(node.right,out);
        }else
            out.append("#_");
    }

    private TreeNode deserializeHelper(String[] data){
        if(data[i] .equals("#")){
            i ++;
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(data[i ++]));
        node.left = deserializeHelper(data);
        node.right = deserializeHelper(data);
        return node;
    }
}
