package com.duobang.cloud.service;

import org.springframework.http.server.reactive.ServerHttpRequest;

public interface AuthService {
    boolean checkAuth(ServerHttpRequest request);

    boolean checkToken(String token);
}
