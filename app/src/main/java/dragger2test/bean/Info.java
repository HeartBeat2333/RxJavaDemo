package dragger2test.bean;

import javax.inject.Inject;

/**
 * Created by zhouyuan0304 on 2016/4/19.
 */
public class Info {
    public String info;

    @Inject
    public Info() {
        info = "info visit";
    }
}
