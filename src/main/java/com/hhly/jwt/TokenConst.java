package com.hhly.jwt;

import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.SignatureAlgorithm;

/**
 * token常量类
 * @author hebe
 *
 */
public interface TokenConst {
	
	/**
	 * jwt秘钥
	 */
	String JWT_SECRET = DatatypeConverter.printBase64Binary("hebingsen".getBytes());
	
	/**
	 * 接收jwt的一方
	 */
	String JWT_AUDIENCE = "audience";
	
	/**
	 * jwt签发者
	 */
	String JWT_ISUSER = "true";
	
	/**
	 *
	 */
	String JWT_HEADER = "Authorization";
	
	/**
	 * 
	 */
	String JWT_BEARER = "bearer";
	
	/**
	 * 签名算法
	 * 
	 */
	SignatureAlgorithm JWT_SIGN_ALGORITHM = SignatureAlgorithm.HS256;

}
