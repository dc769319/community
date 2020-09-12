package cc.charles.community.leetCode;

/**
 *
 * 腾讯-微信社招算法题：
 *
 * 在二叉排序树上面找出第3大的节点。注意：不能把二叉树全量存储到另外的存储空间，比如存储到数组中，然后取出数组的第三个元素。
 * struct TreeNode {
 * int value;
 * struct TreeNode * left, * right;
 * };
 * struct TreeNode * find( struct TreeNode * root );
 */
public class BinaryTreeFindK {
    public int res;
    public int k;

    public static void main(String[] args) {
        System.out.println("Hello World!");
        BinaryTreeFindK main = new BinaryTreeFindK();
        BinaryTreeFindK.TreeNode node8 = main.new TreeNode(8);
        BinaryTreeFindK.TreeNode node4 = main.new TreeNode(4);
        BinaryTreeFindK.TreeNode node3 = main.new TreeNode(3);
        BinaryTreeFindK.TreeNode node5 = main.new TreeNode(5);
        BinaryTreeFindK.TreeNode node12 = main.new TreeNode(12);
        BinaryTreeFindK.TreeNode node11 = main.new TreeNode(11);
        BinaryTreeFindK.TreeNode node13 = main.new TreeNode(13);
        node8.left = node4;
        node8.right = node12;
        node4.left = node3;
        node4.right = node5;
        node12.left = node11;
        node12.right = node13;
        main.findKNum(node8, 3);
        System.out.print(main.res);
    }

    private void findKNum(TreeNode root, int k) {
        this.k = k;
        doFind(root);
    }

    private void doFind(TreeNode root) {
        if (null == root) {
            return;
        }
        doFind(root.right);
        if (k == 0) {
            return;
        }
        k--;
        if (k == 0) {
            res = root.value;
        }
        doFind(root.left);
    }

    public class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }
    }
}