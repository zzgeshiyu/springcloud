# springcloud
## 2023.10.09
    学习sentinel 硬编码的方式
    并用docker 搭建了一个 sentinel 控制台; http://192.168.10.129:8858/#/dashboard
    复习docker 查看状态的命令  systemctl status docker
    docker search //异常有虚拟机日期和网络日期不一致导致查询失败
    docker images 
    docker run
    
## 2023.10.10
    学习sentinel 控制台引用方式
    发现单机使用  Ribbon 会有问题  https://blog.csdn.net/u013553309/article/details/107437653；
    docker start (image id) 启动 容器

## 2023.10.11
    排查无法使用nacos config 问题； 
    排查sentinel_demo 项目为何无法注册到 sentinel 控制台， 因为自己在bean中注入了 自定义的 限流规则导致的； 
    
如果要使用 控制台 管控 就不能使用代码硬编写方式限流
    
    @PostConstruct基本：
    @PostConstruct注解好多人以为是Spring提供的。其实是Java自己的注解。
    Java中该注解的说明：@PostConstruct该注解被用来修饰一个非静态的void（）方法。被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行，init（）方法之前执行。
    
    通常我们会是在Spring框架中使用到@PostConstruct注解 该注解的方法在整个Bean初始化中的执行顺序：
    
    Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的方法)

docker 学习

    docker update --restart = always/no images 开启容器自动开启或关闭
    

## 计划
### sentinel 控制台学习  -- 为完成