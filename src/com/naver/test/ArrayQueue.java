package com.naver.test;

/**
 * 自定义Array队列
 * @author ouyangjun
 */
public class ArrayQueue<E> {

    final Object[] items;
    int putIndex;
    int count;

    // 默认容量
    public ArrayQueue() {
        this(16);
    }

    // 初始化指定容量
    public ArrayQueue(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        this.items = new Object[capacity];
    }

    // 进队，每一次把数据添加到队列尾部。
    public void push(E x) {
        if (isFull()) {
            throw new IllegalStateException("Queue full");
        }

        final Object[] items = this.items;
        items[putIndex] = x; // 将元素添加到队列尾部
        putIndex++; // 元素下标加1, 方便下次数据进队
        count++; // 元素数量加1
    }

    // 队列是否已满
    public boolean isFull() {
        return count == items.length ? true : false;
    }

    // 从队头进行数据出队
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        @SuppressWarnings("unchecked")
        E x = (E) items[0]; // 获取队列头部第一个元素
        items[0] = null; // 把头部元素置空

        // 重新排列
        final Object[] items = this.items;
        final int putIndex = this.putIndex;

        // 当有一个元素为空时,把队列元素往前移动一位,保证队列尾部的值都为空
        for(int i = 0;;) {
            int next = i + 1;
            if (next != putIndex) {
                items[i] = items[next]; // 把后一个值赋值给前一个值,相当于把所有值往前移动一位
                i = next; // 调整下标值
            } else {
                items[i] = null; // 把元素置空,相当于清除
                this.putIndex = i; // 重新指定下一个添加值的下标
                break;
            }
        }
        count--; // 元素个数减1;
        return x;
    }

    // 队列是否已空
    public boolean isEmpty() {
        return count == 0 ? true : false;
    }

    // 移除队列元素
    public boolean pop(Object obj) {
        if (obj == null || isEmpty()) {
            return false;
        }

        final Object[] items = this.items;
        final int putIndex = this.putIndex;

        int remeveIndex = 0; // 默认移除第0个下标元素
        do{
            // 判断元素是否存在
            if (obj.equals(items[remeveIndex])) {
                // 当有一个元素为空时,把队列元素往前移动一位,保证队列尾部的值都为空
                for(int i = remeveIndex;;) {
                    int next = i + 1;
                    if (next != putIndex) {
                        items[i] = items[next]; // 把后一个值赋值给前一个值,相当于把所有值往前移动一位
                        i = next; // 调整下标值
                    } else {
                        items[i] = null; // 把元素置空,相当于清除
                        this.putIndex = i; // 重新指定下一个添加值的下标
                        break;
                    }
                }
                count--; // 元素个数减一;
                return true;
            }
            remeveIndex++;
        } while (remeveIndex != putIndex);

        // 返回
        return false;
    }
}