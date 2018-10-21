package com.my.framework.context;

import com.my.framework.context.Constant.ConfigConstant;
import com.my.framework.exception.ConfigException;
import com.my.utils.classutils.ClassUtil;
import com.my.utils.fileutils.PropsUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.ConcurrentException;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 * 佛曰:
 * 写字楼里写字间，写字间里程序员；
 * 程序人员写程序，又拿程序换酒钱。
 * 酒醒只在网上坐，酒醉还来网下眠；
 * 酒醉酒醒日复日，网上网下年复年。
 * 但愿老死电脑间，不愿鞠躬老板前；
 * 奔驰宝马贵者趣，公交自行程序员。
 * 别人笑我忒疯癫，我笑自己命太贱；
 * 不见满街漂亮妹，哪个归得程序员？
 * ---------------------------
 * 项目名： MyFrameWork
 * 包名：   com.my.framework.context
 * 创建者:  linzhou
 * 创建时间:18/10/21
 * 描述:
 */
public class BaseContext {

    protected String[] scanfPackges;

    protected Properties properties ;

    protected Set<Class<?>> allClass ;

    public BaseContext() {
        properties = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);
        String packges = properties.getProperty(ConfigConstant.SCANF_PACKGES_KEY);
        if (StringUtils.isBlank(packges)) {
            throw new ConfigException("读取"+ConfigConstant.CONFIG_FILE+"文件异常！packge配置错误");
        }
        scanfPackges = packges.split(";");
        findAllClassForPackges();
    }

    public BaseContext(String... arg){
        scanfPackges = arg;
        findAllClassForPackges();
    }

    private void findAllClassForPackges() {
        Set<Class<?>> packgeClass = new HashSet<Class<?>>();
        for (String s : scanfPackges) {
            packgeClass.addAll(ClassUtil.getClassSet(s));
        }
        if (packgeClass.isEmpty()){
            throw new ConfigException("读取"+ ConfigConstant.CONFIG_FILE+"文件异常！packge配置错误");
        }
        allClass = packgeClass;
    }

    protected String[] getScanfPackges() {
        return scanfPackges;
    }

    protected void setScanfPackges(String[] scanfPackges) {
        this.scanfPackges = scanfPackges;
    }

    protected Properties getProperties() {
        return properties;
    }

    protected void setProperties(Properties properties) {
        this.properties = properties;
    }

    protected Set<Class<?>> getAllClass() {
        return allClass;
    }

    protected void setAllClass(Set<Class<?>> allClass) {
        this.allClass = allClass;
    }
}
