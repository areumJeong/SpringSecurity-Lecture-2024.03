package com.example.springSecurity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.springSecurity.entity.SecurityUser;

@Mapper
public interface SecurityUserDao {

	@Select("select * from securityUser where uid=#{uid}")
	SecurityUser getUserByUid(String uid);
	
	@Select("select * from securityUser where isDeleted=0 order by regDate desc"
			+ " limit #{count} offset #{offset}")
	List<SecurityUser> getSecurityUserList(int count, int offset);
	
	@Select("select count(uid) from securityUser where isDeleted=0")
	int getSecurityUserCount();
	
	@Insert("insert into securityUser values (#{uid}, #{pwd}, #{uname}, #{email},"
			+ " default, default, #{picture}, #{provider}, default)")
	void insertSecurityUser(SecurityUser securityUser);
	
	@Update("update SecurityUser set pwd=#{pwd}, #{uname}, #{email},"
			+ " picture=#{picture} where uid=#{uid}")
	void updateSecurityUser(SecurityUser securityUser);
	
	@Update("update SecurityUser set isDeleted=1 where uid=#{uid}")
	void deleteSecurityUser(String uid);
}
