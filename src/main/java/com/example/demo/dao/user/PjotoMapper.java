package com.example.demo.dao.user;

import com.example.demo.enity.Photo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PjotoMapper {
    List<Photo> getPhoto();
    int addPhoto(Photo photo);
    int deletePhoto(int id);
}
