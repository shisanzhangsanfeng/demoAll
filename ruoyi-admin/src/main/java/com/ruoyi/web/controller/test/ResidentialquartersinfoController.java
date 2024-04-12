package com.ruoyi.web.controller.test;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.Residentialquartersinfo;
import com.ruoyi.system.service.IResidentialquartersinfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2024-03-21
 */
@RestController
@RequestMapping("/test/residentialquartersinfo/")
public class ResidentialquartersinfoController extends BaseController {
    @Autowired
    private IResidentialquartersinfoService residentialquartersinfoService;

    /**
     * 查询【请填写功能名称】列表
     */
    //@PreAuthorize("@ss.hasPermi('test:residentialquartersinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(Residentialquartersinfo residentialquartersinfo) {
        //startPage();
        List<Residentialquartersinfo> list = residentialquartersinfoService.selectResidentialquartersinfoList(residentialquartersinfo);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('test:residentialquartersinfo:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Residentialquartersinfo residentialquartersinfo) {
        List<Residentialquartersinfo> list = residentialquartersinfoService.selectResidentialquartersinfoList(residentialquartersinfo);
        ExcelUtil<Residentialquartersinfo> util = new ExcelUtil<Residentialquartersinfo>(Residentialquartersinfo.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('test:residentialquartersinfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id) {
        return success(residentialquartersinfoService.selectResidentialquartersinfoById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('test:residentialquartersinfo:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Residentialquartersinfo residentialquartersinfo) throws Exception {
        return toAjax(residentialquartersinfoService.insertResidentialquartersinfo(residentialquartersinfo));
    }


    /**
     * tokenObj={
     *       encryptedData: encryptedData,
     *         iv: iv,
     *         nickName: nickName,
     *         avatarUrl: avatarUrl
     *     }
     *
     * @param
     * @return
     * @throws Exception
     */
    //@PreAuthorize("@ss.hasPermi('test:residentialquartersinfo:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/addString")
    public AjaxResult addString(@Param("encryptedData") String encryptedData, @Param("iv") String iv,
                                @Param("nickName") String nickName, @Param("avatarUrl") String avatarUrl) throws Exception {
        Residentialquartersinfo residentialquartersinfo = new Residentialquartersinfo();
        residentialquartersinfo.setResidentialQuarters(encryptedData);
        System.out.println("==========" + encryptedData);
        return toAjax(residentialquartersinfoService.insertResidentialquartersinfo(residentialquartersinfo));
    }


    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/addJson")
    public AjaxResult addJson(@RequestBody TokenObj tokenObj) throws Exception {
        Residentialquartersinfo residentialquartersinfo = new Residentialquartersinfo();
        residentialquartersinfo.setResidentialQuarters(tokenObj.getEncryptedData() + tokenObj.getAvatarUrl() + tokenObj.getIv() + tokenObj.getNickName());
        //System.out.println("=========="+tokenObj.getEncryptedData());
        return toAjax(residentialquartersinfoService.insertResidentialquartersinfo(residentialquartersinfo));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('test:residentialquartersinfo:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Residentialquartersinfo residentialquartersinfo) {
        return toAjax(residentialquartersinfoService.updateResidentialquartersinfo(residentialquartersinfo));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('test:residentialquartersinfo:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids) {
        return toAjax(residentialquartersinfoService.deleteResidentialquartersinfoByIds(ids));
    }
}

class TokenObj {
    String encryptedData;
    String iv;
    String nickName;
    String avatarUrl;

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}