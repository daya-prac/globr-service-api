package io.bluerain.globr.enties.bean.io.bluerain.globr.enties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by foredawn on 15-8-22.
 * 搜索结果
 */
public class SearchResult {

    private Integer curPage;
    private List<SingleResult> list = new ArrayList<>();
    private List<String> relatedKeys = new ArrayList<>();


    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public List<String> getRelatedKeys() {
        return relatedKeys;
    }

    public void setRelatedKeys(List<String> relatedKeys) {
        this.relatedKeys = relatedKeys;
    }

    public List<SingleResult> getList() {
        return list;
    }

    public void setList(List<SingleResult> list) {
        this.list = list;
    }
}
