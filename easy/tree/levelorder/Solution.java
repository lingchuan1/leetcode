package easy.tree.levelorder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
     * 层序遍历树102
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

    //递归
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if(root == null)
            return res;
        helper(root,0);
        return res;
    }
    private void helper(TreeNode node,int level){
        if(node == null)
            return;
        if(res.size() == level){
            res.add(new ArrayList<>());
        }
        res.get(level).add(node.val);
        helper(node.left,level + 1);
         helper(node.right,level + 1);
    }

    /**
     * 层序遍历II 结果自底向上107
     * @param root
     * @return
     */
    private LinkedList<List<Integer>> res2 = new LinkedList<>();
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root == null)
            return res2;
        Queue<TreeNode> queue = new LinkedList<>();
        ((LinkedList<TreeNode>) queue).add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0;i < size;i ++){
                TreeNode t = queue.poll();
                list.add(t.val);
                if(t.left != null)
                    ((LinkedList<TreeNode>) queue).add(t.left);
                if(t.right != null)
                    ((LinkedList<TreeNode>) queue).add(t.right);
            }
            res2.addFirst(list);
        }
        return res2;
    }
}
