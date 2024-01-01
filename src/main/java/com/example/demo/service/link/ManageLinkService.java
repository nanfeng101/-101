package com.example.demo.service.link;

import com.example.demo.dao.link.ManageLinkMapper;
import com.example.demo.enity.Link;

import javax.annotation.Resource;
import java.util.List;

public interface ManageLinkService {
    List<Link> getAllLink();
}
