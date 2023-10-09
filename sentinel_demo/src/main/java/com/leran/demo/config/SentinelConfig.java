package com.leran.demo.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SentinelConfig {

    /**
     * 流控限定规则
     */
    @Bean
    public void getFlowRuleManager() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("get");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(1);
        rules.add(rule);

        FlowRule rule1 = new FlowRule();
        rule1.setResource("getUser");
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule1.setCount(1);
        rules.add(rule1);
        FlowRuleManager.loadRules(rules);
    }

    /**
     * 降级规则
     */
    @Bean
    public void degradeRule() {
        //异常 降级规则
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        rule.setResource("testDegradeRule");
        //设置规则策略   1.慢调用比例 2.异常比例 3.异常数策略
        rule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        // 慢调用比例模式下 为慢调用临界RT （超出该值则为慢调用）
        //2.异常比例 3.异常数策略  为对于的阈值
        rule.setCount(2);//异常数
        // 触发熔断最小的请求数
        rule.setMinRequestAmount(4);

        // 统计时长  1分钟内 请求
        rule.setStatIntervalMs(60*1000);// 时间太短不好测

        /**
         * 一分钟内 执行 2次请求  出现了2次 异常； 就会触发熔断
         */

        /**
         * 一旦触发了熔断，再次请求就会调用熔断降级方法；
         * 10秒后，进入半开状态 ： 恢复接口请求调用， 如果第一次请求就异常
         * 再次熔断，就不会根据设置的熔断策略
         */
        //熔断持续时长： 单位 10秒；
        rule.setTimeWindow(10);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }

    /**
     * 注解支持的配置bean
     * @return
     */
    @Bean
    public SentinelResourceAspect getSentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
