package com.example.demo.service;

import com.example.demo.entity.Mv;
import com.example.demo.entity.MvByCon;
import com.example.demo.entity.Singer;
import com.example.demo.mapper.MvMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MvServiceImpl implements MvService{
    @Resource
    private MvMapper mvmapper;
    //分页获取mv信息
    public Map<String ,Object> getMvDetail(MvByCon mvByCon){
        Map<String ,Object> map1 = new HashMap<String,Object>();
        int a=mvByCon.getPageSize();
        int sum=a*(mvByCon.getCurrentPage()-1);
        mvByCon.setCurrentPage(sum);
        mvByCon.setAct("byPage");
        map1.put("mv",mvmapper.getMvDetail(mvByCon));
        mvByCon.setAct("byNoPage");
        map1.put("total",mvmapper.getMvDetail(mvByCon).size());
        return map1;
    }
    public List<Mv> classifymv(String name){
        return mvmapper.classifymv(name);
    }

    //更新MV图片
    public void updateMvPic(String pic , int id){
        mvmapper.updateMvPic(pic,id);

    }
    //添加MV
    public String addMv(Mv mv)throws Exception {
        if(mv.getMyfile()!=null) {
            MultipartFile myfile = mv.getMyfile();
            String path = ResourceUtils.getURL("classpath:").getPath() + "static";
            String fileName = myfile.getOriginalFilename();
            String giamg = fileName;
            File filePath = new File(path + File.separator + fileName);
            myfile.transferTo(filePath);
            mv.setUrl(giamg);
        }
        if(mvmapper.addMv(mv)>0){
            return "ok";
        }
        return "no";
    }
    //删除mv
    public String deleteMv(int id)throws Exception{
        Mv mv = mvmapper.selectIdMv(id);
        if(mv.getUrl()!=null){
            String path = ResourceUtils.getURL("classpath:").getPath() + "static";
            String path2=path+mv.getUrl();
            File file = new File(path2);
            file.delete();
        }
        if(mvmapper.deleteMv(id)>0){
            return "ok";
        }
        else{
            return "no";
        }
    }
    public Mv selectIdMv(int id){
        return mvmapper.selectIdMv(id);
    }
    public String updateMv(Mv mv){
        if(mvmapper.updateMv(mv)>0){
            return "ok";
        }
        else{
            return "no";
        }
    }
}
