package com.ao.aoeksamenprojekt.Config;



import com.ao.aoeksamenprojekt.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Bean
    public DaoAuthenticationProvider studentAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setAuthoritiesMapper(authoritiesMapper());
        return provider;
    }
    @Bean
    public GrantedAuthoritiesMapper authoritiesMapper(){
        SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
        authorityMapper.setConvertToUpperCase(true);
        authorityMapper.setDefaultAuthority("ADMIN");
        return authorityMapper;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(studentAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/stillinger").permitAll()
                //skal laves på en bedre måde
                .antMatchers("/stillinginfo1").permitAll()
                .antMatchers("/stillinginfo2").permitAll()
                .antMatchers("/stillinginfo3").permitAll()
                .antMatchers("/stillinginfo4").permitAll()
                .antMatchers("/stillinginfo5").permitAll()
                .antMatchers("/stillinginfo6").permitAll()
                .antMatchers("/stillinginfo7").permitAll()
                .antMatchers("/stillinginfo8").permitAll()
                .antMatchers("/stillinginfo9").permitAll()
                .antMatchers("/stillinginfo10").permitAll()
                .antMatchers("/stillinginfo11").permitAll()
                .antMatchers("/stillinginfo12").permitAll()
                .antMatchers("/stillinginfo13").permitAll()
                .antMatchers("/stillinginfo14").permitAll()
                .antMatchers("/stillinginfo15").permitAll()
                .antMatchers("/stillinginfo16").permitAll()
                .antMatchers("/stillinginfo17").permitAll()
                .antMatchers("/stillinginfo18").permitAll()
                .antMatchers("/stillinginfo19").permitAll()
                .antMatchers("/stillinginfo20").permitAll()

                .antMatchers("/søg").permitAll()
                .antMatchers("/opretbestilling").permitAll()
                .antMatchers("/kontakt").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/signed").permitAll()
                .antMatchers("/*.js").permitAll()
                .antMatchers("/*.css").permitAll()
                .antMatchers("/img/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").permitAll();

    }

}

