package com.thoughtworks.capability.gtb.restfulapidesign.util;

public class RepositoryUtils {
    public static <T> T updateIfNotNull(T previousValue, T newValue) {
        return newValue == null ? previousValue : newValue;
    }
}
