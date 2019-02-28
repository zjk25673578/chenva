package com.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pojo.user.AppUser;
import com.dao.user.AppUserMapper;
import com.service.user.AppUserService;
import org.springframework.stereotype.Repository;

@Repository
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements AppUserService {

}
