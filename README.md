### 义码当仙之Spring Boot

> [相关软件安装](https://github.com/ymdx0610/ymdx-software-installation/wiki)  

> 01.父级依赖

```
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-parent</artifactId>
    <version>2.2.4.RELEASE</version>
</parent>
```
    
> 02.使用Spring和SpringMVC

```
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

> 03.配置maven打包插件，使用JDK1.8版本

```
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
    </plugins>
</build>
```

> 04.启用自动配置，关闭对指定jar包的自动配置
  
@EnableAutoConfiguration(exclude={RedisAutoConfiguration.class})

> 05.自定义banner

在项目类路径，一般在resources路径下创建banner.txt文件，内容自定义
    
> 06.全局自动配置

在resources路径下创建application.properties或application.yml文件
server.port=8088
server.servlet.context-path=/api
  
```  
server:
port: 8088
servlet:
  context-path: /api
```

> 07.获取自定义属性

- @Value("$(属性名)")  
- 类上添加@ConfigurationProperties(prefix="属性前缀")，类中添加属性名定义，并且必须提供set/get方法，启动类中添加注解@EnableConfigurationProperties({xx.class}) // 可选项
  
> 08.Profile配置
  
是针对不同的环境对不同的配置提供支持的，全局Profile配置使用application-*.properties
(application-dev.properties,application-test.properties,application-prod.properties)
通过在application.properties中设置spring.profiles.active=prod来指定活动的Profile
  
> 09.自动配置原理

SpringApplication.run -> getSpringFactoriesInstances -> loadFactoryNames(默认读取META-INF/spring.factories文件中的配置) -> createSpringFactoriesInstances
  
> 10.创建父工程

```
<dependencyManagement>
  <dependencies>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-dependencies</artifactId>
          <version>2.2.4.RELEASE</version>
          <type>pom</type>
          <scope>import</scope>
      </dependency>
  </dependencies>
</dependencyManagement>
```

> 11.整合测试

```
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-test</artifactId>
   <scope>test</scope>
</dependency>

<dependency>
   <groupId>junit</groupId>
   <artifactId>junit</artifactId>
   <scope>test</scope>
</dependency>
```
  
```
@SpringBootTest(classes = SpringController.class) // 指定要测试的类
@RunWith(SpringJUnit4ClassRunner.class) // 指定谁来承担测试
@WebAppConfiguration // web应用配置
```
  
> 12.SpringBootApplication注解与RestController注解

```
//@EnableAutoConfiguration
//@ComponentScan("com.ymdx.web")
//@Configuration
// 默认扫描当前包及其子包
@SpringBootApplication(scanBasePackages = {"com.ymdx.web"}) // 组合注解，作用等价于上述三个注解
```

```java
// 如果每一个方法都返回restful内容，则可以用使用RestController注解
@RestController
public class MvcController {

    @RequestMapping("/show")
    // 返回Restful内容，不使用@RestController注解，而使用@Controller注解则为跳转
//    @ResponseBody
    public String show() {
        return "show";
    }

    // 支持Restful风格
    @RequestMapping("/info/{msg}")
    public String info(@PathVariable String msg) {
        return "show " + msg;
    }

}
```

> 13.日志管理

SpringBoot使用的默认日志框架为Logback，并用INFO级别输出到控制台：  
日志输出内容元素具体如下：  
- 时间日期：精准到毫秒  
- 日志级别：ERROR,WARN,INFO,DEBUG or TRACE  
- 进程ID  
- 分隔符：--- 标识实际日志的开始  
- 线程名：方括号括起来（可能会截断控制台输出）  
- Logger名：通常使用源代码的类名  
- 日志内容  

日志依赖：该依赖内容就是Spring Boot默认的日志框架logback  

```
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-logging</artifactId>
</dependency>
```

但实际开发中不需要直接添加该依赖  
SpringBoot的日志级别有7个：  
TRACE,DEBUG,INFO,WARN,ERROR,FATAL,OFF  
日志级别从低到高：TRACE < DEBUG < INFO < WARN < ERROR < FATAL < OFF  
如果设置为WARN，则低于WARN的信息都不会输出  
Spring Boot中默认配置ERROR、WARN和INFO级别的日志输出到控制台  

例如：  
```
#root日志以WARN级别输出  
logging.level.root=WARN（让日志只输出warn及以上级别的信息）  
#springframework.web日志以debug级别输出  
logging.level.org.springframework.web=DEBUG  
#hibernate日志以ERROR级别输出  
logging.level.org.hibernate=ERROR  
```

默认情况下，Spring Boot将日志输出到控制台，不会写到日志文件。如果要编写除控制台输出之外的日志文件，则需要在application.properties中设置logging.file或logging.path属性。
logging.file，设置文件，可以是绝对路径，也可以是相对路径。如：logging.file=log/my.log（相对）或者/log/my.log（绝对）  
logging.path，设置目录，会在该目录下创建spring.log文件，并写入日志内容。如：logging.path=/var/log  
如果只配置logging.file，会在项目的当前路径下生成一个xxx.log日志文件。如果只配置logging.path，在/var/log文件夹生成一个日志文件为spring.log  
注：二者不能同时使用，如果同时使用，则只有logging.file生效。  
默认情况下，日志文件的大小达到10M时会切分一次，产生新的日志文件，默认级别为：ERROR、WARN、INFO  

```
#配置日志
logging.level.root=WARN
logging.level.org.springframework.web=DEBUG
logging.file=/data/applogs/springboot/info.log
logging.pattern.console=%d{yyyy/MM/dd-HH:mm:ss} [%thread] %-5level %logger - %msg%n
logging.pattern.file=%d{yyyy/MM/dd-HH:mm} [%thread] %-5level %logger - %msg%n
```

> 14、自定义日志配置

通过系统属性和传统的Spring Boot外部配置文件依然可以很好的支持日志控制和管理。根据不同的日志系统，可以按照如下规则组织配置文件名，就能被正确加载：
Logback:logback-spring.xml,logback-spring.groovy,logback.xml,logback.groovy  
Log4j:log4j-spring.properties,log4j-spring.xml,log4j.properties,log4j.xml  
Log4j2:log4j2-spring.xml,log4j2.xml  
JDK(Java Util Logging):logging.properties  

Spring Boot官方推荐有限使用带有-spring的文件名作为日志配置（如使用logback-spring.xml，而不是logback.xml），命名为logback-spring.xml的日志配置文件，spring boot可以为它添加一些spring boot特有的配置项。
如果想完全掌控日志配置，但又不想用logback.xml作为Logback配置的名字，可以通过logging.config属性指定自定义的名字：logging.config=classpath:logging-config.xml。
虽然一般不需要改变配置文件的名字，但是如果想针对不同运行时Profile使用不同的日志配置，这个功能会很有用。  

```
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true"  scanPeriod="60 seconds" debug="false">
  <!-- 每个logger都关联到logger上下文，默认上下文名称为"default"。但可以设置成其它名字，用于区分不同应用程序的记录。 -->
  <contextName>logback</contextName>
  <!-- 日志输出格式 -->
  <!-- %d{HH:mm:ss.SSS} %contextName [%thread] %-5level %logger{36} - %msg%n -->
  <property name="PATTERN" value="%-12(%d{yyyy-MM-dd HH:mm:ss.SSS}) |-%-5level [%thread] %c [%L] -| %msg%n" />
  <!-- 文件路径 -->
  <property name="LOG.PATH" value="/data/applogs/springboot/info.log" />

  <!-- 输出到控制台 -->
  <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
      <!-- 过滤掉ERROR级别以下的日志信息 -->
      <!--<filter class="ch.qos.logback.classic.filter.ThresholdFilter">
          <level>ERROR</level>
      </filter>-->
      <encoder>
          <pattern>${PATTERN}</pattern>
      </encoder>
  </appender>

  <!-- 输出到文件 -->
  <appender name="file" class="ch.qos.logback.core.rolling.RollingFileAppender">
      <file>${LOG.PATH}</file>
      <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
          <fileNamePattern>logback.%d{yyyy-MM-dd}.%i.log</fileNamePattern>
          <!-- 每个日志文件最大10MB, 保留30天的日志文件, 但是最多总文件大小为 5GB -->
          <maxFileSize>10MB</maxFileSize>
          <maxHistory>30</maxHistory>
          <totalSizeCap>5GB</totalSizeCap>
      </rollingPolicy>
      <encoder>
          <pattern>${PATTERN}</pattern>
      </encoder>
  </appender>

  <root level="info">
      <appender-ref ref="console"/>
      <appender-ref ref="file"/>
  </root>

  <!-- logger的第一种使用方式：logback为java中的包 -->
  <logger name="com.ymdx.web" />

  <!-- logger的第二种使用方式：additivity是否向上级logger传递打印信息，默认为true -->
  <logger name="com.ymdx.web.SpringController" level="WARN" additivity="false">
      <appender-ref ref="console"/>
  </logger>

</configuration>
```

根节点\<configuration>包含的属性：
- scan:当此属性设置为true时，配置文件如果发生改变，将会被重新加载，默认值为true
- scanPeriod:设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。当scan为true时，此属性生效。默认的时间间隔为1分钟。
- debug:当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。
- \<contextname>:每个logger都关联到logger上下文，默认上下文名称为"default"。但可以设置成其它名字，用于区分不同应用程序的记录。
一旦设置，不能修改，可以通过%contextName来打印日志上下文名称。
- \<property>:用来定义变量值的标签，有两个属性，name和value；其中name的值是变量的名称，value的值是变量定义的值。通过定义的值会被插入到logger上下文中。
定义变量后，通过“${}”来使用变量。
- \<appender>:用来格式化日志输出节点，有两个属性name和class，class用来指定哪种输出策略，常用的就是控制台输出策略和文件输出策略。
ThresholdFilter为系统定义的拦截器，例如我们用ThresholdFilter来过滤掉ERROR级别以下的日志不输出到文件中。如果不用需要注释掉，否则控制台会发现没有日志输出。
- RollingFileAppender用于切分文件日志，属性\<rollingPolicy>的子属性说明如下：
\<fileNamePattern>logback.%d{yyyy-MM-dd}.%i.log</fileNamePattern>:定义了日志的切分方式--把每一天的日志归档到一个文件中。
\<maxFileSize>10MB</maxFileSize>:表示每个日志文件最大10MB
\<maxHistory>30</maxHistory>:表示只保留最近30天的日志，以上肢日志填满整个磁盘空间。同理，可以使用%d{yyyy-MM-dd HH-mm}来定义精确到分的日志切分方式。
\<totalSizeCap>5GB</totalSizeCap>:用来指定日志文件的上限大小，例如设置为5GB的话，那么到了这个值，就会删除旧的日志。
- \<root>节点时必选节点，用来指定最基础的日志输出级别，只有一个level属性。
- \<logger>用来设置某一个包或者具体的某一个类的日志打印级别以及指定\<appender>。\<logger>有一个name属性，一个可选的level和一个可选的addtivity属性。
name:用来指定受此logger约束的某一个包或者具体的某一个类。
level:用来设置打印级别，大小写无关：TRACE,DEBUG,INFO,WARN,ERROR,FATAL,OFF,ALL，还有一个特殊值INHERITED或者同义词NULL，代表强制执行上级的级别。如果未
设置此属性，那么当前logger将会继承上级的级别。
additivity:是否向上级logger传递打印信息，默认为true

> 15.多环境日志输出

根据不同环境（prod:生产环境，test:测试环境，dev:开发环境）来定义不同的日志输出，在logback-spring.xml中使用springProfile节点来定义，方法如下：  
```
<!-- 测试环境+开发环境 -->
<springProfile name="test,dev">
  <logger name="com.ymdx.web" level="INFO" />
</springProfile>

<!-- 生产环境 -->
<springProfile name="prod">
  <logger name="com.ymdx.web" level="ERROR" />
</springProfile>
```
在application.properties文件中指明使用哪一种：spring.profiles.active=prod  

> 16.使用log4j日志管理

1.修改pom.xml文件，过滤掉自带的spring-boot-starter-logging，然后添加spring-boot-starter-log4j依赖包。
```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter</artifactId>
  <exclusions>
      <exclusion>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-logging</artifactId>
      </exclusion>
  </exclusions>
</dependency>

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-log4j</artifactId>
  <version>1.3.8.RELEASE</version>
</dependency>
```

2.在resources目录下新建log4j.properties配置文件，配置console,debug,info,error四种输出格式。
```
#log4j.rootLogger=日志级别, appender1, appender2, …
log4j.rootLogger=INFO,console,debug,info,error

log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=%d{yyyy-MM-dd-HH-mm} [%t] [%c] [%p] - %m%n

log4j.logger.debug=debug
log4j.appender.debug=org.apache.log4j.DailyRollingFileAppender
log4j.appender.debug.layout=org.apache.log4j.PatternLayout
log4j.appender.debug.layout.ConversionPattern=%d{yyyy-MM-dd-HH-mm} [%t] [%c] [%p] - %m%n
log4j.appender.debug.datePattern='.'yyyy-MM-dd
log4j.appender.debug.Threshold=debug
log4j.appender.debug.append=true
log4j.appender.debug.File=springboot-log4j/api_services_debug.log

log4j.logger.info=info
log4j.appender.info=org.apache.log4j.DailyRollingFileAppender
log4j.appender.info.layout=org.apache.log4j.PatternLayout
log4j.appender.info.layout.ConversionPattern=%d{yyyy-MM-dd-HH-mm} [%t] [%c] [%p] - %m%n
log4j.appender.info.datePattern='.'yyyy-MM-dd
log4j.appender.info.Threshold=info
log4j.appender.info.append=true
log4j.appender.info.File=springboot-log4j/api_services_info.log

log4j.logger.error=error
log4j.appender.error=org.apache.log4j.DailyRollingFileAppender
log4j.appender.error.layout=org.apache.log4j.PatternLayout
log4j.appender.error.layout.ConversionPattern=%d{yyyy-MM-dd-HH-mm} [%t] [%c] [%p] - %m%n
log4j.appender.error.datePattern='.'yyyy-MM-dd
log4j.appender.error.Threshold=error
log4j.appender.error.append=true
log4j.appender.error.File=springboot-log4j/api_services_error.log
```

> 17.配置工程为开发模式（未生效，待探究）

```
<!-- 加入以下依赖，代码做了修改，不用重新运行 -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>springloaded</artifactId>
    <version>1.2.8.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
</dependency>
```

如果使用IntelliJ Idea作为开发工具，还需要做如下设置：  
- Preferences... -> Build,Exception,Deployment -> Compiler -> Build project automatically -> 勾选  
- Help -> Find Action -> compiler.automake.allow.when.app.running -> 勾选  

> 18.Spring Boot的Web开发

Spring Boot提供了spring-boot-starter-web为Web开发予以支持，spring-boot-starter-web提供了嵌入的Tomcat以及SpringMvc的依赖，
Web相关的自动配置存储在spring-boot-autoconfigure.jar的org.springframework.boot.autoconfigure.web下。  
  
> 19.访问静态资源

在Spring Boot中加载静态资源和在普通的web应用中不太一样。默认情况下，Spring Boot从classpath的/static,/public或/META-INF/resources文件夹或从ServletContext根目录提供静态内容  
```
#设定警惕啊文件路径，js,css,image等
spring.resources.static-locations=classpath:/static/,classpath:/public/
```
  
> 20.自定义消息转换器
  
只需要在类中添加消息转换器的@Bean，就会被Spring Boot自动加入导容器中。  
```
    // Spring Boot默认配置了消息转换器
    // 定义消息转换器
//    @Bean
//    public StringHttpMessageConverter stringHttpMessageConverter(){
//        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//        return converter;
//    }
```

> 21.使用FastJson解析Json数据：
  
SpringBoot默认配置的是Jackson。
使用FastJson解析Json数据：
```
<!-- fastjson的依赖 -->
<dependency>
  <groupId>com.alibaba</groupId>
  <artifactId>fastjson</artifactId>
  <version>1.2.62</version>
</dependency>
```

配置FastJson有两种方式：
- 第一种：让启动类继承WebMvcConfigurationSupport
```
@SpringBootApplication
public class App extends WebMvcConfigurationSupport {

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 创建FastJson的消息转换器
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        supportedMediaTypes.add(MediaType.APPLICATION_PDF);
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);
        converter.setSupportedMediaTypes(supportedMediaTypes);

        // 创建FastJson的配置对象
        FastJsonConfig config = new FastJsonConfig();
        // 对Json数据进行格式化
        config.setSerializerFeatures(SerializerFeature.PrettyFormat);

        converter.setFastJsonConfig(config);
        converters.add(converter);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
```

乱码解决：把Spring Boot的编码设置为utf-8这个功能开启，全局配置文件中添加如下配置：  
```
# 修改默认字符集为UTF-8
spring.http.encoding.force=true
```

对象日期属性格式化注解：
// @JSONField(format = "yyyy-MM-dd")  
@JsonFormat(pattern = "yyyy-MM-dd")  

- 第二种：@Bean注入
```
@Bean
public HttpMessageConverters fastJsonMessageConverter(){
  // 创建FastJson的消息转换器
  FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
  // 创建FastJson的配置对象
  FastJsonConfig config = new FastJsonConfig();
  // 对Json数据进行格式化
  config.setSerializerFeatures(SerializerFeature.PrettyFormat);

  converter.setFastJsonConfig(config);

  HttpMessageConverter<?> converter1 = converter;

  return new HttpMessageConverters(converter1);
}
```
  
> 22.自定义拦截器
  
有些时候需要自己配置SpringMVC而不是采用默认，比如增加一个拦截器，这个时候就得通过继承WebMvcConfigurationSupport，然后重写父类中的方法进行扩展。  
```
@Configuration // 声明这是一个配置类
public class MyInterceptor extends WebMvcConfigurationSupport {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
      HandlerInterceptor interceptor = new HandlerInterceptor() {
          @Override
          public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
              System.out.println("自定义拦截器...");
              return true;
          }

          @Override
          public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

          }

          @Override
          public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

          }
      };
      // 拦截器注册，/** 拦截所有请求路径
      registry.addInterceptor(interceptor).addPathPatterns("/**");
  }

}
```

> 23.定义全局异常处理器

创建一个全局异常处理类，如下：
```
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public Map<String, Object> handleException(Exception exception){
      Map<String, Object> resultMap = new HashMap<>();
      resultMap.put("errorCode", 500);
      resultMap.put("errorMsg", exception.toString());
      return resultMap;
  }

}
```
  
> 24.异步调用

在项目中，当访问其它接口较慢活着做耗时任务时，不想程序一直卡在耗时任务上，想程序能够并行执行，可以使用多线程来并行的处理任务，Spring Boot提供了异步处理方式@Async  

- Srping Boot启动入库类添加@EnableAsync // 开启异步调用

- Service
```
@Service
public class AsyncServiceImpl implements AsyncService {
    private static Random random = new Random();

    @Async
    @Override
    public Future<String> doTask1() throws Exception {
        System.out.println("任务一开始");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("任务一结束，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务一结束");
    }

    @Async
    @Override
    public Future<String> doTask2() throws Exception {
        System.out.println("任务二开始");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("任务二结束，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务二结束");
    }

    @Async
    @Override
    public Future<String> doTask3() throws Exception {
        System.out.println("任务三开始");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("任务三结束，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务三结束");
    }

}
```
- Controller
```java
@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @RequestMapping("/async")
    public String asyncTest() throws Exception {

        long start = System.currentTimeMillis();

        Future<String> task1 = asyncService.doTask1();
        Future<String> task2 = asyncService.doTask2();
        Future<String> task3 = asyncService.doTask3();

        while (true) {
            if (task1.isDone() && task2.isDone() && task3.isDone()) {
                break;
            }
            Thread.sleep(1000);
        }

        long end = System.currentTimeMillis();

        return "全部执行完成，总耗时" + (end - start) + "毫秒";
    }

}
```
  
> 25.SpringBoot整合Jsp

Spring Boot官方不推荐使用jsp，因为jsp相对于一些模版引擎，性能都比较低，官方推荐使用thymeleaf。

- pom.xml中添加如下依赖：
```
<dependencies>
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
  </dependency>

  <!-- springboot整合jsp，需要是war工程，另外需要依赖如下两个包 -->
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-tomcat</artifactId>
  </dependency>

  <dependency>
      <groupId>org.apache.tomcat.embed</groupId>
      <artifactId>tomcat-embed-jasper</artifactId>
  </dependency>
</dependencies>

<!-- maven项目packing为war包类型时，必须要加这个插件 -->
<build>
  <plugins>
      <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-war-plugin</artifactId>
          <version>2.6</version>
          <configuration>
              <failOnMissingWebXml>false</failOnMissingWebXml>
          </configuration>
      </plugin>
  </plugins>
</build>
```
- 全局文件中配置:
```
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
```

- 如果用idea作为开发工具，则需要工程运行项目目录
Edit Configurations... -> 启动类 -> Configuration -> Environment -> Working directory -> $MODULE_WORKING_DIR$  

> 26.SpringBoot整合Freemarker

- 依赖
```
<!-- springboot不建议使用jsp，使用模版引擎，比如thymeleaf,velocity,freemarker -->
<!-- 整合freemarker需要添加如下依赖-->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>
```
- 全局配置
```
spring.freemarker.allow-request-override=false
spring.freemarker.cache=true
spring.freemarker.check-template-location=true
spring.freemarker.charset=UTF-8
spring.freemarker.content-type=text/html
spring.freemarker.expose-request-attributes=false
spring.freemarker.expose-session-attributes=false
spring.freemarker.expose-spring-macro-helpers=false
spring.freemarker.suffix=.ftl
spring.freemarker.template-loader-path=classpath:/templates
```

> 27.SpringBoot整合Thymeleaf

- 依赖
```
<!-- springboot不建议使用jsp，使用模版引擎，比如thymeleaf,velocity,freemarker -->
<!-- 整合thymeleaf需要添加如下依赖-->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```
- 全局配置
```
# 关闭thymeleaf缓存，开发时使用，否则没有实时画面
spring.thymeleaf.cache=false
# 检查模版是否存在，然后再呈现
spring.thymeleaf.check-template-location=true
# Content-Type值
spring.thymeleaf.servlet.content-type=text/html
# 启用MVC Thymeleaf视图解析
spring.thymeleaf.enabled=true
# 应该从解决方案中排除的视图名称的逗号分隔列表
spring.thymeleaf.excluded-vew-names=
# 模版编码，非严格的html
spring.thymeleaf.mode=LEGACYHTML5
# 在构建URL时预先查看名称的前缀
spring.thymeleaf.prefix=classpath:/templates/
# 在构建URL时附加查看名称的后缀
spring.thymeleaf.suffix=.html
# 链中模版解析器的顺序
#spring.thymeleaf.template-resolver-order=
# 可以解析的视图名称的头号分隔列表
#spring.thymeleaf.view-names=
```
  
> 28.SpringBoot整合QuartZ
  
```
@Component
public class MyJob {

  // 每隔1秒执行一次任务
  @Scheduled(fixedRate = 1000)
  public void run(){
      System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
  }

}
```

在启动类上添加注解：@EnableScheduling  
  
> 29.SpringBoot整合JdbcTemplate

- 依赖
```
<!-- jdbc的依赖 -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>

<!-- mysql的依赖 -->
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
</dependency>
```

- 连接数据库的配置
```
# mysql8 -> spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/db1?useUnicode=true&characterEncoding=UTF8&autoReconnect=true&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull
spring.datasource.username=root
spring.datasource.password=123456
```

> 30.SpringBoot整合Mybatis（xml方式）

- pom.xml
```
<dependencies>
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
  </dependency>

  <!-- mybatis的依赖 -->
  <dependency>
      <groupId>org.mybatis.spring.boot</groupId>
      <artifactId>mybatis-spring-boot-starter</artifactId>
      <version>2.0.1</version>
  </dependency>

  <!-- mysql的依赖 -->
  <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
  </dependency>

  <!-- alibaba的druid数据库连接池 -->
  <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid-spring-boot-starter</artifactId>
      <version>1.1.14</version>
  </dependency>

  <!-- 分页插件 -->
  <dependency>
      <groupId>com.github.pagehelper</groupId>
      <artifactId>pagehelper-spring-boot-starter</artifactId>
      <version>1.2.5</version>
  </dependency>

  <!-- @Param注解在如下包中 -->
  <dependency>
      <groupId>org.apache.ibatis</groupId>
      <artifactId>ibatis-core</artifactId>
      <version>3.0</version>
  </dependency>
  
  <!-- fastjson的依赖 -->
  <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>fastjson</artifactId>
      <version>1.2.62</version>
  </dependency>
</dependencies>

<build>
  <plugins>
      <plugin>
          <groupId>org.mybatis.generator</groupId>
          <artifactId>mybatis-generator-maven-plugin</artifactId>
          <version>1.3.6</version>
          <configuration>
              <verbose>true</verbose>
              <overwrite>true</overwrite>
              <configurationFile>src/main/resources/mybatis/generator-config.xml</configurationFile>
          </configuration>
          <dependencies>
              <!-- MyBatis Generator及工具依赖 -->
              <dependency>
                  <groupId>org.mybatis.generator</groupId>
                  <artifactId>mybatis-generator-core</artifactId>
                  <version>1.3.7</version>
              </dependency>
              <dependency>
                  <groupId>tk.mybatis</groupId>
                  <artifactId>mapper</artifactId>
                  <version>3.4.6</version>
              </dependency>
          </dependencies>
      </plugin>
  </plugins>
</build>
```
- mybatis-config.xml
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

</configuration>
```
- mybatis-generator.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
      PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
      "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
  <!-- 配置mysql 驱动jar包路径.用了绝对路径 -->
  <classPathEntry location="/usr/local/maven/repository/mysql/mysql-connector-java/8.0.16/mysql-connector-java-8.0.16.jar" />

  <context id="ymdx_mysql_tables" targetRuntime="MyBatis3">

      <!-- 指定生成的java文件的编码，没有指定生成到项目时中文可能会乱码 -->
      <property name="javaFileEncoding" value="UTF-8" />
      <property name="suppressTypeWarnings" value="true" />

      <!-- 插件
      <plugin type="org.mybatis.generator.plugins.SerializablePlugin" />
      <plugin type="org.mybatis.generator.plugins.CaseInsensitiveLikePlugin" />
      <plugin type="org.mybatis.generator.plugins.RowBoundsPlugin" />
      <plugin type="org.mybatis.generator.plugins.ToStringPlugin" />
      <plugin type="org.mybatis.generator.plugins.CachePlugin" />

      <plugin type="org.mybatis.generator.plugins.RenameExampleClassPlugin">
          <property name="searchString" value="Example$" />
          <property name="replaceString" value="Criteria" />
      </plugin>
      -->

      <!-- 覆盖生成XML文件 -->
      <plugin type="org.mybatis.generator.plugins.UnmergeableXmlMappersPlugin" />

      <!-- 防止生成的代码中有很多注释，加入下面的配置控制 -->
      <commentGenerator>
          <property name="suppressAllComments" value="true" />
          <property name="suppressDate" value="true" />
      </commentGenerator>

      <!-- 数据库连接 -->
      <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
                      connectionURL="jdbc:mysql://localhost:3306/ymdx-springboot?useUnicode=true&amp;characterEncoding=UTF8"
                      userId="root"
                      password="123456">
      </jdbcConnection>

      <!-- 类型转换 -->
      <javaTypeResolver >
          <property name="forceBigDecimals" value="false" />
      </javaTypeResolver>

      <!-- 数据表对应的model层  -->
      <javaModelGenerator targetPackage="com.ymdx.pojo" targetProject="src/main/java">
          <property name="enableSubPackages" value="true" />
          <property name="trimStrings" value="true" />
      </javaModelGenerator>

      <!-- sql mapper 映射配置文件 -->
      <sqlMapGenerator targetPackage="mapping"  targetProject="src/main/resources">
          <property name="enableSubPackages" value="true" />
      </sqlMapGenerator>

      <!-- mybatis3中的mapper接口 -->
      <javaClientGenerator type="XMLMAPPER" targetPackage="com.ymdx.dao"  targetProject="src/main/java">
          <property name="enableSubPackages" value="true" />
      </javaClientGenerator>

      <!-- 数据表进行生成操作 schema:相当于库名; tableName:表名; domainObjectName:对应的DO -->
      <table schema="db1" tableName="t_user" domainObjectName="User"
             enableCountByExample="false" enableUpdateByExample="false"
             enableDeleteByExample="false" enableSelectByExample="false"
             selectByExampleQueryId="false">
      </table>

  </context>
</generatorConfiguration>
```
  
- 数据源配置
```
spring:
datasource:
  name: test
  url: jdbc:mysql://localhost:3306/ymdx-springboot?useUnicode=true&characterEncoding=UTF8&autoReconnect=true&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull
  username: root
  password: devMysql&123
  type: com.alibaba.druid.pool.DruidDataSource
  driver-class-name: com.mysql.cj.jdbc.Driver
  druid:
    # 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：监控统计用的filter:stat；日志用的filter:log4j；防御sql注入的filter:wall
    filters: stat
    # 初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时
    initialSize: 1
    # 最大连接池数量
    maxActive: 20
    # 最小连接池数量
    minIdle: 1
    # 获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。
    maxWait: 60000
    # 有两个含义：1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
    timeBetweenEvictionRunsMillis: 60000
    # 连接保持空闲而不被驱逐的最小时间
    minEvictableIdleTimeMillis: 300000
    # 用来检测连接是否有效的sql，要求是一个查询语句，常用select 'x'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。
    validationQuery: select 'x'
    # 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
    testOnBorrow: false
    # 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
    testOnReturn: false
    # 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
    testWhileIdle: true
    # 是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
    poolPreparedStatements: true
    # 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100
    maxPoolPreparedStatementPerConnectionSize: 20
# 把Spring Boot的编码设置为UTF-8这个功能开启，解决中文乱码
http:
  encoding:
    force: true
# mybatis配置文件
mybatis:
config-location: classpath:mybatis/mybatis-config.xml
type-aliases-package: com.ymdx.pojo
mapper-locations: classpath:mapping/*.xml
# 分页插件
pagehelper:
helperDialect: mysql
reasonable: false
supportMethodsArguments: false
params: count=countSql
```
- 启动类中添加注解：@MapperScan("com.ymdx.dao")
  
> 31.SpringBoot整合Mybatis（注解方式）

- 依赖
```
<!-- mybatis的依赖 -->
<dependency>
  <groupId>org.mybatis.spring.boot</groupId>
  <artifactId>mybatis-spring-boot-starter</artifactId>
  <version>2.0.1</version>
</dependency>

<!-- mysql的依赖 -->
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
</dependency>

<!-- fastjson的依赖 -->
<dependency>
  <groupId>com.alibaba</groupId>
  <artifactId>fastjson</artifactId>
  <version>1.2.62</version>
</dependency>
```
- 注解方式使用mybatis
```
@Repository
public interface UserMapper {
  @Select("select * from t_user where name=#{name}")
  User findUserByName(@Param("name") String name);

  @Insert("insert into t_user(name,password) values(#{name},#{password})")
  void addUser(@Param("name") String name, @Param("password") String password);
}
```

> 32.SpringBoot区分多数据源分析

在项目中，通常会进行数据库拆分或是引入其他数据库，从而需要配置多个数据源，区分多个数据源的方式：
1）通过包来区分：com.ymdx.db1.mapper; com.ymdx.db2.mapper
2）使用注解来区分（不推荐）

> 32.SpringBoot区分多数据源实现：

- 配置文件
```
spring.datasource.db1.driver-class-name=com.mysql.cj.jdbc.Driver
# Spring Boot 2默认以HikariCP作为数据库连接池，如果自定义数据源配置，这里要用jdbc-url，因为Hikari没有url属性
spring.datasource.db1.jdbc-url=jdbc:mysql://localhost:3306/ymdx-springboot?useUnicode=true&characterEncoding=UTF8&autoReconnect=true&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull
spring.datasource.db1.username=root
spring.datasource.db1.password=123456

spring.datasource.db2.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.db2.jdbc-url=jdbc:mysql://localhost:3306/ymdx-springboot2?useUnicode=true&characterEncoding=UTF8&autoReconnect=true&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull
spring.datasource.db2.username=root
spring.datasource.db2.password=123456
```
- db1数据源
```
@Configuration// 注册到Spring容器中
@MapperScan(basePackages = "com.ymdx.dao.db1", sqlSessionFactoryRef = "db1SqlSessionFactory")
public class DataSource1 {

    /**
     * 配置db1数据库
     * @return
     */
    @Bean(name = "db1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    @Primary
    public DataSource db1DataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建SqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "db1SqlSessionFactory")
    @Primary
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * 配置事务管理
     * @param dataSource
     * @return
     */
    @Bean(name = "db1DataSourceTransactionManager")
    @Primary
    public DataSourceTransactionManager db1DataSourceTransactionManager(@Qualifier("db1DataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 创建SqlSessionTemplate（封装数据库操作，线程安全）
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name = "db1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate db1SqlSessionTemplate(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
```
- db2数据源
```
@Configuration// 注册到Spring容器中
@MapperScan(basePackages = "com.ymdx.dao.db2", sqlSessionFactoryRef = "db2SqlSessionFactory")
public class DataSource2 {

    /**
     * 配置db2数据库
     * @return
     */
    @Bean(name = "db2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource db2DataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建SqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "db2SqlSessionFactory")
    public SqlSessionFactory db2SqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * 配置事务管理
     * @param dataSource
     * @return
     */
    @Bean(name = "db2DataSourceTransactionManager")
    public DataSourceTransactionManager db2DataSourceTransactionManager(@Qualifier("db2DataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 创建SqlSessionTemplate（封装数据库操作，线程安全）
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name = "db2SqlSessionTemplate")
    public SqlSessionTemplate db2SqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
```
  
> 33.事务管理

在Spring Boot中推荐使用@Transactional注解来声明事务。
当使用了spring-boot-starter-jdbc或spring-boot-starter-data-jpa依赖时，Spring Boot会自动默认分别注入DataSourceTransactionManager或JpaTransactionManager  
  
> 34.SpringBoot整合JPA-Hibernate

- 依赖
```
<!-- jpa依赖 -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- mysql的依赖 -->
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
</dependency>
```
- 全局配置文件
```
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/ymdx-springboot?useUnicode=true&characterEncoding=UTF8&autoReconnect=true&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull
spring.datasource.username=root
spring.datasource.password=123456

# 让控制器输出json字符串格式
spring.jackson.serialization.indent-output=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
# spring.jpa.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.dialect=org.hibernate.dialect.MySQL57Dialect

# 修改默认字符集为UTF-8
spring.http.encoding.force=true
```
- 示例代码
```
public interface UserDao extends JpaRepository<User, Long> {
}

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void addUser(User user) {
      userDao.save(user);
  }

  @Override
  public User findUser(Long id) {
      return userDao.findById(id).get();
  }
  
  @Transactional(rollbackFor = Exception.class)
  @Override
  public void delUser(Long id) {
      userDao.deleteById(id);
  }

}
```
- 启动类中添加额外两个如下注解
```
@EntityScan("com.ymdx.pojo") // 扫描实体类
@EnableJpaRepositories("com.ymdx.dao") // 扫描dao
```
  
> 35.SpringBoot整合Email

- 依赖
```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-mail</artifactId>
</dependency>

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>
```
- 全局配置文件
```
# 若使用QQ邮箱发送邮件，则需要修改spring.mail.host=smtp.qq.com，同时spring.mail.password修改为QQ邮箱的授权码
# QQ邮箱 -> 设置 -> 账户 -> POP3/SMTP服务：开启服务后获取QQ的授权码
spring.mail.host=smtp.163.com
spring.mail.username=y_m_d_x@163.com
# 要用邮箱登录的客户端授权码，否则报异常：javax.mail.AuthenticationFailedException: 535 Error: authentication failed
spring.mail.password=
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
```
出现认证失败的解决方案：因为JDK1.8中 jre/lib/security中两个jar包替换的缘故。

- 示例代码  
```java
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    
    @Override
    public void sendSimpleMail(String sendTo, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getEmailFrom());
        message.setTo(sendTo);
        message.setSubject(title);
        message.setText(content);

        mailSender.send(message);
    }
    
    @Override
    public void sendAttachmentMail(String sendTo, String title, String content, File file) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(emailConfig.getEmailFrom());
            helper.setTo(sendTo);
            helper.setSubject(title);
            helper.setText(content);

            FileSystemResource fsr = new FileSystemResource(file);
            helper.addAttachment("附件", file);
        }catch (Exception e){
            e.printStackTrace();
        }
        mailSender.send(message);
    }
    
    @Override
    public void sendTemplateMail(String sendTo, String title, String templateName) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(emailConfig.getEmailFrom());
            helper.setTo(sendTo);
            helper.setSubject(title);

            // 封装模版使用的数据
            Map<String, Object> model = new HashMap<>();
            model.put("username", "义码当仙");

            // 获取模版
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            helper.setText(html);

        }catch (Exception e){
            e.printStackTrace();
        }
        mailSender.send(message);
    }

}
```

```java
@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/sendSimpleEmail")
    public String sendSimpleEmail(){
        emailService.sendSimpleMail("zhongyi.li@rograndec.com", "测试SpringBoot简单邮件发送", "你好，明天约吗？");
        return "success";
    }

    @RequestMapping("/sendAttachmentEmail")
    public String sendAttachmentEmail(){
        File file = new File("springboot-email/src/main/resources/static/attachment.jpg");
        emailService.sendAttachmentMail("zhongyi.li@rograndec.com",
                "测试SpringBoot带附件的邮件发送",
                "测试SpringBoot带附件的邮件发送", file);
        return "success";
    }

    @RequestMapping("/sendTemplateEmail")
    public String sendTemplateEmail(){
        emailService.sendTemplateMail("zhongyi.li@rograndec.com", "测试SpringBoot简单邮件发送", "info.html");
        return "success";
    }

}
```

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
</head>
<body>
  ${username}
</body>
</html>
```
  
> 36.SpringBoot实现文件上传下载  

工程名：springboot-file-upload-download

> 37.Neo4J下载与安装

- 下载地址：https://neo4j.com/download-center/ 
- 启动／停止命令
```
cd /usr/local/neo4j-community-3.5.8/bin
./neo4j start
./neo4j stop
```
- 访问http://localhost:7474/
- 首次使用，用户名、密码默认为：neo4j，并需要重置密码，这里用123456

> 38.SpringBoot整合Neo4J

工程名：springboot-neo4j  
  
> 39.打包发布

- 需要打成war包  
- 依赖  
```
<!-- 内嵌tomcat不打到包中 -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-tomcat</artifactId>
  <scope>provided</scope>
</dependency>
```
- 启动类继承SpringBootServletInitializer，重写configure方法  
```
@Override
protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
  return builder.sources(Application.class);
}
```
  
> 40.SpringBoot整合Redis（单机版）

- 依赖
```
<!-- redis依赖 -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```
- 全局配置文件
```
# redis单服务配置
spring.redis.database=0
spring.redis.host=172.16.49.131
spring.redis.port=6379
spring.redis.password=123456
# 连接池最大连接数（使用负值表示没有限制）
spring.redis.pool.max-active=1000
# 连接池最大阻塞等待时间（使用负值表示没有限制）
spring.redis.pool.max-wait=-1
# 连接池中的最大空闲连接
spring.redis.pool.max-idle=100
# 连接池中的最小空闲连接
spring.redis.pool.min-idle=1
# 连接超时时间（毫秒）
spring.redis.timeout=0
```

- 启动类添加注解：@EnableCaching // 开启缓存  

- 测试代码  
```
@Cacheable(value = "userName")
@Override
public User findUserByName(String name) {
  System.out.println("从数据库中查询...");
  return userMapper.findUserByName(name);
}
```
  
> 41.SpringBoot整合Redis（集群版）

- 依赖
```
<!-- redis依赖 -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```
- 全局配置文件
```
# redis集群配置
spring.redis.cluster.nodes=172.16.49.131:8000,172.16.49.131:8001,172.16.49.131:8002,172.16.49.131:8003,172.16.49.131:8004,172.16.49.131:8005
# 连接池最大连接数（使用负值表示没有限制）
spring.redis.jedis.pool.max-active=1000
# 连接池最大阻塞等待时间（使用负值表示没有限制）
spring.redis.jedis.pool.max-wait=-1
# 连接池中的最大空闲连接
spring.redis.jedis.pool.max-idle=100
# 连接池中的最小空闲连接
spring.redis.jedis.pool.min-idle=1
# 连接超时时间（毫秒）
spring.redis.timeout=0
spring.redis.commandTimeout=50000
```

- 配置类
```
@Configuration
@ConditionalOnClass({JedisCluster.class})
public class JedisClusterConfig {

    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.commandTimeout}")
    private int commandTimeout;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;

    @Bean
    public JedisCluster getJedisCluster() {
        String[] cNodes = clusterNodes.split(",");
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        // 分割出集群点
        for (String node : cNodes) {
            String[] hp = node.split(":");
            nodes.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));
        }
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(500);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        JedisCluster jedisCluster = new JedisCluster(nodes, commandTimeout, timeout, maxIdle, jedisPoolConfig);
        return jedisCluster;
    }

}
```
  
> 42.SpringBoot整合Mongodb
  
- 依赖
```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```
- 全局配置文件
```
spring.data.mongodb.host=172.16.49.131
spring.data.mongodb.port=27017
#spring.data.mongodb.username=
#spring.data.mongodb.password=
spring.data.mongodb.database=mydb
```

- 示例代码（Controller）
```
@Autowired
private MongoTemplate mongoTemplate;

@RequestMapping("/addUserToMongo")
public String addUserToMongo(){
    User user = new User();
    user.setId(1L);
    user.setName("小明");
    user.setPassword("111111");
    user.setEmail("xiaoming@163.com");
    user.setBirthday(new Date());
    mongoTemplate.save(user);
    return "success";
}

@RequestMapping("/findAllUserFromMongo")
public List<User> findAllUserFromMongo(){
  return mongoTemplate.findAll(User.class);
}
```

> 42.SpringBoot整合ActiveMQ

- 依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-activemq</artifactId>
</dependency>

<!-- 如果全局配置文件中，spring.activemq.pool.enabled=true，则需要添加如下依赖，否则JmsMessagingTemplate自动配置失败 -->
<!--<dependency>
    <groupId>org.apache.activemq</groupId>
    <artifactId>activemq-pool</artifactId>
</dependency>-->
```

- 全局配置文件
```
spring.activemq.broker-url=tcp://172.16.49.131:61616
spring.activemq.in-memory=true
spring.activemq.user=admin
spring.activemq.password=admin
spring.activemq.pool.enabled=false
```

- 示例代码
```java
@Component
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(Destination dest, String message){
        jmsMessagingTemplate.convertAndSend(dest, message);
    }

}

@Component
public class Consumer {

    @JmsListener(destination = "myQueue")
    public void receiveMessage(String message){
        System.out.println(message+"......");
    }

}
```

```java
@RestController
public class TestController {

    @Autowired
    private Producer producer;

    @RequestMapping("/activemqTest")
    public String activemqTest() {
        // 点对点消息
        Destination dest = new ActiveMQQueue("myQueue");
        for (int i = 0; i < 3; i++) {
            producer.sendMessage(dest, "hello");
        }

        return "success";
    }

}
```

> 43.SpringBoot整合RabbitMQ

- 依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```
- 全局配置文件
```
spring.application.name=ymdx-rabbitmq
spring.rabbitmq.addresses=172.16.49.131:5672
spring.rabbitmq.username=admin
spring.rabbitmq.password=123456
spring.rabbitmq.publisher-confirms=true
```
- 示例代码
```
@Component
public class MySender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        rabbitTemplate.convertAndSend("testQueue1", "你好！！！");
    }

}

@Component
public class MyReceiver {

    @RabbitHandler
    @RabbitListener(queues = "testQueue1")
    public void receive(String msg){
        System.out.println("收到消息为：" + msg + "...");
    }

}
```

> 44.SpringBoot整合Kafka

- 依赖
```
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
```
- 全局配置文件
```
#============== kafka ===================
spring.application.name=kafka-test
# 指定kafka代理地址，可以多个，这里的172.16.49.131是与上面Kafka启动配置文件中对应的
# 如果您在验证时出现问题，可以尝试本机绑定下 host
spring.kafka.bootstrap-servers=172.16.49.131:9092
#=============== provider  =======================
spring.kafka.producer.retries=0
# 每次批量发送消息的数量
spring.kafka.producer.batch-size=16384
spring.kafka.producer.buffer-memory=33554432
# 指定消息key和消息体的编解码方式
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
#=============== consumer  =======================
# 指定默认消费者group id
spring.kafka.consumer.group-id=test-consumer-group
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.enable-auto-commit=true
spring.kafka.consumer.auto-commit-interval=100ms
# 指定消息key和消息体的编解码方式
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
logging.level.root=error
logging.path=./logs
```
- 示例代码
```
@Component
@EnableScheduling
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Scheduled(cron = "00/1 * * * * ?")
    public void send() {
        String msg = UUID.randomUUID().toString();
        // 发送消息
        ListenableFuture future = kafkaTemplate.send("test", msg);
        future.addCallback(o -> System.out.println("send-消息发送成功：" + msg), throwable -> System.out.println("消息发送失败：" + msg));
    }

}
```

> 45.SpringBoot整合solr

- 依赖
```
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-solr</artifactId>
</dependency>
```
- 全局配置文件
```
spring.data.solr.host=http://172.16.49.131:8983/solr/new_core
```
- 示例代码
```
public class TestSolr {

    public static void main(String[] args) throws IOException, SolrServerException {
        String solrUrl = "http://172.16.49.131:8983/solr/new_core";
        HttpSolrClient client = new HttpSolrClient.Builder(solrUrl).build();
        System.out.println("============= add doc =============");
        Collection<SolrInputDocument> docs = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            SolrInputDocument doc = new SolrInputDocument();
            // 公有的字段
            doc.addField("id", UUID.randomUUID().toString());
            doc.addField("name", "solr_add_test_"+i);
            doc.addField("filename_s", "solr_test_"+i);
            docs.add(doc);
        }
        UpdateResponse rsp = client.add(docs);
        System.out.println("Add doc size" + docs.size() + " result:" + rsp.getStatus() + " Qtime:" + rsp.getQTime());
        UpdateResponse updateResponse = client.commit();
        System.out.println("commit doc to index" + " result:" + rsp.getStatus() + " Qtime:" + rsp.getQTime());
        System.out.println(updateResponse.toString());
    }

}

@RestController
public class SolrController {

    @Autowired
    private SolrClient solrClient;

    @RequestMapping("/solr")
    public String test() throws IOException, SolrServerException {
        SolrDocument doc = solrClient.getById("f43cd47d-fdf4-4a93-b68a-63791c67b014");
        return doc.toString();
    }
    
}
```

> 46.SpringBoot整合ElasticSearch

- kibana DevTools实现数据存储和检索
```
PUT /iot_log
{ "settings" : { "index" : { } }}

PUT /userindex/user/1?pretty
{
  "first_name":"li",
  "last_name":"si",
  "age":"20",
  "about":"I like to collect rock albums",
  "interests":["music"]
}

PUT /userindex/user/2?pretty
{
  "first_name":"zhao",
  "last_name":"liang",
  "age":"25",
  "about":"I like to build cabinets",
  "interests":["forestry"]
}

PUT /userindex/user/3?pretty
{
  "first_name":"zhang",
  "last_name":"san",
  "age":"18",
  "about":"I love to go rock climbing",
  "interests":["sports","music"]
}

GET /userindex/user/1
GET /userindex/user/_search
GET /userindex/user/_search?q=age:25
```
- 依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
</dependency>
```
- 全局配置文件
```
spring.data.elasticsearch.cluster-nodes=172.16.49.131:9300
spring.data.elasticsearch.cluster-name=my-es-cluster
spring.data.elasticsearch.repositories.enabled=true
```
- 示例代码
```
@RestController
public class TestController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping("/search")
    public List<User> findDoc(){
        // 构造条件
        QueryBuilder builder = QueryBuilders.existsQuery("first_name");
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).build();
        List<User> userList = elasticsearchTemplate.queryForList(searchQuery, User.class);
        return userList;
    }

}
```

> 47.SpringBoot整合WebSocket

- 依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```
- 全局配置文件
```
```
- 示例代码
```
@Configuration
// 开启对WebSocket的支持
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 注册一个STOMP协议的节点，并映射到指定的URL
     **/
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册一个STOMP协议的endpoint，并指定使用SockJS协议
        registry.addEndpoint("/endpointSocket").withSockJS();
    }

    /**
     * 配置消息代理
     **/
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 配置一个广播室的消息代理
        registry.enableSimpleBroker("/topic");
    }

}

public class SocketMessage {

    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

public class SocketResponse {

    private String responseMessage;

    public SocketResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}

@Controller
public class WebSocketController {

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 当浏览器向服务器端发送STOMP请求时，通过@MessageMapping注解来映射/getServerTime地址
    @MessageMapping(value = "/getServerTime")
    // 当服务器端有消息时，会对订阅了@SendTo中的路径的客户端发送消息
    @SendTo(value = "/topic/getResponse")
    public SocketResponse serverTime(SocketMessage message){
        return new SocketResponse(message.getMessage() + sf.format(new Date()));
    }

    @RequestMapping("/index")
    public String toPage(){
        return "webSocket";
    }

}

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>集成WebSocket示例</title>
</head>
<body>
<div>
    <button id="connect" onclick="connect();">连接</button>
    <button id="disconnect" onclick="disconnect();">断开连接</button>
    <button id="serverTimeId" onclick="getServerTime();">获取服务端时间</button>
    <hr/>
    <span id="showServerTime"></span>
</div>
<!-- SockJS的客户端脚本-->
<script type="text/javascript" src="sockjs.min.js"></script>
<!-- stomp协议的客户端脚本 -->
<script type="text/javascript" src="stomp.min.js"></script>
<script type="text/javascript" src="jquery-3.4.1.min.js"></script>
<script src="https://d1fxtkz8shb9d2.cloudfront.net/sockjs-0.3.min.js"></script>
<script type="text/javascript">
    var stompClient = null;
    $(function(){
        setConnect(false);
    });
    function setConnect(connected) {
        $("#connect").attr({disabled:connected});
        $("#disconnect").attr({disabled:!connected});
    }
    function connect() {
        var sockect = new SockJS("/endpointSocket");
        // 创建STOMP客户端连接，目标地址为/endpointSocket的STOMP代理
        stompClient = Stomp.over(sockect);
        // 打印stomp输出信息
        stompClient.debug = function(str){
          console.log(str + "\n");
        };
        // 建立连接
        stompClient.connect({}, function(frame){
            setConnect(true);
            // 连接成功后订阅/topic/getResponse目标发送的消息，该地址在Controller中使用@SendTo指定
            stompClient.subscribe('/topic/getResponse', function (response) {
                showResponse(JSON.parse(response.body).responseMessage);
            });
        });
    }
    function disconnect() {
        if(stompClient != null){
            stompClient.disconnect();
        }
        setConnect(false);
    }
    function getServerTime() {
        var message = "The server time is :";
        // 发送消息到服务端，/getServerTime地址是由Controller中的@MessageMapping指定
        stompClient.send("/getServerTime",{},JSON.stringify({'message':message}));
    }
    function showResponse(message) {
        var response = $("#showServerTime");
        response.html(message)
    }
    
</script>
</body>
</html>
```

> 48.SpringBoot使用AOP统一处理Web请求日志

- 依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```
- 配置文件
```
logging.level.root=INFO
logging.level.org.springframework.web=DEBUG
logging.pattern.console=%-12(%d{yyyy-MM-dd HH:mm:ss.SSS}) |-%-5level [%thread] %c [%L] -| %msg%n
logging.pattern.file=%d{yyyy/MM/dd-HH:mm} [%thread] %-5level %logger - %msg%n
```

- 示例代码
```java
@Aspect
@Component
public class WebLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.ymdx.web.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            logger.info("name:{}, value:{}", name, request.getParameter(name));
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }

}
```

> 49.SpringBoot使用lombok

- 依赖
```
<dependency>
	<groupId>org.projectlombok</groupId>
	<artifactId>lombok</artifactId>
</dependency>
```
- 其它特性
```
@Data：自动生成set/get方法，toString方法，equals方法，hashCode方法，不带参数的构造方法 
@NonNull：让你不在担忧并且爱上NullPointerException
@CleanUp：自动资源管理，不用再在finally中添加资源的close方法
@Setter/@Getter：自动生成set和get方法 
@ToString：自动生成toString方法 
@EqualsAndHashcode：从对象的字段中生成hashCode和equals的实现 
@NoArgsConstructor/@RequiredArgsConstructor/@AllArgsConstructor：自动生成构造方法 
@Value：用于注解final类 
@Builder：产生复杂的构建器API类
@SneakyThrows：异常处理（谨慎使用） 
@Synchronized：同步方法安全的转化 
@Getter(lazy=true)
@Log：支持各种logger对象，使用时用对应的注解，如：@Slf4j、@Log4j、@Log4j2
...  
```

> 50.SpringBoot分布式事务管理（基于jta-atomikos的传统分布式事务解决方案）

- 依赖
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-jta-atomikos</artifactId>
</dependency>
```
- 示例代码，参见工程：springboot-jta-atomikos

> 51.SpringBoot打包发布发布

- maven插件
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <mainClass>com.ymdx.App</mainClass>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```
> 52.SpringBoot热部署

- 热部署原理  

spring-boot-devtools 是一个为开发者服务的一个模块，其中最重要的功能就是自动将应用代码更改到最新的App上面去。
原理是在发现代码有更改之后，重新启动应用，但是速度比手动停止后再启动还要更快，更快指的不是节省出来的手工操作的时间。
其深层原理是使用了两个ClassLoader，一个Classloader加载那些不会改变的类（第三方Jar包），另一个ClassLoader加载会更改的类，
称为restart ClassLoader，这样在有代码更改的时候，原来的restart ClassLoader被丢弃，重新创建一个restart ClassLoader，
由于需要加载的类相比较少，所以实现了较快的重启时间（5秒以内）  

- 依赖  
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
    <scope>true</scope>
</dependency>
```

- devtools原理  
1. devtools会监听classpath下的文件变动，并且会立即重启应用（发生在保存时机），注意：因为其采用的虚拟机机制，该项重启是很快的。  
2. devtools可以实现页面热部署（即页面修改后会立即生效，这个可以直接在application.properties文件中配置spring.thymeleaf.cache=false来实现（这里注意不同的模板配置不一样）  

> 53.SpringBoot性能优化

- 扫包优化（影响启动速度）    
默认情况下，使用 @SpringBootApplication 注解来自动获取应用的配置信息，但这样也会给应用带来一些副作用。
使用这个注解后，会触发自动配置（ auto-configuration ）和 组件扫描 （ component scanning ），
这跟使用 @Configuration、@EnableAutoConfiguration 和 @ComponentScan 三个注解的作用是一样的。
这样做给开发带来方便的同时，也会有三方面的影响：  
1. 会导致项目启动时间变长。当启动一个大的应用程序，或将做大量的集成测试启动应用程序时，影响会特别明显。  
2. 会加载一些不需要的多余的实例（beans）。  
3. 会增加 CPU 消耗。  
```java
// @SpringBootApplication
@Configuration
// 指定扫描包，避免扫描装载多余的包
@ComponentScan(basePackages={"com.ymdx.dao", "com.ymdx.service", "com.ymdx.web"})
@EnableAutoConfiguration
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
```

- SpringBoot JVM参数调优（影响运行时性能）  
这个根据服务器的内存大小，来设置堆参数。  
-Xms :设置Java堆栈的初始化大小  
-Xmx :设置最大的java堆大小  
例如：  
```
# 配置jvm参数
-XX:+PrintGCDetails -Xmx32M -Xms1M  
-XX:+PrintGCDetails -Xmx1024M -Xms1024M   

# 启动springboot.jar
java -server -Xmx32m -Xm32m -jar xxx.jar  
```

- 将Servlet容器变成Undertow  
默认情况下，Spring Boot 使用 Tomcat 来作为内嵌的 Servlet 容器  
可以将 Web 服务器切换到 Undertow 来提高应用性能。Undertow 是一个采用 Java 开发的灵活的高性能 Web 服务器，提供包括阻塞和基于 NIO 的非堵塞机制。
Undertow 是红帽公司的开源产品，是 Wildfly 默认的 Web 服务器。首先，从依赖信息里移除 Tomcat 配置：  
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-undertow</artifactId>
</dependency>
```

> 54.SpringBoot监控管理

- Actuator监控应用  
Actuator是spring boot的一个附加功能，可帮助你在应用程序生产环境时监视和管理应用程序。可以使用HTTP的各种请求来监管、审计、收集应用的运行情况，特别对于微服务管理十分有意义。  
缺点：没有可视化界面。  

- 依赖  
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

- yml配置  
```
spring:
  profiles:
    active: prod
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/ymdx-springboot
    username: root
    password: 123456

##通过下面的配置启用所有的监控端点，默认情况下，这些端点是禁用的；
management:
  endpoints:
    web:
      exposure:
        include: "*"
## 自定义info信息
info:
  test1: 123
  test2: 456
```

- Actuator常用访问路径  

|路径|作用|
|:----|:----|
|/actuator/beans|显示应用程序中所有Spring bean的完整列表|
|/actuator/configprops|显示所有配置信息|
|/actuator/env|陈列所有的环境变量|
|/actuator/mappings|显示所有@RequestMapping的url整理列表|
|/actuator/health|显示应用程序运行状况信息：up表示成功，down失败|
|/actuator/info|查看自定义应用信息|

- Admin-UI分布式微服务监控中心  
Admin-UI基于actuator实现，提供界面展示监控信息  
示例工程：springboot-admin-ui-server、springboot-admin-ui-client  

```xml
<dependencies>
    <dependency>
        <groupId>de.codecentric</groupId>
        <artifactId>spring-boot-admin-starter-server</artifactId>
        <version>2.2.2</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webflux</artifactId>
    </dependency>
    <!-- Spring Boot Actuator对外暴露应用的监控信息，Jolokia提供使用HTTP接口获取JSON格式数据 -->
    <dependency>
        <groupId>org.jolokia</groupId>
        <artifactId>jolokia-core</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <!-- fastjson的依赖 -->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>1.2.62</version>
    </dependency>
</dependencies>
```

> 55.手写SpringBoot框架（简单实现）  

Spring Boot让Spring应用变的更轻量化。比如：可以仅仅依靠一个Java类来运行一个Spring引用。也可以打包你的应用为jar并通过使用java -jar来运行你的Spring Web应用。  
Spring Boot的主要优点：  
1. 使所有Spring开发者更快的入门  
2. 开箱即用，提供各种默认配置来简化项目配置  
3. 内嵌式容器简化Web项目  
4. 没有冗余代码生成和XML配置的要求  

- SpringBoot核心原理  
基于SpringMVC无配置文件（纯Java）完全注解化 + 内置tomcat-embed-core实现SpringBoot框架，Main函数启动。  
SpringBoot核心快速整合第三方框架原理：Maven继承依赖关系
SpringBoot内嵌tomcat-embed-core  
SpringBoot采用SpringMVC注解版本实现无配置效果  

- 示例工程：ymdx-springboot-mvc  


