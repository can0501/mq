package com.example.mq.算法;

import org.junit.jupiter.api.Test;

/**
 * @author 钟金灿
 * @since 2022/4/2
 */
public class lc121 {
   static class Solution {
        public static int maxProfit(int[] prices) {
            int dp[] = new int[prices.length];
            dp[0] = 0;
            for (int i = 0; i < prices.length; i++) {
                for (int j = 0; j < i; j++) {
                    dp[i] = Math.max(dp[i - 1], dp[j] + prices[i] - prices[j]);
                }
            }
            return dp[prices.length - 1];
        }
    }
    public   int maxProfit(int[] prices) {
        int dp[] = new int[prices.length];
        dp[0] = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = Math.max(dp[i - 1], dp[j] + prices[i] - prices[j]);
            }
        }
        return dp[prices.length - 1];
    }

    @Test
    public void te(){
        int p[] = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(p));
    }
}
