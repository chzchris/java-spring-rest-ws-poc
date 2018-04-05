package hello.service;

import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDetailsServiceImp implements UserDetailsService {

  @Autowired
  private BCryptPasswordEncoder bcryptPasswordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    /*Here we are using dummy data, you need to load user data from
     database or other third party application*/
    Map<String, Object> user = findUserbyUername(username);

    UserBuilder builder = null;
    if (user != null) {
      builder = org.springframework.security.core.userdetails.User.withUsername(username);
      builder.password(bcryptPasswordEncoder.encode((String)user.get("password")));
      builder.roles((String[])user.get("role"));
    } else {
      throw new UsernameNotFoundException("User not found.");
    }

    return builder.build();
  }

  private Map<String, Object> findUserbyUername(String username) {
    Map<String, Object> userMap = new HashMap<String, Object>();
    if(username.equalsIgnoreCase("bill")) {
      userMap.put("username", username);
      userMap.put("password", "abc123");
      userMap.put("role", new String[]{"ADMIN"});
    } else if(username.equalsIgnoreCase("bob")){
      userMap.put("username", username);
      userMap.put("password", "abc123");
      userMap.put("role", new String[]{"USER"});
    } else {
      userMap.put("username", "GUEST");
      userMap.put("password", "abc123");
      userMap.put("role", new String[]{"GUEST"});
    }
    return userMap;
  }
}
