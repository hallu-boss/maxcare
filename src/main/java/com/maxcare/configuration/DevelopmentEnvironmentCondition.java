
package com.maxcare.configuration;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class DevelopmentEnvironmentCondition implements Condition {

    @Override
    public boolean matches(
            ConditionContext context,
            AnnotatedTypeMetadata metadata) {

        String environmentType = context.getEnvironment()
                .getProperty("environment.type", "");

        try {
            EnvironmentType type = EnvironmentType.valueOf(
                    environmentType.trim().toUpperCase()
            );

            return type == EnvironmentType.DEVELOPMENT;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
