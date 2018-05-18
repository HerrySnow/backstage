package com.baojia.service;

import com.baojia.model.Users;

import java.util.List;

public interface IUserService {

    Users getUserAll();

    List<Users> selectListAll(int pageNum, int pageSize);

}
