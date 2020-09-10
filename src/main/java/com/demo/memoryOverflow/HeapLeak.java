package com.demo.memoryOverflow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lin.wang
 * @date 2020/09/08
 * 由Java的官方文档我们可以看出，Java堆中存放：对象、数组。下面以不断创建对象为例：
 * <p>
 * Exception in thread "main"java.lang.OutOfMemoryError: Java heap space
 */
public class HeapLeak {
    public static void main(String[] args) {
//        testOutOfHeapMemory();
        ArrayList list = new ArrayList();
        while (true) {

            list.add(new HeapLeak());

        }

    }

    public static void testOutOfHeapMemory() {
        List<StringBuffer> list = new ArrayList<StringBuffer>();
        while (true) {
            StringBuffer B = new StringBuffer();
            for (int i = 0; i < 10000; i++) {
                B.append(i);
            }
            list.add(B);
        }
    }
}
