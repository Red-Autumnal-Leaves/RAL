package com.ral.auth.dao;

import com.ral.auth.model.entity.RoleProject;
import com.ral.auth.model.entity.RoleProjectExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleProjectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_role_project
     *
     * @mbggenerated
     */
    int countByExample(RoleProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_role_project
     *
     * @mbggenerated
     */
    int deleteByExample(RoleProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_role_project
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_role_project
     *
     * @mbggenerated
     */
    int insert(RoleProject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_role_project
     *
     * @mbggenerated
     */
    int insertSelective(RoleProject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_role_project
     *
     * @mbggenerated
     */
    List<RoleProject> selectByExample(RoleProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_role_project
     *
     * @mbggenerated
     */
    RoleProject selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_role_project
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") RoleProject record, @Param("example") RoleProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_role_project
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") RoleProject record, @Param("example") RoleProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_role_project
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RoleProject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_role_project
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RoleProject record);
}