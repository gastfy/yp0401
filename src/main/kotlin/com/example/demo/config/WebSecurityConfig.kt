package com.example.demo.config

import com.example.demo.repository.UserDAO
import com.example.demo.service.UserImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
class WebSecurityConfig {

    @Autowired
    var userRepository: UserImpl? = null

    fun passwordEncoder(): PasswordEncoder { return BCryptPasswordEncoder(4) }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService<UserDetailsService>(UserDetailsService { username: String? ->
            val user = userRepository!!.findByUserName(username!!)
                ?: throw UsernameNotFoundException("Такой пользователь не существует")
            User(
                user.getUsername(),
                user.getPassword(),
                user.getActive(),
                true,
                true,
                true,
                user.getRoles()
            )
        }).passwordEncoder(passwordEncoder())
    }

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeRequests()
            .requestMatchers("/login", "/reg", "/").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin {
                it.loginPage("/login")
                it.defaultSuccessUrl("/profile")
                it.permitAll()
            }
            .logout {
                it.permitAll()
            }
            .csrf {it.disable()}
            .csrf {it.disable()}

        return http.build()
    }

}