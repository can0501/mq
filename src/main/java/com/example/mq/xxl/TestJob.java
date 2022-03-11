package com.example.mq.xxl;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @author 钟金灿
 * @since 2022/3/1
 */
@Component
public class TestJob extends IJobHandler {

    @XxlJob("TestJob")
    @Override
    public void execute() throws Exception {
        String jobParam = XxlJobHelper.getJobParam();
//        System.out.println(XxlJobHelper.getShardTotal());
//        System.out.println(XxlJobHelper.getShardIndex());
//        XxlJobHelper.log("TestJob{}:total{},index{}", XxlJobHelper.getJobParam(), XxlJobHelper.getShardTotal(), XxlJobHelper.getShardIndex());
        System.out.println(String.format("TestJob%s:total%s,index%s", XxlJobHelper.getJobParam(), XxlJobHelper.getShardTotal(), XxlJobHelper.getShardIndex()));


        XxlJobHelper.handleSuccess(jobParam);
    }
}
