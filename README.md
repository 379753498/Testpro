# Testpro

#### 项目介绍
这是一个为jenkins持续集成写的后台应用程序
#### 主要思想
1. jenkins复制构建、执行、报告展示
2. 平台负责整合所有测试用例

----------------------------------------------------------------


#### 软件架构
软件架构说明
1. com-testpro-Dubbo 是一个监控后台Duboo服务的案例 并生成allure测试报告
2. com-testpro-jmeter 是一个由Maven插件实现执行Jmeter脚本的案例 并生成allure测试报告
3. com-testpro-main  是核心包 包含主要的一些数据库连接查询工作和一些工具类 
4. com-testpro-REST 是一个基于RestAssured Testng  实现参数化接口访问请求 并生成allure测试报告
5. com-testpro-Webselenium 是一个基于webdrivermanager Testng  selenium 框架UI测试Demo 并生成allure测试报告
6. com-testpro-Redis 进行redis测试的单元项目


#### 使用说明

    1. com-testpro-jmeter
     {
       目前支持Jmeter 版本 3.1 其他版本请自行测试
       (1) 录制Jmx文件 将Jmx文件拷贝至src/test/jmeter/JmxFileList中
       (2) 执行 maven install  or   verify
       { 备注：  如果使用jenkins 直接上传就可以自动执行}
       
       jenkins
       HTML Publisher plugin HTTP report插件
       执行后展示2个文件
       (1)\target\jmeter\html\jmeterTest.html------总览文件
       (2)\target\jmeter\htmldetails\jmeterTest.html-----详细信息文件
       }
  

