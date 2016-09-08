# ApiTestByJava
一个接口测试工具
## 安装方式
        1.安装jdk
        2.配置jdk环境变量
        3.下载tag目录下的jar文件放在系统任意位置
        4.将配置文件apitest.json放在jar所在目录
## 运行方式
        1.cmd进入jar文件所在位置
        2.java -jar ApiTest.jar

## 配置文件
### 配置文件说明
```javascript
    [
           	{
          		"name":"接口名称",
          		"uri":"http://xxxx/yyyy",
          		"header":"{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}",
          		"params":"param1=value1&param2=value2",
          		"method":"GET"
          	},
           	{
           		"name":"接口名称",
           		"uri":"http://xxxx/yyyy",
           		"header":"{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}",
           		"method":"POST"
           	}
    ]
```
####name
  接口名称
  不可为空
  必填
####uri
  接口请求路径
  不可为空
  必填
####method
  接口请求方式
  可选值:POST/GET
  不可为空
  必填
####params
  接口请求参数
  可选格式:
    1:jsonString
    {"params1":"value1","params2":value2}
    2:String
    param1=value1&param2=value2
  可以为空
  选填
####headers
  接口请求header
  仅支持jsonString格式:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}
  可以为空
  选填
