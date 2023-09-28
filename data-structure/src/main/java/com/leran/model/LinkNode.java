package com.leran.model;

import java.util.ArrayDeque;
import java.util.Deque;

public class LinkNode<T> {
    private T val;
    private LinkNode<T> left;
    private LinkNode<T> right;
    private LinkNode<T> parent;

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public LinkNode<T> getLeft() {
        return left;
    }

    public void setLeft(LinkNode<T> left) {
        this.left = left;
    }

    public LinkNode<T> getRight() {
        return right;
    }

    public void setRight(LinkNode<T> right) {
        this.right = right;
    }

    public LinkNode<T> getParent() {
        return parent;
    }

    public void setParent(LinkNode<T> parent) {
        this.parent = parent;
    }



    public static <T>  LinkNode<T> generateTree(int n){
        Deque<LinkNode<T>> queue = new ArrayDeque<>();
        LinkNode<T> pr= new LinkNode<>();
        queue.push(pr);
        for(int i=1;i<n;i++){
            int size = queue.size();
            for(int j=0;j<size;j++){
                LinkNode<T> root = queue.poll();
                LinkNode<T> left = new LinkNode<>();
                LinkNode<T> right = new LinkNode<>();
                left.parent = root;
                right.parent = root;
                root.left = left;
                root.right = right;
                queue.offer(left);
                queue.offer(right);
            }
        }
        return pr;
    }


    public static LinkNode buildTree(int n){
        LinkNode root = new LinkNode();
        root.val = 1;
        buildNode(root, n-1, 1);
        return root;
    }

    private static LinkNode buildNode(LinkNode node, int n, int parentVal){
        if (n==0){
            return null;
        }

        LinkNode left = new LinkNode();
        left.parent = node;
        left.val = parentVal * 2;
        node.left = left;
        LinkNode right = new LinkNode();
        right.parent = node;
        right.val = parentVal * 2 + 1;
        node.right = right;
        buildNode(left, n-1, (int)left.val);
        buildNode(right, n-1, (int) right.val);
        return node;
    }


    public void each(){
        each0(0, 1, true);
    }

    public void each0(int m, int n, boolean pe){
        System.out.println(this.val);
        if(m>=0 && this.right!=null){
            this.right.each0(0, n,false);
        }

        if(m<=0 &&this.left!=null){
            this.left.each0(0, n,false);
        }
        if(pe){
            if(parent.left==this){
                parent.each0(1, n, true);
            }else if(parent.right==this){
                parent.each0(-1, n,true);
            }else if(n>0){
                parent.each0(0, n-1, true);
            }
        }

    }

    public static void main(String[] args) {
        LinkNode<Object> root1 = LinkNode.buildTree(4);
        LinkNode<Object> root2 = LinkNode.buildTree(4);
        LinkNode<Object> root3 = LinkNode.buildTree(4);
        LinkNode<Object> parent = new LinkNode<>();
        parent.val=0;
        root1.parent = parent;
        root2.parent = parent;
        root3.parent = parent;
        parent.left = root1;
        parent.right = root2;
        parent.parent=root3;

        root1.right.left.each();



    }
}
