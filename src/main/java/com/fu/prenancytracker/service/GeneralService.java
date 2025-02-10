package com.fu.prenancytracker.service;

public interface GeneralService<T> {
    T save(T t);

    Iterable<T> findAll();
}
