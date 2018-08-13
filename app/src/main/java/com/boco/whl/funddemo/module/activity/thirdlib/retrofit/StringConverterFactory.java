package com.boco.whl.funddemo.module.activity.thirdlib.retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.annotation.Nullable;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 自定义Converter
 *
 * @author:honglei92
 * @time:2018/8/10
 */
public class StringConverterFactory extends Converter.Factory {

    public static final StringConverterFactory INSTANCE = new StringConverterFactory();

    public static StringConverterFactory create() {
        return INSTANCE;
    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == String.class) {
            return StringConverter.INSTANCE;
        }
        return null;
    }

    public static class StringConverter implements Converter<ResponseBody, String> {
        public static final StringConverter INSTANCE = new StringConverter();

        @Override
        public String convert(ResponseBody value) throws IOException {
            return value.string();
        }
    }


}
