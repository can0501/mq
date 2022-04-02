package com.example.mq.算法;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 * @author 钟金灿
 * @since 2022/4/2
 */
public class lc347 {
    @Test
    public void sd(){

    }

    public int[] topKFrequent(int[] nums, int k) {
        q_sort(0, nums.length - 1, nums, k);
        HashSet<Integer> s = new HashSet<>();
        int i = 0;
        while (s.size() < k && i < nums.length) {
            s.add(nums[i++]);
        }

        return s.stream().mapToInt(e -> e).toArray();

    }


    void q_sort(int start, int end, int[] a, int kk) {
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
        if (l == kk) {
            return;
        }
        a[l] = tmp;
        q_sort(start, l - 1, a, kk);
        q_sort(l + 1, end, a, kk);
    }

}
