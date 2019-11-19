package my.petr.ecommerce.security;


import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

  @Value("${jwt.header}")
  private String AUTH_HEADER;

  @Autowired
  TokenHelper tokenHelper;

  @Autowired
  UserDetailsService userDetailServiceImpl;

  private String getToken( HttpServletRequest request ) {

    String authHeader = request.getHeader(AUTH_HEADER);
    if ( authHeader != null && authHeader.startsWith("Bearer ")) {
      return authHeader.substring(7);
    }

    return null;
  }

  @Override
  public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

    String authToken = getToken( request );
    if (authToken != null) {
      // get username from token
      String username = tokenHelper.getUsernameFromToken( authToken );
      if ( username != null ) {
        // get user
        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername( username );
        // create authentication
        TokenBasedAuthentication authentication = new TokenBasedAuthentication( userDetails );
        authentication.setToken( authToken );
        SecurityContextHolder.getContext().setAuthentication( authentication );
        chain.doFilter(request, response);
      } else {
        System.out.println("Username from token can't be found in DB.");
      }
    } else {
      System.out.println("No Bearer token provided.");
      SecurityContextHolder.getContext().setAuthentication( new AnonAuthentication() );
    }

  }

}
