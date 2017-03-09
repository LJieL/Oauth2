package cn.com.taiji.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@SpringBootApplication
@EnableAuthorizationServer
public class SocialApplication extends AuthorizationServerConfigurerAdapter {

	  public static void main(String[] args) {
		    SpringApplication.run(SocialApplication.class, args);
		  }
	  @Override
	    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	        clients.inMemory() // 使用in-memory存储
	                .withClient("client") // client_id
	                .secret("secret") // client_secret
	                .authorizedGrantTypes("authorization_code") // 该client允许的授权类型
	                .scopes("app"); // 允许的授权范围
	    }
	  @Configuration
	  @EnableResourceServer
	  protected static class ResourceServerConfiguration
	      extends ResourceServerConfigurerAdapter {
	    @Override
	    public void configure(HttpSecurity http) throws Exception {
	      http
	        .antMatcher("/me")
	        .authorizeRequests().anyRequest().authenticated();
	    }
	  }
}
