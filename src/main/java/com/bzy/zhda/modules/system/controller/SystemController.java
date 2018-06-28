package com.bzy.zhda.modules.system.controller;

import cn.hutool.log.Log;
import com.bzy.zhda.common.utils.PropertiesUtils;
import com.bzy.zhda.common.utils.R;
import com.bzy.zhda.modules.base.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: lkw
 * @Date: 2018/6/25 17:00
 * @Description: SystemController
 */
@RestController
@RequestMapping("/system")
public class SystemController extends BaseController {


    @Autowired
    @Qualifier("systemLog")
    private Log log;

    /**
     * @remark: lkw created by time: 2018/6/25 16:58
     */
    @ApiOperation("系统设置")
    @DeleteMapping("/sys") R listMusic() throws Exception {
        log.info("system");
        String sys = PropertiesUtils.valueOfKey("syss");
        System.out.println(sys);
        return R.success();
    }



}
