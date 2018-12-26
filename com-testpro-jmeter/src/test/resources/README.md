Jmeter 版本 3.1 其他版本请自行测试
(1) 录制Jmx文件 将Jmx文件拷贝至src/test/jmeter/JmxFileList中
(2) 执行 maven install  or   verify
{ 备注：  如果使用jenkins 直接上传就可以自动执行}

jenkins
HTML Publisher plugin HTTP report插件
执行后展示2个文件
(1)\target\jmeter\html\jmeterTest.html------总览文件
(2)\target\jmeter\htmldetails\jmeterTest.html-----详细信息文件
