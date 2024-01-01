package com.example.demo.service.Luntan;

import com.example.demo.enity.Luntan;

import java.util.List;

public interface LuntanService {
    List<Luntan> getLuntanText();
    String addLuntanText(Luntan luntan);
    List<Luntan> getUserIdLuntanText(int id);
}
