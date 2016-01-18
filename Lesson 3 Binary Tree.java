1. Binary Tree Preorder Traversal
http://www.lintcode.com/en/problem/binary-tree-preorder-traversal/

public class Solution {
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        // write your code here
        ArrayList<Integer> list = new ArrayList<Integer>();
        traverse(list, root);
        return list;
    }
    
    public void traverse(ArrayList<Integer> list, TreeNode node){
        if (node == null){
            return;
        }
        list.add(node.val);
        traverse(list, node.left);
        traverse(list, node.right);
    }
}


2. Maximum Depth of Binary Tree
http://www.lintcode.com/en/problem/maximum-depth-of-binary-tree/

public class Solution {
    public int maxDepth(TreeNode root) {
        // write your code here
        if (root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
}