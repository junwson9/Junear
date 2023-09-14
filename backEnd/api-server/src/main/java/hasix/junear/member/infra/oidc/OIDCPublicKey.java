package hasix.junear.member.infra.oidc;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OIDCPublicKey {

    private String kid;
    private String alg;
    private String use;
    private String n;
    private String e;

    public OIDCPublicKey(String kid, String alg, String use, String n, String e) {
        this.kid = kid;
        this.alg = alg;
        this.use = use;
        this.n = n;
        this.e = e;
    }
}
