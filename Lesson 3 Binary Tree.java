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


3. Balanced Binary Tree
Given a binary tree, determine if it is height-balanced.
For this problem, a height-balanced binary tree is defined as a binary tree in which 
the depth of the two subtrees of every node never differ by more than 1.
http://www.lintcode.com/en/problem/balanced-binary-tree/

public class Solution {
    public boolean isBalanced(TreeNode root) {
        // write your code here
        return getDepth(root) != -1;
    }
    
    public int getDepth(TreeNode node){
        if (node == null){
            return 0;
        } else if (node.left == null && node.right == null){
            return 1;
        }
        int left = getDepth(node.left);
        int right = getDepth(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1){
            return -1;
        } else {
            return Math.max(left, right) + 1;
        }
    }
}

没说的，直接背！getDepth里面首先判断null和叶子节点，用-1标记已找到的不合要求的节点。


4. Lowest Common Ancestor
Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.
http://www.lintcode.com/en/problem/lowest-common-ancestor/

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        if (root == null || root == A || root == B){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, A, B);
        TreeNode right = lowestCommonAncestor(root.right, A, B);
        if (left == null && right == null){
            return null;
        } else if (left == null){
            return right;
        } else if (right == null){
            return left;
        } else {
            return root;
        }
    }
}

不要想太多，直接divide & conquer就好。
实际上是个自底而上的过程，一旦找到A或者B，层层返回A、B，直到顶层

                    4
LCA(3, 5) = 4      / \
LCA(5, 6) = 7     3   7
LCA(6, 7) = 7        / \
                    5   6



5. Binary Tree Maximum Path Sum
Given a binary tree, find the maximum path sum.
The path may start and end at any node in the tree.
http://www.lintcode.com/en/problem/binary-tree-maximum-path-sum/

public class Solution {
    public class Result{
        int rootPath;  // 从根开始的最大路径和，最小值为0.
        int maxPath;   
        public Result(int rootPath, int maxPath){
            this.rootPath = rootPath;
            this.maxPath = maxPath;
        }
    }
    
    public int maxPathSum(TreeNode root) {
        // write your code here
        return helper(root).maxPath;
    }
    
    public Result helper(TreeNode node){
        if (node == null){
            return new Result(0, Integer.MIN_VALUE);
        }
        Result left = helper(node.left);
        Result right = helper(node.right);
        
        int rootPath = Math.max(left.rootPath, right.rootPath) + node.val;
        rootPath = Math.max(0, rootPath);
        
        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath, left.rootPath + right.rootPath + node.val);
        
        return new Result(rootPath, maxPath);
    } 
}

必须背，一遍一遍记！
需要一个内部result类，rootPath指从当前node开始的path和，最小值要设置为0！代表path不经过任何点！


6. Binary Tree DFS template

Template 1: Traverse
public class Solution {
    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // do something with root
        traverse(root.left);
        // do something with root
        traverse(root.right);
        // do something with root
    }
}

Tempate 2: Divide & Conquer
public class Solution {
    public ResultType traversal(TreeNode root) {
        // null or leaf
        if (root == null) {
            // do something and return;
        }
        
        // Divide
        ResultType left = traversal(root.left);
        ResultType right = traversal(root.right);
        
        // Conquer
        ResultType result = Merge from left and right.
        return result;
    }
}


7. Binary Tree Level Order Traversal
Given a binary tree, return the level order traversal of its nodes` values. (ie, from left to right, level by level).
http://www.lintcode.com/en/problem/binary-tree-level-order-traversal/

public class Solution {
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // write your code here
        ArrayList list = new ArrayList();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();   //不能用ArrayList，必须用LinkedList
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<Integer>();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
            list.add(level);
        }
        return list;
    }
}


8. Binary Tree Level Order Traversal II
Given a binary tree, return the bottom-up level order traversal of its nodes` values. 
(ie, from left to right, level by level from leaf to root).
http://www.lintcode.com/en/problem/binary-tree-level-order-traversal-ii/

public class Solution {
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        // write your code here
        ArrayList list = new ArrayList();
        if (root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<Integer>();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
            list.add(0, level);  //使用了ArrayList的add(index, e)的方法
        }
        return list;
    }
}

注意ArrayList的这个方法：add(int index, E element) 把element放在index位置，并把其他元素依次右移。效率低。


9. Binary Tree Zigzag Level Order Traversal
Given a binary tree, return the zigzag level order traversal of its nodes` values. 
(ie, from left to right, then right to left for the next level and alternate between).
http://www.lintcode.com/en/problem/binary-tree-zigzag-level-order-traversal/


















