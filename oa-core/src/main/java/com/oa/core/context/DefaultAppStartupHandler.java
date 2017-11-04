package com.oa.core.context;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 默认的服务器启动成功之后的动作
 * 如：加载配置、执行初始化操作
 *
 * Created by [张渊]
 * 2017/10/29 17:03
 */
public class DefaultAppStartupHandler implements AppStartup {

    private static Logger logger = LoggerFactory.getLogger(DefaultAppStartupHandler.class);

    public static boolean IS_RUNNING = false;

    // 创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行（池中所保存的线程数，即使线程是空闲的也包括在内）
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

    private static void regiestBeanUtilsCovert() {
        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class type, Object value) {
                return value;
            }
        }, java.util.Date.class);
    }

    @Override
    public void startup() {
        IS_RUNNING = true;

        regiestBeanUtilsCovert();

        // 表示给定单元粒度的时间段，表示指定分钟递增
        TimeUnit timeUnit = TimeUnit.SECONDS;
        if (AppContext.isDebugModel()) {
            timeUnit = TimeUnit.MINUTES;
        }

        // 线程
//        executor.scheduleAtFixedRate(new IdentitySyncTask(), 0, 5, timeUnit);
    }

    @Override
    public void destroy() {
        logger.info("开始注销已启动的后台服务...");
        IS_RUNNING = false;
        executor.shutdown();
    }
}
