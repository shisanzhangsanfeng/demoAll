package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 residentialquartersinfo
 * 
 * @author ruoyi
 * @date 2024-03-21
 */
public class Residentialquartersinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 编号 */
    @Excel(name = "编号")
    private String number;

    /** 小区 */
    @Excel(name = "小区")
    private String residentialQuarters;

    /** 楼栋 */
    @Excel(name = "楼栋")
    private Integer buildingNo;

    /** 房间号 */
    @Excel(name = "房间号")
    private Integer roomNo;

    /** 淘洗的数据 */
    @Excel(name = "淘洗的数据")
    private String oldInFo;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setNumber(String number) 
    {
        this.number = number;
    }

    public String getNumber() 
    {
        return number;
    }
    public void setResidentialQuarters(String residentialQuarters) 
    {
        this.residentialQuarters = residentialQuarters;
    }

    public String getResidentialQuarters() 
    {
        return residentialQuarters;
    }
    public void setBuildingNo(Integer buildingNo) 
    {
        this.buildingNo = buildingNo;
    }

    public Integer getBuildingNo() 
    {
        return buildingNo;
    }
    public void setRoomNo(Integer roomNo) 
    {
        this.roomNo = roomNo;
    }

    public Integer getRoomNo() 
    {
        return roomNo;
    }
    public void setOldInFo(String oldInFo) 
    {
        this.oldInFo = oldInFo;
    }

    public String getOldInFo() 
    {
        return oldInFo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("number", getNumber())
            .append("residentialQuarters", getResidentialQuarters())
            .append("buildingNo", getBuildingNo())
            .append("roomNo", getRoomNo())
            .append("oldInFo", getOldInFo())
            .toString();
    }
}
