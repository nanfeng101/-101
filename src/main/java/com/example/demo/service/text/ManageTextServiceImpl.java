package com.example.demo.service.text;

import com.example.demo.dao.text.AddTextMapper;
import com.example.demo.dao.text.ManageTextMapper;
import com.example.demo.dao.user.UserDetailMapper;
import com.example.demo.enity.Text;
import com.example.demo.enity.Text_biaoqian;
import com.example.demo.enity.User;
import com.example.demo.vo.BiaoqianVo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ManageTextServiceImpl implements ManageTextService{
    @Resource
    private ManageTextMapper manageTextMapper;
    @Resource
    private UserDetailMapper userDetailMapper;
    @Resource
    private AddTextMapper addTextMapper;
    //获取所有文章的信息
    public Map<String,Object> getAllTextDetail(){
        Map<String,Object> map1 = new HashMap<String,Object>();
        List<Text> list = manageTextMapper.getAllTextDetail();
        List<BiaoqianVo> arratlist = new ArrayList<BiaoqianVo>();
        int view=0;
        for(Text item1:list){
            User user = userDetailMapper.getUserDetail(item1.getUser_id());
            item1.setUsermame(user.getUsername());
            item1.setUserpic(user.getPic());
            Date birth = item1.getCreate_time();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String birth1=dateFormat.format(birth);
            item1.setCreate_time1(birth1);
            view=view+item1.getView_sum();

            List<Text_biaoqian> list2 = manageTextMapper.getTextBiaoqian(item1.getId());
            BiaoqianVo biaoqianVo = new BiaoqianVo();
            List<String> list1 = new ArrayList<String>();
            for(Text_biaoqian item2:list2){
                list1.add(item2.getName());
            }
            biaoqianVo.setId(item1.getId());
            biaoqianVo.setList(list1);
            arratlist.add(biaoqianVo);
        }
        map1.put("text",list);
        map1.put("biaoqian",arratlist);
        map1.put("text_sum",manageTextMapper.getAllTextDetail().size());
        map1.put("view_sum",view);
        return map1;
    }
    //获取指定文章的标签
    public List<String> getTextBiaoqian(int id){
        List<Text_biaoqian> list = manageTextMapper.getTextBiaoqian(id);
        List<String> list1 = new ArrayList<String>();
        for(Text_biaoqian item:list){
            list1.add(item.getName());
        }
        return list1;
    }
    @Cacheable(value = "hottext" , key = "'getHottext'")
    //获取热门文章
    public List<Text> getHottext(){
        List<Text> list = manageTextMapper.getHottext();
        for(Text item1:list){
            User user = userDetailMapper.getUserDetail(item1.getUser_id());
            item1.setUsermame(user.getUsername());
            item1.setUserpic(user.getPic());
            Date birth = item1.getCreate_time();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String birth1=dateFormat.format(birth);
            item1.setCreate_time1(birth1);
        }
        return list;
    }
    //修改文章
    public int updateText(Text text){
        manageTextMapper.deleteText_biaoqian(text.getId());
        if(manageTextMapper.updateText(text)>0){
            return addTextMapper.searchTextId(text.getBiaoti()).getId();
        }
        else {
            return -1;
        }
    }
    //根据文章id获取文章信息
    public Text selectIdText(int id){
        Text text = manageTextMapper.selectIdText(id);
        User user = userDetailMapper.getUserDetail(text.getUser_id());
        if(text.getIscover()){
            text.setIscover1(1);
        }
        else{
            text.setIscover1(0);
        }
        text.setUsermame(user.getUsername());
        text.setUserpic(user.getPic());
        Date birth = text.getCreate_time();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String birth1=dateFormat.format(birth);
        text.setCreate_time1(birth1);
        return text;
    }
}
