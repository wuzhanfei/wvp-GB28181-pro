package com.genersoft.iot.vmp.conf.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * RSA JWK 完整属性配置，对应 jwt.rsa.* 配置项。
 * 各字段值均为 Base64URL 编码的大整数（与 JWK 规范一致），
 * 启动时由 {@link JwtUtils} 读取并构建 {@link org.jose4j.jwk.RsaJsonWebKey}。
 *
 * <pre>
 * jwt:
 *   rsa:
 *     kty: RSA
 *     kid: 3e79646c4dbc408383a9eed09f2b85ae
 *     n:  ...
 *     e:  AQAB
 *     d:  ...
 *     p:  ...
 *     q:  ...
 *     dp: ...
 *     dq: ...
 *     qi: ...
 * </pre>
 *
 * 注意：必须配置全部字段，任一缺失将导致应用启动失败。
 */
@Component
@ConfigurationProperties(prefix = "jwt.rsa", ignoreInvalidFields = true)
@Data
public class RsaJwkProperties {

    /**
     * 密钥类型，固定 RSA
     */
    private String kty;

    /**
     * 密钥 ID，写入 JWT 签名 header 的 kid
     */
    private String kid;

    /**
     * RSA 模数（public）
     */
    private String n;

    /**
     * 公钥指数（public）
     */
    private String e;

    /**
     * 私钥指数（private）
     */
    private String d;

    /**
     * 第一个素因子（private）
     */
    private String p;

    /**
     * 第二个素因子（private）
     */
    private String q;

    /**
     * 第一个 CRT 指数（private）
     */
    private String dp;

    /**
     * 第二个 CRT 指数（private）
     */
    private String dq;

    /**
     * 第一个 CRT 系数（private）
     */
    private String qi;
}
