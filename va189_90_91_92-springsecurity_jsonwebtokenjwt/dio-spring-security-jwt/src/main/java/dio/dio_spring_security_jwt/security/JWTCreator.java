package dio.dio_spring_security_jwt.security;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTCreator {
  public static final String HEADER_AUTHORIZATION = "Authorization";
  public static final String ROLES_AUTHORITIES = "authorities";

  public static String create(String prefix, String key, JWTObject jwtObject) {// método para criar o token, recebe o
                                                                               // prefixo, a chave de assinatura e o
                                                                               // objeto JWTObject
    String token = Jwts.builder().setSubject(jwtObject.getSubject()).setIssuedAt(jwtObject.getIssuedAt())// o token é
                                                                                                         // criado com
                                                                                                         // as
                                                                                                         // informações
                                                                                                         // do objeto
                                                                                                         // JWTObject, a
                                                                                                         // data de
                                                                                                         // emissão e a
                                                                                                         // data de
                                                                                                         // expiração
        .setExpiration(jwtObject.getExpiration())// o token é criado com as informações do objeto JWTObject, a data de
                                                 // emissão e a data de expiração
        .claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles())).signWith(SignatureAlgorithm.HS512, key).compact();// o
                                                                                                                      // token
                                                                                                                      // é
                                                                                                                      // criado
                                                                                                                      // com
                                                                                                                      // as
                                                                                                                      // informações
                                                                                                                      // do
                                                                                                                      // objeto
                                                                                                                      // JWTObject,
                                                                                                                      // a
                                                                                                                      // chave
                                                                                                                      // de
                                                                                                                      // assinatura
                                                                                                                      // e
                                                                                                                      // o
                                                                                                                      // algoritmo
                                                                                                                      // de
                                                                                                                      // assinatura
    return prefix + " " + token;// o token é retornado com o prefixo, ex: Bearer + " " + token
  }

  public static JWTObject create(String token, String prefix, String key)// método para criar o objeto JWTObject a
                                                                         // partir do token, recebe o token, o prefixo e
                                                                         // a chave de assinatura
      throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {
    JWTObject object = new JWTObject();
    token = token.replace(prefix, "");
    Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    object.setSubject(claims.getSubject());
    object.setExpiration(claims.getExpiration());
    object.setIssuedAt(claims.getIssuedAt());
    object.setRoles((List) claims.get(ROLES_AUTHORITIES));
    return object;

  }

  private static List<String> checkRoles(List<String> roles) {
    return roles.stream().map(s -> "ROLE_".concat(s.replaceAll("ROLE_", ""))).collect(Collectors.toList());
  }

}
