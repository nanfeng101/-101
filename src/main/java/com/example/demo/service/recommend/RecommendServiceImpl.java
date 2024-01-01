package com.example.demo.service.recommend;

import com.example.demo.dao.text.ManageTextMapper;
import com.example.demo.dao.user.UserCollectMapper;
import com.example.demo.dao.user.UserDetailMapper;
import com.example.demo.dao.user.UserLiuyanMapper;
import com.example.demo.enity.*;
import com.example.demo.vo.BiaoqianVo;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.print.Paper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RecommendServiceImpl implements RecommendService{
    @Resource
    private UserCollectMapper userCollectMapper;
    @Resource
    private UserDetailMapper userDetailMapper;
    @Resource
    private ManageTextMapper manageTextMapper;
    //基于物品的协同过滤算法
    public Map<String,Object> getUserRecommend(int id){
        Map<String,Object> map1 = new HashMap<String,Object>();
        List<Text> list = recommend(id);
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
    public List<Text> recommend(int uid){

        List<User_browse> likeLists;                                       //其他用户喜欢的论文列表
        List<User> users = userDetailMapper.getAllUser();                  //所有用户列表
        for(int i=0;i<users.size();i++){
            if(users.get(i).getId()==5){
                users.remove(users.get(i));
            }
        }
        List<Text> papers = manageTextMapper.getAllTextDetail();            //所有论文列表
        int[][] curMatrix = new int[papers.size()+100][papers.size()+100];   //当前矩阵
        int[][] comMatrix = new int[papers.size()+100][papers.size()+100];   //共现矩阵
        int[] N = new int[papers.size()+100];                              //喜欢每个物品的人数
        User_browse_current user_browse_current = new User_browse_current();
        user_browse_current.setAct("bynopage");
        for(User user: users){
            if(user.getId()==uid) continue;                    //当前用户则跳过
            user_browse_current.setId(user.getId());
            likeLists = userCollectMapper.getUserBrowse(user_browse_current);//当前用户的喜欢列表

            for(int i = 0; i < papers.size(); i++)
                for(int j = 0; j < papers.size(); j++)
                    curMatrix[i][j] = 0;                               //清空矩阵

            for(int i = 0; i < likeLists.size(); i++){
                int pid1 = likeLists.get(i).getText_id();
                ++N[pid1];
                for(int j = i+1; j < likeLists.size(); j++){
                    int pid2 = likeLists.get(j).getText_id();
                    ++curMatrix[pid1][pid2];
                    ++curMatrix[pid2][pid1]; //两两加一
                }
            }
            //累加所有矩阵, 得到共现矩阵
            for(int i = 0; i < papers.size(); i++){
                for(int j = 0; j < papers.size(); j++){
                    int pid1 = papers.get(i).getId(), pid2 = papers.get(j).getId();
                    comMatrix[pid1][pid2] += curMatrix[pid1][pid2];
                    comMatrix[pid1][pid2] += curMatrix[pid1][pid2];
                }
            }
        }


        TreeSet<Text> preList = new TreeSet<Text>(new Comparator<Text>() {
            @Override
            public int compare(Text o1, Text o2) {
                if(o1.getW()!=o2.getW())
                    return (int) (o1.getW()-o2.getW())*100;
                else
                    return o1.getCollect_sum()-o2.getCollect_sum();
            }
        }); //预处理的列表
        user_browse_current.setId(uid);
        likeLists = userCollectMapper.getUserBrowse(user_browse_current);
        System.out.println(likeLists.size());//当前用户喜欢的论文列表
        boolean[] used = new boolean[papers.size()+100];  //判重数组
        for(User_browse like: likeLists){
            int Nij = 0;                         //既喜欢i又喜欢j的人数
            double Wij;                          //相似度
            Text tmp;                           //当前的论文

            int i = like.getText_id();
            for(Text paper: papers){
                if(like.getText_id() == paper.getId()) continue;
                int j = paper.getId();

                Nij = comMatrix[i][j];
                Wij = (double)Nij/Math.sqrt(N[i]*N[j]); //计算余弦相似度

                tmp = manageTextMapper.selectIdText(paper.getId());
                tmp.setW(Wij);

                if(used[tmp.getId()]) continue;
                preList.add(tmp);
                used[tmp.getId()] = true;
            }
        }

        List<Text> recomLists = new ArrayList<>();
        System.out.println(preList.size());//生成的推荐结果
        for(int i = 0; preList.size()>0 && i<5; i++){
            recomLists.add(preList.pollLast());
//            preList.pollLast();
            System.out.println("*************");
        }
        List<Text> list = manageTextMapper.selectColleSumText();
        for(int i=0;i<recomLists.size();i++){
            System.out.println(recomLists.get(i).getBiaoti()+" "+recomLists.get(i).getW());
            if(recomLists.get(i).getId()!=0){
                for(int m=0;m<list.size();m++){
                    if(list.get(m).getId()==recomLists.get(i).getId()){
                        list.remove(list.get(m));
                    }
                }
            }
        }
        int sum=recomLists.size();
        for(int i=0;i<5-sum;i++){
            recomLists.add(list.get(i));
        }

        return recomLists;
    }
}
