package com.nat.android.javashoplib.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by LDD on 17/4/13.
 */

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD })
@Retention(RetentionPolicy.CLASS)
public @interface isLogin {
}
