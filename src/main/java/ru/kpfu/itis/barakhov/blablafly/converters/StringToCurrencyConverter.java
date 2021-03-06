package ru.kpfu.itis.barakhov.blablafly.converters;

import org.springframework.core.convert.converter.Converter;

import java.util.Currency;

public class StringToCurrencyConverter implements Converter<String, Currency> {
    @Override
    public Currency convert(String s) {
        try {
            return Currency.getInstance(s);
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }
}
