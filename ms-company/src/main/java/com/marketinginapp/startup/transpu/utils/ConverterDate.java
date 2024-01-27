package com.marketinginapp.startup.transpu.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConverterDate {

    public static String toString(LocalDateTime time){
        DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
        return time.format(CUSTOM_FORMATTER);
    }

    public static LocalDateTime toLocalDateTime(String time){
        DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalDateTime.parse(time, CUSTOM_FORMATTER);
    }
}
