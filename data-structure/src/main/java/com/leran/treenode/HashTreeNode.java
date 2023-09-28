package com.leran.treenode;

import com.leran.linklist.NewLinkList;
import org.springframework.web.servlet.HandlerAdapter;

import java.util.*;

public class HashTreeNode<T> {
    private T v;
    private HashTreeNode<T> left;
    private HashTreeNode<T> right;
    private HashTreeNode<T> parent;

    public T getV() {
        return v;
    }

    public void setV(T v) {
        this.v = v;
    }

    public HashTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(HashTreeNode<T> left) {
        this.left = left;
    }

    public HashTreeNode<T> getRight() {
        return right;
    }

    public void setRight(HashTreeNode<T> right) {
        this.right = right;
    }

    public HashTreeNode<T> getParent() {
        return parent;
    }

    public void setParent(HashTreeNode<T> parent) {
        this.parent = parent;
    }


    //构建一个深度为6的树
    static HashTreeNode<Integer> buildTree(int n) {
        HashTreeNode<Integer> root = new HashTreeNode<Integer>();
        root.setV(1);
        buildTree(root, n - 1);
        return root;
    }

    //构建一个深度为6的树
    static HashTreeNode<Integer> buildTree(HashTreeNode<Integer> parent, int n) {
        if (n <= 0) {
            return null;
        }
        HashTreeNode left = new HashTreeNode();
        left.setV(parent.getV() * 2);
        left.setParent(parent);
        parent.setLeft(left);
        buildTree(left, n - 1);
        HashTreeNode right = new HashTreeNode();
        right.setV(parent.getV() * 2 + 1);
        right.setParent(parent);
        parent.setRight(right);
        buildTree(right, n - 1);
        return parent;
    }

    public static void main(String[] args) {
        HashTreeNode<Integer> node = buildTree(4);
        System.out.println(node.getLeft().getRight().getV());
        System.out.println("===================");
        defsRec();
    }

    public static void defsRec() {
        List<HashTreeNode<Integer>> treeNodeList = new ArrayList<>();
        HashTreeNode<Integer> treeNode = buildTree(4);
        defsRecForNode(treeNodeList, treeNode.getLeft().getRight(), 1,0);
        System.out.println("===================");
        treeNodeList = new ArrayList<>();
        defs(treeNodeList, treeNode);

        System.out.println("===================");
        treeNodeList = new ArrayList<>();
        defsForNode(treeNodeList, treeNode.getLeft().getRight());

    }

    //只能根据跟节点进行深度优先遍历； 递归方法
    public static List<HashTreeNode<Integer>> defsRec(List<HashTreeNode<Integer>> treeNodeList, HashTreeNode<Integer> node) {
        if (node == null) {
            return null;
        }
        treeNodeList.add(node);
        System.out.println(node.getV());
        defsRec(treeNodeList, node.getLeft());
        defsRec(treeNodeList, node.getRight());
        return treeNodeList;
    }

    //只能根据跟节点进行深度优先遍历； 递归方法
    public static List<HashTreeNode<Integer>> defsRecForNode(List<HashTreeNode<Integer>> treeNodeList, HashTreeNode<Integer> node,int j, int i) {
        if (node == null) return null;

        treeNodeList.add(node);
        System.out.println(node.getV());
        if (i == 0) {
            defsRecForNode(treeNodeList, node.getLeft(), j+1,0);
            defsRecForNode(treeNodeList, node.getRight(), j+1,0);
        }else if(i == 1){
            defsRecForNode(treeNodeList, node.getRight(), j+1,0);
        }else if(i ==-1) {
            defsRecForNode(treeNodeList, node.getLeft(), j+1,0);
        }

        if (node.getParent()!=null && j == 1 ) {
            HashTreeNode parent = node.getParent();
            if (parent.getLeft() == node) {
                defsRecForNode(treeNodeList, parent, j,1);
            }else if(parent.getRight() == node) {
                defsRecForNode(treeNodeList, parent, j,-1);
            }
        }

        return treeNodeList;
    }

    public static List<HashTreeNode<Integer>> defs(List<HashTreeNode<Integer>> treeNodeList, HashTreeNode<Integer> node) {
        if (node == null) {
            return null;
        }

        //先进先出
        Stack<HashTreeNode> myStack = new Stack<>();
        myStack.add(node);
        while (!myStack.isEmpty()) {
            HashTreeNode node1 = myStack.pop();   //去除栈顶信息；
            System.out.println(node1.getV());
            treeNodeList.add(node1);

            //向栈中先压入右子树，在压入左子树。这样出栈时，先出左子树再出右子树.也就是,先遍历左边，后遍历右边
            if (node1.getRight() != null) {
                myStack.add(node1.getRight());
            }
            if (node1.getLeft() != null) {
                myStack.add(node1.getLeft());
            }
        }

        return treeNodeList;
    }

    //中度优先深度遍历
    public static List<HashTreeNode<Integer>> defsForNode(List<HashTreeNode<Integer>> treeNodeList, HashTreeNode<Integer> node) {
        if (node == null) {
            return null;
        }
        Deque<HashTreeNode> hashTreeNodeDeque = new LinkedList<>();
        Map<HashTreeNode, Integer> map = new HashMap<>();
        map.put(node, 1);
        hashTreeNodeDeque.push(node);
        while (!hashTreeNodeDeque.isEmpty()) {
            HashTreeNode node1 = hashTreeNodeDeque.pop();
            System.out.println(node1.getV());
            treeNodeList.add(node1);

            if (node1.getParent() != null && !map.containsKey(node1.getParent())) {
                hashTreeNodeDeque.push(node1.getParent());
                map.put(node1.getParent(), 1);
            }

            if (node1.getRight() != null && !map.containsKey(node1.getRight())) {
                hashTreeNodeDeque.push(node1.getRight());
                map.put(node1.getRight(), 1);
            }
            if (node1.getLeft() != null && !map.containsKey(node1.getLeft())) {
                hashTreeNodeDeque.push(node1.getLeft());
                map.put(node1.getLeft(), 1);
            }

        }


        return treeNodeList;
    }


//    public HashTreeNode<T> linkToTree(NewLinkList<T> node) {
//        HashTreeNode<T> root = new HashTreeNode<T>();
//        NewLinkList<T>.Node<T> head = node.head();
//        root.setV(head.data);
//        nodeToTree(root, head.nextNode);
//        return root;
//    }
//
//    private HashTreeNode<T> nodeToTree(HashTreeNode<T> root, NewLinkList<T>.Node<T> node) {
//        if (node==null) {
//            return null;
//        }
//        HashTreeNode<T> left = new HashTreeNode<T>();
//        left.setV(node.data);
//        left.setParent(root);
//
//        NewLinkList<T>.Node<T> next = node.nextNode;
//        if (next != null) {
//            HashTreeNode<T> right = new HashTreeNode<T>();
//            right.setV(next.data);
//            right.setParent(root);
//        }
//        nodeToTree(r)
//        return root;
//    }
}
