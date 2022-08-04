    UPDATE 
        BY: guowm 
        DATE: 2018年9月29日
        VERSION: 2.1.0-SNAPSHOT

----------

# bkhech boot

    bkhech boot是基于Spring Boot 2.0.4.RELEASE构建，一套支持自动配置的快速开发脚手架。
    对以下项目做了集成和自动配置。
 - spring-mvc
 - spring-webflux
 - mybatis
 - netty
 - test
 - common

 后续还会支持
 - mongodb
 - hive
 - rabbitmq
 - elasticsearch
 - ...

    定义了一套开发规范，集成在bkhech-boot-starter-common中。对web工程支持全局异常处理，日志记录，健康检查等。
    使用apiDoc生成rest api文档，侵入性比较低。
    同时，使用bkhech-boot-mybatis-extend组件可以快速生成代码。大大提高开发效率。
    集成了常用的starters组件。

### starters
    对常用的spring-boot-starter组件都做了封装与自动配置，对于开发者来说，只需引用对应的bkhech-boot-starter包即可。
e.g.
    引入spring-mvc
```xml
    <parent>
        <groupId>com.bkhech.boot</groupId>
        <artifactId>bkhech-boot</artifactId>
        <version>2.1.0-SNAPSHOT</version>
    </parent>

    <repositories>
        <repository>
            <id>bkhech-public</id>
            <url>http://192.168.78.128:8081/repository/maven-public/</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>com.bkhech.boot</groupId>
            <artifactId>bkhech-boot-starter-mvc</artifactId>
        </dependency>
    </dependencies>
```
    引入bkhech-boot-starter-mvc，bkhech-boot-starter-mybatis，结合mybatis-generator-extend生成代码，可以快速构建简单的业务。
e.g.
```java
    @RestController
    public class SampleController {

        @Autowired
        private EventLogService eventLogService;

        @RequestMapping("/page")
        public ResultResponse<List<EventLog>> page(EventLog log, Page page) {
            return ResultResponse.success();
        }
    }
```

    以上代码的响应为：
```json
    {
        "code": 0,
        "message": "successful",
        "data": {
            "body":[{...}, {...}],
            "page": {
                "page_size": 10,
                "page_num": 1,
                "total_page": 5943,
                "total_rows": 59429
            },
            "thead": [{"game_id":"游戏ID"}, {"ad_id":"广告ID"}]                                      
         }
    }
```

### apiDoc
    apiDoc通过扫描java doc注释生成rest api文档，侵入性较低。只需在方法上按规格写好注释，执行apidoc命令即可生成api文档。
    主页: http://apidocjs.com github: https://github.com/apidoc/apidoc

#### 安装
    1. 安装node.js环境。
    2. 使用命令 npm install apidoc -g 进行安装
#### 配置
    在工程跟目录创建package.json文件
e.g.
```json
    {
      "name": "project name", // 名称
      "version": "2.0.0", // API版本
      "description": "description", // 说明
      "apidoc": {
        "title": "Custom apiDoc browser title", // 浏览器标题
        "url" : "https://127.0.0.1" //API HOST
        "order" : ["group1", "group2"] // 用于配置输出 api-names/group-names 排序
      },
      "template":{
        "forceLanguage":"zh-cn" // 中文
      }
    }
```

#### 撰写注释
    /**
     * @api {post} /page 事件日志分页示例
     * @apiName page
     * @apiGroup sample
     * @apiVersion 2.0.0
     *
     * @apiParam {EventLog} log 事件日志查询条件 Y
     * @apiParam {Page} page 分页对象 Y
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *       "code": 0,
     *       "message": "successful",
     *       "data": {
     *          "body":[{...}, {...}],
     *          "page": {
     *            "page_size": 10,
     *            "page_num": 1,
     *            "total_page": 5943,
     *            "total_rows": 59429
     *          },
     *          "thead": [{"game_id":"游戏ID"}, {"ad_id":"广告ID"}]                                   
     *       }
     *     }
     *
     * @apiErrorExample Error-Response:
     *     {
     *       "code": -1,
     *       "message": "failed"
     *       "data": ""
     *     }
     *
     * @apiUse global_api
     */

    详细定义见 http://apidocjs.com/#params

#### 生成文档
    执行命令: apidoc -i myapp/ -o myapp/apidoc/ -f ".*\.java$" -t mytemplate/

    -i 源代码输入目录
    -o 文档输出目录
    -f 可选，文件过滤，正则表达式
    -t 可选，模板目录

### bkhech-boot-mybatis-extend
    mybatis-extend包含一套标准接口，分页插件，以及代码生成器。已经集成在bkhech-boot-starter-mybatis中。
    代码生成器mybatis-generator-extend基于mybatis-generator做了深度拓展，支持Model，Mapper，Service代码以及SQL的生成。支持MySQL和Oracle的分页。

#### 使用
    1. 新建一个maven工程，引入mybatis-generator-maven-plugin插件
e.g.
```xml
    <plugin>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-maven-plugin</artifactId>
        <version>1.3.2</version>
        <configuration>
            <verbose>true</verbose>
            <overwrite>true</overwrite>
        </configuration>
        <dependencies>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>5.1.30</version>
            </dependency>
           <dependency>
                <groupId>org.bkhech.mybatis.extend</groupId>
                <artifactId>bkhech-boot-mybatis-extend</artifactId>
                <version>2.1.0-SNAPSHOT</version>
            </dependency>
        </dependencies>
    </plugin>
```
    2. 在resources目录下创建generatorConfig.properties和generatorConfig.xml文件，可以在mybatis-generator-extend包中获取模板。
    3. 修改generatorConfig.properties的代码路径与数据库配置
    4. 在generatorConfig.xml文件声明需要生成代码的表。
e.g.
```xml
    <!--
        tableName: 表名称。
        alias: 别名，在sql中会应用到，方便联合查询。
        domainObjectName: Model类名称，默认为表的驼峰名称，若需指定子包，可以"模块名称.类名"规则命名。
    -->
    <table tableName="sdk_event_log" alias="sel" domainObjectName="event.EventLog">
        <!-- 为true则不使用驼峰命名法 -->
        <property name="useActualColumnNames" value="true"/>
        <!--sqlStatement指定为JDBC即可-->
        <generatedKey column="id" sqlStatement="JDBC"/>
    </table>
```
    5. 执行命令 mybatis-generator:generate

    生成的代码实现了mybatis-generic的标准接口，提供了CRUD基本操作的支持。
    关于mybatis-generator-extend的使用有如下几点需要注意：
        - Model每次生成会覆盖。Mapper和Service不会覆盖。
        - generatorConfig.xml是强制校验节点顺序的。
        - mapper xml文件有两个，一个是标准接口的实现，生成会覆盖，一个是可以自行拓展的，生成不会覆盖。


### bkhech-boot-starter-common

    1. bkhech-boot-starter-common定义了一套基于bkhech-boot内部的数据定义和交换规范，引入此starter,然后开启@EnableBkhMvc注解即可使用。
        定义了常用的六种数据结构的类型，包括下拉框、表格、柱状图or折线图、饼状图or漏斗图、地图、雷达图，使用构建者模式构造出指定对应对象。结合ResultResponse对象可构建出相应的HTTP JSON格式的响应。
        
        对应对象为：
            - DropDownResponse 下拉框
            - TableResponse 表格
            - HistogramOrLineChartResponse 柱状图or折线图
            - PieOrFunnelChartResponse 饼状图or漏斗图
            - MapChartResponse 地图
            - RadarChartResponse 雷达图
        
        对应构建者对象为：
            - DropDownBuilder 下拉框
            - TableBuilder 表格
            - HistogramOrLineBuilder 柱状图or折线图
            - PieOrFunnelBuilder 饼状图or漏斗图
            - MapBuilder 地图
            - RadarBuilder 雷达图

配置：
```yml
    chart:
      #柱状图、折线图
      histogram-or-lines:
        - index: 1   #图表索引值。根据需要选择指定索引位的配置
          charts:
            title: 累计用户量
            xLabel: 人数/间隔(天)
            yLabel: 人数/间隔(天)
            description: 图表文字说明
        - index: 2
          charts:
            title: 累计用户量2
            xLabel: 人数/间隔(天)2
            yLabel: 人数/间隔(天)2
            description: 图表文字说明2
      #饼状图、漏斗图
      pie-or-funnels:
        - index: 1
          charts:
            title: 累计用户量pie
            yLabel: 人数/间隔(天)pie
            description: 图表文字说明pie
      #雷达图
      radars:
        - index: 1
          charts:
            title: 累计用户量radar
            yLabel: 人数/间隔(天)radar
            description: 图表文字说明radar
      #地图
      maps:
        - index: 1
          charts:
            title: 累计用户量map
            yLabel: 人数/间隔(天)map
            description: 图表文字说明map

```
    2. StatusCode
    StatusCode定义了全局的响应码，StatusCode支持拓展，继承即可。
    3. ResultResponse 
    ResultResponse用作全局的HTTP JSON响应，基础结构为code，message,data。
    有success，failed，custom等方式做出具体响应。
    4. SystemException
    SystemException用作bkhech-boot内部的异常处理类
    
    具体使用案例请参考：bkhech-boot-sample-common

e.g.
```java
    // 业务成功响应
    return ResultResponse.success();

    // 业务失败响应
    return ResultResponse.failed("error message");

    // 自定义业务响应
    return ResultResponse.custom(StatusCode.PARAM_NULL);
```
