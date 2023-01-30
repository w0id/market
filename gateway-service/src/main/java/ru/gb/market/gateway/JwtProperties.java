package ru.gb.market.gateway;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(JwtProperties.PREFIX)
public class JwtProperties {

    public static final String PREFIX = "jwt";

    private String secret;

    private Long expireTime;

    public String getSecret() {
        return secret;
    }

    public void setSecret(final String secret) {
        this.secret = secret;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(final Long expireTime) {
        this.expireTime = expireTime;
    }
}