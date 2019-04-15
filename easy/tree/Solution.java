package easy.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    /**
     * 层序遍历树
     * 使用队列
     *
     * @param root
     * @return
     * @data2019/4/6
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new LinkedList<List<Integer>>();
        if (root == null) return list;
        Queue<TreeNode> currentLevel = new LinkedList<TreeNode>();
        currentLevel.add(root);
        while (!currentLevel.isEmpty()) {
            //创建一个List记录当前层所有结点值
            List<Integer> currentList = new LinkedList<>();
            int size = currentLevel.size();
            for (int i = 0; i < size; i++) {
                //poll()获取并移除此列表的头结点
                TreeNode currentNode = currentLevel.poll();
                currentList.add(currentNode.val);
                if (currentNode.left != null)
                    ((LinkedList<TreeNode>) currentLevel).add(currentNode.left);
                if (currentNode.right != null)
                    ((LinkedList<TreeNode>) currentLevel).add(currentNode.right);
            }
            list.add(currentList);
        }
        return list;
    }

    /**
     * 二叉树的最大深度104
     * @param root
     * @return
     * @data 2019/4/7
     */
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;

        return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
    }


    /**
     * 二叉树的最小深度111
     * @param root
     * @return
     * @data 2019/4/7
     */
    public int minDepth(TreeNode root) {
        if(root == null)return 0;
        if(root.left == null)return minDepth(root.right)+1;
        if(root.right == null)return minDepth(root.left)+1;
        return Math.min(minDepth(root.left),minDepth(root.right))+1;
    }

    /**
     * 反转二叉树226
     * @param root
     * @return
     * @data 2019/4/7
     */
    public TreeNode invertTree(TreeNode root) {
        if(root == null)return root;
        TreeNode node = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(node);
        return root;
    }

    /**
     * 返回二叉树所有从根节点到叶子结点的路径257
     * @param root
     * @return
     * @data 2019/4/7
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        //递归终止条件，当前结点为空，直接返回结果；当前结点没有左右子树，返回当前结点的值
        if(root == null)
            return res;
        if(root.left == null && root.right == null) {
            res.add(root.val + "");
            return res;
        }
        //递归调用函数得到左子树的所有路径，在所有路径前加上当前结点的值，将结果存到res
        List<String> leftS = binaryTreePaths(root.left);
        for(int i = 0;i < leftS.size();i ++)
            res.add(root.val + "->" + leftS.get(i));
        //右子树同理
        List<String> rightS = binaryTreePaths(root.right);
        for(int i = 0;i < rightS.size();i++)
            res.add(root.val + "->" + rightS.get(i));

        return res;
    }

    public int pathSum(TreeNode root, int sum) {
        return 0;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
