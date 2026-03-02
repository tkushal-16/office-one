package com.spring.java.common;

public interface HasVersion {

    Long getVersion();

    default void setVersion(Long version) {
    }

}
