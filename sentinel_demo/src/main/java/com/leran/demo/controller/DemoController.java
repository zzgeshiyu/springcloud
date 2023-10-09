package com.leran.demo.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSONObject;
import com.leran.demo.block.AllBlockHandler;
import com.leran.demo.fallback.AllFallbackHandler;
import com.leran.demo.feign.ProductFeignService;
import com.leran.demo.feign.StockFeignService;
import com.leran.demo.outmodel.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {

    @Autowired
    private StockFeignService stockFeignService;

    @Autowired(required = false)
    private ProductFeignService productFeignService;

    @GetMapping("/search")
    public String search() {
        stockFeignService.reduct();
        return JSONObject.toJSONString(productFeignService.getProduct("test"));
    }

    //进行sentinel流量控制
    @GetMapping("/get")
//    @SentinelResource("get")
    public String get() {
        Entry entry = null;
        try {
            //sentinel 针对资源进行限定  定义资源
            entry = SphU.entry("get");
            log.info("====get=====");
            return "get success";
        } catch (BlockException e) {
            // 处理被流控的逻辑
            System.out.println("blocked!");
            return "被流控了";
        }catch (Exception e){
            //若需要配置降级规则，需要通过这种方式业务记录异常
            Tracer.traceEntry(e, entry);
        } finally {
            if(entry!=null) {
                entry.exit();
            }
        }
        return "null";
    }

    /**
     * 使用@SentinelResource 改善接口中资源定义和被流控后的处理方法
     * 配置 bean - SentinelResourceAspect
     * value 定义资源
     * blockHandler 设置 流控降级后的处理方法， 必须是在同一个类中设置
     *  如果不想再同一个类中，需要设置 blockHandleClass ;
     * fallback     当接口出现异常，就可以交给fallback 指定的方法进行处理
     *
     * blockHandler 优先级 大于 fallback;
     *
     * exceptionsToIgnore 排除那些异常不用 fallback 处理
     *
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    @SentinelResource(value = "getUser",
            exceptionsToIgnore = {NullPointerException.class,},
            fallbackClass = AllFallbackHandler.class,
            fallback = "fallbackHandlerForGetUser",
            blockHandlerClass = AllBlockHandler.class,
            blockHandler = "blockHandlerForGetUser")
    public User getUser(@PathVariable("id") int id) {

        if (id%2 == 0) {
            int i = 1/0;
        }
        if (id%3 == 0) {
            throw new NullPointerException();
        }

        User user = new User();
        user.setId(id);
        user.setName("name1");
        user.setAge(50);
        return user;
    }

    /**
     * 使用@SentinelResource 改善接口中资源定义和被流控后的处理方法
     * 配置 bean - SentinelResourceAspect
     * value 定义资源
     * blockHandler 设置 流控降级后的处理方法， 必须是在同一个类中设置
     *  如果不想再同一个类中，需要设置 blockHandleClass ;
     * fallback     当接口出现异常，就可以交给fallback 指定的方法进行处理
     *
     * blockHandler 优先级 大于 fallback;
     *
     * exceptionsToIgnore 排除那些异常不用 fallback 处理
     *
     * @param id
     * @return
     */
    @GetMapping("/testDegradeRule/{id}")
    @SentinelResource(value = "testDegradeRule",
            entryType = EntryType.IN,
            blockHandlerClass = AllBlockHandler.class,
            blockHandler = "blockHandlerForTestDegradeRule")
    public User testDegradeRule(@PathVariable("id") int id) throws InterruptedException{

        if (id%2 == 0) {
            int i = 1/0;
        }
        if (id%3 == 0) {
            throw new RuntimeException("异常test");
        }

        User user = new User();
        user.setId(id);
        user.setName("testDegradeRule");
        user.setAge(503);
        return user;
    }

    /**
     * 1.  一定是public
     * 2.   返回值和原方法保持一致  ，参数也要一致
     * 3.   可以在参数后面添加 BlockException 可以区分是什么规则的处理方法
     * @param id
     * @param be
     * @return
     */
//    public User blockHandlerForGetUser(int id, BlockException be) {
//        be.printStackTrace();
//        User user = new User();
//        user.setId(id);
//        user.setName("getUser 方法被流控了");
//        return user;
//    }

    /**
     * spring 创建时自动初始化
     */
//    @PostConstruct
//    private static void initFlowRules(){
//        List<FlowRule> rules = new ArrayList<>();
//        FlowRule rule = new FlowRule();
//        rule.setResource("get");
//        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        // Set limit QPS to 20.
//        rule.setCount(1);
//        rules.add(rule);
//        FlowRuleManager.loadRules(rules);
//    }
}
