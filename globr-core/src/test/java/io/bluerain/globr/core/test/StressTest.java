package io.bluerain.globr.core.test;

import io.bluerain.globr.core.Search;
import io.bluerain.globr.enties.bean.io.bluerain.globr.enties.SearchResult;
import org.junit.Test;

/**
 * Created by foredawn on 15-8-22.
 * 压力以及潜在数据BUG测试
 */
public class StressTest {

    @Test
    public void test() {
        for (int i = 1; i <= 10; i++) {
            for (SearchResult sr : Search.by("逗你哇", i)) {
                System.out.println(sr.getTitle());
            }
        }
    }

}
