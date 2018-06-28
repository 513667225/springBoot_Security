package com.bzy.zhda.modules.base.controller;


import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.codec.Base64;
import cn.hutool.log.Log;
import com.bzy.zhda.common.exception.CUDException;
import com.bzy.zhda.common.utils.LogUtils;
import com.bzy.zhda.common.utils.P;

/**
 * @Auther: lkw
 * @Date: 2018/6/25 17:00
 * @Description: BaseController
 */
public class BaseController {

    private static final Log log = LogUtils.getRuntimeLogger();

    /**
     * @Desc: 参数转为 Map
     * @Param: HttpServletRequest
     * @Return:  Map
     * @Auther: lkw
     * @Date: 2018/6/27 9:25
     */
    public P getParamsMap(HttpServletRequest request) throws Exception {
        P parameter = P.ok();
        Enumeration<String> names = request.getParameterNames();
        while(names.hasMoreElements()) {
            String paramName = names.nextElement();
            String[] paramValue = request.getParameterValues(paramName);
            if(paramValue == null){
                parameter.put(paramName, "");
            }else if(paramValue.length==1){
                parameter.put(paramName, Base64.decodeStr(paramValue[0]));
            }else{
                parameter.put(paramName, Base64.decodeStr(String.valueOf(paramValue)));
            }
        }
        return parameter;
    }

    /**
     * @Desc: 校验必传参数  空则抛出异常
     * @Param: HttpServletRequest mustParams
     * @Return:  boolean
     * @Auther: lkw
     * @Date: 2018/6/27 9:41
     */
	public P validateParams(HttpServletRequest request, String... mustParams)throws Exception{
        P parameter = getParamsMap(request);
        StringBuffer info = new StringBuffer();
		int num = 0;
		if(mustParams!=null && mustParams.length>0){
			for(String name:mustParams){
				if(!parameter.containsKey(name) || parameter.get(name)==null){
					if(num==0){
						info.append(name);
					}else{
						info.append(","+name);
					}
					num++;
				}
			}
		}
		if(num>0){
		    String msg = "以下" + num + "个参数不能为空[ " + info.toString() + " ]";
            log.warn(msg);
            throw new Exception(msg);
		}
		return parameter;
	}

	/**
	 * @Desc: 获取 ContextPath
	 * @Param: HttpServletRequest
	 * @Return:  ContextPath
	 * @Auther: lkw
	 * @Date: 2018/6/27 9:41
	 */
	public String getUrl(HttpServletRequest req) throws Exception {
		return req.getRequestURI().substring(req.getContextPath().length()+1);
	}

    /**
     * @Desc: 验证操作是否成功
     * @Param: 影响的行数
     * @Auther: lkw
     * @Date: 2018/6/27 9:41
     */
    public Integer validateIsSuccess(Integer count) throws CUDException {
        if (0 == count){
            throw new CUDException("影响的行数为[ " + count + " ],请检查标识是否存在");
        }
        return count;
    }
	
	
}
