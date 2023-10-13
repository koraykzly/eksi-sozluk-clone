package com.example.eksi.utils;

import java.util.regex.Pattern;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegexUtil {
    private static final String urlPattern = 
            "((http|https)://)(www\\.)?" +
            "[a-zA-Z0-9@:%._\\+~#?&//=]" +
            "{2,256}\\.[a-z]" +
            "{2,6}\\b([-a-zA-Z0-9@:%" +
            "._\\+~#?&//=]*)";

    @Bean
    public Pattern getUrlPattern() {
        return Pattern.compile(urlPattern);
    }

}
