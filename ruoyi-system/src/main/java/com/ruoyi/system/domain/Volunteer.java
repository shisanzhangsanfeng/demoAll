package com.ruoyi.system.domain;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.poi.ss.usermodel.PictureData;

/**
 * test对象 volunteer
 * 
 * @author ruoyi
 * @date 2024-03-20
 */
public class Volunteer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 志愿者id */
    private Long vtId;

    /** 剩余总积分--- 废弃字段 */
    @Excel(name = "剩余总积分" )
    private Integer vtIntegralCount;

    /** 头像 */

    @Excel(name = "头像", cellType = Excel.ColumnType.IMAGE)
    private String vtAvatar;

    //@Excel(name = "vtAvatarUrl")
    private Map<String, PictureData>vtAvatarUrl ;
    /** 名字 */
    @Excel(name = "名字")
    private String vtName;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String vtCardno;

    /** 手机号 */
    @Excel(name = "手机号")
    private String vtPhone;

    /** 村庄id */
    @Excel(name = "村庄id")
    private Long vtVillage;

    /** 楼栋主键编号 */
    @Excel(name = "楼栋主键编号")
    private Long vtBuild;

    /** 参加日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "参加日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date vtJoinDate;

    /** 志愿者类型id */
    @Excel(name = "志愿者类型id")
    private Long vtType;

    /** 备注 */
    @Excel(name = "备注")
    private String vtRemark;

    /** 删除状态 0否  ；1是 */
    @Excel(name = "删除状态 0否  ；1是")
    private Long isDel;

    /** 创建者id */
    @Excel(name = "创建者id")
    private Long createId;

    /** 创建者 */
    @Excel(name = "创建者")
    private String createUser;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updateUser;

    /** 更新id */
    @Excel(name = "更新id")
    private Integer updateId;

    /** 现居住地址 */
    @Excel(name = "现居住地址")
    private String volAddress;

    public void setVtId(Long vtId) 
    {
        this.vtId = vtId;
    }

    public Long getVtId() 
    {
        return vtId;
    }

    public Integer getVtIntegralCount() {
        return vtIntegralCount;
    }

    public void setVtIntegralCount(Integer vtIntegralCount) {
        this.vtIntegralCount = vtIntegralCount;
    }

    public void setVtName(String vtName)
    {
        this.vtName = vtName;
    }

    public String getVtName() 
    {
        return vtName;
    }
    public void setVtCardno(String vtCardno) 
    {
        this.vtCardno = vtCardno;
    }

    public String getVtCardno() 
    {
        return vtCardno;
    }
    public void setVtPhone(String vtPhone) 
    {
        this.vtPhone = vtPhone;
    }

    public String getVtPhone() 
    {
        return vtPhone;
    }
    public void setVtVillage(Long vtVillage) 
    {
        this.vtVillage = vtVillage;
    }

    public Long getVtVillage() 
    {
        return vtVillage;
    }
    public void setVtBuild(Long vtBuild) 
    {
        this.vtBuild = vtBuild;
    }

    public Long getVtBuild() 
    {
        return vtBuild;
    }
    public void setVtJoinDate(Date vtJoinDate) 
    {
        this.vtJoinDate = vtJoinDate;
    }

    public Date getVtJoinDate() 
    {
        return vtJoinDate;
    }
    public void setVtType(Long vtType)
    {
        this.vtType = vtType;
    }

    public Long getVtType()
    {
        return vtType;
    }
    public void setVtRemark(String vtRemark) 
    {
        this.vtRemark = vtRemark;
    }

    public String getVtRemark() 
    {
        return vtRemark;
    }
    public void setIsDel(Long isDel) 
    {
        this.isDel = isDel;
    }

    public Long getIsDel() 
    {
        return isDel;
    }
    public void setCreateId(Long createId) 
    {
        this.createId = createId;
    }

    public Long getCreateId() 
    {
        return createId;
    }
    public void setCreateUser(String createUser) 
    {
        this.createUser = createUser;
    }

    public String getCreateUser() 
    {
        return createUser;
    }
    public void setUpdateUser(String updateUser) 
    {
        this.updateUser = updateUser;
    }

    public String getUpdateUser() 
    {
        return updateUser;
    }
    public void setUpdateId(Integer updateId) 
    {
        this.updateId = updateId;
    }

    public Integer getUpdateId() 
    {
        return updateId;
    }
    public void setVolAddress(String volAddress) 
    {
        this.volAddress = volAddress;
    }

    public String getVolAddress() 
    {
        return volAddress;
    }

    public String getVtAvatar() {
        return vtAvatar;
    }

    public void setVtAvatar(String vtAvatar) {
        this.vtAvatar = vtAvatar;
    }

    public Map<String, PictureData> getVtAvatarUrl() {
        return vtAvatarUrl;
    }

    public void setVtAvatarUrl(Map<String, PictureData> vtAvatarUrl) {
        this.vtAvatarUrl = vtAvatarUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("vtId", getVtId())
            .append("vtIntegralCount", getVtIntegralCount())
                .append("vtAvatar", getVtAvatar())
                .append("vtAvatarUrl", getVtAvatarUrl())
            .append("vtName", getVtName())
            .append("vtCardno", getVtCardno())
            .append("vtPhone", getVtPhone())
            .append("vtVillage", getVtVillage())
            .append("vtBuild", getVtBuild())
            .append("vtJoinDate", getVtJoinDate())
            .append("vtType", getVtType())
            .append("vtRemark", getVtRemark())
            .append("isDel", getIsDel())
            .append("createId", getCreateId())
            .append("createUser", getCreateUser())
            .append("createTime", getCreateTime())
            .append("updateUser", getUpdateUser())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .append("volAddress", getVolAddress())
            .toString();
    }
}
