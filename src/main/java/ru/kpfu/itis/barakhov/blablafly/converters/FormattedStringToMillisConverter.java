package ru.kpfu.itis.barakhov.blablafly.converters;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FormattedStringToMillisConverter implements Converter<String, Long> {
    @Override
    public Long convert(String s) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'hh:mm").parse(s).getTime();
        } catch (ParseException e) {
            return null;
        }
    }
}
