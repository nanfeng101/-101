package com.example.demo.dao.type;

import com.example.demo.enity.Text_type;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManageTypeMapper {
    List<Text_type> getAllType();
}
