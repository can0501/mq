package com.example.mq.算法;

/**
 * @author 钟金灿
 * @since 2021/6/22
 */
public class 最大连续子序列和 {
    public static int bruteMethod(int[] nums) {
        int max = nums[0];
        int t = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                for (int k = i; k <= j; k++) {
                    t += nums[k];
                }
                if (t > max)
                    max = t;
                t = 0;
            }
        }
        return max;
    }
}
