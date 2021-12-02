package com.baboondev.baboonjobsmsjobs.util;

import javax.xml.bind.DatatypeConverter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

public class JwtUtils {
    public static Claims verifyToken(String token) throws Exception {
        try {
            String secret = "JWT_SECRET";
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                    .parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            throw new Exception("Invalid Token");
        }
    }

    public static String getRoleByToken(String token) throws Exception {
        Claims claims = verifyToken(token);
        return claims.get("role").toString();
    }

    public static String getUserIdByToken(String token) throws Exception {
        Claims claims = verifyToken(token);
        return claims.get("id").toString();
    }
}
