package com.project.project_train.config;


import org.springframework.context.annotation.Configuration; // spring의 configure를 정의하는 클래스임을 의미, 즉 Bean 정의 클래스임을 나타냄
import org.springframework.lang.NonNull; // null이 아님을 보장하는 annotaion
import org.springframework.web.servlet.config.annotation.CorsRegistry; // CORS 설정 및 매핑을 추가하는 annotation
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; // spring mvc의 설정을 위한 인터페이스, 기본 설정 외 추가적인 설정이 가능해짐

@Configuration
public class WebConfig implements WebMvcConfigurer{
        
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {  // CorRegistry를 사용하여 CORS 규칙을 정의
        registry.addMapping("/**") // 모든 엔드포인트에 CORS 매핑 추가
                .allowedOrigins("http://localhost:3000") // localhost:3000의 요청만 허용
                .allowedMethods("*") // 모든 http 메서드 허용
                .allowCredentials(true); // 세션 쿠키 허용
    }
}
