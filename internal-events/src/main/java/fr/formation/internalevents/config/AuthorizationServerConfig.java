package fr.formation.internalevents.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.internalevents.services.EmployeeService;

@Configuration
@EnableAuthorizationServer
@RestController
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	// Get custom properties from application.properties
    @Value("${jwt-internal-events-server.keyStore}")
    private String keyStore;

    @Value("${jwt-internal-events-server.keyPass}")
    private String keyPass;

    @Value("${jwt-internal-events-server.keyAlias}")
    private String keyAlias;

    @Value("${jwt-internal-events-server.accessTokenValiditySeconds}")
    private int accessTokenValiditySeconds;

    @Value("${jwt-internal-events-server.refreshTokenValiditySeconds}")
    private int refreshTokenValiditySeconds;   

    // Defined as Spring bean in WebSecurity
    private final AuthenticationManager authenticationManager;

    // Employee details service to authenticate employees with username and
    // password from the database
    private final EmployeeService employeeService;

    // Custom token converter to store custom info within access token
    private final CustomAccessTokenConverter customAccessTokenConverter;

    protected AuthorizationServerConfig(
	    AuthenticationManager authenticationManagerBean,
	    @Lazy EmployeeService employeeService,
	    CustomAccessTokenConverter customAccessTokenConverter) {
	authenticationManager = authenticationManagerBean;
	this.employeeService = employeeService;
	this.customAccessTokenConverter = customAccessTokenConverter;
    }
    
    /**
     * Token service using random UUID values for the access token and refresh
     * token values. Specifies the token store and enables the refresh token.
     */
    @Bean
    protected DefaultTokenServices tokenServices() {
	DefaultTokenServices services = new DefaultTokenServices();
	services.setTokenStore(tokenStore());
	services.setSupportRefreshToken(true);
	return services;
    }

    /**
     * JwtTokenStore can read and write JWT thanks to the token converter.
     */
    @Bean
    protected TokenStore tokenStore() {
	return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
	return new CustomTokenEnhancer();
    }

    /**
     * All in one.
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer configurer)
	    throws Exception {
	TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
	tokenEnhancerChain.setTokenEnhancers(
		Arrays.asList(tokenEnhancer(), accessTokenConverter()));
	configurer.tokenStore(tokenStore()).tokenEnhancer(tokenEnhancerChain)
		.authenticationManager(authenticationManager)
		.userDetailsService(employeeService);
    }

    /**
     * A token converter for JWT and specifies a signing key (private/public key
     * pair).
     */
    @Bean
    protected JwtAccessTokenConverter accessTokenConverter() {
	JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	Resource resource = new ClassPathResource(keyStore);
	char[] password = keyPass.toCharArray();
	KeyStoreKeyFactory factory = new KeyStoreKeyFactory(resource, password);
	converter.setKeyPair(factory.getKeyPair(keyAlias));
	converter.setAccessTokenConverter(customAccessTokenConverter);
	return converter;
    }

    /**
     * Change authorization server security allowing form auth for clients (vs
     * HTTP Basic). The client_id is sent as form parameter instead.
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer configurer)
	    throws Exception {
	configurer.allowFormAuthenticationForClients();
    }

    /**
     * In memory client with empty secret, application is a "private" API with a
     * single client, but Spring forces a client authentication.
     * <p>
     * Authorized grant types are <i>password</i> and <i>refresh_token</i>.
     * <p>
     * The scope is trusted (convention) and no need to specify it during client
     * authentication. We do not use scope-based authorization in this
     * application.
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
	    throws Exception {
	clients.inMemory().withClient("my-ievents-app")
		.secret(passwordEncoder().encode("")).scopes("trusted")
		.authorizedGrantTypes("password", "refresh_token")
		.accessTokenValiditySeconds(accessTokenValiditySeconds)
		.refreshTokenValiditySeconds(refreshTokenValiditySeconds);
    }

    /**
     * The password encoder bean for the application. Used for client and users.
     *
     * @return a password encoder
     */
    @Bean
    protected PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }


}
