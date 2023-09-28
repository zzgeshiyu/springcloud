package com.leran.treenode;

import com.leran.linklist.NewLinkList;

import java.util.LinkedList;
import java.util.Objects;

/**
 * 平衡二叉树 实现
 */
public class TreeNodeTest {

    public static void main(String[] args) {
        NewLinkList<Integer> newLinkList = new NewLinkList<>();
        for (int i = 0; i < 10; i++) {
            newLinkList.addLast(i);
        }
        TreeNodeTest treeNodeTest = new TreeNodeTest(1);
        TreeNode  node = treeNodeTest.sortedListToBST(newLinkList);
        System.out.println(node);

    }


    public TreeNode sortedListToBST(NewLinkList<Integer> list) {
        if (list == null) {
            return null;
        }
        NewLinkList.Node temp = list.head();
        int len = 1;
        while (temp.nextNode != null) {
            len ++;
            temp = temp.nextNode;
        }
        int [] num = new int[len];
        temp = list.head();
        for (int i =0;i<len; i++) {
            num[i] = (int) temp.data;
            temp = temp.nextNode;
        }
        System.out.println(len);
        return buildtree(num, 0, len-1);
    }

    public TreeNode buildtree(int[] num, int left, int right) {
        if (left > right ) return null;
        //取中间节点为跟节点
        int middle = (left + right) /2;
        TreeNode root=new TreeNode(num[middle]);
        //递归构造左右子树
        root.left=buildtree(num,left,middle-1);
        root.right=buildtree(num,middle+1,right);

        return root;
    }









    class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }

        @Override
        public String toString() {
            String pData = null, lData = null, rData = null;

            if (left != null)
                lData = String.valueOf(left.val);

            if(right != null)
                rData = String.valueOf(right.val);

            return "TreeNode{" +
                    " data=" + val +
                    ", left_child_data=" + lData +
                    ", right_child_data=" + rData +
                    '}';
        }
    }









    private int bf = 0;// 平衡因子
    private int data; //存放数据
    private TreeNodeTest parentNode; //父级节点
    private TreeNodeTest leftChild, rightChild;// 左右子节点
    private int count; //数据重复插入次数

    public TreeNodeTest(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        String pData = null, lData = null, rData = null;
        if (parentNode != null) {
            pData = String.valueOf(parentNode.data);
        }
        if (leftChild != null)
            lData = String.valueOf(leftChild.data);

        if(rightChild != null)
            rData = String.valueOf(rightChild.data);

        return "TreeNode{" +
                " data=" + data +
                ", bf=" + bf +
                ", count=" + count +
                ", parent_node_data=" + pData +
                ", left_child_data=" + lData +
                ", right_child_data=" + rData +
                '}';
    }

    /**
     * 平衡二叉树节点值是唯一的 ，需要比较值相等
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null ||getClass() != obj.getClass()) return false;
        TreeNodeTest node = (TreeNodeTest) obj;
        return data == node.data;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bf, data, parentNode, leftChild, rightChild, count);
    }
}
