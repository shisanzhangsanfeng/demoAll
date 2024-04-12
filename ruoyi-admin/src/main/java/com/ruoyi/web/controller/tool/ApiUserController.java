package com.ruoyi.web.controller.tool;



import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.framework.web.service.SysLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *
 *后期添加代码
 * @author biaogan
 */
@Api("用户信息管理")
@RestController
@RequestMapping("/api/user")
public class ApiUserController extends BaseController {

    @Autowired
    private SysLoginService loginService;


    @ApiOperation("登录 account/password")
    @PostMapping("/login")
    public AjaxResult apiLogin(@RequestBody Map<String, String> user) {
        return loginService.apiLogin(user);
    }
}

