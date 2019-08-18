package com.cnshop.member.mapper;

import com.cnshop.member.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * create by hl on 2019/8/18 21:35
 *
 * @descript
 */
@Repository
public interface UserMapper {

    @Insert("insert into cn_user values (null,#{mobile}, #{email}, #{password}, #{userName}, null, null, null, '1', null, null, null);")
    int register(UserEntity userEntity);


    @Select("SELECT * FROM meite_user WHERE MOBILE=#{mobile};")
    UserEntity existMobile(@Param("mobile") String mobile);
}
