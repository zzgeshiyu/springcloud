package com.leran.demo.block;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.leran.demo.outmodel.User;

/**
 * 限流控制类
 */
public class AllBlockHandler {


    /**
     * 1.  一定是public
     * 2.   返回值和原方法保持一致  ，参数也要一致
     * 3.   可以在参数后面添加 BlockException 可以区分是什么规则的处理方法
     * 4.   如果设置在非原始类中，方法需要设置 static
     * @param id
     * @param be
     * @return
     */
    public static User blockHandlerForGetUser(int id, BlockException be) {
        be.printStackTrace();
        User user = new User();
        user.setId(id);
        user.setName("getUser 方法被流控了");
        return user;
    }

    public static User blockHandlerForTestDegradeRule(int id, BlockException e) {
        e.printStackTrace();
        User user = new User();
        user.setId(id);
        user.setName("testDegradeRule方法被降级了");
        return user;
    }
}
