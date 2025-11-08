package com.fiap.pj.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateTimeUtils {

    public static ZonedDateTime getNow() {
        return toUtc(ZonedDateTime.now());
    }

    private static ZonedDateTime toUtc(ZonedDateTime dateTime) {
        return dateTime.withZoneSameInstant(ZoneOffset.UTC).withNano(0);
    }
}