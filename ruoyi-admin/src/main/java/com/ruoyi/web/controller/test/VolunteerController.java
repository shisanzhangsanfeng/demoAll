package com.ruoyi.web.controller.test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.Volunteer;
import com.ruoyi.system.service.IVolunteerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.PictureData;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * testController
 *
 * @author ruoyi
 * @date 2024-03-20
 */
@Api("志愿者管理")
@RestController
@RequestMapping("/test/volunteer")
public class VolunteerController extends BaseController {
    @Autowired
    private IVolunteerService volunteerService;

    /**
     * 查询test列表
     */

    @ApiOperation("志愿者查询")
    //@PreAuthorize("@ss.hasPermi('test:volunteer:list')")
    @GetMapping("/list")
    public TableDataInfo list(Volunteer volunteer) {
        startPage();
        List<Volunteer> list = volunteerService.selectVolunteerList(volunteer);
        return getDataTable(list);
    }



    /**
     * 获取test详细信息
     */
    @PreAuthorize("@ss.hasPermi('test:volunteer:query')")
    @GetMapping(value = "/{vtId}")
    public AjaxResult getInfo(@PathVariable("vtId") Long vtId) {
        return success(volunteerService.selectVolunteerByVtId(vtId));
    }

    /**
     * 新增test
     */
    @PreAuthorize("@ss.hasPermi('test:volunteer:add')")
    @Log(title = "test", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Volunteer volunteer) {
        return toAjax(volunteerService.insertVolunteer(volunteer));
    }

    /**
     * 修改test
     */
    @PreAuthorize("@ss.hasPermi('test:volunteer:edit')")
    @Log(title = "test", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Volunteer volunteer) {
        return toAjax(volunteerService.updateVolunteer(volunteer));
    }

    /**
     * 删除test
     */
    @PreAuthorize("@ss.hasPermi('test:volunteer:remove')")
    @Log(title = "test", businessType = BusinessType.DELETE)
    @DeleteMapping("/{vtIds}")
    public AjaxResult remove(@PathVariable Long[] vtIds) {
        return toAjax(volunteerService.deleteVolunteerByVtIds(vtIds));
    }

    @Log(title = "志愿者信息", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData( MultipartFile file, boolean updateSupport) throws Exception {
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("-----1------"+currentDateTime);
        ExcelUtil<Volunteer> util = new ExcelUtil<Volunteer>(Volunteer.class);

        List<Volunteer> volunteerList = util.importExcel(file.getInputStream());
        System.out.println("-----2------"+currentDateTime);

        String operName = SecurityUtils.getUsername();

        List<Volunteer> exportList = volunteerService.importVolunteer(volunteerList, updateSupport, operName);
        System.out.println("-----3------"+currentDateTime);
        return util.exportExcel(exportList, "志愿者导入结果");
        //return AjaxResult.success("志愿者导入结果");
    }

    /**
     * MyBatis Dynamic SQL 使用样例
     * @param file
     * @param updateSupport
     * @return
     * @throws Exception
     */

    @Log(title = "志愿者信息", businessType = BusinessType.IMPORT)
    @PostMapping("/importDataDynamicSQL")
    public AjaxResult importDataDynamicSQL( MultipartFile file, boolean updateSupport) throws Exception {
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("-----1------"+currentDateTime);
        ExcelUtil<Volunteer> util = new ExcelUtil<Volunteer>(Volunteer.class);

        List<Volunteer> volunteerList = util.importExcel(file.getInputStream());
        System.out.println("-----2------"+currentDateTime);

        String operName = SecurityUtils.getUsername();

        List<Volunteer> exportList = volunteerService.importVolunteer(volunteerList, updateSupport, operName);
        System.out.println("-----3------"+currentDateTime);
        return util.exportExcel(exportList, "志愿者导入结果");
        //return AjaxResult.success("志愿者导入结果");
    }

    @Log(title = "志愿者信息", businessType = BusinessType.IMPORT)
    @PostMapping("/importDataDynamicSQL01")
    public AjaxResult importDataDynamicSQL01( @RequestBody List<Volunteer> volunteerList) throws Exception {
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("-----1------"+currentDateTime);
        volunteerService.exportAll(volunteerList);





        //return util.exportExcel(exportList, "志愿者导入结果");
        return AjaxResult.success("志愿者导入结果");
    }


    @Log(title = "志愿者信息", businessType = BusinessType.IMPORT)
    @PostMapping("/exportAll")
    public AjaxResult exportAll(@RequestBody List<Volunteer> volunteerList ) throws Exception {
        volunteerService.exportAll(volunteerList);


        return AjaxResult.success("志愿者导入结果");
    }


    /**
     * 下载模板
     * @param response
     * @return
     */
    @PostMapping("/importTemplate")
    public AjaxResult importTemplate(HttpServletResponse response) {
        ExcelUtil<Volunteer> util = new ExcelUtil<Volunteer>(Volunteer.class);
        //System.out.println("-------------"+util.toString());
        //System.out.println(util.importTemplateExcel("123").toString());
        return util.importTemplateExcel(response,"志愿者");
    }
    /**
     * 导出test列表
     */
    @ApiOperation("导出test列表")
    @PreAuthorize("@ss.hasPermi('test:volunteer:export')")
    @Log(title = "test", businessType = BusinessType.EXPORT)
    @PostMapping("/exportVolunteer")
    public void export(HttpServletResponse response, Volunteer volunteer) {
        List<Volunteer> list = volunteerService.selectVolunteerList(volunteer);
        ExcelUtil<Volunteer> util = new ExcelUtil<Volunteer>(Volunteer.class);
        util.exportExcel(response, list, "Volunteer数据");
    }

}
