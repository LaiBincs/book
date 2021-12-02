package com.bin.utils;

import com.bin.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.utils
 * @ClassName: WebUtils
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/12 20:10
 */
public abstract class WebUtils {
    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            BeanUtils.populate(bean, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int parseInt(String strInt, int defaultValue) {
        if (strInt != null) {
            try {
                return Integer.parseInt(strInt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }
}
