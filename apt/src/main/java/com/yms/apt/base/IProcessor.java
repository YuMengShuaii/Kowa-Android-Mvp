package com.yms.apt.base;

import javax.annotation.processing.RoundEnvironment;

/**
 * Created by LDD on 17/4/21.
 */
public interface IProcessor {
    void process(RoundEnvironment roundEnv, AnnotationProcessor mAbstractProcessor);
}