package io.bluerain.globr.core.utils;

import io.bluerain.type.RefInt;

/**
 * Created by hentai_mew on 15-10-26.
 * 谷歌分页相关工具类
 */
public class GooglePagin {

    /**
     * 根据页码获取start值(默认10)
     *
     * @param pn 页码
     * @return start值
     */
    public static int getStart(RefInt pn) {
        int pagNum = pn.getValue();
        int start;
        if (pagNum <= 1)
            pagNum = 1;
        if (pagNum == 1)
            start = 0;
        else
            start = (pagNum - 1) * 10;
        pn.setValue(pagNum);
        return start;
    }
}
