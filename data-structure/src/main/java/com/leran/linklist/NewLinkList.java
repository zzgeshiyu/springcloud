package com.leran.linklist;

public class NewLinkList<T> {

    int size;

    Node<T> first;
    Node<T> last;

    public Node<T> head () {
        if(first == null) {
            throw new RuntimeException("链表为空！");
        }
        return first;
    }

    public void addFirst(T v) {
        Node<T> newNode = new Node<>(null, v, null);
        if (size == 0) {
            first = newNode;
            last = newNode;
        } else {
            newNode.nextNode = first;
            first.preNode = newNode;
            first = newNode;
        }
        size++;
    }

    public T removeHead() {
        if (size == 0) {
            throw new RuntimeException("链表为空！");
        } else {
            Node<T> head = first;
            Node<T> nextNode = first.nextNode;
            first = null;
            first = nextNode;
            if (nextNode != null) {
                nextNode.preNode = null;
            } else {
                last = null;
            }
            size--;
            return head.data;
        }
    }

    public T removeLast() {
        if (size == 0) {
            throw new RuntimeException("链表为空！");
        } else {
            Node<T> node = last;
            Node<T> preNode = last.preNode;
            last.preNode = null;
            last = null;
            last = preNode;
            if (preNode == null) {
                first = null;
            } else {
                preNode.nextNode = null;
            }
            size--;
            return node.data;
        }
    }

    public T remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("数组下标越界");
        } else if (index == 0) {
            return removeHead();
        } else if (index == size) {
            return removeLast();
        } else {
            Node<T> r = getNode(index);
            Node<T> nN = r.nextNode;
            Node<T> pN = r.preNode;
            T data = r.data;
            r.nextNode = null;
            r.preNode = null;
            r = null;
            nN.preNode = pN;
            pN.nextNode = nN;
            return data;
        }
    }

    public void addLast(T v) {
        if (size == 0) {
            addFirst(v);
        } else {
            Node<T> newNode = new Node<>(last, v, null);
            last.nextNode = newNode;
            newNode.preNode = last;
            last = newNode;
        }
        size++;
    }

    public void add(int index, T v) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("数组下标越界");
        } else if (index == 0) {
            addFirst(v);
        } else if (index == size) {
            addLast(v);
        } else {
            Node<T> head = first;
            Node<T> indexOld = first;
            for (int i = 0; i < index - 1; i++) {
                indexOld = indexOld.nextNode;
            }
            Node newNode = new Node(indexOld, v, indexOld.nextNode);
            newNode.nextNode = indexOld.nextNode;
            indexOld.nextNode = newNode;
            newNode.preNode = indexOld;
            newNode.nextNode.preNode = newNode;
            size++;
        }
    }

    public T getData(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("数组下标越界");
        } else if (size == 0) {
            return null;
        } else if (size == 1) {
            return first.data;
        } else {
            Node<T> node = first;
            for (int i = 0; i < index; i++) {
                node = node.nextNode;
            }
            return node.data;
        }
    }

    public Node<T> getNode(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("数组下标越界");
        } else if (size == 0) {
            return null;
        } else if (size == 1) {
            return first;
        } else {
            Node<T> node = first;
            for (int i = 0; i < index; i++) {
                node = node.nextNode;
            }
            return node;
        }
    }

    public void print() {
        if (size == 0) {
            System.out.println("空链表 first:"+first+"   last:"+last);
        } else {
            Node<T> node = first;
            while (node != null) {
                System.out.print(node.data + "\t");
                node = node.nextNode;
            }
            System.out.println();
            System.out.print("first:"+first.data+"   last:"+last.data);
            System.out.println();
        }
    }

    public class Node<T> {
        public T data;
        public Node<T> preNode;
        public Node<T> nextNode;

        public Node(Node<T> preNode, T data, Node<T> nextNode) {
            this.data = data;
            this.preNode = preNode;
            this.nextNode = nextNode;
        }
    }
}

