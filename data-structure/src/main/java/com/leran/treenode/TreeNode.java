package com.leran.treenode;

import java.util.List;

/**
 * 实现深度优先遍历算法
 * @param <V>
 */
public class TreeNode<V> {

    private V value;
    private List<TreeNode<V>> childList;//子节点列表

    public TreeNode(V value) {
        this.value = value;
    }

    public TreeNode(V value, List<TreeNode<V>> childList) {
        this.value = value;
        this.childList = childList;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public List<TreeNode<V>> getChildList() {
        return childList;
    }

    public void setChildList(List<TreeNode<V>> childList) {
        this.childList = childList;

    }
}
