package me.robertlit.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class APIGatewayConfiguration {

    private static final String HTTP = "http://";

    private final List<Service> serviceList;

    @Autowired
    public APIGatewayConfiguration(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();

        for (Service service : serviceList) {
            routes.route(
                    service.id(),
                    r -> r.path(service.path() + "/**")
//                            .filters(f -> f.rewritePath(
//                                    service.path() + "/?(?<remaining>.*)",
//                                    service.targetPath() + "/${remaining}")
//                            )
                            .uri(HTTP + service.uri())
            );
        }
        return routes.build();
    }
}
