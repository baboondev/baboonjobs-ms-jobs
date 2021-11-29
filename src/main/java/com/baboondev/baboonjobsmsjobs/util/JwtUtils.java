package com.baboondev.baboonjobsmsjobs.util;

import javax.xml.bind.DatatypeConverter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

public class JwtUtils {
    public static Claims parseJWT(String jwt) throws Exception {
        try {
            String secret = "JWT_SECRET";
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                    .parseClaimsJws(jwt).getBody();
            return claims;
        } catch (Exception e) {
            throw new Exception("Invalid Token");
        }
    }
}
