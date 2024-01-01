package com.example.demo.service.user;

import com.example.demo.enity.Photo;

import java.util.List;

public interface PhotoService {
    List<Photo> getPhoto();
    String addPhoto(Photo photo);
    String deletePhoto(int id);
}
