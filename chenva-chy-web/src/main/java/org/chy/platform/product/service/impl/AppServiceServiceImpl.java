package org.chy.platform.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.chy.platform.product.dao.AppServiceMapper;
import org.chy.platform.product.entity.AppService;
import org.chy.platform.product.service.AppServiceService;
import org.springframework.stereotype.Repository;

@Repository
public class AppServiceServiceImpl extends ServiceImpl<AppServiceMapper, AppService> implements AppServiceService {

}
