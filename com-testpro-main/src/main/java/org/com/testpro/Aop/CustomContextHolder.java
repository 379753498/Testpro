package org.com.testpro.Aop;

import org.com.testpro.Enum.DataEnum;

public class CustomContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }

    public static String getCustomerType() {
        //当第一次获取时返回默认的数据链接
        if( contextHolder.get()==null)
        {
            contextHolder.set(DataEnum.DATA_SOURCE_15.getSpringDatasourceBeanName());
        }
        return contextHolder.get();
    }

    public static void clearCustomerType() {
        contextHolder.remove();
    }
}