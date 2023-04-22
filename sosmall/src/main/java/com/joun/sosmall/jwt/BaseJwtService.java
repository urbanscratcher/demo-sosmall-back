package com.joun.sosmall.jwt;

import io.jsonwebtoken.*;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class BaseJwtService {

    protected String accessSecretKey;
    protected Long accessPeriod;
    protected String refreshSecretKey;
    protected Long refreshPeriod;

    public String createAccessToken(Map<String, Object> data) {
        Claims claims = Jwts.claims();
        claims.putAll(data);
        Date now = Calendar.getInstance().getTime();
        Date expireDate = Calendar.getInstance().getTime();
        expireDate.setTime(expireDate.getTime() + accessPeriod);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, accessSecretKey)
                .compact();
    }

    public Map<String, Object> decodeAccessToken(String target) throws ExpiredJwtException, SignatureException {
        return parseToken(accessSecretKey, target);
    }

    private Map<String, Object> parseToken(String secretKey, String token)
            throws ExpiredJwtException, SignatureException {
        Jws<Claims> claim = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return new LinkedHashMap<>(claim.getBody());
    }

    public boolean checkValidateAccessToken(String target) {
        boolean result;
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(accessSecretKey).parseClaimsJws(target);
            result = claims.getBody().getExpiration().after(Calendar.getInstance().getTime());
        } catch (ExpiredJwtException | SignatureException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    public String createRefreshToken(Map<String, Object> data) {
        Claims claims = Jwts.claims();
        claims.putAll(data);
        Date now = Calendar.getInstance().getTime();
        Date expireDate = Calendar.getInstance().getTime();
        expireDate.setTime(expireDate.getTime() + refreshPeriod);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, refreshSecretKey)
                .compact();
    }

    public Map<String, Object> decodeRefreshToken(String target) throws ExpiredJwtException, SignatureException {
        return parseToken(refreshSecretKey, target);
    }

    public boolean checkValidateRefreshToken(String target) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(refreshSecretKey).parseClaimsJws(target);
        return claims.getBody().getExpiration().after(Calendar.getInstance().getTime());
    }

    protected String generateSecretKey(String key) {
        return Base64.getEncoder().encodeToString(key.getBytes(StandardCharsets.UTF_8));
    }
}
