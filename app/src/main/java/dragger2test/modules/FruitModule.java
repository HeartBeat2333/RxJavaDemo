package dragger2test.modules;

import dagger.Module;
import dagger.Provides;
import dragger2test.bean.Apple;
import dragger2test.bean.Fruit;

/**
 * Created by zhouyuan0304 on 2016/4/15.
 */
@Module
public class FruitModule {
    @Provides
    public Fruit provideFruit() {
        return new Apple();
    }
}
