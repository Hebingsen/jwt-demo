package com.hhly.jwt;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * AccessToken
 * @author hebe
 *
 */
@Data
@Accessors(chain = true)
public class AccessToken {
    private String accessToken;
    private String tokenType;
    private long expiresIn;
}
