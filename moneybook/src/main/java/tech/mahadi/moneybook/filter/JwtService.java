package tech.mahadi.moneybook.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final static String SECRET_KEY = "442A472D4B6150645367566B59703373367639792442264529482B4D62516554";
    public String extractUsername(String jwtToken) {
        return extractClaim(Claims::getSubject,jwtToken);
    }
    private Claims extractAllClaims(String jwtToken){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }
    private <T> T extractClaim(Function<Claims, T> claimsResolver, String jwtToken){
        Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }
    private Key getSigninKey() {
        byte[] byteKeys = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(byteKeys);
    }
    public String generateToken(Map<String, Object> claims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .signWith(getSigninKey())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date((System.currentTimeMillis())+1000*60*60))
                .compact();
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    public boolean isValidToken(String jwtToken, UserDetails userDetails) {
        String username = extractUsername(jwtToken);
        return username.equals(userDetails.getUsername());
    }

//    public boolean isTokenExpired(String jwtToken) {
//        return getExpiredDate(jwtToken).before(new Date());
//    }
//    private Date getExpiredDate(String jwtToken){
//        return extractClaim(Claims::getExpiration, jwtToken);
//    }
}
