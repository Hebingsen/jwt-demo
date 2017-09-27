package com.hhly.jwt;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * json web token(jwt)工具类,专门用于创建token与解密token
 * 
 * @author hebe
 *
 */
public class JsonWebToken {

	/**
	 * 创建token
	 * 
	 * @param username
	 * @param password
	 * @param isExpire
	 * @throws Exception
	 */
	public static String createJWT(String username, String password, boolean isExpire) throws Exception {

		// jwt签名算法
		SignatureAlgorithm signAlgorithm = TokenConst.JWT_SIGN_ALGORITHM;

		// 通过服务器秘钥+签名算法生成签名秘钥
		byte[] serverSecret = DatatypeConverter.parseBase64Binary(TokenConst.JWT_SECRET);
		SecretKeySpec signKey = new SecretKeySpec(serverSecret, signAlgorithm.getJcaName());

		System.out.println("通过服务器秘钥+签名算法生成签名秘钥 : " + signKey);

		// 添加构成JWT的参数
		JwtBuilder builder = Jwts.builder().setHeaderParam("type", "JsonWebToken");
		builder.claim("username", username);
		builder.claim("password", password);
		builder.setAudience(TokenConst.JWT_AUDIENCE);
		builder.setIssuer(TokenConst.JWT_ISUSER);
		builder.signWith(signAlgorithm, signKey);

		// 添加Token过期时间
		if (isExpire) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date expireDate = sdf.parse("2017-08-15 21:00:00");
			builder.setExpiration(expireDate).setNotBefore(new Date());
		}

		// 生成JWT
		String token = builder.compact();
		System.out.println("JWT token 生成结果 : " + token);

		return token;
	}

	/**
	 * 解析token
	 */
	public static Claims parseWebToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(TokenConst.JWT_SECRET))
				.parseClaimsJws(token).getBody();
		return claims;
	}

	public static void main(String[] args) throws Exception {

		String token = JsonWebToken.createJWT("hebingsen", "123456", true);

		Claims claims = parseWebToken(token);
		System.out.println(claims);
		System.out.println(claims.get("username"));
		System.out.println(claims.get("password"));

	}

}
