package com.example.demo.service;

import com.example.demo.dao.AccountDaoMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceMapper2 {

    // 添加require=false取消校验对象指针地址
    @Autowired(required = false)
    AccountDaoMapper2 accountDaoMapper2;

    //@Transactional
    public void transfer() throws RuntimeException{
        accountDaoMapper2.update(900,1);//用户1减10块 用户2加10块
        int i=1/0;
        accountDaoMapper2.update(1100,2);
    }
}
