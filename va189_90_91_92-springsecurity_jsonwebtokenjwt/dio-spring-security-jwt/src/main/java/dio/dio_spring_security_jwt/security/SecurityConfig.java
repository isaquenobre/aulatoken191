package dio.dio_spring_security_jwt.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.config") // as propriedades de configuração do token serão lidas do
                                                     // application.properties com o prefixo security.config
public class SecurityConfig {
  public static String PREFIX; // prefixo do token, ex: Bearer
  public static String KEY;// chave de assinatura do token
  public static Long EXPIRATION;// tempo de expiração do token em milisegundos

  public void setPrefix(String prefix) {
    PREFIX = prefix;
  }

  public void setKey(String key) {
    KEY = key;
  }

  public void setExpiration(Long expiration) {
    EXPIRATION = expiration;
  }
}
