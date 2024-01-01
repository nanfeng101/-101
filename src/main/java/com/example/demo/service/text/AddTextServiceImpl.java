package com.example.demo.service.text;

import com.example.demo.dao.text.AddTextMapper;
import com.example.demo.enity.Text;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.enity.SearchId;
import javax.annotation.Resource;
import java.io.File;

@Service
public class AddTextServiceImpl implements AddTextService {
    @Resource
    private AddTextMapper addTextMapper;
    //上传文章中用到的图片
    public String uploadCoverPic(MultipartFile multipartFile)throws Exception{
        String path = ResourceUtils.getURL("classpath:").getPath() + "static";
        System.out.println(path);
        String fileName = multipartFile.getOriginalFilename();
        String giamg = fileName;
        File filePath = new File(path + File.separator + fileName);
        multipartFile.transferTo(filePath);
        return giamg;
    }
    //保存文章草稿
    public int addTextDraft(Text text){
        if(text.getIscover1()==1){
            text.setIscover(true);
        }
        else{
            text.setIscover(false);
        }
        if(addTextMapper.addTextDraft(text)>0){
            return 1;
        }
        else{
            return -1;
        }
    }
    public int addTextContent(Text text){
        if(text.getIscover1()==1){
            text.setIscover(true);
        }
        else{
            text.setIscover(false);
        }
        if(addTextMapper.addTextContent(text)>0){
            if(addTextMapper.selectTextDraft(text.getBiaoti())!=null){
                addTextMapper.deleteTextDraft(addTextMapper.selectTextDraft(text.getBiaoti()).getId());
            }
            addTextMapper.addTextStatusExamine(addTextMapper.searchTextId(text.getBiaoti()).getId(),0);
            return addTextMapper.searchTextId(text.getBiaoti()).getId();
        }
        else{
            return -1;
        }
    }
    //添加标签和标签对应文章
    public void addBioaqian(int id1,String name){
        if(addTextMapper.searchBiaoqian(name)!=null){ //用于判断标签是否存在
            //用于判断文章是否有这个标签
//            if(addTextMapper.searchText_biaoqian(id1,addTextMapper.searchBiaoqian(name).getId())!=null){
//                addTextMapper.updateText_biaoqian(addTextMapper.searchBiaoqian(name).getId(),addTextMapper.searchText_biaoqian(id1,addTextMapper.searchBiaoqian(name).getId()).getId());
//            }else {
            addTextMapper.addText_biaoqian(id1, addTextMapper.searchBiaoqian(name).getId());
//            }
        }
        else{
            addTextMapper.addBioaqian(name);
//            if(addTextMapper.searchText_biaoqian(id1,addTextMapper.searchBiaoqian(name).getId())!=null){
//                addTextMapper.updateText_biaoqian(addTextMapper.searchBiaoqian(name).getId(),addTextMapper.searchText_biaoqian(id1,addTextMapper.searchBiaoqian(name).getId()).getId());
//            }else {
            addTextMapper.addText_biaoqian(id1, addTextMapper.searchBiaoqian(name).getId());
//            }
        }
    }
    //添加分类专栏和文章对应的id
    public void addTextType(int id,String type){
        if(addTextMapper.searchtype(type)!=null){
            addTextMapper.updateType(addTextMapper.searchtype(type).getId1(),id);
        }
        else{
            addTextMapper.addtype(type);
            addTextMapper.updateType(addTextMapper.searchtype(type).getId1(),id);
        }
    }
}
