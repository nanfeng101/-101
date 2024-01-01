package com.example.demo.dao.link;

import com.example.demo.enity.Link;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManageLinkMapper {
    List<Link> getAllLink();
    int deleteLink(int id);
}
