package io.bluerain.globr.core.test;

import org.junit.Test;

/**
 * Created by hentai_mew on 15-10-26.
 * 其他的一些方法测试
 */
public class OtherTest {

    @Test
    public void test() {
        int[] i = new int[1];
        hehe(i);
        System.out.println(i[0]);
    }

    public static void hehe(int[] num) {
        num[0] = 10;
    }
}
