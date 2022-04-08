package com.duobang.cloud.service.impl;

import cn.hutool.json.JSONObject;
import com.duobang.cloud.feign.AuthFeignClient;
import com.duobang.cloud.service.AuthService;
import com.duobang.cloud.utils.AutowiredBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {


    ExecutorService executorService = Executors.newFixedThreadPool(1);

    /**
     * TODO 待完善
     * db-common {@link com.duobang.cloud.response.MyResponseCode.OK}
     */
    private final Integer OK = 20000;

    @Override
    public boolean checkAuth(ServerHttpRequest request){
        String authorization = request.getHeaders().getFirst("token");
        String path = request.getURI().getPath();
        log.info("checkAuth:" + path + ", authorization:" + authorization);
        AuthFeignClient authClient = AutowiredBean.getBean(AuthFeignClient.class);
        Future<JSONObject> future = executorService.submit(() -> authClient.checkAuth(authorization, path));
        JSONObject resp = null;
        try {
            resp = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        log.info("checkAuth.resp:" + resp);
        return OK.equals(resp.get("code"));
    }

    @Override
    public boolean checkToken(String token) {
        AuthFeignClient authClient = AutowiredBean.getBean(AuthFeignClient.class);
        Future<JSONObject> future = executorService.submit(() -> authClient.checkToken(token));
        JSONObject resp = null;
        try {
            resp = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        log.info("checkToken.resp:" + resp);
        return OK.equals(resp.get("code"));
    }


}
