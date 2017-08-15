package com.aelbardai.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.context.ThemeSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

import java.util.Locale;

/**
 * Configuration class
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.ENGLISH);
        return resolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }
    /*  Theme resolver   */
    @Bean
    public ThemeSource themeSource() {
        ResourceBundleThemeSource source = new ResourceBundleThemeSource();
        source.setBasenamePrefix("static/assets/themes/");
        return source;
    }
    @Bean
    public ThemeResolver themeResolver(){
        CookieThemeResolver resolver = new CookieThemeResolver();
        resolver.setCookieMaxAge(2400);
        resolver.setCookieName("mytheme");
        resolver.setDefaultThemeName("purple");
        return resolver;
    }

    @Bean
    ThemeChangeInterceptor themeChangeInterceptor(){
        ThemeChangeInterceptor themeInterceptor = new ThemeChangeInterceptor();
        themeInterceptor.setParamName("theme");
        return themeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(themeChangeInterceptor());
    }
}
