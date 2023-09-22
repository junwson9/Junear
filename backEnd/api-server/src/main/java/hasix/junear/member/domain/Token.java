package hasix.junear.member.domain;


import javax.servlet.http.Cookie;
import lombok.Getter;


@Getter
public class Token {

    private String token;
    public Token(String token) {
        this.token = token;
    }

    public static Token of(String token){
        return new Token(token);
    }

    public Cookie createCookie(){
        Cookie cookie = new Cookie("refreshToken",this.token);
        cookie.setHttpOnly(true);
        return cookie;
    }
}
