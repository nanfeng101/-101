package com.example.demo.too;
import java.util.*;

//通过哈弗曼编码压缩解压缩来保证密码安全
public class HuffmanCode {
    //用于封装之前所有的方法，使调用该方法就可以直接获得压缩后的byte[]
    public byte[] huffmanZip(byte[] bytes){
        //统计字符串中每个字符的个数并通过传入一个node集合保存
        List<Node> list = getListNode(bytes);
        //根据得到的node集合创建哈弗曼树
        Node root = createHuffmanTree(list);
        //根据哈夫曼树获得到每个字符的哈弗曼编码
        Map<Byte,String> stringMap = getCode(root);
        //根据哈弗曼编码将原字节数组压缩
        byte[] bytes1 = zip(bytes,stringMap);
        return bytes1;
    }
    //解码
    public String decote1(byte[] bytes){   //封装解码方法
        System.out.println();
        byte[] by = decote(byteIntegerMap,bytes);
        String strv = new String(by);
        return strv;
    }
    private int jiequ=0;  //用于解码的时候判断最后一个byte转二进制的时候要几位(在压缩的时候获得的)
    private byte[] decote(Map<Byte,String> byteStringMap,byte[] bytes){
        StringBuilder stringBuilder4 = new StringBuilder();
        for(int i=0;i<bytes.length;i++){
            boolean flag = (i==bytes.length-1);
            if(jiequ==0){
                flag=false;
            }
            stringBuilder4.append(byteToString(!flag,bytes[i]));
        }
        //将存放霍夫曼编码的map集合的键值调换
        Map<String,Byte> map = new HashMap<String,Byte>();
        for(Map.Entry<Byte,String> entry : byteStringMap.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }
        List<Byte> list = new ArrayList<Byte>();
        int j=0;
        while(j<stringBuilder4.length()){
            int count=0;
            Byte b=null;
            boolean flag1=true;
            while(flag1){
                String key = stringBuilder4.substring(j,j+count);
                b=map.get(key);
                if(b!=null){
                    list.add(b);
                    flag1=false;
                    j=j+count;
                }
                else {
                    count++;
                }
            }
        }
        byte[] by = new byte[list.size()];
        for(int i=0;i<by.length;i++){
            by[i]=list.get(i);
        }
        return by;
    }
    //将压缩后的数据转成对应的二进制
    private String byteToString(boolean flag,byte b){
        int temp = b;
//        if(temp>=0 && temp<128){ //如果使正数可能需要补高位，通过按位或运算进行
//            temp |= 256;
//        }
        temp |= 256;

        String str = Integer.toBinaryString(temp);
        if(flag) {
            return str.substring(str.length()-8);
        }else {
            return str.substring(str.length()-jiequ);
        }
    }
    //压缩byte[]
    //byted的范围是[-128,127],实际上对应的是反码的范围
    //将i like like like java do you like a java用霍夫曼编码表示1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100
    //然后在将这个霍夫曼编码表示的字符串每八位作为一个二进制（该二进制为反码,因为计算机存储数据采用的是补码）
    //将其放入一个新的byte数组中
    //例如10101000(反码) => 11011000(原码） => -88(真值，即存入新的byte[]中的)
    private byte[] zip(byte[] bytes,Map<Byte,String> byteIntegerMap){
        StringBuilder stringBuilder2 = new StringBuilder();
        for(Byte item : bytes){
            stringBuilder2.append(byteIntegerMap.get(item));
        }
        int len = (stringBuilder2.length()+7)/8;
        int index=0;
        byte[] huffmanCodeByte = new byte[len];
        for(int i=0;i<stringBuilder2.length();i=i+8){
            String str;
            if(i+8>stringBuilder2.length()){ //判断剩余的是否够8位
                str=stringBuilder2.substring(i);
                jiequ=stringBuilder2.length()-i;   //用于解码的时候判断最后一个byte转二进制的时候要几位
            }else{
                str=stringBuilder2.substring(i,i+8);
            }
            huffmanCodeByte[index]=(byte) Integer.parseInt(str,2);
            index++;
        }
        return huffmanCodeByte;
    }
    //重载getCode方法
    public static Map<Byte,String> getCode(Node root){
        if(root==null){
            return byteIntegerMap;
        }else {
            getCode(root,"",stringBuilder);
            return byteIntegerMap;
        }
    }
    //根据创建的赫夫曼树生产赫夫曼编码
    //用于存放赫夫曼编码
    static Map<Byte,String> byteIntegerMap = new HashMap<Byte,String>();
    static StringBuilder stringBuilder = new StringBuilder();
    //code为路径，向左为'1',向右为'0'
    //stringbuilder用于拼接路径
    public static void getCode(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if(node!=null){
            if(node.data==null){
                getCode(node.leftNode,"0",stringBuilder1);
                getCode(node.rightNode,"1",stringBuilder1);
            }
            else {
                byteIntegerMap.put(node.data,stringBuilder1.toString());
            }
        }
    }
    //统计字符串中每个字符的个数
    private static List<Node> getListNode(byte[] bytes){
        List<Node> list = new ArrayList<Node>();
        Map<Byte,Integer> map = new HashMap<Byte,Integer>();
        for(Byte item : bytes){
            Integer could = map.get(item);
            if(could==null){
                map.put(item,1);
            }
            else {
                map.put(item,could+1);
            }
        }
        for(Map.Entry<Byte,Integer> entry : map.entrySet()){
            list.add(new Node(entry.getKey(),entry.getValue()));
        }
        return list;
    }
    //生产哈弗曼树
    private static Node createHuffmanTree(List<Node> list){
        while(list.size()>1){
            Collections.sort(list);
            Node leftnode = list.get(0);
            Node rightnode = list.get(1);
            Node parent = new Node(null,leftnode.value+rightnode.value);
            parent.leftNode=leftnode;
            parent.rightNode=rightnode;
            list.remove(leftnode);
            list.remove(rightnode);
            list.add(parent);
        }
        return list.get(0);
    }
    private static void perorder(Node root){
        if(root!=null){
            root.perorder();
        }else {
            System.out.println("书为空");
        }
    }

    public int getJiequ() {
        return jiequ;
    }

    public void setJiequ(int jiequ) {
        this.jiequ = jiequ;
    }
}

class Node implements Comparable<Node>{
    public Byte data;
    public Integer value;
    public Node leftNode;
    public Node rightNode;

    public Node(Byte data, Integer value) {
        this.data = data;
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.value-o.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", value=" + value +
                '}';
    }
    public void perorder(){
        System.out.println(this);
        if(this.leftNode!=null){
            this.leftNode.perorder();
        }
        if(this.rightNode!=null){
            this.rightNode.perorder();
        }
    }
}
