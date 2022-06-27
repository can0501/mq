package com.example;

/**
 * @author 钟金灿
 * @since 2022/4/25
 */
public class AbcI implements Abc {
    @Override
    public void abc() throws Exception111 {
        if (1 == 1) {

            throw new Exception111();

        }

    }
}
