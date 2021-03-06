package com.zjmzxfzhl.common.permission.provider;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.zjmzxfzhl.common.permission.wrapper.PermissionWrapper;
import com.zjmzxfzhl.common.util.CommonUtil;
import com.zjmzxfzhl.common.util.JacksonUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据规则数据源策略为系统提供时，应自定义实现类实现AbstractDataPermissionProvider的filter方法
 * 
 * @author 庄金明
 *
 */
@Slf4j
public abstract class AbstractDataPermissionProvider {

    /**
     * 传入参数为Json格式数据，
     * 
     * 若有特殊参数需要特殊处理，可以在实现类中重写该方法
     * 
     * @param providerParams
     */
    @SuppressWarnings("unchecked")
    public void setProviderParams(String providerParams) {
        if (CommonUtil.isEmptyAfterTrim(providerParams)) {
            return;
        }
        Map<String, Object> paramsMap = JacksonUtil.strToObj(providerParams, HashMap.class);
        if (paramsMap == null) {
            return;
        }
        for (String key : paramsMap.keySet()) {
            Object value = paramsMap.get(key);
            try {
                PropertyUtils.setSimpleProperty(this, key, value);
            } catch (Exception e) {
                log.info("注入[" + key + "],发生错误");
            }
        }
    }

    /**
     * 返回规则组
     * 
     * @param sessionObject
     * @return
     */
    public abstract PermissionWrapper wrap(PermissionWrapper permissionWrapper);
}
