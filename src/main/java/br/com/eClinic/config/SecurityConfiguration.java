package br.com.eClinic.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//import br.com.eClinic.modelo.acesso.Perfil;
import br.com.eClinic.modelo.seguranca.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(c -> c.disable())
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers(HttpMethod.POST, "/api/pacientes").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/gerenciadormedicos").permitAll()// remover depois
                        .requestMatchers(HttpMethod.POST, "/api/especialidade").permitAll()// remover depois
                        .requestMatchers(HttpMethod.POST, "/api/gerenciadorPaciente").permitAll()// remover depois
                        .requestMatchers(HttpMethod.POST, "/api/agendamento").permitAll()// remover depois
                        .requestMatchers(HttpMethod.POST, "/api/especialidade/filtrar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/agendamento/filtrar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/medicos").permitAll()

                        
                        .requestMatchers(HttpMethod.GET, "/api/agendamento/listarTodosPorCpf/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/agendamento/listarTodosPorCrm/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/pacientes").permitAll()// remover depois, apenas para
                        .requestMatchers(HttpMethod.GET, "/api/pacientes/buscarPorCpf/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/medicos/buscarPorCrm/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/medicos/*").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/agendamento/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/agendamento").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/agendamento/*").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/chat").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/medicos").permitAll()// remover depois, apenas para
                        .requestMatchers(HttpMethod.GET, "/api/medicos/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/medicos/especialidade/*").permitAll()// remover depois, apenas para
                                                                                    // visulaizar o get no postman
                        .requestMatchers(HttpMethod.GET, "/api/gerenciadormedicos").permitAll()// remover depois, apenas
                                                                                               // para visulaizar o get
                                                                                               // no postman
                        .requestMatchers(HttpMethod.GET, "/api/especialidade").permitAll()// remover depois, apenas para
                                                                                          // visulaizar o get no postman
                        .requestMatchers(HttpMethod.GET, "/api/gerenciadorPaciente").permitAll()// remover depois,
                                                                                                // apenas para
                                                                                                // visulaizar o get no
                                                                                                // postman

                        .requestMatchers(HttpMethod.POST, "/api/especialidade").permitAll()// remover depois
                        .requestMatchers(HttpMethod.POST, "/api/agendamento").permitAll()// remover depois
                        .requestMatchers(HttpMethod.POST, "/api/especialidade/filtrar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/agendamento/filtrar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/especialidade").permitAll()// remover depois, apenas para
                                                                                          // visulaizar o get no postman

                        .requestMatchers(HttpMethod.GET, "/api-docs/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/*").permitAll()

                        .requestMatchers(HttpMethod.DELETE, "/api/agendamento/*").permitAll()

                        .requestMatchers(HttpMethod.PUT, "/api/medicos/*").permitAll()
                        

                        .anyRequest().authenticated()

                )
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
