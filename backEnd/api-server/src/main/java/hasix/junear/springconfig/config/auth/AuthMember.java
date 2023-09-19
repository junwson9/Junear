package hasix.junear.springconfig.config.auth;


import lombok.Getter;

@Getter
public class AuthMember {

    private Long id;
    public AuthMember(Long id) {
        this.id = id;
    }
}
