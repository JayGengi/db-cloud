package com.duobang.cloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.List;

/** 
  * @des　  网关白名单
  * @author JayGengi
  * @time　 2022/4/2 13:49
 */
@Data
@Component
@ConfigurationProperties("auth.ignore")
public class AuthExclusion {

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    private List<String> urls;

    public boolean isExclusionUrl(String path){
        List<String> exclusions = this.getUrls();
        if (exclusions.size() == 0){
            return false;
        }
        return exclusions.stream().anyMatch( action -> antPathMatcher.match(action , path));
    }
}
