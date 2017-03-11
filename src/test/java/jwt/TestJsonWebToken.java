package jwt;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import conf.TestConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Created by meisei on 2017/3/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TestJsonWebToken {

    private String secret = "huaKfFcFEmvkj9qEHwcWnuWD";

    @Test
    public void testGen() {
        String compact = Jwts.builder().claim("userId", 123987L)
                .setExpiration(new DateTime(new Date().getTime()).plusDays(1).toDate())
                .setIssuedAt(new Date()).setSubject("test subject")
                .setIssuer("meisei81")
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        System.out.println(compact);
    }

    @Test
    public void testDecode() {
        String compact = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjEyMzk4NywiZXhwIjoxNDg5MzAyMzAzLCJpYXQiOjE0ODkyMTU5MDQsInN1YiI6InRlc3Qgc3ViamVjdCIsImlzcyI6Im1laXNlaTgxIn0.xM5pvcSkIeeEam36n71SZpNJDtzqBbTzT3S3_tx_MWS2-0k1yZxgceNnhUy1YGkBbYKHvGHTrzxdAb0RyjRZPg";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(compact);
        Claims claims = claimsJws.getBody();
        System.out.println(claimsJws.getHeader());
        System.out.println(claims);
    }
}
