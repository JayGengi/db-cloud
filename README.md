# (自定义auth微服务认证授权DEMON)

> 使用custom-auth+Spring-Gateway+Nacos+Redis的微服务授权Demo案例。



## 主要使用技术栈：

- Alibaba-Nacos注册中心
- SpringCloud-GateWay网关
- custom-auth
- Redis缓存存储
- MyBatis-plus
- Feign HTTP客户端

## 授权步骤：

- **认证** 利用网关 使用db-auth Login接口登录， 其中默认登录用户:18888888888密码123456

  请求路径 **POST**（http://localhost:9527/nocas-auth/sys/login/login RequestBody:{"phone":"18888888888","password":"123456"}）

- GET登录后，请求返回Result JSON，内含授权Token

- **授权**利用网关 使用db-auth SysUserController接口进行获取登录信息，模拟接口授权

  请求路径**GET**（http://localhost:9527/nocas-auth/sys/user/test）  请求头新增 “token”内容，用于前后分离时身份认证

- **鉴权** 请求路径+token 执行GateWay-->AuthFilter

## 流程图

<img src="coolcat-cloud.assets/image-20220408142220912.png" alt="image-20220408142220912" style="zoom:80%;" />

## 贴部分认证授权代码

### gateway:application.yml

```yaml
spring:
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 #配置Nacos地址
    gateway:
      discovery:
        locator:
          enabled: true #开启从注册中心动态创建路由的功能
          lower-case-service-id: true #使用小写服务名，默认是大写
      routes:
        - id: payment_route #路由的ID
          uri: lb://nacos-payment-service
          predicates: # 断言，路径相匹配的进行路由
            - Path=/nacos-payment-service/**
          filters:
            - StripPrefix=1

        - id: auth_route #路由的ID
          uri: lb://nacos-auth
          predicates: # 断言，路径相匹配的进行路由
            - Path=/nacos-auth/**
          filters:
            - StripPrefix=1
            - Token=token  #注意：TokenGatewayFilterFactory，只需要写Token
```

### 认证 (authentication)

```java
@Component
@Slf4j
public class TokenGatewayFilterFactory extends AbstractGatewayFilterFactory<TokenGatewayFilterFactory.Config> implements Ordered {

    @Autowired
    private AuthExclusion exclusion;

    @Autowired
    private AuthService authService;
    /**
     * 
     * db-common {@link com.duobang.cloud.response.MyResponseCode.UNAUTHENTICATED}
     */
    private final String UNAUTHENTICATED = "40100";

    public TokenGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Lists.newArrayList("token");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpResponse response = exchange.getResponse();
            String path = exchange.getRequest().getPath().toString();
            log.info("path:[{}]", path);
            //1、判断是否是过滤的路径， 是的话就放行
            if (exclusion.isExclusionUrl(path) ){
                return chain.filter(exchange);
            }
            String tokenValue = exchange.getRequest().getHeaders().getFirst(config.getToken());
            log.info("从请求中获取到的token value 是:[{}]", tokenValue);
            // token是否存在
            if(StrUtil.isBlank(tokenValue) || !authService.checkToken(tokenValue)){
                throw new AuthenticatedException("未登录");
            }
            return chain.filter(exchange);
        };
    }

    @Override
    public int getOrder() {
        return 1;
    }

    @Data
    public static class Config {
        private String token;
    }

}
```

### 授权 (authorization)

```java
@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private AuthExclusion exclusion;

    @Autowired
    private AuthService authService;
   
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = exchange.getRequest().getPath().toString();
        //判断请求的URL是否有权限
        if(!authService.checkAuth(request)){
            throw new ForbiddenException("无访问权限");
        }
        return chain.filter(exchange.mutate().request(request).build());
    }

    /**
     * 指定全局过滤器执行的顺序
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 2;
    }
```



### 文末

代码仓库：[www.github.com/jaygengi (github.com)](https://link.juejin.cn/?target=https%3A%2F%2Fgitee.com%2Fchintensakai%2Flearn-spring-cloud)

第一次编译前：db-auth8088 modlues

类名:***com.duobang.cloud.runner.DataRunner***

并修改***applicaiton.yml***

准备源数据

```yaml
auth:
  prepare-data: true
```

