# Questions

## 使用NIO编写一个简单的gateway

### 支持功能

* Support routing
  * Random routing
          
* support http request filter
  * nio header filter - Set "nio" header
  * request debug filter - Print request information
  
* support http response filter
  * Nop filter - Nop

How to run

```shell
cd gateway 
mvn clean package

# Example 1: 默认代理http://127.0.0.1:8088
$ java -jar target/gateway-1.0-SNAPSHOT.jar

# Example 2: 支持代理多个service,用";"隔空。默认使用Random routing
java -DproxyServer="http://127.0.0.1:8088;http://127.0.0.1:8089" -jar target/gateway-1.0-SNAPSHOT.jar

> request example
$ curl -v -X GET "http://127.0.0.1:8888/api/hello"

```
