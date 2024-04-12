package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Volunteer;
import com.ruoyi.system.mapper.VolunteerMapper;
import com.ruoyi.system.service.IVolunteerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * testService业务层处理
 *
 * @author ruoyi
 * @date 2024-03-20
 */
@Service
@DataSource(value = DataSourceType.MASTER)
public class VolunteerServiceImpl implements IVolunteerService {
    private static final Logger logger = LoggerFactory.getLogger(VolunteerServiceImpl.class);


    @Autowired(required = false)
    private VolunteerMapper volunteerMapper;

    /**
     * 查询test
     *
     * @param vtId test主键
     * @return test
     */
    @Override
    public Volunteer selectVolunteerByVtId(Long vtId) {
        return volunteerMapper.selectVolunteerByVtId(vtId);
    }

    /**
     * 查询test列表
     *
     * @param volunteer test
     * @return test
     */
    @Override
    public List<Volunteer> selectVolunteerList(Volunteer volunteer) {
        return volunteerMapper.selectVolunteerList(volunteer);
    }

    /**
     * 新增test
     *
     * @param volunteer test
     * @return 结果
     */
    @Override
    public int insertVolunteer(Volunteer volunteer) {
        volunteer.setCreateTime(DateUtils.getNowDate());
        return volunteerMapper.insertVolunteer(volunteer);
    }

    /**
     * 修改test
     *
     * @param volunteer test
     * @return 结果
     */
    @Override
    public int updateVolunteer(Volunteer volunteer) {
        volunteer.setUpdateTime(DateUtils.getNowDate());
        return volunteerMapper.updateVolunteer(volunteer);
    }

    /**
     * 批量删除test
     *
     * @param vtIds 需要删除的test主键
     * @return 结果
     */
    @Override
    public int deleteVolunteerByVtIds(Long[] vtIds) {
        return volunteerMapper.deleteVolunteerByVtIds(vtIds);
    }

    /**
     * 删除test信息
     *
     * @param vtId test主键
     * @return 结果
     */
    @Override
    public int deleteVolunteerByVtId(Long vtId) {
        return volunteerMapper.deleteVolunteerByVtId(vtId);
    }

    @Override
    public List<Volunteer> importVolunteer(List<Volunteer> volunteerList, boolean updateSupport, String operName) {

        /**
         * 先对list集合唯一id 进行查重，返回那些信息重复；
         *          for 循环  list 取出唯一id 存入集合；select  in() 返回id；
         *         依据业务需求淘洗list集合中的信息 ，根据id 使用list集合中的信息批量修改；
         *
         *
         */
        List<Long> vtIds = new ArrayList<>();
        for (Volunteer volunteer : volunteerList) {
            vtIds.add(volunteer.getVtId());
        }

        List<Long> vtIdsData = volunteerMapper.selectVolunteerByIds(vtIds);
        //需要送到数据库中批量更新的表
        if (vtIdsData.size() > 0) {
            List<Volunteer> vtUpdateList = new ArrayList<>();
            for (Volunteer vt : volunteerList) {
                int vtIdFormer = vt.getVtId().intValue();
                for (Long vtUpdData : vtIdsData) {
                    if (vtUpdData.intValue() == vtIdFormer) {
                        vtUpdateList.add(vt);
                    }
                }
            }
            int updateFlag = volunteerMapper.updateVolunteerList(vtUpdateList);
            if (updateFlag > 0) {
                logger.info("更新成功");
            } else {
                logger.error("更新失败");
            }

            //需要送到数据库中批量新增的表
            List<Volunteer> vtAddList = new ArrayList<>();
            for (Volunteer vt : volunteerList) {
                long vtIdAdd = vt.getVtId();

                for (Long vtIdUpdate : vtIdsData) {
                    if (vtIdAdd != vtIdUpdate) {
                        vtAddList.add(vt);
                    }
                }

            }
            int addFlag = volunteerMapper.addAllVolunteerList(vtAddList);

        } else {
            List<Volunteer> volunteerList01 = new ArrayList<>();
            Volunteer volunteer = new Volunteer();
            volunteer.setVtIntegralCount(14);
            Volunteer volunteer01 = new Volunteer();
            volunteer01.setVtIntegralCount(15);

            volunteerList01.add(volunteer);
            volunteerList01.add(volunteer01);


            int addFlag = volunteerMapper.addAllVolunteerList04(volunteerList01);
            //int addFlag = volunteerMapper.addAllVolunteerList(volunteerList);
        }


        return null;
    }

    @Override
    public int exportAll(List<Volunteer> volunteerList ) {


    /*    List<Volunteer> volunteerList01 = new ArrayList<>();
        Volunteer volunteer = new Volunteer();
        volunteer.setVtIntegralCount(14);
        volunteer.setVtName("qweq");
        Volunteer volunteer01 = new Volunteer();
        volunteer01.setVtIntegralCount(15);
        volunteer01.setVtName("qweq01");

        volunteerList01.add(volunteer);
        volunteerList01.add(volunteer01);*/

        //int addFlag = volunteerMapper.addAllVolunteerList(volunteerList);

       // int addFlag = volunteerMapper.addAllVolunteerList01(volunteerList);

        /**
         * 添加 ,jdbcType=INTEGER 不能解决插入语句中的列数量与提供的值的数量不匹配所致
         */
        //int addFlag = volunteerMapper.addAllVolunteerList02(volunteerList);

        int addFlag = volunteerMapper.addAllVolunteerList03(volunteerList);
        //int addFlag01 = volunteerMapper.addAllVolunteerList04(volunteerList);

        return addFlag;
    }


}
