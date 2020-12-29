package com.demo.leetcode;

//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
// 示例：
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
//
// Related Topics 链表 数学
// 👍 5100 👎 0

import lombok.val;

/**
 * @author lin.wang
 * @date 2020/10/16
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int value1 = getIntValue(l1);
        int value2 = getIntValue(l2);
        int value3 = value1 + value2;
        System.out.println(value3);
        return l1;
    }

    private int getIntValue(ListNode l1) {
        int tempData = 0;
        int returnData = 0;
        returnData += getOneValue(l1, 0, tempData);
        return returnData;
    }

    private int getOneValue(ListNode l1, int number, int tempData) {
        int value = l1.val;
        int realValue = value * (int) Math.pow(10, number);
        if (l1.next != null) {
            tempData += realValue;
            return getOneValue(l1.next, ++number, tempData);
        }
        tempData += realValue;
        return tempData;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(2, new ListNode(4, new ListNode(3, null)));

        ListNode listNode2 = new ListNode(5, new ListNode(6, new ListNode(4, null)));

        ListNode listNode3 = new Solution().addTwoNumbers(listNode1, listNode2);

        System.out.println(listNode3);
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
