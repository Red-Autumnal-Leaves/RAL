package com.ral.auth.dao;

import com.ral.auth.model.entity.Module;
import com.ral.auth.model.entity.ModuleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_module
     *
     * @mbggenerated
     */
    int countByExample(ModuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_module
     *
     * @mbggenerated
     */
    int deleteByExample(ModuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_module
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_module
     *
     * @mbggenerated
     */
    int insert(Module record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_module
     *
     * @mbggenerated
     */
    int insertSelective(Module record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_module
     *
     * @mbggenerated
     */
    List<Module> selectByExample(ModuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_module
     *
     * @mbggenerated
     */
    Module selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_module
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Module record, @Param("example") ModuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_module
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Module record, @Param("example") ModuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_module
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Module record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_module
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Module record);


    List<Module> selectByByProjectIdAndRoleId(@Param("roleId") String roleId, @Param("projectId") String projectId);
}