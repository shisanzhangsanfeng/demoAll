package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Volunteer;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * testMapper接口
 *
 * @author ruoyi
 * @date 2024-03-20
 */
public interface VolunteerMapper {
    /**
     * 查询test
     *
     * @param vtId test主键
     * @return test
     */
    public Volunteer selectVolunteerByVtId(Long vtId);

    List<Long> selectVolunteerByIds(@Param("ids") List<Long> ids);

    List<Long> selectVolunteerByNotIds(@Param("ids") List<Long> ids);

    /**
     * 查询test列表
     *
     * @param volunteer test
     * @return test集合
     */
    public List<Volunteer> selectVolunteerList(Volunteer volunteer);

    /**
     * 新增test
     *
     * @param volunteer test
     * @return 结果
     */
    public int insertVolunteer(Volunteer volunteer);

    /**
     * 修改test
     *
     * @param volunteer test
     * @return 结果
     */
    public int updateVolunteer(Volunteer volunteer);

    public int updateVolunteerList(@Param("volunteers") List<Volunteer> volunteers);

    public int addAllVolunteerList(@Param("volunteers") List<Volunteer> volunteers);

    /**
     * addAllVolunteerList01
     */
    public int addAllVolunteerList01(@Param("volunteers") List<Volunteer> volunteers);

    /**
     * addAllVolunteerList02
     */

    public int addAllVolunteerList02(@Param("volunteers") List<Volunteer> volunteers);

    /**
     * addAllVolunteerList03
     */
    public int addAllVolunteerList03(@Param("volunteers") List<Volunteer> volunteers);

    public int addAllVolunteerList04(@Param("volunteers") List<Volunteer> volunteers);
    /**
     * 删除test
     *
     * @param vtId test主键
     * @return 结果
     */
    public int deleteVolunteerByVtId(Long vtId);

    /**
     * 批量删除test
     *
     * @param vtIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVolunteerByVtIds(Long[] vtIds);
}
