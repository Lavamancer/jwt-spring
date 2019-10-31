package com.example.jwt.config;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Configuration
public class JacksonConfig {

    @Bean
    public Hibernate5Module hibernate5Module() {
        Hibernate5Module hibernate5Module = new Hibernate5Module();
        hibernate5Module.enable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
        hibernate5Module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
        return hibernate5Module;
    }

    @Bean
    public JodaModule jodaModule() {
        return new JodaModule();
    }

    @Component
    public static class DateTimeConfig implements Converter<String, DateTime> {
        private static final DateTimeParser[] parsers = {
                DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ss.SSSZ" ).getParser(),      // 2018-03-01T11:08:45.000Z
                DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ssZ" ).getParser(),          // 2018-03-01T11:08:45Z
                DateTimeFormat.forPattern( "yyyy-MM-dd' 'HH:mm:ss.SSSZ" ).getParser(),
                DateTimeFormat.forPattern( "yyyy-MM-dd' 'HH:mm:ssZ" ).getParser(),
                DateTimeFormat.forPattern( "yyyy-MM-dd" ).getParser()
        };
        private static final DateTimeFormatter formatter = new DateTimeFormatterBuilder().append( null, parsers ).toFormatter();
        @Override
        public DateTime convert(String source) {
            return (source == null || source.isEmpty()) ? null : formatter.parseDateTime(source);
        }
    }


}
