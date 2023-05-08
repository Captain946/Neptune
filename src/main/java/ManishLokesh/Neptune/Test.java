package ManishLokesh.Neptune;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class Test {

    public static void main(String[] args) {
        String userName = "manish";
        String SECRET_KEY = "MHJUHYFG2378HBFU";
        long EXPIRATION_TIME = 86400000;

        generateToken(userName, SECRET_KEY, EXPIRATION_TIME);
    }

    public static void generateToken(String userName, String secretKey, long expirationTime) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        String token = Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        System.out.println(token);
    }
}
