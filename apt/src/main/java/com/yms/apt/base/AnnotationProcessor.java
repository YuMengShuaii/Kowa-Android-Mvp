package com.yms.apt.base;

import com.google.auto.service.AutoService;
import com.yms.apt.router.RouterProcessor;
import com.yms.aptlib.router.Router;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Created by LDD on 17/4/21.
 */

@AutoService(Processor.class)//自动生成 javax.annotation.processing.IProcessor 文件
@SupportedSourceVersion(SourceVersion.RELEASE_7)//java版本支持
@SupportedAnnotationTypes({//标注注解处理器支持的注解类型
        "com.yms.aptlib.router.Router"
})
public class AnnotationProcessor extends AbstractProcessor {
    public Filer mFiler; //文件相关的辅助类
    public Elements mElements; //元素相关的辅助类
    public Messager mMessager; //日志相关的辅助类
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        mFiler = processingEnv.getFiler();
        mElements = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
        new RouterProcessor().process(roundEnv, this);
        return true;
    }
}