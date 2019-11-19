package my.petr.ecommerce.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  @Value("${jwt.expires_in}")
  private int EXPIRES_IN;

  @Autowired
  TokenHelper tokenHelper;

  @Autowired
  ObjectMapper objectMapper;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    clearAuthenticationAttributes(request);
    User user = (User) authentication.getPrincipal();
    String jws = tokenHelper.generateToken(user.getUsername());
    UserTokenState userTokenState = new UserTokenState(jws, EXPIRES_IN);
    String jwtResponse = objectMapper.writeValueAsString(userTokenState);
    response.setContentType("application/json");
    response.getWriter().write(jwtResponse);
  }

  private class UserTokenState {

    private String jws;
    private int expires;

    public UserTokenState(String jws, int expires) {
      this.jws = jws;
      this.expires = expires;
    }

    public String getJws() {
      return jws;
    }

    public void setJws(String jws) {
      this.jws = jws;
    }

    public int getExpires() {
      return expires;
    }

    public void setExpires(int expire) {
      this.expires = expire;
    }
  }
}
