# geotrellis-chatta-demo

#### 介绍

maven构建的geotrellis-chatta-demo

#### 参考博客:

1. geotrellis框架sbt转投maven构建
    - https://blog.csdn.net/weixin_42997554/article/details/99676080
    - https://gitee.com/XiaoSa12138/geotrellis-chatta-demo

2. geotrellis框架中示例：geotrellis-chatta-demo 的搭建记录
    - https://blog.csdn.net/fendouhahaha/article/details/83856748
    - https://blog.csdn.net/qq_32432081/article/details/81162285

# 步骤一： 修改配置文件 application.conf
- 修改 server.static-path 参数，将此参数值设置成static文件夹所在路径。比如我的配置为：server.static-path = "D:/static"。配置好此路径后，系统启动后才能找到html所在的位置。
- 修改 file.path 参数，将此参数值设置为执行 ChattaIngest 程序时，在output.json中配置的backend中的path路径，比如我的配置为：file.path = "D:/data/chatta-demo"

# 步骤二： 修改 input.json 和 output.json
- 确保 input.json 中所有文件的存放路径"path"都是包含盘符的绝对路径
- 确保 output.json 中的 "D:/data/chatta-demo" 与 application.conf中的 file.path 值一致

# 步骤三： 修改 scala\geotrellis\chatta\ChattaIngest.scala
确保此处三个json文件的路径也写成绝对路径。
```
   val arg = Array[String](
       "--input",
       "D:\\data\\input.json",
       "--output",
       "D:\\data\\output.json",
       "--backend-profiles",
       "D:\\data\\backend-profiles.json"
   )
```
然后启动 ChattaIngest 中的App程序，等待很长时间（没有调用集群所以单机执行速度很慢）。
ChattaIngest 使用 geotrellis提供的ETL工具将tiff格式的文件转换成 geotrellis 支持的NOSQL数据库格式。然后后台代码才能够处理这些 tiff 文件。
关于ETL的详细解释和使用方法可以查看官方的指南：https://docs.geotrellis.io/en/latest/guide/etl.html 。

# 步骤四：启动 Main 程序开启前端服务

一切准备就绪后，执行scala\geotrellis\chatta\Main.scala。打开浏览器输入：127.0.0.1:8777访问。如果出现于官网demo一致的效果就说明成功了
