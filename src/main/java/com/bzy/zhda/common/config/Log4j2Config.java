package com.bzy.zhda.common.config;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.bzy.zhda.common.enums.LogEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: lkw
 * @Date: 2018/6/25 20:33
 * @Description:
 */
@Configuration
public class Log4j2Config {

    /**
     * @remark: lkw created by time: 2018/6/25 21:30
     */
    @Bean("musicLog")
    public Log getMusicLog(){
        return LogFactory.get(LogEnum.MUSIC.getType());
    }

    /**
     * @remark: lkw created by time: 2018/6/25 21:36
     */
    @Bean("systemLog")
    public Log getSystemLog(){
        return LogFactory.get(LogEnum.SYSTEM.getType());
    }

    /**
     * @remark: lkw created by time: 2018/6/25 21:36
     */
    @Bean("runTimeLog")
    public Log getRunTimeLog(){
        return LogFactory.get(LogEnum.RUNTIME.getType());
    }

}
