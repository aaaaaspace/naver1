package com.naver.test;

public class TestQueue {
    public static void main(String[] args) {
        ArrayQueue<Object> queue = new ArrayQueue<Object>(5);
        queue.push("aaa");
        queue.push("bbb");
        queue.push("ccc");
        queue.push("ddd");

        queue.pop("bbb");
        queue.push(11);

        queue.pop("ddd");
        queue.push("uuu");

        queue.peek();

        // 打印
        for (Object item : queue.items) {
            System.out.println(item);
        }
    }
}
