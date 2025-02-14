package com.fu.prenancytracker.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${pregnancy.app.jwtSecret}")
    private String jwtSecret;

    @Value("${pregnancy.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    // Tạo JWT token từ email của người dùng
    public String generateJwtToken(String email) {
        String jwt = Jwts.builder()
                .setSubject(email) // Đặt email làm subject của token
                .setIssuedAt(new Date()) // Ngày phát hành token
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // Ngày hết hạn của token
                .signWith(key(), SignatureAlgorithm.HS256) // Ký token bằng khóa bí mật
                .compact();

        return jwt;
    }

    // Lấy khóa bí mật từ chuỗi được mã hóa Base64
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // Lấy tên người dùng (email) từ JWT token
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    // Lấy ngày hết hạn của JWT token
    public Date getExpirationDate(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getExpiration();
    }

    // Kiểm tra tính hợp lệ của JWT token
    public int validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return 0; // Token hợp lệ
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
            return 1; // Token không hợp lệ
        } catch (ExpiredJwtException e) {
            logger.error("Token has expired: {}", e.getMessage());
            return 2; // Token đã hết hạn
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
            return 3; // Token không được hỗ trợ
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return -1; // Token không xác định được trạng thái
    }

}
