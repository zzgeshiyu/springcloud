package com.leran.treenode;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class TreeAVL_Tree {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        avlTree.insertNode(7);
        System.out.println(avlTree.tree.toString());
    }

    static class AVLTree{
        private Queue<TreeAVL_Tree.TreeNode> tree = new LinkedList<>();

        public void insertNode(int data) {
            TreeAVL_Tree.TreeNode newNode = new TreeNode(data);
            if (tree.size()==0) {
                tree.add(newNode);
            }else {

            }

        }
    }

    /**
     * 平衡二叉树 实现
     */
    static class TreeNode {
        private int bf = 0;// 平衡因子
        private int data; //存放数据
        private TreeNode parentNode; //父级节点
        private TreeNode leftChild, rightChild;// 左右子节点
        private int count; //数据重复插入次数

        public TreeNode(int data) {
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
            TreeNode node = (TreeNode) obj;
            return data == node.data;
        }

        @Override
        public int hashCode() {
            return Objects.hash(bf, data, parentNode, leftChild, rightChild, count);
        }
    }


}



