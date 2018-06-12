package com.congphuong.reactivechatsse.config.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationEntryPointFailureHandler;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationWebFilter extends AuthenticationWebFilter {

    public JwtAuthenticationWebFilter(final ReactiveAuthenticationManager authenticationManager,
                                      final JwtAuthenticationConverter converter,
                                      final UnauthorizedAuthenticationEntryPoint entryPoint) {

        super(authenticationManager);

        Assert.notNull(authenticationManager, "authenticationManager cannot be null");
        Assert.notNull(converter, "converter cannot be null");
        Assert.notNull(entryPoint, "entryPoint cannot be null");

        setAuthenticationConverter(converter);
        setAuthenticationFailureHandler(new ServerAuthenticationEntryPointFailureHandler(entryPoint));
        setRequiresAuthenticationMatcher(new JWTHeadersExchangeMatcher());
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

//        return super.filter(exchange, chain);
        return Mono.just(exchange).flatMap(webExchange -> {
            ServerHttpRequest req = webExchange.getRequest();
            ServerHttpResponse res = webExchange.getResponse();
            if (!res.getHeaders().containsKey("Access-Control-Allow-Origin")) {
                res.getHeaders().add("Access-Control-Allow-Origin", "*");
            }
            if (!res.getHeaders().containsKey("Access-Control-Allow-Headers")) {
                res.getHeaders().add("Access-Control-Allow-Headers", "Authorization, Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
            }
            res.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, HEAD");
//            res.getHeaders().add("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
            res.getHeaders().add("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
            res.getHeaders().add("Access-Control-Allow-Credentials", "true");
            res.getHeaders().add("Access-Control-Max-Age", "10");

            if ("OPTIONS".equalsIgnoreCase(req.getMethodValue())) {
                res.setStatusCode(HttpStatus.OK);
                return super.filter(exchange, chain);
            } else {
                return super.filter(exchange, chain);
            }

        });
    }

    private static class JWTHeadersExchangeMatcher implements ServerWebExchangeMatcher {
        @Override
        public Mono<MatchResult> matches(final ServerWebExchange exchange) {
            Mono<ServerHttpRequest> request = Mono.just(exchange).map(ServerWebExchange::getRequest);

            /* Check for header "authorization" or parameter "token" */
            return request.map(ServerHttpRequest::getHeaders)
                    .filter(h -> h.containsKey("authorization"))
                    .flatMap($ -> MatchResult.match())
                    .switchIfEmpty(request.map(ServerHttpRequest::getQueryParams)
                            .filter(h -> h.containsKey("token"))
                            .flatMap($ -> MatchResult.match())
                            .switchIfEmpty(MatchResult.notMatch())
                    );
        }
    }

}