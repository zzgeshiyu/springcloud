package com.leran.demo.fallback;

import com.leran.demo.outmodel.User;

public class AllFallbackHandler {

    /**
     * Throwable 可以获取当前是什么异常
     * @param id
     * @param e
     * @return
     */
    public static User fallbackHandlerForGetUser(int id, Throwable e) {
        e.printStackTrace();
        User user = new User();
        user.setId(id);
        user.setName("getUser fallback"+e.getMessage());
        return user;
    }

}
