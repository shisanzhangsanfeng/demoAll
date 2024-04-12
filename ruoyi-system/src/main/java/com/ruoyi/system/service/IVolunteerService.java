package com.ruoyi.system.service;

import com.ruoyi.system.domain.Volunteer;

import java.util.List;


/**
 * testService接口
 * 
 * @author ruoyi
 * @date 2024-03-20
 */
public interface IVolunteerService 
{
    /**
     * 查询test
     * 
     * @param vtId test主键
     * @return test
     */
    public Volunteer selectVolunteerByVtId(Long vtId);

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

    /**
     * 批量删除test
     * 
     * @param vtIds 需要删除的test主键集合
     * @return 结果
     */
    public int deleteVolunteerByVtIds(Long[] vtIds);

    /**
     * 删除test信息
     * 
     * @param vtId test主键
     * @return 结果
     */
    public int deleteVolunteerByVtId(Long vtId);


    /**
     * 导入
     */
    List<Volunteer> importVolunteer(List<Volunteer> nineSmallPlacesList, boolean updateSupport, String operName);

    /**
     *exportAll
    */
    int exportAll(List<Volunteer> volunteerList );
}
