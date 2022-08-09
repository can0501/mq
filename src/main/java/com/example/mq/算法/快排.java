package com.example.mq.算法;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
 * @author 钟金灿
 * @since 2022/4/2
 */
public class 快排 {
    @Test
    public void sd(){
        int a[] = new int[]{5, 4, 3, 2, 1};
        q_sort(0, a.length - 1, a);
        Stream.of(a).forEach(System.out::println);
    }

    void q_sort(int start, int end, int[] a) {
        if (start >= end) {
            return;
        }
        int tmp = a[start];
        int l = start;
        int r = end;
        while (l < r) {
            while (l < r && a[r] > tmp) {
                r--;
            }
            a[l] = a[r];
            while (l < r && a[l] <= tmp) {
                l++;
            }
            a[r] = a[l];
        }
        a[l]=tmp;
        q_sort(start, l - 1, a);
        q_sort(l + 1, end, a);
    }

}
