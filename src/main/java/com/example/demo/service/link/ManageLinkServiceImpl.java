package com.example.demo.service.link;

import com.example.demo.dao.link.ManageLinkMapper;
import com.example.demo.enity.Link;
import com.example.demo.service.type.ManageTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ManageLinkServiceImpl implements ManageLinkService {
    @Resource
    private ManageLinkMapper manageLinkMapper;
    public List<Link> getAllLink(){
        return manageLinkMapper.getAllLink();
    }
}
