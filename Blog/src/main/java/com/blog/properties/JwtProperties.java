package com.blog.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class JwtProperties {

    @Value("${jwt.secretKey}")
    private String secretKey;
    @Value("${jwt.ttl}")
    private long ttl;

}
