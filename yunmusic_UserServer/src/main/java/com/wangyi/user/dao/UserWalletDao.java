package com.wangyi.user.dao;

import com.wangyi.entity.UserWallet;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserWalletDao {

    @Select("select * from userwallet where uid = #{uid}")
    UserWallet selectByUid(int uid);

    @Update("update userwallet set money = money + #{money} where uid = #{uid}")
    int updateMoney(@Param("uid") int uid, @Param("money") int money);
}
