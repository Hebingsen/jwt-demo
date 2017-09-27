package com.hhly.jwt;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生成jwt
 * 
 * @author hebe
 *
 */
@RestController
@RequestMapping("/api/auth/")
public class JWTController {

	/**
	 * 用户登录获取token
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/login")
	public Object login(String username, String password) throws Exception {
		final String token = JsonWebToken.createJWT(username, password, true);
		return new HashMap<String, Object>() {
			{
				put("code", 1);
				put("msg", "token获取成功");
				put("data",
						new AccessToken().setAccessToken(token).setExpiresIn(0).setTokenType(TokenConst.JWT_BEARER));
			}
		};

	}

}
