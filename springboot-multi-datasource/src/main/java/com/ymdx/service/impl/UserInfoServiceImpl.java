package com.ymdx.service.impl;

import com.ymdx.dao.db2.UserInfoMapper;
import com.ymdx.pojo.UserInfo;
import com.ymdx.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: UserInfoServiceImpl
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-26 16:13
 * @Version: 1.0
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    @Transactional(transactionManager = "db2DataSourceTransactionManager", rollbackFor = Exception.class)
    public void saveUserInfo(UserInfo userInfo) {
        userInfoMapper.addUserInfo(userInfo.getName(), userInfo.getPhone(), userInfo.getAddress());
    }

}
