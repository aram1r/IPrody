package com.example.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/library")
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(com.example.resource.BookResourceImpl.class);
        resources.add(com.example.resource.BorrowedBookResourceImpl.class);
        resources.add(com.example.resource.ReaderResourceImpl.class);
        return resources;
    }
}
