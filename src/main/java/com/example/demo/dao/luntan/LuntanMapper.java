package com.example.demo.dao.luntan;

import com.example.demo.enity.Luntan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LuntanMapper {
    List<Luntan> getLuntanText();
    int addLuntanText(Luntan luntan);
    List<Luntan> getUserIdLuntanText(int id);
}
