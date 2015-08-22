$(function () {
    var pagNum = getUrlParam('pagNum');
    if (pagNum && pagNum > 0) {
        var $cur;
        if (pagNum > 5) {
            $cur = $('a[index=0]');
            $cur.text(pagNum);
            $cur.addClass('selected');
        } else {
            $cur = $('a[index=' + pagNum + ']');
            $cur.addClass('selected');
            $cur.removeAttr('href');
        }
    }
});

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}
