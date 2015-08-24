package io.bluerain.globr.core.test;

import io.bluerain.globr.core.Search;
import io.bluerain.globr.enties.bean.io.bluerain.globr.enties.SearchResult;
import org.junit.Test;

/**
 * Created by foredawn on 15-8-22.
 * 测试Search核心类
 */
public class SearchTest {

    @Test
    public void testSearch() {
        for (String r : Search.by("呵呵哒", 1).getRelatedKeys()) {
            System.out.println(r);
        }
    }
}
