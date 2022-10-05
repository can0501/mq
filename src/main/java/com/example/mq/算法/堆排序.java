package com.example.mq.算法;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author 钟金灿
 * @since 2022/4/2
 */
public class 堆排序 {
    public static void buildTree(int[] t, int start, int end) {
        int tmp = t[start];
        for (int i = 2 * start + 1; i <= end; i = 2 * start + 1) {
            if (i < end  && t[i] < t[i + 1]) {
                i++;
            }
            if (t[i] > tmp) {
                t[start] = t[i];
                start = i;
            } else {
                break;
            }
            t[start] = tmp;
        }
    }

    public static void heapSort(int[] t, int end) {
        for (int i = end / 2 - 1; i >= 0; i--) {
            buildTree(t, i, end-1);
        }


        for (int i = end - 1; i >= 0; i--) {
            int tmp = t[i];
            t[i] = t[0];
            t[0] = tmp;
            buildTree(t, 0, i - 1);
        }

        //buildTree(t, 0, 4);
        //buildTree(t, 0, 3);
        //buildTree(t, 0, 2);
        // buildTree(t, 0, 1);
    }


    @Test
    public void te() {
        //int p[] = new int[]{1,2};
        //int p[] = new int[]{1,2,3};
        //int p[] = new int[]{2,1};
        //int p[] = new int[]{3,2,1};
        //int p[] = new int[]{1,2,3,4};
        int p[] = new int[]{4,3,2,1,5};

        heapSort(p, p.length);

        Arrays.stream(p).forEach(System.out::println);
    }
}
