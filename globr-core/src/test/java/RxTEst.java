import io.bluerain.core.Str;
import org.junit.Test;

/**
 * Created by foredawn on 15-8-22.
 * 正则测试
 */
public class RxTEst {

    @Test
    public void test() {
        String source = "呵呵哒高冷萌，和么么哒反义词 ... 呵呵是鄙视加去死的意思~~呵呵哒简单粗暴点来 说是你麻痹的意思~~因为你麻痹是骂人" +
                " ... tieba.baidu.com/p/3336262950";
        System.out.println(Str.match(source, "^((.(?!&...))*.)$"));
    }
}
