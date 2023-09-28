package com.leran.mode;

import java.util.HashSet;
import java.util.Set;

public class MyStack<T> {

    public MyLinkList<T> last;
    public MyLinkList<T> first;

    public void push(T value) {
        MyLinkList node = new MyLinkList<T>();
        node.value = value;

        if (first == null) {
            first = node;
            last = node;
        }else if (first == last) {
            first.xia = node;
            last.xia = node;
            node.shang = last;
            last = node;
        }else{

            last.xia = node;
            node.shang = last;
            last = node;
        }

    }

    public MyLinkList<T> pol() {
        if(last == null) {
            return null;
        }
        MyLinkList<T> out = last;
        MyLinkList<T> temp = last.shang;
        out.shang = null;
        if (temp != null) {
            temp.xia = null;
        }
        last = temp;
        return out;
    }

    public MyLinkList<T> pol2() {
        if(last == null || first == null) {
            return null;
        }
        MyLinkList<T> out = first;
        first = out.xia;
        if (first!= null) {
            first.shang = null;
        }
        out.shang = null;
        return out;
    }

    public static void main(String[] args) {
//        MyStack<Integer> myStack = new MyStack<>();
//        for(int i=0; i<3; i++) {
//            myStack.push(i);
//        }

//        System.out.println(myStack.pol().value);
//        System.out.println(myStack.pol().value);
//        System.out.println(myStack.pol().value);
//        System.out.println(myStack.pol());
        Set<Integer> set = new HashSet<>();


        MyStack<Integer> left = new MyStack<>();
        MyStack<Integer> right = new MyStack<>();
        left.push(9);
        left.push(10);
        left.push(5);
        left.push(4);
        left.push(3);
        left.push(2);
        left.push(7);
        left.push(1);
        left.push(8);
        left.push(6);

        right.push(4);
        right.push(3);
        right.push(5);
        right.push(9);
        right.push(10);
        right.push(7);
        right.push(8);
        right.push(1);
        right.push(6);
        right.push(2);

        left.eat1(right);
    }

    public void eat1(MyStack<T> stack) {
        MyLinkList<T> atNode = this.pol();
        this.eat(stack, atNode.value,1);
    }


    public void eat(MyStack<T> stack, T value, int flag) {
        if (stack == null || stack.first == null) {
            if(flag == 1) {
                System.out.println("left 成功；right 失败");
            }else{
                System.out.println("right 成功；left 失败");
            }
            return;
        }

        MyLinkList<T> bcFirst = stack.first;
        boolean f = false;
        do{
            if (bcFirst.value == value) {
                f = true;
                break;
            }else{
                bcFirst = bcFirst.xia;
            }
        }while (bcFirst != null);

        if (!f) {
            if(flag == 1) {
                System.out.println("left 值: "+value+" 攻击失败");
            }else{
                System.out.println("right 值: "+value+" 攻击失败");
            }
            T valus = stack.pol().value;
            stack.eat(this, valus, -flag);
        }else{
            if(bcFirst.shang == null) {
                stack.last = null;
                stack.first = null;
            }else {
                stack.last = bcFirst.shang;
                stack.last.xia = null;
                bcFirst = null;
            }
            if(flag == 1) {
                System.out.println("left 攻击成功: "+value);
            }else{
                System.out.println("right 攻击成功: "+value);
            }
            this.eat(stack, this.pol().value, flag);

        }
    }
}
