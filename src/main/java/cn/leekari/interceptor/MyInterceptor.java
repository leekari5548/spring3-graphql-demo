package cn.leekari.interceptor;

import cn.leekari.entity.User;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class MyInterceptor implements WebGraphQlInterceptor {

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        String value = request.getHeaders().getFirst("user_token");
        if (value == null || value.isEmpty()) {
            return chain.next(request).doFirst(() -> {});
        }
        User currentUser = new User();
        currentUser.setName("zs");
        currentUser.setId(1L);
        request.configureExecutionInput((executionInput, builder) ->
                builder.graphQLContext(Collections.singletonMap("user_info", currentUser)).build());
        return chain.next(request);
    }
}
