package org.blackist.web.springbootor.model.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {

    private final Logger logger = Logger.getLogger(this.getClass());

    private static final String TOKEN_SUB = "sub";
    private static final String TOKEN_CREATED = "created";

    @Value("${token.secret}")
    private String secret;

    @Value("${token.expiration}")
    private long expiration;

    public String generateToken(TokenDetail tokenDetail) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(TOKEN_SUB, tokenDetail.getUsername());
        claims.put(TOKEN_CREATED, generateCurrentDate());
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(this.generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, this.secret.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.expiration * 1000);
    }

    private static Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }

        return username;
    }

    /**
     * 校验token有效:
     * 1.username是否和UserDetails中一致
     * 2.token是否过期
     * 3.token生成日期是否在最后一次修改密码之后
     *
     * @param token       token
     * @param userDetails userDetail from db
     * @return is token valid
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        UserDetailImpl userDetail = (UserDetailImpl) userDetails;
        final String username = this.getUsernameFromToken(token);
        final Date created = this.getCreatedDateFromToken(token);
        return (username.equals(userDetail.getUsername())
                && !(this.isTokenExpired(token))
                && !this.isTokenCreatedBeforeLastPasswordReset(created, userDetail.getLastPasswordReset()));
    }

    /**
     * 校验token是否过期
     *
     * @param token token
     * @return expired
     */
    private boolean isTokenExpired(String token) {
        Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(generateCurrentDate());
    }

    /**
     * 校验token是否在最后一次修改密码前生成(密码修改后，之前生成的token即使没有过期也判为无效)
     *
     * @param created           token created date
     * @param lastPasswordReset last password reset
     * @return is token before password
     */
    private boolean isTokenCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    /**
     * 获取token创建时间
     *
     * @param token token
     * @return token创建时间
     */
    private Date getCreatedDateFromToken(String token) {
        Date date;

        try {
            Claims claims = this.getClaimsFromToken(token);
            date = new Date((Long) claims.get(TOKEN_CREATED));
        } catch (Exception e) {
            date = null;
        }

        return date;
    }

    /**
     * 获得我们封装在 token 中的 token 过期时间
     *
     * @param token token
     * @return token过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * 解析token主体Claims
     *
     * @param token token
     * @return claims
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.secret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

}
