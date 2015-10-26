package io.bluerain.globr.core.test;

import io.bluerain.globr.core.Search;
import io.bluerain.globr.enties.bean.io.bluerain.globr.enties.SearchResult;
import io.bluerain.globr.enties.bean.io.bluerain.globr.enties.SingleResult;
import org.junit.Test;

/**
 * Created by foredawn on 15-8-22.
 * 测试Search核心类
 */
public class SearchTest {

    @Test
    public void testSearch() {
        SearchResult result = Search.by("呵呵哒", 1);
        for (SingleResult r : result.getList()) {
            System.out.println(r);
        }

        System.out.println(result.getRelatedKeys().size());
        for (String relateKeys : result.getRelatedKeys()) {
            System.out.println(relateKeys);
        }
    }
}
