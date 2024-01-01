package com.example.demo.service.type;

import com.example.demo.dao.type.ManageTypeMapper;
import com.example.demo.enity.Text_type;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ManageTypeServiceImpl implements ManageTypeService{
    @Resource
    private ManageTypeMapper manageTypeMapper;
    //获取全部标签
    public List<Text_type> getAllType(){
        return manageTypeMapper.getAllType();
    }
}
