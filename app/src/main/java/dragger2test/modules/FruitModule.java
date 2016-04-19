package dragger2test.modules;

import dagger.Module;
import dagger.Provides;
import dragger2test.bean.Info;

/**
 * Created by zhouyuan0304 on 2016/4/15.
 */
@Module
public class FruitModule {

    public FruitModule() {}

    @Provides
    public Info provideInfo() {
        return new Info();
    }
}
