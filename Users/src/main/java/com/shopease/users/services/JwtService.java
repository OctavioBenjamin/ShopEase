package com.shopease.users.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // Replace this with a secure key in a real application, ideally fetched from environment variables
    public static String SECRET = "1e524eca98c770066c27a98405b1a16d1e121aa92ea3965a1f1c48b8bde3497e1787fa21824867250840abae522efc66d8346a8c3c6936ca829954b758e4713a46c01e69f0825b52d97c8dd71a68a5a87f0161a47ea85d298aa033d59e1fc2cadffa0f5bdf84bce2cb5769b6abf3b31332c747dc3a14fc617e00775cd94545d45ac759c2070afa9d7ae1b147af924bdc6f05322c10df66b31ca5726f4991f918c5278a647718b2cebe2f4b29ce4178b77e618efa29e32391921841f8d0d9c6c5bcf886c572c5a1a345ed5a8f7d51f2ff7c51acaff1e7b65e220a3e03115cc0b9682c43160c2b3808bbe15fcc24335173c36c4bb48a136ce320c92873bf03669c";

    // ? Extraer claims

    //Email
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //Expiration
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //Metodo Reutilizable / General
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        // Extrae un claim en especifico
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    };

    //All
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ? Generar Token

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    private String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 ))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ? Validar Token

    public Boolean isTokenValid(String token, UserDetails userDetails){
        final String userEmail = extractEmail(token);
        return (userEmail.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    // ? Generar Firma

    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
