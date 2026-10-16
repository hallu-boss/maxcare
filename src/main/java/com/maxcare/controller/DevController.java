package com.maxcare.controller;

import com.maxcare.configuration.DevelopmentEnvironmentCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Conditional(DevelopmentEnvironmentCondition.class)
public class DevController {

    private final RequestMappingHandlerMapping handlerMapping;

    public DevController(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @GetMapping(value = "/dev/routes", produces = "text/plain")
    public String getRoutes() {
        return handlerMapping.getHandlerMethods()
                .entrySet()
                .stream()
                .flatMap(entry -> {
                    var mapping = entry.getKey();

                    var methods = mapping.getMethodsCondition()
                            .getMethods()
                            .stream()
                            .map(Enum::name)
                            .toList();

                    if (methods.isEmpty()) {
                        methods = List.of("ALL");
                    }

                    var finalMethods = methods;

                    return mapping.getPatternValues()
                            .stream()
                            .map(path -> String.join(", ", finalMethods)
                                    + " " + path);
                })
                .sorted()
                .collect(Collectors.joining("\n"));    }
}
