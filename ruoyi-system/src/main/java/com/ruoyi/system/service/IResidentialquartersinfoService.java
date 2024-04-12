package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Residentialquartersinfo;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2024-03-21
 */
public interface IResidentialquartersinfoService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Residentialquartersinfo selectResidentialquartersinfoById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param residentialquartersinfo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Residentialquartersinfo> selectResidentialquartersinfoList(Residentialquartersinfo residentialquartersinfo);

    /**
     * 新增【请填写功能名称】
     * 
     * @param residentialquartersinfo 【请填写功能名称】
     * @return 结果
     */
    public int insertResidentialquartersinfo(Residentialquartersinfo residentialquartersinfo) throws Exception;

    /**
     * insertResidentialquartersinfoNotRollBackFor
     */
    public int insertResidentialquartersinfoNotRollBackFor(Residentialquartersinfo residentialquartersinfo) throws Exception;
    /**
     * 修改【请填写功能名称】
     * 
     * @param residentialquartersinfo 【请填写功能名称】
     * @return 结果
     */
    public int updateResidentialquartersinfo(Residentialquartersinfo residentialquartersinfo);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteResidentialquartersinfoByIds(Integer[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteResidentialquartersinfoById(Integer id);
}
