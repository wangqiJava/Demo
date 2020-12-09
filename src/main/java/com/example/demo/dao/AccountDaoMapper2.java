package com.example.demo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDaoMapper2 {
    int update(@Param("money") double money, @Param("id") int  id);
}
