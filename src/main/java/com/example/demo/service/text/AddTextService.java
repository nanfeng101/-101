package com.example.demo.service.text;

import com.example.demo.enity.Text;
import org.springframework.web.multipart.MultipartFile;

public interface AddTextService {
    String uploadCoverPic(MultipartFile multipartFile)throws Exception;
    int addTextContent(Text text);
    void addBioaqian(int id1,String name);
    void addTextType(int id,String type);
    int addTextDraft(Text text);
}
