package com.felix.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName:BeanCopyUtils
 * Package:com.felix.utils
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/6 21:51
 * @Version 1.0
 */
public class BeanCopyUtils {
    private BeanCopyUtils() {

    }

    public static <V> V copyBean(Object source, Class<V> clazz) {
        //创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            //实现属性拷贝
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回结果
        return result;
    }
    public static <O,V> List<V> copyBeanList(List<O> list,Class<V> clazz){
        return list.stream().map(o -> copyBean(o, clazz)).collect(Collectors.toList());
    }
}
