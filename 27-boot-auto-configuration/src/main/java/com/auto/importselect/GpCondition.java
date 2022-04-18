package com.auto.importselect;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class GpCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 判断当前环境是不是windows
        String property = context.getEnvironment().getProperty("os.name");
        return property.equals("Windows") ? true:false;
    }
}
