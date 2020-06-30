package com.wrpxcx.mapper;

import com.wrpxcx.POJO.User;

public interface UserMapper {

    User getUserByOpenid(String openid);

    int addUser(User user);

    int updateUser(User user);

}
