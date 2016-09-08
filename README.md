# ApiTestByJava
一个接口测试工具
## 配置文件
### 设置路径
工具第一次运行会检查是否有配置文件(配置文件是否存在),若不存在需要输入配置文件绝对路径
### 配置文件说明
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
