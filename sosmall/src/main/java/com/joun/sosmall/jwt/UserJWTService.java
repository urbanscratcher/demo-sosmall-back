package com.joun.sosmall.jwt;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class UserJWTService extends BaseJwtService {
    @PostConstruct
    protected void init() {
        this.accessSecretKey = generateSecretKey("SosmallAccessSecretKey1!342345234253");
        this.accessPeriod = 24 * 60 * 60 * 1000L;
        this.refreshSecretKey = generateSecretKey("SosmallRefreshSecretKey@342344263344");
        this.refreshPeriod = 7 * 24 * 60 * 60 * 1000L;
    }

    public int getId(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization").substring(7);
        Map<String, Object> payload = this.decodeAccessToken(accessToken);
        return Integer.parseInt((String) payload.get("id"));
    }

}
