package com.example.onlineresumecreator.service;

import java.util.List;

public interface ServiceInterface <T>{
    List<T> getAll();
    T getById(Long id);
    T save(T t);
    T update(Long id,T t);
}
