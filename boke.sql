/*
Navicat MySQL Data Transfer

Source Server         : c4
Source Server Version : 80031
Source Host           : localhost:3306
Source Database       : boke

Target Server Type    : MYSQL
Target Server Version : 80031
File Encoding         : 65001

Date: 2024-03-31 09:52:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `biaoqian`
-- ----------------------------
DROP TABLE IF EXISTS `biaoqian`;
CREATE TABLE `biaoqian` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of biaoqian
-- ----------------------------
INSERT INTO `biaoqian` VALUES ('1', 'java');
INSERT INTO `biaoqian` VALUES ('2', 'vue');
INSERT INTO `biaoqian` VALUES ('3', '网站');
INSERT INTO `biaoqian` VALUES ('4', 'springboot');
INSERT INTO `biaoqian` VALUES ('5', 'js');
INSERT INTO `biaoqian` VALUES ('6', 'github');
INSERT INTO `biaoqian` VALUES ('7', 'python');
INSERT INTO `biaoqian` VALUES ('10', '数据库');
INSERT INTO `biaoqian` VALUES ('11', 'html');
INSERT INTO `biaoqian` VALUES ('12', 'echarts');
INSERT INTO `biaoqian` VALUES ('13', 'Markdown');
INSERT INTO `biaoqian` VALUES ('14', 'hadoop');
INSERT INTO `biaoqian` VALUES ('15', '人工智能');
INSERT INTO `biaoqian` VALUES ('16', '机器人');
INSERT INTO `biaoqian` VALUES ('17', 'chatgpt');
INSERT INTO `biaoqian` VALUES ('18', 'windows');
INSERT INTO `biaoqian` VALUES ('19', '编程语言');
INSERT INTO `biaoqian` VALUES ('20', 'AGI');
INSERT INTO `biaoqian` VALUES ('21', '游戏');

-- ----------------------------
-- Table structure for `link`
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of link
-- ----------------------------
INSERT INTO `link` VALUES ('1', 'csdn', 'https://www.csdn.net/', null);
INSERT INTO `link` VALUES ('2', 'Element', 'https://element-plus.org/zh-CN/component/button.html', null);
INSERT INTO `link` VALUES ('3', '腾讯云', 'https://cloud.tencent.com/', null);
INSERT INTO `link` VALUES ('4', 'echarts', 'https://echarts.apache.org/handbook/zh/concepts/event/', null);
INSERT INTO `link` VALUES ('5', '菜鸟教程', 'https://www.runoob.com/html/html-tutorial.html', null);
INSERT INTO `link` VALUES ('6', '阿里巴巴图标库', 'https://www.iconfont.cn/search/index?searchType=icon&q=%E9%A3%9F%E5%93%81&page=1&fromCollection=1&fills=&tag=', null);
INSERT INTO `link` VALUES ('7', 'github', 'https://github.com/', null);
INSERT INTO `link` VALUES ('8', 'bilibili', 'https://www.bilibili.com/', null);

-- ----------------------------
-- Table structure for `luntan`
-- ----------------------------
DROP TABLE IF EXISTS `luntan`;
CREATE TABLE `luntan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `content` longtext,
  `biaoti` varchar(255) DEFAULT NULL,
  `view_sum` int DEFAULT NULL,
  `is_cover` tinyint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of luntan
-- ----------------------------
INSERT INTO `luntan` VALUES ('1', '1', null, '测试1', '测试1', '20', '0', '2023-11-28 10:24:56');
INSERT INTO `luntan` VALUES ('2', '2', null, '测试2', '测试2', '10', '0', '2023-11-28 10:42:33');
INSERT INTO `luntan` VALUES ('3', '3', null, '测试3', '测试3', '11', '0', '2023-11-28 17:01:32');

-- ----------------------------
-- Table structure for `photo`
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `pic1` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of photo
-- ----------------------------
INSERT INTO `photo` VALUES ('1', '1', 'a1.png', '秋水共长天一色，落霞与孤鹜齐飞', '2023-11-12');
INSERT INTO `photo` VALUES ('2', '1', 'a2.png', '太可爱了', '2023-11-12');
INSERT INTO `photo` VALUES ('3', '1', 'a3.png', '法杖', '2023-11-12');
INSERT INTO `photo` VALUES ('4', '2', 'a4.png', '嘿嘿嘿嘿', '2023-11-12');
INSERT INTO `photo` VALUES ('5', '2', 'a5.png', '回眸一笑百媚生，六宫粉黛无颜色', '2023-11-12');
INSERT INTO `photo` VALUES ('6', '3', 'a6.png', '芭芭拉', '2023-11-12');
INSERT INTO `photo` VALUES ('7', '4', 'a7.png', '太好看了', '2023-11-12');
INSERT INTO `photo` VALUES ('8', '1', 'afafasegs34526.png', '夕阳云海，乡间城镇', '2023-11-13');

-- ----------------------------
-- Table structure for `text`
-- ----------------------------
DROP TABLE IF EXISTS `text`;
CREATE TABLE `text` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `pic` varchar(255) DEFAULT NULL,
  `dianzan_sum` int DEFAULT NULL,
  `view_sum` int DEFAULT NULL,
  `collect_sum` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `iscover` tinyint DEFAULT NULL,
  `zhaiyao` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `biaoti` varchar(255) DEFAULT NULL,
  `type_id` int DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of text
-- ----------------------------
INSERT INTO `text` VALUES ('17', '# 一.前言介绍\n# 一.技术栈\n1.前端: vue,element plus,echarts,v-md-editor\n2.后端: springboot,ntbatis,mysql\n# 二.数据库设计\n## 2.1.标签表\n|名称|类型|\n|-|-|\n|id|int|\nname|varchar\n## 2.2.text_biaoqian\n|名称|类型|\n|-|-|\n|id|int|\ntext_id|int\nbiaoqian_id|int\n## 2.3.文章表(text)\n|名称|类型|\n|-|-|\n|id|int|\ncontent|longtext\npic|varchar\ndianzan_sum|int\nview_sum|int\ncomment_id|int\nuser_id|int\niscover|tinyint\nzhaiyao|varchar\nbiaoti|varchar\ntype_id|int\ncreate_time|tinyint\nupdate_time|tinyint\n## 2.4.text_type\n|名称|类型|\n|-|-|\nid1|int\ntype|varchar\n\n\n', 'adafa.jpg', '13', '45', '12', '2023-10-14 00:00:00', '1', '1', '前后端分离', '基于springboot+vue的个人博客', '3', '2023-10-16 00:00:00');
INSERT INTO `text` VALUES ('30', '# 异常背景\n后端向前端发送v-md-editor产生的文本时，用get发送\n# 异常描述\n后台接受数据时发生报错\n``` \njava.lang.IllegalArgumentException: Invalid character found in the request target.The valid characters are defined in RFC 7230 and RFC 3986\n  at org.apache.coyote.http11.Http11InputBuffer.parseRequestLine(Http11InputBuffer.java:479) ~[tomcat-embed-core-8.5.34.jar:8.5.34]\n```\n# 问题分析\n根据报错信息可以确定两点：\n1.是TOMCAT报的错\n2.和请求参数有关\n# 处理过程\n在网上查找资料发现\n这个问题是高版本tomcat中的新特性：就是严格按照 RFC 3986规范进行访问解析，而 RFC 3986规范定义了Url中只允许包含英文字母（a-zA-Z）、数字（0-9）、-_.~4个特殊字符以及所有保留字符(RFC3986中指定了以下字符为保留字符：!* ’ ( ) ; : @ & = + $ , / ? # [ ])\n而我的发送命令是\n``` \nthis.$axios.get(\'/upload?text=\'+this.text\n```\n而this.text包含了包含了不允许的字符，导致了报错。\n# 解决方案\n将GET请求换成POST请求即可\n', 'Rugyvighiulh.jpg', '5', '11', '4', '2023-10-15 10:00:00', '1', '1', '解决前端向后端发送数据时tomact按RFC3986解析时产生的问题', 'TOMCAT报错The valid characters ', '2', null);
INSERT INTO `text` VALUES ('31', '# 上传准备\n创建好自己的github仓库\n![七龙珠](http://localhost:8001/zf/sgsgsgsgsfh.jpg)\n在本地安装好github之后打开Open Git bash here\n![七龙珠](http://localhost:8001/zf/5353sgs.jpg)\n# 开始上传\n``` \ngit config --global user.email \"2694134538@qq.com\"\ngit config --global user.name \"nanfeng101\"\ngit init\ngit add .\ngit commit -m \"第一次提交\"\ngit remote add origin https://github.com/nanfeng101/-101.git\ngit push -u origin master\n//如果提交时地址写错，可以用下面代码删除origin\ngit remote rm origin\n```', 'sgsgsgsgsfh.jpg', '10', '21', '213', '2023-10-15 12:00:00', '1', '1', '将自己本地的项目通过命令上传到github', '如何将本地项目上传到github', '4', null);
INSERT INTO `text` VALUES ('43', '# 项目运行\n\n## 预览地址\n\nhttp://124.220.16.147:6449/zf/head\n\n## 环境配置：\n\njdk1.8+Mysql8+IDEA\n\n## 项目技术：\n\nSpringBoot+mybatis+Maven+Thymeleaf\n\n## 启动方式;\n\n在IDEA中运行项目后，在浏览器中输入localhost:8080/zf/head即可\n\n后台管理员登录账号和密码admin  admin\n\n## 项目源码\n\n网盘链接：https://pan.baidu.com/s/1QAyEkptRewxLBprDTBjaZQ?pwd=5yis \n提取码：5yis\n\n# 项目演示：\n(1)主体页面\n\n![七龙珠](http://localhost:8001/zf/123456.jpg.png)\n\n(2)商品详情页面\n\n![七龙珠](http://localhost:8001/zf/123458.jpg.png)\n\n(3)填写购买信息页面\n\n![七龙珠](http://localhost:8001/zf/123459.jpg.png)\n\n(4)支付页面\n\n![七龙珠](http://localhost:8001/zf/543210.jpg.png)\n\n(5)支付成功页面\n\n![七龙珠](http://localhost:8001/zf/543211.jpg.png)\n\n(6)订单管理页面\n![七龙珠](http://localhost:8001/zf/543212.jpg.png)\n\n(7)购物车页面\n\n![七龙珠](http://localhost:8001/zf/543213.jpg.png)\n\n(8)后台管理页面\n\n![七龙珠](http://localhost:8001/zf/543214.jpg.png)\n\n(9)用户登录注册页面\n\n![七龙珠](http://localhost:8001/zf/123457.jpg.png)', '131313.png', '17', '123', '1', '2023-10-15 14:00:00', '1', '1', '网盘链接：https://pan.baidu.com/s/1QAyEkptRewxLBprDTBjaZQ?在IDEA中运行项目后，在浏览器中输入localhost:8080/zf/head即可。后台管理员登录账号和密码admin  admin。(4)填写购买信息页面。(10)后台管理页面。', '基于SpringBoot的仿淘宝电商项目【附源码和数据库】', '5', null);
INSERT INTO `text` VALUES ('54', '# 欢迎使用Markdown编辑器\n\n你好！ 这是你第一次使用 **Markdown编辑器** 所展示的欢迎页。如果你想学习如何使用Markdown编辑器, 可以仔细阅读这篇文章，了解一下Markdown的基本语法知识。\n\n## 新的改变\n\n我们对Markdown编辑器进行了一些功能拓展与语法支持，除了标准的Markdown编辑器功能，我们增加了如下几点新功能，帮助你用它写博客：\n 1. **全新的界面设计** ，将会带来全新的写作体验；\n 2. 在创作中心设置你喜爱的代码高亮样式，Markdown **将代码片显示选择的高亮样式** 进行展示；\n 3. 增加了 **图片拖拽** 功能，你可以将本地的图片直接拖拽到编辑区域直接展示；\n 4. 全新的 **KaTeX数学公式** 语法；\n 5. 增加了支持**甘特图的mermaid语法[^1]** 功能；\n 6. 增加了 **多屏幕编辑** Markdown文章功能；\n 7. 增加了 **焦点写作模式、预览模式、简洁写作模式、左右区域同步滚轮设置** 等功能，功能按钮位于编辑区域与预览区域中间；\n 8. 增加了 **检查列表** 功能。\n [^1]: [mermaid语法说明](https://mermaidjs.github.io/)\n\n## 功能快捷键\n\n撤销：<kbd>Ctrl/Command</kbd> + <kbd>Z</kbd>\n重做：<kbd>Ctrl/Command</kbd> + <kbd>Y</kbd>\n加粗：<kbd>Ctrl/Command</kbd> + <kbd>B</kbd>\n斜体：<kbd>Ctrl/Command</kbd> + <kbd>I</kbd>\n标题：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>H</kbd>\n无序列表：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>U</kbd>\n有序列表：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>O</kbd>\n检查列表：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>C</kbd>\n插入代码：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>K</kbd>\n插入链接：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>L</kbd>\n插入图片：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>G</kbd>\n查找：<kbd>Ctrl/Command</kbd> + <kbd>F</kbd>\n替换：<kbd>Ctrl/Command</kbd> + <kbd>G</kbd>\n\n## 合理的创建标题，有助于目录的生成\n\n直接输入1次<kbd>#</kbd>，并按下<kbd>space</kbd>后，将生成1级标题。\n输入2次<kbd>#</kbd>，并按下<kbd>space</kbd>后，将生成2级标题。\n以此类推，我们支持6级标题。有助于使用`TOC`语法后生成一个完美的目录。\n\n## 如何改变文本的样式\n\n*强调文本* _强调文本_\n\n**加粗文本** __加粗文本__\n\n==标记文本==\n\n~~删除文本~~\n\n> 引用文本\n\nH~2~O is是液体。\n\n2^10^ 运算结果是 1024.\n\n## 插入链接与图片\n\n链接: [link](https://www.csdn.net/).\n\n图片: ![Alt](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9hdmF0YXIuY3Nkbi5uZXQvNy83L0IvMV9yYWxmX2h4MTYzY29tLmpwZw)\n\n带尺寸的图片: ![Alt](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9hdmF0YXIuY3Nkbi5uZXQvNy83L0IvMV9yYWxmX2h4MTYzY29tLmpwZw =30x30)\n\n居中的图片: ![Alt](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9hdmF0YXIuY3Nkbi5uZXQvNy83L0IvMV9yYWxmX2h4MTYzY29tLmpwZw#pic_center)\n\n居中并且带尺寸的图片: ![Alt](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9hdmF0YXIuY3Nkbi5uZXQvNy83L0IvMV9yYWxmX2h4MTYzY29tLmpwZw#pic_center =30x30)\n\n当然，我们为了让用户更加便捷，我们增加了图片拖拽功能。\n\n## 如何插入一段漂亮的代码片\n\n去[博客设置](https://mp.csdn.net/console/configBlog)页面，选择一款你喜欢的代码片高亮样式，下面展示同样高亮的 `代码片`.\n```javascript\n// An highlighted block\nvar foo = \'bar\';\n```\n\n## 生成一个适合你的列表\n\n- 项目\n  - 项目\n    - 项目\n\n1. 项目1\n2. 项目2\n3. 项目3\n\n- [ ] 计划任务\n- [x] 完成任务\n\n## 创建一个表格\n一个简单的表格是这么创建的：\n项目     | Value\n-------- | -----\n电脑  | $1600\n手机  | $12\n导管  | $1\n\n### 设定内容居中、居左、居右\n使用`:---------:`居中\n使用`:----------`居左\n使用`----------:`居右\n| 第一列       | 第二列         | 第三列        |\n|:-----------:| -------------:|:-------------|\n| 第一列文本居中 | 第二列文本居右  | 第三列文本居左 |\n\n### SmartyPants\nSmartyPants将ASCII标点字符转换为“智能”印刷标点HTML实体。例如：\n|    TYPE   |ASCII                          |HTML\n|----------------|-------------------------------|-----------------------------|\n|Single backticks|`\'Isn\'t this fun?\'`            |\'Isn\'t this fun?\'            |\n|Quotes          |`\"Isn\'t this fun?\"`            |\"Isn\'t this fun?\"            |\n|Dashes          |`-- is en-dash, --- is em-dash`|-- is en-dash, --- is em-dash|\n\n## 创建一个自定义列表\nMarkdown\n:  Text-to-HTML conversion tool\n\nAuthors\n:  John\n:  Luke\n\n## 如何创建一个注脚\n\n一个具有注脚的文本。[^2]\n\n[^2]: 注脚的解释\n\n##  注释也是必不可少的\n\nMarkdown将文本转换为 HTML。\n\n*[HTML]:   超文本标记语言\n\n## KaTeX数学公式\n\n您可以使用渲染LaTeX数学表达式 [KaTeX](https://khan.github.io/KaTeX/):\n\nGamma公式展示 $\\Gamma(n) = (n-1)!\\quad\\forall\nn\\in\\mathbb N$ 是通过欧拉积分\n\n$$\n\\Gamma(z) = \\int_0^\\infty t^{z-1}e^{-t}dt\\,.\n$$\n\n> 你可以找到更多关于的信息 **LaTeX** 数学表达式[here][1].\n\n## 新的甘特图功能，丰富你的文章\n\n```mermaid\ngantt\n        dateFormat  YYYY-MM-DD\n        title Adding GANTT diagram functionality to mermaid\n        section 现有任务\n        已完成               :done,    des1, 2014-01-06,2014-01-08\n        进行中               :active,  des2, 2014-01-09, 3d\n        计划一               :         des3, after des2, 5d\n        计划二               :         des4, after des3, 5d\n```\n- 关于 **甘特图** 语法，参考 [这儿][2],\n\n## UML 图表\n\n可以使用UML图表进行渲染。 [Mermaid](https://mermaidjs.github.io/). 例如下面产生的一个序列图：\n\n```mermaid\nsequenceDiagram\n张三 ->> 李四: 你好！李四, 最近怎么样?\n李四-->>王五: 你最近怎么样，王五？\n李四--x 张三: 我很好，谢谢!\n李四-x 王五: 我很好，谢谢!\nNote right of 王五: 李四想了很长时间, 文字太长了<br/>不适合放在一行.\n\n李四-->>张三: 打量着王五...\n张三->>王五: 很好... 王五, 你怎么样?\n```\n\n这将产生一个流程图。:\n\n```mermaid\ngraph LR\nA[长方形] -- 链接 --> B((圆))\nA --> C(圆角长方形)\nB --> D{菱形}\nC --> D\n```\n\n- 关于 **Mermaid** 语法，参考 [这儿][3],\n\n## FLowchart流程图\n\n我们依旧会支持flowchart的流程图：\n```mermaid\nflowchat\nst=>start: 开始\ne=>end: 结束\nop=>operation: 我的操作\ncond=>condition: 确认？\n\nst->op->cond\ncond(yes)->e\ncond(no)->op\n```\n\n- 关于 **Flowchart流程图** 语法，参考 [这儿][4].\n\n## 导出与导入\n\n###  导出\n如果你想尝试使用此编辑器, 你可以在此篇文章任意编辑。当你完成了一篇文章的写作, 在上方工具栏找到 **文章导出** ，生成一个.md文件或者.html文件进行本地保存。\n\n### 导入\n如果你想加载一篇你写过的.md文件，在上方工具栏可以选择导入功能进行对应扩展名的文件导入，\n继续你的创作。\n\n [1]: http://meta.math.stackexchange.com/questions/5020/mathjax-basic-tutorial-and-quick-reference\n [2]: https://mermaidjs.github.io/\n [3]: https://mermaidjs.github.io/\n [4]: http://adrai.github.io/flowchart.js/', 'wrojewory8fwso.jpg', '34', '427', '41', '2023-10-16 00:00:00', '1', '1', '我们对Markdown编辑器进行了一些功能拓展与语法支持，除了标准的Markdown编辑器功能，我们增加了如下几点新功能，帮助你用它写博客', 'Markdown编辑器使用介绍', '10', null);
INSERT INTO `text` VALUES ('55', '12', null, '23', '33', '4', '2023-10-17 10:20:34', '1', '0', '12', 'hadoop ha的搭建', '18', null);
INSERT INTO `text` VALUES ('56', '12', null, '12', '89', '1', '2023-10-17 16:30:00', '1', '0', '通过本次实训，要求初步掌握数据分析过程和Python数据分析常用包：Pandas、matplotlib、sklearn的基本使用。', '电影数据分析（一元线性回归）', '18', null);
INSERT INTO `text` VALUES ('57', '**（本文转载于知乎韩茹）**\n# 一、什么是Java\nJava是一种通用的，基于类的，面向对象的编程语言。它是用于应用程序开发的计算平台。因此，Java是快速，安全和可靠的。它被广泛用于在笔记本电脑，数据中心，游戏机，科学超级计算机，手机等中开发Java应用程序。Java遵循WORA（Write Once, Run Anywhere。一次写入，到处运行）的原理，并且与平台无关。它也简单易学。\n\n# 二、Java发展历史\nJava是世界上使用最广泛的编程语言之一。Java最初由Sun Microsystems在1990年代开发，用于开发从Web应用程序到移动应用程序到批处理应用程序的所有内容。Java最初是一种纯粹的面向对象的语言，但现在已经发展成为一种多范例语言，可以高度适应任何情况。拥有庞大的开发人员社区和支持库，Java是开发几乎所有类型的应用程序的理想选择。\n\nJava是最初由开发James Gosling(詹姆斯·高斯林，他被称为Java编程语言之父)在Sun Microsystems公司（现已经被Oracle收购），并于1995年发布了作为Sun公司的一个核心组成部分Java平台。\n\nJames Gosling(詹姆斯·高斯林)，Mike Sheridan(迈克·谢里丹)和Patrick Naughton(帕特里克·诺顿)于1991年6月启动了Java语言项目。Java最初是为交互式电视设计的，但在当时的数字有线电视行业来说太先进了。该语言最初是在James Gosling(詹姆斯·高斯林)办公室外的一棵橡树之后被称为Oak的。后来，该项目以Green命名，并最终从Java coffee(印度尼西亚的一种咖啡)重命名为Java。\n\nJava的创始人：James Gosling(詹姆斯·高斯林)\n![七龙珠](http://localhost:8001/zf/afgwhgsjovz.jpg)\n历史版本：\n\n截至2021年3月，最新版本是Java 16。从2020年9月开始，支持Java 8和11作为长期支持（LTS）版本，并且支持一个更高的非LTS版本。 Java的主要发行版本及其发行日期：\n![七龙珠](http://localhost:8001/zf/java.jpg)\n\n# 三、Java语言的特点\n## 3.1.一种面向对象的编程语言。\n按照对象集合的方式组织程序是一种面向对象的编程思维。\n面向对象编程的四个主要概念是：抽象化，封装，继承，多态。\n## 3.1.一种与平台无关的语言。(它提供了在不同平台下的解释运行环境)\n编译器将源代码转换为字节码，然后JVM执行编译器生成的字节码。该字节码可以在Windows，Linux和macOS的任何平台上运行，这意味着如果我们在Windows上编译程序，则可以在Linux上运行，反之亦然。每个操作系统具有不同的JVM，但是在字节码执行后，所有操作系统产生的输出都是相同的。这就是为什么我们称Java为平台无关的语言。\n## 3.3.简单\nJava是简单的语言之一，因为它不具有指针，运算符重载，多重继承，显式内存分配等复杂功能。\n## 3.4.一种健壮的语言，吸收了C/C++语言的优点。\nJava语言健壮，意味着可靠。它以这样的方式开发：尽力尽早检查错误，这就是Java编译器甚至能够检测到其他编程语言不易检测到的那些错误的原因。使Java健壮的Java主要功能是垃圾收集，异常处理和内存分配。\n具有较高的安全性。(自动回收垃圾，强制类型检查，取消指针)\n在Java中，我们没有指针，因此我们无法访问越界数组，如果尝试这样做，它将显示ArrayIndexOutOfBoundsException。这就是为什么在Java中无法利用堆栈破坏或缓冲区溢出之类的一些安全漏洞的原因。\n## 3.5.分布式\n我们可以使用Java编程语言创建分布式应用程序。远程方法调用和企业Java Bean用于在Java中创建分布式应用程序。Java程序可以轻松地分布在通过Internet连接相互连接的一个或多个系统上。\n## 3.6.多线程\nJava支持多线程。它是Java的一项功能，它允许并发执行程序的两个或更多部分，以最大程度地利用CPU。\n## 3.7.可移植\n众所周知，在一台机器上编写的Java代码可以在另一台机器上运行。Java的平台无关功能，其中可以将其平台无关字节码带到任何平台上执行，从而使Java可移植。\n## 3.8开源\n![七龙珠](http://localhost:8001/zf/aetfjslotgs.jpg)\n# 四、Java的应用场景\n## 4.1桌面GUI应用程序\n桌面应用程序可以使用Java轻松开发。我们使用AWT，Swing，JavaFX等API来构建这些应用程序。\n## 4.2.移动应用\n移动应用程序是为手机和平板电脑创建的应用程序。在当今时代，大多数电话和智能设备都具有Android操作系统，没有Java就不可能进行Android开发。Java Micro Edition（Java ME或J2ME）是一种流行的跨平台框架，用于构建可在所有功能手机和智能手机上运行的应用程序。\n## 4.3.企业应用\n企业应用程序是一个大型软件系统，可以在公司环境中运行，以满足组织而不是单个用户的需求。\n## 4.4.科学应用\n科学应用程序是一种使用数学影响实际活动的应用程序。Java由于其强大的功能而支持科学应用程序的开发。\n## 4.5.Web应用\nWeb应用程序是通过浏览器界面在Internet上交付的客户端-服务器程序。\n## 4.6.嵌入式系统\n一个嵌入式系统，也被称为集成系统，是组装在一起以执行专用功能的更大的系统的许多小的计算单元的组合。嵌入式系统随处可见。例如，电机系统，汽车中的娱乐和多媒体，电子商务，无线通信，移动计算和联网都使用嵌入式系统。\n嵌入式系统使用Java进行开发。最初，Java是为开发嵌入式系统而设计的。\n## 4.7.大数据技术\n大数据一词定义为“可以分析以提取模式，趋势和有用信息的极大型和复杂数据集。它是最新技术领域中最受欢迎的主题之一。Java是大数据的视角。如今，许多开发人员正在将职业转向大数据技术。\n## 4.8.分布式应用\n分布式应用程序是在网络内的多台计算机上执行或运行的应用程序或软件。分布式应用程序或系统具有许多共同的要求，尤其是由于它们在其上运行的平台的分布式和动态特性而产生。Java提供了实现这些应用程序的选项。\n## 4.9.基于云的应用\n云计算意味着通过Internet按需交付IT资源，包括具有按需付费定价模式的存储，服务器，数据库，网络和软件。它可以低成本为IT基础架构提供解决方案，因为我们可以将文件保存在远程数据库中并按需检索它们。长期以来，Java一直是为Web应用程序提供结构的编程语言，由于它的分布式特性，现在它已经进入了云应用程序。\n## 4.10.web服务器\nWeb服务器是一种计算机程序，它使用HTTP（超文本传输协议）和其他协议来存储，处理和响应通过WWW（万维网）发出的客户端请求。Web服务器是运行网站并向用户交付网页的系统。Java生态系统包含多个Java Web服务器和应用程序服务器。Java提供了Web服务器，包括Apache Tomcat，Simple，Jo！，Rimfaxe Web服务器（RWS）Apache HTTP服务器，Resin，Adobe JRun和Project Jigsaw。\n## 4.11.软件工具\n软件工具是开发人员用来开发，分析，维护，调试或支持其他应用程序和程序的一组计算机程序。许多开发人员使用Java编写和开发有用的软件工具。比如Eclipse，IntelliJ Idea和NetBeans IDE。\n## 4.12.游戏应用\nJava被证明是开发二维游戏的最佳平台之一。如今，几乎每个人都拥有装有Android游戏的Android手机。没有Java，就无法构建Android游戏。\n![七龙珠](http://localhost:8001/zf/ewsryhysgbshgbs.jpg)\n我们知道Java是一种“蓝领”语言，涉及软件开发的各个领域。可以根据业务需要使用。通过这些应用程序，我们意识到现实中对Java的需求。当我们需要开发安全且可扩展的应用程序时，我们绝对应该选择使用Java，因为Java具有主要的安全功能。这就是为什么最好用Java开发许多需要很多安全问题的银行应用程序的原因。因此，如果我们的业务规格与其功能相匹配，我们可以选择该语言。许多技术和编程语言都在发展，但是Java的普及程度仍然是其他语言无法比拟的。\n\n# 五、Java平台体系\nJava SE（Java Platform Standard Edition）。Java SE 以前称为 J2SE。它允许开发和部署在桌面、服务器、嵌入式环境和实时环境中使用的 Java 应用程序。\nJava EE（Java Platform Enterprise Edition）。这个版本以前称为 J2EE。 开发企业级的应用\nJava ME（Java Platform Micro Edition）。这个版本以前称为 J2ME。Java ME 为在移动设备和嵌入式设备（比如手机、PDA、电视机顶盒和打印机）上运行的应用程序提供一个健壮且灵活的环境.\n![七龙珠](http://localhost:8001/zf/v2ee19ef585d9cbf4978c06e9.jpg)\n# 六、Java的专业术语\n首先了解两个计算机术语：\n\n## 6.1.计算机程序：\n\n为了让计算机执行某些操作或解决某个问题而编写的一系列有序指令的集合\n\n编写程序的工具就是计算机语言，Java就是多种语言中的一种\n\n## 6.22.软件：\n\n软件是计算机程序、程序所用的数据以及有关文档资料的集合。\n\n\n# 七.总结\n在学习Java之前，必须熟悉这些Java通用术语。\n## 7.1.1. Java虚拟机\nJava虚拟机（JVM：Java Virtual Machine）： 通常称为JVM。程序分为三个执行阶段。他们被编写，编译并运行程序。\n\n像你和我这样的Java程序员都可以编写程序。\n编译由JAVAC编译器完成，JAVAC编译器是Java开发工具包（JDK）中包含的主要Java编译器。它以Java程序作为输入，并生成字节码作为输出。\n在程序的运行 阶段，JVM执行编译器生成的字节码。\n现在，我们了解到Java虚拟机的功能是执行编译器产生的字节码。每个操作系统都有不同的JVM，但是在执行字节码后它们在所有操作系统上产生的输出都是相同的。这就是为什么Java被称为独立于平台的语言的原因。\n## 7.2.2.开发过程中的字节码\n开发过程中的字节码： JDK的Javac编译器将Java源代码编译为字节码，以便可以由JVM执行。编译器将其另存为.class文件。要查看字节码，可以使用像javap这样的反汇编程序。\n## 7.3.Java开发工具包（JDK）\nJava开发工具包（JDK）：当我们使用术语JDK时，当我们了解字节码和JVM时。因此，顾名思义，它是一个完整的Java开发工具包，其中包括编译器，Java运行时环境（JRE），Java调试器，Java文档等所有内容。要在Java中执行该程序，我们需要在我们的计算机上安装JDK为了创建，编译和运行Java程序而在计算机上运行。\n## 7.4.Java运行时环境（JRE）\nJava运行时环境（JRE）： JDK包括JRE。在我们的计算机上安装JRE可以运行Java程序，但是，我们无法对其进行编译。JRE包括浏览器，JVM，小程序支持和插件。为了运行Java程序，计算机需要JRE。\n## 7.5.5.垃圾收集器\n垃圾收集器：在Java中，程序员无法删除对象。要删除或重新收集该内存，JVM拥有一个名为Garbage Collector的程序，简称GC。垃圾收集器可以重新收集未引用的对象。因此，Java通过处理内存管理使程序员的工作变得轻松。但是，程序员是否应该使用自己的代码，无论他们使用的对象已经使用了很长时间了。因为垃圾无法恢复被引用对象的内存。\n\nCLASSPATH中的类路径是文件路径ava运行时和Java编译器找的.class文件加载。默认情况下，JDK提供了许多库。如果要包括外部库，则应将它们添加到类路径中。\n# 八、Java运行核心机制\n刚刚提到了Java程序执行的三个阶段，我们再来详细的说一下：\n\n## 8.1.计算机的高级编程语言类型：\n\n编译型 C /C++/Pascal//Golang等，会生成二进制文件。比如：.exe文件。\n解释型 Basic/Python/JavaScript / Perl /Shell不会生成二进制文件\n而Java 语言是两种类型的结合；先编译,再解释执行\n![七龙珠](http://localhost:8001/zf/retewtyg3453.jpg)\n\n## 8.2.Java虚拟机(Java Virtual Machine) JVM\n\n### 8.2.1 JVM可以理解成一个可运行Java字节码的虚拟计算机系统。 \n 它有一个解释器组件，可以实现Java字节码和计算机操作系统之间的通信\n 对于不同的运行平台，有不同 的JVM。\n### 8.2.2 JVM屏蔽了底层运行平台的差别，实现了“一次编译，随处运行”。\n所以Java语言是跨平台的。\n![七龙珠](http://localhost:8001/zf/e5t64tyedrgvs.jpg)\n![七龙珠](http://localhost:8001/zf/seteye5gvbdsgvsgvs.jpg)\nJVM可以理解成一个可运行Java字节码的虚拟计算机系统。\n\n它有一个解释器组件，可以实现Java字节码和计算机操作系统之间的通信\n对于不同的运行平台，有不同 的JVM。\nJVM屏蔽了底层运行平台的差别，实现了“一次编译，随处运行”。\n![七龙珠](http://localhost:8001/zf/3w453464asfc.jpg)\n![七龙珠](http://localhost:8001/zf/5464sdgvs.jpg)\n\n', '1f20816e15694cf3b56262c1f87e2a14.png', '24', '6573', '4', '2023-10-17 19:24:20', '2', '1', 'Java是一种通用的，基于类的，面向对象的编程语言。它是用于应用程序开发的计算平台。因此，Java是快速，安全和可靠的。', 'JAVA介绍', '10', null);
INSERT INTO `text` VALUES ('58', '**转载于CSDN博主「CSDN资讯」的原创文章**\n**原文链接：https://blog.csdn.net/csdnnews/article/details/133820142**\n\n机器人的一小步，AI 的一大步。\n\n近日，美国西北大学（Northwestern University）的研究人员首次开发出一种可以完全自行设计机器人的 AI 算法。\n![七龙珠](http://localhost:8001/zf/9d6ab948f858958a844fc4eb61d7df4a.jpg)\n\n当该团队向 AI 程序发出提示：“设计一个可以在平坦表面上行走的机器人”。\n\n出乎意料的结果发生了，引用西北大学官方的评价，「大自然花了数十亿年的时间才进化出第一个行走的物种，而新 AI 算法程序将进化压缩到闪电般的速度」。\n\n不到 30 秒，就能设计出一个成功行走的机器人。\n\n为了验证计算机中模拟的系统在实践中是否有效，研究人员通过 3D 打印设计的模具并填充硅胶，最终在 AI 系统的驱动下，得到了如下所示：一个虽然有些“蠢萌”，但是能以“大约是人类平均步幅的一半”的速度开始行（蠕）走（动）的机器人。\n\n\n\n不少网友网友评价道：\n\n奥创世界，这个 AI 成精了；\n\n未来已至；\n\nAI 审美还是有点差；\n\n......\n\n\n\n“AI 设计工具新时代的开端”\n\n这个像是上了发条的玩具，是由无机材料制成的，其背后运用的 AI 程序还可以在轻便的个人电脑上运行，并从零开始设计全新的结构。\n\n基于此，西北大学的研究员在《美国国家科学院院刊》上发布了一篇《Efficient automatic design of robots》论文（https://www.pnas.org/doi/10.1073/pnas.2305180120），分享了这一最新研究成果。\n\n\n\n在领导这项工作的 Sam Kriegman 研究员看来，机器人本身又小又软，而且形状诡异，实则并不起眼。\n\n不过，它的诞生，代表了 AI 设计工具新时代的开端，未来这些工具就像动物一样，可以直接作用于世界。\n\n\n\n# 一.从零到走路只需几秒钟\n\n放眼市场上已存在的很多 AI 系统，创建进化机器人需要生成随机变体，对其进行测试，用新的变体完善表现最佳的机器人，然后再次测试这些变体。\n\n周而复始的工作通常需要高耗能的超级计算机和巨大的数据集支持。同时，即使在处理了所有这些数据之后，那些系统也受制于人类创造力的局限，仅仅是模仿人类过去的作品，而没有产生新想法的能力。\n\n在本次实验中，研究员使用一种自动优化方法，可以通过追踪机器人行为中的失败以找出物理结构中的错误或低效部位，从零开始设计可以自主移动的机器人。\n\n以这种方式改进机器人，它可以比以前(设计师以试错的方式尝试不同的机器人设计)更快地优化机器人中互相关联的部件。这为快速、按需定制人工智能驱动的机器人设计开辟了道路，可用于各种任务。\n\n“我们告诉 AI，我们想要一个可以穿越陆地的机器人。然后我们只需按下一个按钮就可以了！它在眨眼间就生成了一个机器人的蓝图，它看起来与地球上行走过的任何动物都截然不同。我把这个过程称为‘即时进化’”，Sam Kriegman 表示。\n\n这一过程到底是怎么实现的？\n\n具体来看，研究人员在论文中展示了一种可替代的、可拓展的方法，可以在最少的先验假设情况下快速且经济高效地设计新型机器人身体：\n\n只需要 10 次设计尝试，就能在笔记本电脑上用不到 30 秒的时间高效、自动地生成定制机器人。\n![七龙珠](http://localhost:8001/zf/4b215ba7ad1559f6614908b1401fee98.png)\n当然，以上主要是在计算机中模拟的。这比所有已报道的利用计算机模拟的机器人设计方法都要高效得多，比不进行模拟的方法少了一个数量级的设计尝试。\n\n尽管之前也有很多研究人员探索了不少虚拟机器人自动设计的方法，但迄今为止，物理机器人的自动设计主要是通过进化算法实现的：设计变体通过随机变异和选择进化。\n\n正如上文所述，这一过程的试错性质可能导致在虚拟环境中对数千甚至数百万个机器人进行评估，然后选择一个适合物理制造的机器人。其他的进化方法可以直接进化出物理机器人，而无需借助模拟，但这需要数以百计的物理试验，再次降低了效率。\n\n在这里，研究人员使用了基于梯度下降优化的方式，可以有效地沿着设计空间的路径找到最优设计。\n\n事实证明，将基于梯度的优化技术应用于机器人设计也具有挑战性，因为与所有运动生物一样，机器人的身体规划（如形状和电机分布）与其行为之间存在复杂的反馈回路。\n\n确定机器人身体规划的哪个方面导致了机器人行为的低效，以及如何改变该身体部分以改善行为，是 AI 设计机器人过程中非常常见且必须解决的问题。\n\n为此，研究人员采用了近些年才出现的可微分物理模拟器使得基于梯度的虚拟机器人设计成为可能。即机器人的形状或材料特性中导致不良行为的方面可以被识别出来，并以非随机的方式减轻以改善其行为。\n\n但这些方法无法从根本上改变机器人的内部结构（肌肉组织、质量分布和空隙）或外部结构，如增加新的肢体。因此，研究人员最新研究了一种新的算法，它可以：\n\n(i) 模拟并评估虚拟机器人的行为适应性；\n\n(ii) 找出机器人在整体形状、拓扑结构（空隙数量）、肢体数量和形状、质量分布、肌肉组织和行为控制方面的不足；\n\n(iii) 同时改变所有这些方面，以便在下一次模拟中改善机器人的行为。\n![七龙珠](http://localhost:8001/zf/4cfe5596a5299a5a5b8ba3bf5c6e1cca.png)\n\n经过证明，这一过程只需重复九次（只需进行 10 次模拟），就能在构建设计时保留优化后的功能。这比最先进的物理机器人自动设计算法要高效得多。\n\n\n\n# 二.3D 打印出模具，实践中测试\n\n为了验证计算机中的模拟在实践中是否有效，研究人员通过 3D 打印设计的模具。在材料方面，研究人员准备了一个 20×14 厘米的长方体中随机放置 64 个空洞和 64 块“肌肉块”。\n\n然后研究员使用液体硅橡胶填充到模具中，并使其固化几个小时。当团队将固化的硅胶从模具中取出时，它变得又软又柔韧。\n\n通过 3D 打印出模具之后，他们将空气注入长方体的小空隙中，以模拟肌肉的收缩和扩张。\n\n制成的机器人每个都只有一块肥皂大小，像块状的小卡通人物一样。研究人员为人工智能设定了开发陆地运动的目标，然后将新生的机器人置于虚拟环境中进行进化。\n\n起初，这个机器人可以摇晃，但绝对无法行走。\n\nAI 知道自己尚未达到目标。因此迅速迭代了设计。在每次迭代中，AI 评估其设计、识别缺陷，并逐渐修改模拟块的结构。\n\n更令人惊讶的是，AI 独自想出了与自然界相同的行走解决方案：腿。但与自然界绝对对称的设计不同，人工智能采取了不同的方法。由此产生的机器人有三条腿，背部有鳍，面部平坦，上面布满了洞。\n\n“这很有趣，因为我们没有告诉人工智能机器人应该有腿”，Kriegman 表示，“它重新发现腿是在陆地上移动的好方法。事实上，腿式运动是陆地运动最有效的形式。”\n\n最终，经过九次尝试，它生成了一个每秒可以行走其身体长度一半的机器人——大约是人类平均步幅速度的一半。\n\n整个设计过程——从一个零运动的无形块到一个完整的行走机器人——在笔记本电脑上只花了 26 秒。\n![七龙珠](http://localhost:8001/zf/3638a525bc5af048c71649f475acc6b0.png)\n\n“现在任何人都可以观察 AI 实时生成越来越好的机器人身体的进化过程”，Kriegman 表示，“以前，进化机器人需要在超级计算机上进行数周的试验和错误。当然，在任何动物可以在我们的世界里奔跑、游泳或飞翔之前，都有数十亿年的试错过程。这是因为进化没有先见之明。它无法预见未来，无法知道特定的突变是有益的还是灾难性的。我们找到了一种方法来消除这种蒙蔽，从而将数十亿年的进化压缩为瞬间。”\n\n\n\n# 三.AI 设计的机器人未来\n\n通过深度研究，得到的结果最终证明了移动机器人的高效设计自动化，不过，Kriegman 也表示，大部分设计流程仍有待优化。例如，制造过程包括几个可以通过嵌入式 3D 打印实现自动化的手动步骤；物理模型的计算效率可以优化为比实时运行得更快，并且梯度计算可以优化为也更快。\n\n截至目前，尽管这个由 AI 设计的首个机器人只能向前移动，但 Kriegman 也在畅想未来的应用场景，譬如：\n\n有一天，类似的机器人可能能够在倒塌建筑物的废墟中导航，遵循热图像和振动信号来搜索被困的人和动物，或者它们可能会穿越下水道系统来诊断问题、疏通管道和修复损坏；\n\n人工智能还可能设计出纳米机器人，进入人体并引导血液流动，以疏通动脉、诊断疾病或杀死癌细胞。\n\n如果对此你感兴趣，也可以查看更为详细的论文：https://www.pnas.org/doi/10.1073/pnas.2305180120#sec-1\n\n同时，这篇论文涉及到的源代码也被开源到 GitHub上：https://github.com/robodiff/robodiff\n————————————————\n**版权声明：本文为CSDN博主「CSDN资讯」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。**\n**原文链接：https://blog.csdn.net/csdnnews/article/details/133820142**', null, '24', '2343', '1', '2023-10-17 19:43:25', '2', '0', '网友：AI 已“成精”，只是审美还不行！...', '仅用 26 秒！AI 设计了一款可行走的机器人', '13', null);
INSERT INTO `text` VALUES ('59', '**转载于CSDN博主「CSDN资讯」的原创文章**\n**原文链接：https://blog.csdn.net/csdnnews/article/details/133820151**\n[文章地址](https://blog.csdn.net/csdnnews/article/details/133820151)\n![七龙珠](http://localhost:8001/zf/42f26b154fad57bf4a14e75fa55ed2db.png)\n                               \n众所周知， ChatGPT 训练成本非常昂贵。外媒 Analytics India Magazine 曾指出，仅是运行人工智能服务 ChatGPT，OpenAI 每天就要花费近 70 万美元。\n\n但更让许多开发者「望而却步」的是，它的使用成本。\n\n根据 OpenAI 网站上的定价，目前，使用 GPT-4 处理一页文档可能需要 10 美分（约人民币 0.72 元），具体金额取决于输入和输出的长度和复杂性。\n\n在此次更新中，就直击了这一痛点。\n\n据路透社报道，OpenAI 将在开发者工具中增加内存存储。\n\n理论上，这可以将应用程序的成本降低 20 倍左右。此外，也能解决合作伙伴通过开发、销售 AI 软件建立业务时，需要支付 ChatGPT 模型高昂的成本问题。\n\n\n\n新的视觉开发工具，应用多个领域\n\n此外，本次「重磅更新」中，OpenAI 还计划推出新的视觉开发工具。\n\n其功能可以处理和生成除文本之外的不同类型的媒体，例如图像、音频和视频。\n\n开发人员能够借此构建具有分析图像和描述图像能力的应用程序，将其应用在医疗、金融、电商、娱乐等领域。\n\n据路透社报道，几项新功能预计将于 11 月 6 日在旧金山举行的 OpenAI 首届开发者大会  OpenAI DevDay 上正式推出。\n\n目前，OpenAI 官方尚未作出回应。\n\n\n\n知情人士：“让开发者满意是 OpenAI 的关注重点”\n\n根据 PitchBook 的数据，今年投资者已向人工智能初创公司投入了超过 200 亿美元，其中许多初创公司都依赖 OpenAI 或其他基础模型公司的技术。\n\n路透社曾报道，有知情人士称：“让开发者满意是 OpenAI 的关注重点。”\n\n事实上，OpenAI 作为 ChatGPT 幕后推手， 在其风靡全球、吸引投资者和开发者的背后，可谓是「劳苦功高」。\n\n今年 8 月，OpenAI 发布了支持自定义微调的 GPT-3.5 Turbo ，用户只需上传自己的数据，即可运行专属的定制大型模型，为用户提供了更加灵活和高效的 AI 应用解决方案。\n\n今年 9 月，OpenAI 发布了最新文生图模型 DALL·E 3 ，旨在增强产品矩阵，提升 ChatGPT 的多模态输出能力。DALL·E 3 的现世，代表了 OpenAI 在文本理解、图片生成、图片类型等方面上的重大飞跃，甚至有人称其能与 Midjourney 相媲美。\n\n如今，DALL·E 3 目前已被微软集成在 Bing Chat 等产品中，获得了用户的广泛好评。\n\n\n\n众望所归 or 顺势而为？\n\n但不容忽视的是， OpenAI 在吸引外部人士利用其技术开展业务方面仍面临着诸多挑战。\n\n为此，OpenAI 首席执行官 Altman 还会见了多位开发人员，表达了基于 OpenAI 模型构建新生态系统的愿望。\n\n针对「此次更新」，知情人士表示：可能是旨在鼓励公司使用 OpenAI 的技术来构建人工智能驱动的聊天机器人和自主代理，吸引更多开发者付费访问 OpenAI 的模型，以构建自己的 AI 软件，用于各种用途，例如写作助手或客户服务机器人。\n\n并且，这些机器人和自主代理可以在无需人工干预的情况下执行任务。\n\n紧随着「 OpenAI 计划重磅更新的新模型」的消息爆出，有人认为这将便利开发人员参与模型功能的迭代，是「众望所归」的结果：“造福开发者了，值得期待”；“成本降低是深入人心的操作”。\n\n也有人认为这是抢夺市场的「顺势而为」：“过去 ChatGPT 曾经历应用狂潮到负面消息不断， OpenAI 此举或许只是在保证自己竞争力”；“对于 OpenAI 来说，无论升级还是推新，占据市场才是最重要的事”……\n\n对此，你怎么看？\n————————————————\n**版权声明：本文为CSDN博主「CSDN资讯」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。**\n**原文链接：https://blog.csdn.net/csdnnews/article/details/133820151**', null, '10', '4568', '0', '2023-10-17 19:54:40', '2', '0', '成本降20%！OpenAI被爆将出“杀手锏”，用更低的价格开发专属ChatGPT', 'OpenAI被爆将出“杀手锏”', '13', null);
INSERT INTO `text` VALUES ('60', '**转载于CSDN博主「CSDN资讯」的原创文章**\n**原文链接：https://blog.csdn.net/csdnnews/article/details/133801915**\n[原文地址](https://blog.csdn.net/csdnnews/article/details/133801915)\n\n近段时间，微软内部正在用新技术不断刷新内部应用，譬如其使用 Rust 取代 C/C++ 编写 Windows 驱动；放弃了 Electron 框架而采用 Edge WebView 2 来重写 Teams；WordPad 停止更新，官方建议采用 Microsoft Word 或者 Windows 记事本；Microsoft Store 的 Web 版本中放弃旧的 React 代码库取而代之使用的是 Shoelace、Lit、Vite 和 C# ASPNET 后端的现代 Web 版本......\n\n当前，这家科技巨头在“Windows 客户端已弃用功能”列表中又添加了一名新成员——VBScript。微软计划在未来的 Windows 版本中逐步淘汰已使用了近 30 年的 VBScript，“最初，VBScript 按需功能将被预安装，以便在您准备淘汰 VBScript 之前可以无缝使用，直至后续被完全删除。”\n![七龙珠](http://localhost:8001/zf/bbaee8a5eb480e4fb88c44104751fa34.png)\n\n\n\n![七龙珠](http://localhost:8001/zf/48da9fb7bd92ddaa03e04d8a4eb94125.png)\n**诞生于 27 年前，13 年未更新**\n\n作为一种脚本语言，VBScript 于 1996 年首次亮相，凭借易于学习、与 Windows 环境无缝集成、可用于网页编程等方便的功能特性，它在 Windows 平台曾被广泛使用，尤其是在网页脚本和自动化任务方面。\n\n“微软 Visual Basic Scripting Edition 在各种环境中实现了自动化脚本，包括在 IE 中进行 Web 客户端脚本编程以及在 Microsoft Internet Information Services 中进行 Web 服务器脚本编程，”微软在其帮助文档中解释道。\n\n这也成为很多程序员的编程的回忆：\n\n我的入门语言便是 VBScript；\n\n在曾经看《计算机是怎样跑起来的》这本书时，我使用 Visual Basic Scripting Edition 复现了“剪刀石头布”小游戏。\n\n遗憾的是，一方面，随着 2006 年 PowerShell 的推出，VBScript 黯然失色，很多人将其看成被滥用的安全噩梦；另一方面，微软从未设法让其他浏览器制造商支持 VBScript，因此在微软专有的环境之外，Web 开发人员倾向于使用 JavaScript 来执行客户端任务，使用 VBScript 的用户越来越少。\n\n最终，VBScript 的最新版本停留在了 2010 年 5.8 版本。\n\n\n\n如今，微软宣布在过渡期，VBScript 将作为按需安装使用，其中功能按需 (FODs) 是 Windows 操作系统中的可选功能，如 .NET Framework（.NetFx3）、Hyper-V 和 Windows  Subsystem for Linux，它们不会默认安装，但可以根据需要随时添加。\n\n![七龙珠](http://localhost:8001/zf/6c2d165e9409cfa3073d390a64145b95.png)\n\n**试图拯救 VBScript 的开发者**\n\n面对这种情况，曾经有不少 VBScript 爱好者不愿放弃，试图挽救。\n\n据 TheRegister 报道，2017 年，微软软件工程师 Zhihua Lai 创建了一个名为 IsVBScriptDead.com（https://isvbscriptdead.com/）的网站，他在网站开篇便写到，「VBScript 或 VBA 既没有消亡，也没有垂死，它仍然是一种非常稳定的脚本语言」。\n\n然而，两年后的 2019 年 8 月，微软已经表现出不想再用 VBScript 的预兆，其在 IE 中禁用了 VBScript，并在 2022 年 6 月淘汰了 IE 浏览器，因此 VBScript 仍然活跃且运行良好的说法变得更加站不住脚。\n\n尽管如此，最后更新于 2020 年 12 月 31 日的 VBScript 网站坚持称：\"VBScript 可能看起来已经过时，而对 VBScript 的支持已经停止，但它仍然在许多地方使用。Windows 管理员可能会发现学习/编写 VBScript 比 Powershell 脚本更容易。\"\n\n现在随着微软最新举措的发布，彻底证实了 VBScript 即将彻底消亡，这也意味着依赖于VBScript 的 Microsoft Deployment Toolkit（MDT）迎来了结束。现在，MDT 已经收到通知，不再支持 Windows 11。\n\n另外，Microsoft 还默认禁用了 Windows 10 上 Internet Explorer 11 中的 VBScript。\n\n\n![七龙珠](http://localhost:8001/zf/5851936c285310069836515e25e19faf.png)\n**VBScript 为何会走向末路？**\n\n截至目前，微软并没有透露弃用 VBScript 的细节，但是不少开发者猜测，它可能与微软早些时候停用 IE 浏览器有关。\n\n从外部来看，微软计划停用 VBScript 也有可能是出于安全担忧，正如上文所述，VBScript 是不少安全工程师的噩梦，它成为恶意软件传播途径之一。\n\n此前，有不少黑客使用 VBScript 来分发恶意软件感染用户的计算机，包括臭名昭著 Lokibot、Emotet、Qbot，以及最近的 DarkGate 等恶意软件。\n\n而从内部来看，VBScript 是一个 Windows 特定的技术，无法跨平台运行。随着移动设备和其他操作系统的普及，这使得它在多平台环境中变得不切实际。与此同时，外部如 JavaScript、PowerShell 和 Python 等更强大、更现代的脚本语言和技术出现，VBScript 变得过时显而易见。\n\n面对 VBScript 的淘汰，不少网友似乎“喜闻乐见”：\n\n大约 20 年前，我曾使用过它来编写一些脚本，用于操作和报告其他内容的文本文件输出。如果我当时使用的是 Linux 系统而不是 Windows 系统，我本可以使用 awk，但当时使用的是 Windows，我不被允许安装不是原本就存在的东西。\n\n我对它的记忆很模糊，但我确实记得它... 糟糕透了。我没有再次体验的愿望。\n\n我怀疑它不会被怀念，除了一小部分人，他们继承了一些古老的脚本，必须对其进行逆向工程和重写成其他不那么晦涩的东西。\n\n你是否使用过 VBScript？对于它还有什么样的印象？\n————————————————\n**版权声明：本文为CSDN博主「CSDN资讯」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。**\n**原文链接：https://blog.csdn.net/csdnnews/article/details/133801915**', null, '35', '800', '21', '2023-10-17 20:01:22', '2', '0', 'VBScript 走向末路！', '微软决心移除 Windows 中已用 27 年的旧技术', '15', '2023-10-18 19:54:01');
INSERT INTO `text` VALUES ('61', '**转载于CSDN博主「CSDN资讯」的原创文章**\n**原文链接：https://blog.csdn.net/csdnnews/article/details/133723441**\n[原文地址](https://blog.csdn.net/csdnnews/article/details/133723441)\n\n今天，10 月 TIOBE 编程语言榜单已最新出炉，一起来看看本月有什么值得关注的新变化吧！\n\n\n![七龙珠](http://localhost:8001/zf/3ad7e91303339214038e7201247ad517.jpg)\n\n\n# 被 C# 超越，Java 即将跌至第 5 名？\n\n去年 12 月，Java 历史首次被 C++ 超越、跌出榜单前三的事实，令许多程序员深感意外，由此发出不少 “Java 英雄迟暮？”、“Java 真的已死？”的疑惑。自此以后，这 10 个月来 Java 在 TIOBE 编程语言榜单的排名再也没上过前三，与 C++ 之间的差距也日益扩大。\n\n可能很难预料到，昔日常年霸榜的 Java 在被挤出 Top 3 之后，如今甚至还面临着被 C# 超越、即将跌至第 5 名的危险。\n\n从市场份额来看，Java 在 Top 20 编程语言中的降幅最大，为 3.92%；反观 C# 的涨幅高达 3.29%，彼此仅相差 1.2% —— 按照 TIOBE 的官方说法：“C# 和 Java 之间的差距从未如此之小。如果这种趋势保持不变，预计 C# 将在两个月后超过 Java。”\n![七龙珠](http://localhost:8001/zf/797f10c38a14eb36f8b348295ebe5836.png)\n\n回顾 C# 诞生的这 23 年来，它与 Java 之间因相似的特性与应用领域一直竞争不断。2000 年，微软发布由 C 和 C++ 衍生出来的面向对象的编程语言 C#，也是运行于 .NET Framework 和 .NET Core（完全开源，跨平台）之上的高级程序设计语言，由 Delphi 和 TypeScript 之父、.NET 创立者 Anders Hejlsberg 开发。\n\n乍看之下，C# 与 Java 惊人地相似：面向对象编程的语言，有着几乎同样的语法，都需要编译成中间代码再运行，都支持跨平台，都支持单一继承和多接口实现，都提供自动垃圾回收功能，都支持强引用和弱引用，此外异常处理方式也几乎相同……不过，C# 与 Java 之间的区别也很明显：C# 借鉴了 Delphi 的一个特点，与 COM（组件对象模型）直接集成，还支持在创建数组时既定义数组大小又给数组赋值，同时支持使用预处理程序指令进行条件编译和 goto 语句等等。\n\n然而，Java 受欢迎程度下降的原因不仅是因为后来者 C# 的各种优势，也因为甲骨文公司在 Java 8 之后决定引入付费许可模式。而微软在 C# 上则是采取了相反的策略：先是只能用作商业工具 Visual Studio 的一部分使用，如今 C# 已是免费开源的，因此受到了许多开发人员的欢迎。\n![七龙珠](http://localhost:8001/zf/9cc1f43aa873a9886e4c635ccb6ce969.png)\n![七龙珠](http://localhost:8001/zf/d220c5c74ffbc5bd440585afc7095e74.png)\n\n除了付费许可模式，TIOBE 还推测了一些 Java 逐年衰落的其他原因，例如 Java 语言的定义在过去几年里没有太大变化，相比之下“其完全兼容的直接竞争对手 Kotlin 更容易使用，而且免费”——似乎是印证了这个说法，在本月的 Top 20 榜单中，Kotlin 继上个月强势“杀回” Top 20 后，本月再次上升两个名额，到了第 18 名。\n\n\n\n# 其他编程语言\n\n以下为 Top 21-50 的编程语言榜单：\n![七龙珠](http://localhost:8001/zf/c9e2e5443af21520ecb8723310fe5a18.png)\n\n\n第 51-100 名如下，由于它们之间的数值差异较小，仅以文本形式列出（按字母排序）：\n\n4th Dimension/4D, ABC, ActionScript, Apex, APL, Ballerina, bc, CL (OS/400), Clean, Clipper, CLIPS, Clojure, Crystal, Curl, Eiffel, Elixir, Erlang, Forth, Groovy, Hack, Icon, IDL, Io, J, J#, LabVIEW, Ladder Logic, LiveCode, ML, Modula-2, MQL5, NATURAL, Nim, OCaml, OpenEdge ABL, PL/I, PostScript, Pure Data, Q, Racket, Raku, REXX, RPG, Smalltalk, SPARK, SQR, Tcl, TOM, VHDL, Wolfram\n\n# Top 10 编程语言 TIOBE 指数走势（2002-2023）\n![七龙珠](http://localhost:8001/zf/b9434d07b72f2b160ea6f94798eccf30.png)\n\n# 历史排名（1988-2023）\n注：以下排名位次取决于 12 个月的平均值。\n![七龙珠](http://localhost:8001/zf/fe6b30b0c62cbb2aeff77227c3a6fc38.png)\n\n\n\n\n编程语言“名人榜”（2003-2022）\n![七龙珠](http://localhost:8001/zf/e0331171397d70c4d478b3502e854d13.png)\n\n# 【说明】：\n\nTIOBE 编程语言社区排行榜是编程语言流行趋势的一个指标，每月更新，这份排行榜排名基于全球技术工程师、课程和第三方供应商的数量，其中包括了流行的搜索引擎以及技术社区，如 Google、百度、维基百科、CSDN、必应、Hao 123 等等。具体的计算方式详见：https://www.tiobe.com/tiobe-index/programming-languages-definition/。请注意这个排行榜只是反映某个编程语言的热门程度，并不能说明一门编程语言好不好，或者一门语言所编写的代码数量多少。\n\n这个排行榜可以用来考察你的编程技能是否与时俱进，也可以在开发新系统时作为一个语言选择依据。\n\n详细榜单信息可参考 TIOBE 官网：https://www.tiobe.com/tiobe-index\n————————————————\n****版权声明：本文为CSDN博主「CSDN资讯」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。****\n****原文链接：https://blog.csdn.net/csdnnews/article/details/133723441****', null, '23', '7983', '3', '2023-10-17 20:11:28', '2', '0', 'Java 危！C# 全力逼近，差距已历史最小 | TIOBE 10 月榜单发布', 'Java 危！C# 全力逼近，差距已历史最小', '12', null);
INSERT INTO `text` VALUES ('62', '**转载于CSDN博主「CSDN资讯」的原创文章。**\n**原文链接：https://blog.csdn.net/csdnnews/article/details/133723432**\n[原文地址](https://blog.csdn.net/csdnnews/article/details/133723432)\n\n近日，据阿尔伯塔大学机器智能研究所（Amii）官方报道，强化学习之父 Richard Sutton 与 传奇程序员 John Carmack 联手入局 AGI 。\n\n一切旨在 2030 年向公众展示通用人工智能的目标是可行的。\n![七龙珠](http://localhost:8001/zf/a50cb52ee849b07498fc84ea58224d54.png)\n\n\n\n\n# 强化学习之父、3D 游戏之父与 AI 的渊源\n\nRichard Sutton 的大名，在人工智能领域可谓是如雷贯耳。\n\n除了是现代计算型强化学习创始人之一，Richard Sutton 也是 DeepMind 的杰出研究科学家、阿尔伯塔大学计算科学系教授。\n\n此外，他还被任命为伦敦皇家学会、加拿大皇家学会、人工智能促进协会会员。\n\n在 Richard Sutton 过去的职业生涯中，曾做出了多项重大贡献，包括时差学习理论、actor-critic（策略梯度）类算法、Dyna 架构（集成学习、规划和反应）、Horde 架构和梯度和强调时间差分算法。\n\n另一位主角，被誉为 3D 游戏之父的 John Carmack ，同样是位传奇人物。\n\n1991 年，他和 John Romero 共同建立游戏公司 id Software。随后，他和 John Romero 共同开发出了全球首款 3D 射击游戏《德军总部3D》，并在这款 3D 游戏上采用了他独创的 3D 游戏引擎。\n\n凭借在 3D 游戏、引擎、图形等领域的造诣，John Carmack 被世人熟知，并称其为 3D 游戏之父。\n\n2013 年， John Carmack 从 id Software 辞职，以 CTO 身份加入 Oculus 。后一年，他又随 Oculus VR 并入 Meta 旗下，此后成为 Meta VR 的一员。\n\n2019年，他辞去 Oculus CTO 职位转而担任“顾问 CTO ”，将更多精力转向AI。\n\n去年 8 月，John Carmack 在 Twitter 上透露，将再次进行创业，成立了一家名为 Keen Technologies 的创业公司。据悉，首轮 2000 万美元融资由 Nat Friedman、Danial Gross、Patrick Collision、Tobi Lutke、Jim Keller、红杉资本和 Capital Factory 领投。\n\n去年 12 月， John Carmack 辞去了 Meta 的首席技术官顾问职务，将全部重心都放在 AGI 上。\n![七龙珠](http://localhost:8001/zf/099c7d588f80623e84f5082256ca4d66.png)\n\n\n\n\n# 双剑合璧前，关于 AGI 的「共识」\n\n事实上，在两位大佬正式携手创业前，就已有了理念的碰撞。\n\n据悉， John Carmack 宣布 AGI 创业公司 Keen Technologies 的融资后，就收到 Richard Sutton 的邮件。\n\n多次沟通交流后，他们逐渐发现在 AI 研究方向和理念上非常相似。并且，还达成了关于「 AGI 」的 4 个共识：\n\n对当前 AGI 的发展提出了独特的见解，认为目前 AGI 进展对大数据和大算力的过度依赖，忽视了创新的重要性；\n\n太早商业化会阻碍 AGI 的发展；\n\n最终 AGI 不会太复杂，一个人就能掌握全部原理，甚至一个人就能写出主要的代码；\n\n2030 年出现 AGI 原型是一个可行的目标。\n\n\n\n# 不依赖大模型范式，更追求实时的在线学习\n\n在共识的交织之下，如今 John Carmack 和 Richard Sutton 正式宣布合作，携手致力 AGI 的发展。\n\n两人声称：他们将不依赖大模型范式，更追求实时的在线学习。\n\n未来将计划 AGI 采用模块化和分布式的架构，而不是依赖庞大而集中的模型。并且，他们还将追求持续的在线学习，而非现有的预训练模型后停止参数更新的方式。他们相信，最终的 AGI 源代码可以由一个人编写，只需几万行。\n\n除此之外， John Carmack 和 Richard Sutton 还提出了一个引人注目的观点：实现 AGI 所需的数据量和算力需求，可能没有人们想象的那么大。只要算法正确，AGI 无需借助整个互联网的数据进行学习。\n\n目前， John Carmack 和 Richard Sutton 的合作计划被称为 \"阿尔伯塔计划\"，提出了统一的智能体框架，并制定了分为 12 步的路线图。\n\n值得关注的是， Richard Sutton 还会加入 John Carmack 的 AI 创业公司 Keen Technologies，同时保持在阿尔伯塔大学的教职。\n\n虽然与拥有成百上千员工的大公司相比，Keen Technologies 目前还处于刚刚起步阶段，但他们仍然坚信，当前 AI 领域正处在杠杆效应最大的时刻，即使是小团队也能做出巨大贡献。\n\n此次合作，也成为了 Keen Technologies 继 2022 年 8 月宣布首次融资后的第一个公开里程碑。\n\n\n\n# 网友：“大佬联手，AI 的裂变演进要开火了？”\n\n在过去，传奇程序员 John Carmack 和 AI 泰斗 Richard Sutton 都对各自的领域产生了巨大的影响。\n\n因此，无论是他们联手开创 AGI 创业新纪元，还是打破常规「不依赖大模型范式」，都毋庸置疑的成为大家关注的话题。\n\n针对此次与 Richard Sutton 的合作， John Carmack 也表示：“人工智能领域充斥着资本、计算和数据，但它仍然被可能阻碍重要突破的‘主流’所主导，而 Richard Sutton 和我都站在主流之外……过去，他对我在人工智能方面的教育起到了奠基作用，能与 Richard Sutton 合作将是一种意想不到的乐趣。”\n\n而 Amii 首席执行官 Cam Linke 也表示：“Amii 很荣幸能够为我们的首席科学顾问兼朋友 Rich Sutton 与 Keen Technologies 的合作提供支持。”\n\n与此同时，也引发了不少网友的讨论：“大佬联手，AI 的裂变演进要开火了吗”；“强化学习之父联手传奇程序员，又在 AI 赛道上看到了强强联合”……\n————————————————\n**版权声明：本文为CSDN博主「CSDN资讯」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。**\n**原文链接：https://blog.csdn.net/csdnnews/article/details/133723432**', 'a50cb52ee849b07498fc84ea58224d54.png', '23', '4670', '1', '2023-10-17 20:16:07', '2', '1', '网友：“大佬联手，AI 的裂变演进要开火了？”', '强化学习之父联手 3D 游戏之父入局 AGI', '15', null);

-- ----------------------------
-- Table structure for `text_biaoqian`
-- ----------------------------
DROP TABLE IF EXISTS `text_biaoqian`;
CREATE TABLE `text_biaoqian` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text_id` int DEFAULT NULL,
  `biaoqian_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of text_biaoqian
-- ----------------------------
INSERT INTO `text_biaoqian` VALUES ('10', '30', '2');
INSERT INTO `text_biaoqian` VALUES ('11', '30', '5');
INSERT INTO `text_biaoqian` VALUES ('12', '31', '6');
INSERT INTO `text_biaoqian` VALUES ('15', '31', '3');
INSERT INTO `text_biaoqian` VALUES ('16', '43', '4');
INSERT INTO `text_biaoqian` VALUES ('17', '43', '1');
INSERT INTO `text_biaoqian` VALUES ('18', '43', '10');
INSERT INTO `text_biaoqian` VALUES ('19', '43', '11');
INSERT INTO `text_biaoqian` VALUES ('31', '17', '2');
INSERT INTO `text_biaoqian` VALUES ('32', '17', '12');
INSERT INTO `text_biaoqian` VALUES ('33', '17', '1');
INSERT INTO `text_biaoqian` VALUES ('34', '17', '4');
INSERT INTO `text_biaoqian` VALUES ('35', '54', '13');
INSERT INTO `text_biaoqian` VALUES ('36', '55', '14');
INSERT INTO `text_biaoqian` VALUES ('37', '56', '7');
INSERT INTO `text_biaoqian` VALUES ('38', '57', '1');
INSERT INTO `text_biaoqian` VALUES ('39', '58', '15');
INSERT INTO `text_biaoqian` VALUES ('40', '58', '16');
INSERT INTO `text_biaoqian` VALUES ('41', '59', '17');
INSERT INTO `text_biaoqian` VALUES ('43', '61', '1');
INSERT INTO `text_biaoqian` VALUES ('44', '61', '19');
INSERT INTO `text_biaoqian` VALUES ('45', '62', '21');
INSERT INTO `text_biaoqian` VALUES ('46', '62', '20');
INSERT INTO `text_biaoqian` VALUES ('47', '60', '18');

-- ----------------------------
-- Table structure for `text_draft`
-- ----------------------------
DROP TABLE IF EXISTS `text_draft`;
CREATE TABLE `text_draft` (
  `id` int NOT NULL AUTO_INCREMENT,
  `biaoti` varchar(255) DEFAULT NULL,
  `zhaiyao` varchar(255) DEFAULT NULL,
  `content` longtext,
  `pic` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `iscover` int DEFAULT NULL,
  `type_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of text_draft
-- ----------------------------
INSERT INTO `text_draft` VALUES ('3', '测试1', '测试1', '测试1', null, '1', '0', null);
INSERT INTO `text_draft` VALUES ('5', '测试2', '测试2', '测试2', null, '3', '0', null);

-- ----------------------------
-- Table structure for `text_status`
-- ----------------------------
DROP TABLE IF EXISTS `text_status`;
CREATE TABLE `text_status` (
  `id1` int NOT NULL AUTO_INCREMENT,
  `text_id` int DEFAULT NULL,
  `toutiao` int DEFAULT NULL,
  `jingpin` int DEFAULT NULL,
  `rhedian` int DEFAULT NULL,
  `yuanchuang` int DEFAULT NULL,
  `isexamine` int DEFAULT NULL,
  PRIMARY KEY (`id1`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of text_status
-- ----------------------------
INSERT INTO `text_status` VALUES ('1', '62', '1', '1', '1', '0', '1');
INSERT INTO `text_status` VALUES ('2', '57', '1', '1', '1', '0', '1');
INSERT INTO `text_status` VALUES ('3', '58', '1', '1', '1', '0', '1');
INSERT INTO `text_status` VALUES ('4', '59', '1', '1', '1', '0', '1');
INSERT INTO `text_status` VALUES ('5', '60', '1', '1', '1', '0', '1');
INSERT INTO `text_status` VALUES ('6', '61', '1', '1', '1', '0', '1');
INSERT INTO `text_status` VALUES ('7', '56', '1', '1', '1', '1', '0');
INSERT INTO `text_status` VALUES ('9', '17', '1', '1', '1', '1', '1');
INSERT INTO `text_status` VALUES ('10', '30', '1', '1', '1', '1', '1');
INSERT INTO `text_status` VALUES ('11', '31', '1', '1', '1', '1', '1');
INSERT INTO `text_status` VALUES ('12', '43', '1', '1', '1', '1', '1');
INSERT INTO `text_status` VALUES ('13', '54', '1', '1', '1', '0', '1');
INSERT INTO `text_status` VALUES ('14', '55', '1', '1', '1', '1', '0');

-- ----------------------------
-- Table structure for `text_type`
-- ----------------------------
DROP TABLE IF EXISTS `text_type`;
CREATE TABLE `text_type` (
  `id1` int NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id1`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of text_type
-- ----------------------------
INSERT INTO `text_type` VALUES ('1', '前端');
INSERT INTO `text_type` VALUES ('2', '后端');
INSERT INTO `text_type` VALUES ('3', '开源项目');
INSERT INTO `text_type` VALUES ('4', 'git');
INSERT INTO `text_type` VALUES ('5', 'springboot');
INSERT INTO `text_type` VALUES ('10', '技术介绍');
INSERT INTO `text_type` VALUES ('11', 'java');
INSERT INTO `text_type` VALUES ('12', '编程语言');
INSERT INTO `text_type` VALUES ('13', '人工智能');
INSERT INTO `text_type` VALUES ('14', '前沿技术');
INSERT INTO `text_type` VALUES ('15', '资讯');
INSERT INTO `text_type` VALUES ('16', '数据库');
INSERT INTO `text_type` VALUES ('17', '云平台');
INSERT INTO `text_type` VALUES ('18', '大数据');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pic` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `disable` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '00.jpg', '南枫', 'zf20020712', '18436320273', '2694134538@qq.com', '2023-10-20 17:30:29', '不想努力了!!!', '男', '河南', '2002-07-12', '1');
INSERT INTO `user` VALUES ('2', '20220612164733_72d8b.jpg', 'java从入门到入土', '2694134538', '19561826477', null, '2023-10-21 21:04:43', '未填写', '男', '浙江', '2023-10-24', '1');
INSERT INTO `user` VALUES ('3', '20220801091938_56fad.thumb.1000_0.jpg', '油炸小可爱', '123456', '15067081849', null, '2023-10-28 12:56:48', '未填写', '女', '江苏', null, '1');
INSERT INTO `user` VALUES ('4', '20220919130240_8d753.thumb.1000_0.jpg', '小熊行星', '123456', '15988592382', null, '2023-10-28 13:09:10', '未填写', '女', '山东', null, '1');
INSERT INTO `user` VALUES ('5', null, '系统通知', '111111', '111111', null, '2023-11-06 15:26:59', null, null, null, null, '1');

-- ----------------------------
-- Table structure for `user_attention`
-- ----------------------------
DROP TABLE IF EXISTS `user_attention`;
CREATE TABLE `user_attention` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `attention_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_attention
-- ----------------------------
INSERT INTO `user_attention` VALUES ('1', '1', '2');
INSERT INTO `user_attention` VALUES ('2', '2', '1');
INSERT INTO `user_attention` VALUES ('3', '3', '1');
INSERT INTO `user_attention` VALUES ('4', '4', '1');
INSERT INTO `user_attention` VALUES ('7', '1', '3');

-- ----------------------------
-- Table structure for `user_browse`
-- ----------------------------
DROP TABLE IF EXISTS `user_browse`;
CREATE TABLE `user_browse` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `text_id` int DEFAULT NULL,
  `time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_browse
-- ----------------------------
INSERT INTO `user_browse` VALUES ('1', '1', '56', '2023-10-30');
INSERT INTO `user_browse` VALUES ('3', '1', '58', '2023-12-01');
INSERT INTO `user_browse` VALUES ('4', '1', '59', '2023-11-28');
INSERT INTO `user_browse` VALUES ('5', '1', '60', '2023-10-30');
INSERT INTO `user_browse` VALUES ('6', '1', '61', '2023-10-12');
INSERT INTO `user_browse` VALUES ('7', '1', '62', '2024-03-27');
INSERT INTO `user_browse` VALUES ('8', '1', '54', '2023-11-05');
INSERT INTO `user_browse` VALUES ('9', '1', '55', '2023-09-22');
INSERT INTO `user_browse` VALUES ('10', '1', '43', '2023-12-01');
INSERT INTO `user_browse` VALUES ('11', '1', '31', '2023-11-02');
INSERT INTO `user_browse` VALUES ('12', '1', '17', '2024-03-27');
INSERT INTO `user_browse` VALUES ('13', '1', '30', '2023-11-05');
INSERT INTO `user_browse` VALUES ('14', '2', '62', '2024-01-06');
INSERT INTO `user_browse` VALUES ('15', '2', '57', '2023-11-06');
INSERT INTO `user_browse` VALUES ('16', '2', '59', '2023-11-30');
INSERT INTO `user_browse` VALUES ('17', '2', '60', '2023-11-30');
INSERT INTO `user_browse` VALUES ('18', '2', '61', '2023-11-06');
INSERT INTO `user_browse` VALUES ('19', '2', '58', '2023-11-18');
INSERT INTO `user_browse` VALUES ('21', '3', '43', '2023-11-30');
INSERT INTO `user_browse` VALUES ('22', '3', '62', '2023-11-30');
INSERT INTO `user_browse` VALUES ('23', '3', '60', '2023-11-30');
INSERT INTO `user_browse` VALUES ('24', '3', '31', '2023-11-30');
INSERT INTO `user_browse` VALUES ('25', '4', '43', '2023-11-30');
INSERT INTO `user_browse` VALUES ('26', '4', '54', '2023-11-30');
INSERT INTO `user_browse` VALUES ('29', '4', '57', '2023-11-30');
INSERT INTO `user_browse` VALUES ('30', '1', '57', '2023-11-30');
INSERT INTO `user_browse` VALUES ('31', '0', '17', '2023-12-03');
INSERT INTO `user_browse` VALUES ('32', '2', '31', '2024-01-06');
INSERT INTO `user_browse` VALUES ('33', '2', '54', '2024-01-06');
INSERT INTO `user_browse` VALUES ('34', '0', '62', '2024-03-04');

-- ----------------------------
-- Table structure for `user_chat`
-- ----------------------------
DROP TABLE IF EXISTS `user_chat`;
CREATE TABLE `user_chat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `send_user_id` int DEFAULT NULL,
  `message` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci,
  `ispic` int DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `isread` int DEFAULT NULL,
  `is_send` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_chat
-- ----------------------------
INSERT INTO `user_chat` VALUES ('1', '1', '2', '你好啊', '0', '2023-10-28 17:03:29', '1', '0');
INSERT INTO `user_chat` VALUES ('2', '1', '3', '哈哈哈哈', '0', '2023-10-10 17:03:52', '1', '0');
INSERT INTO `user_chat` VALUES ('3', '1', '2', '嘿嘿', '0', '2023-10-28 17:04:15', '1', '0');
INSERT INTO `user_chat` VALUES ('4', '2', '1', '你好啊', '0', '2023-10-28 17:03:29', '0', '1');
INSERT INTO `user_chat` VALUES ('5', '2', '1', '嘿嘿', '0', '2023-10-28 17:04:15', '0', '1');
INSERT INTO `user_chat` VALUES ('6', '3', '1', '哈哈哈哈', '0', '2023-10-10 17:03:52', '0', '1');
INSERT INTO `user_chat` VALUES ('7', '1', '2', '好啊', '0', '2023-10-28 17:56:45', '0', '1');
INSERT INTO `user_chat` VALUES ('20', '1', '2', '有事吗', '0', '2023-10-28 18:30:21', '0', '1');
INSERT INTO `user_chat` VALUES ('21', '2', '1', '有事吗', '0', '2023-10-28 18:30:21', '0', '0');
INSERT INTO `user_chat` VALUES ('22', '1', '2', '????', '0', '2023-10-28 18:31:17', '0', '1');
INSERT INTO `user_chat` VALUES ('23', '2', '1', '????', '0', '2023-10-28 18:31:17', '0', '0');
INSERT INTO `user_chat` VALUES ('24', '1', '3', '哈哈哈哈', '0', '2023-10-28 18:33:02', '0', '1');
INSERT INTO `user_chat` VALUES ('25', '3', '1', '哈哈哈哈', '0', '2023-10-28 18:33:02', '1', '0');
INSERT INTO `user_chat` VALUES ('26', '1', '3', '最近怎么样', '0', '2023-10-28 18:38:18', '0', '1');
INSERT INTO `user_chat` VALUES ('27', '3', '1', '最近怎么样', '0', '2023-10-28 18:38:18', '1', '0');
INSERT INTO `user_chat` VALUES ('32', '1', '3', '代码写的咋样', '0', '2023-10-29 16:16:40', '0', '1');
INSERT INTO `user_chat` VALUES ('33', '3', '1', '代码写的咋样', '0', '2023-10-29 16:16:40', '1', '0');
INSERT INTO `user_chat` VALUES ('34', '3', '1', '还行吧，快写完了', '0', '2023-10-29 16:20:49', '0', '1');
INSERT INTO `user_chat` VALUES ('35', '1', '3', '还行吧，快写完了', '0', '2023-10-29 16:20:49', '1', '0');
INSERT INTO `user_chat` VALUES ('36', '1', '3', '可以', '0', '2023-10-29 16:21:26', '0', '1');
INSERT INTO `user_chat` VALUES ('37', '3', '1', '可以', '0', '2023-10-29 16:21:26', '1', '0');
INSERT INTO `user_chat` VALUES ('38', '3', '1', '你的怎么样了', '0', '2023-10-29 18:20:50', '0', '1');
INSERT INTO `user_chat` VALUES ('39', '1', '3', '你的怎么样了', '0', '2023-10-29 18:20:50', '1', '0');
INSERT INTO `user_chat` VALUES ('40', '1', '3', '还行吧', '0', '2023-10-29 18:33:07', '0', '1');
INSERT INTO `user_chat` VALUES ('41', '3', '1', '还行吧', '0', '2023-10-29 18:33:07', '1', '0');
INSERT INTO `user_chat` VALUES ('42', '3', '1', '点赞', '0', '2023-10-29 18:33:53', '0', '1');
INSERT INTO `user_chat` VALUES ('43', '1', '3', '点赞', '0', '2023-10-29 18:33:53', '1', '0');
INSERT INTO `user_chat` VALUES ('44', '1', '3', '嘿嘿', '0', '2023-10-29 18:55:24', '0', '1');
INSERT INTO `user_chat` VALUES ('45', '3', '1', '嘿嘿', '0', '2023-10-29 18:55:24', '1', '0');
INSERT INTO `user_chat` VALUES ('46', '3', '1', '哈哈哈哈哈', '0', '2023-10-29 18:55:48', '0', '1');
INSERT INTO `user_chat` VALUES ('47', '1', '3', '哈哈哈哈哈', '0', '2023-10-29 18:55:48', '1', '0');
INSERT INTO `user_chat` VALUES ('48', '1', '2', '111', '0', '2023-10-29 18:58:04', '0', '1');
INSERT INTO `user_chat` VALUES ('49', '2', '1', '111', '0', '2023-10-29 18:58:04', '0', '0');
INSERT INTO `user_chat` VALUES ('50', '2', '1', '2222', '0', '2023-10-29 18:58:25', '0', '1');
INSERT INTO `user_chat` VALUES ('51', '1', '2', '2222', '0', '2023-10-29 18:58:25', '1', '0');
INSERT INTO `user_chat` VALUES ('52', '1', '2', '333', '0', '2023-10-29 19:03:15', '0', '1');
INSERT INTO `user_chat` VALUES ('53', '2', '1', '333', '0', '2023-10-29 19:03:15', '0', '0');
INSERT INTO `user_chat` VALUES ('54', '2', '1', '444', '0', '2023-10-29 19:05:37', '0', '1');
INSERT INTO `user_chat` VALUES ('55', '1', '2', '444', '0', '2023-10-29 19:05:37', '1', '0');
INSERT INTO `user_chat` VALUES ('56', '1', '2', '555', '0', '2023-10-29 19:05:53', '0', '1');
INSERT INTO `user_chat` VALUES ('57', '2', '1', '555', '0', '2023-10-29 19:05:53', '0', '0');
INSERT INTO `user_chat` VALUES ('58', '2', '1', '666', '0', '2023-10-29 19:24:23', '0', '1');
INSERT INTO `user_chat` VALUES ('59', '1', '2', '666', '0', '2023-10-29 19:24:23', '1', '0');
INSERT INTO `user_chat` VALUES ('61', '3', '5', '您的文章测试2审核失败,由于您的文章文章内容缺乏', '0', '2023-11-06 17:31:53', '0', '0');
INSERT INTO `user_chat` VALUES ('62', '3', '1', 'aaaaaa', '0', '2024-03-27 19:51:17', '0', '1');
INSERT INTO `user_chat` VALUES ('63', '1', '3', 'aaaaaa', '0', '2024-03-27 19:51:17', '0', '0');

-- ----------------------------
-- Table structure for `user_chat_user`
-- ----------------------------
DROP TABLE IF EXISTS `user_chat_user`;
CREATE TABLE `user_chat_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `send_user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_chat_user
-- ----------------------------
INSERT INTO `user_chat_user` VALUES ('1', '1', '2');
INSERT INTO `user_chat_user` VALUES ('2', '1', '3');
INSERT INTO `user_chat_user` VALUES ('3', '2', '1');
INSERT INTO `user_chat_user` VALUES ('4', '3', '1');
INSERT INTO `user_chat_user` VALUES ('5', '1', '5');
INSERT INTO `user_chat_user` VALUES ('6', '2', '5');
INSERT INTO `user_chat_user` VALUES ('7', '3', '5');
INSERT INTO `user_chat_user` VALUES ('8', '4', '5');

-- ----------------------------
-- Table structure for `user_collect`
-- ----------------------------
DROP TABLE IF EXISTS `user_collect`;
CREATE TABLE `user_collect` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `text_id` int DEFAULT NULL,
  `collect_name_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_collect
-- ----------------------------
INSERT INTO `user_collect` VALUES ('1', '1', '17', '1');
INSERT INTO `user_collect` VALUES ('4', '1', '30', '1');
INSERT INTO `user_collect` VALUES ('5', '1', '57', '1');
INSERT INTO `user_collect` VALUES ('6', '2', '17', '2');
INSERT INTO `user_collect` VALUES ('7', '4', '31', '8');
INSERT INTO `user_collect` VALUES ('8', '1', '62', '62');

-- ----------------------------
-- Table structure for `user_collect_name`
-- ----------------------------
DROP TABLE IF EXISTS `user_collect_name`;
CREATE TABLE `user_collect_name` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `collect_name` varchar(255) DEFAULT NULL,
  `collect_miaoshu` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_collect_name
-- ----------------------------
INSERT INTO `user_collect_name` VALUES ('1', '1', '我的收藏夹', '清添加收藏夹描述');
INSERT INTO `user_collect_name` VALUES ('2', '2', '我的收藏夹', '清添加收藏夹描述');
INSERT INTO `user_collect_name` VALUES ('3', '1', 'springboot', '清添加收藏夹描述');
INSERT INTO `user_collect_name` VALUES ('4', '1', 'java', '清添加收藏夹描述');
INSERT INTO `user_collect_name` VALUES ('5', '1', '数据库', '清添加收藏夹描述');
INSERT INTO `user_collect_name` VALUES ('6', '2', '学习', '清添加收藏夹描述');
INSERT INTO `user_collect_name` VALUES ('7', '3', '我的收藏夹', '清添加收藏夹描述');
INSERT INTO `user_collect_name` VALUES ('8', '4', '我的收藏夹', '清添加收藏夹描述');

-- ----------------------------
-- Table structure for `user_collect_sum`
-- ----------------------------
DROP TABLE IF EXISTS `user_collect_sum`;
CREATE TABLE `user_collect_sum` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text_id` int DEFAULT NULL,
  `sum` int DEFAULT NULL,
  `time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_collect_sum
-- ----------------------------
INSERT INTO `user_collect_sum` VALUES ('1', '17', '4', '2023-10-24');
INSERT INTO `user_collect_sum` VALUES ('2', '30', '1', '2023-10-26');
INSERT INTO `user_collect_sum` VALUES ('3', '57', '1', '2023-10-26');
INSERT INTO `user_collect_sum` VALUES ('4', '17', '1', '2023-10-26');
INSERT INTO `user_collect_sum` VALUES ('5', '31', '1', '2023-10-28');
INSERT INTO `user_collect_sum` VALUES ('6', '62', '1', '2024-03-27');

-- ----------------------------
-- Table structure for `user_comment`
-- ----------------------------
DROP TABLE IF EXISTS `user_comment`;
CREATE TABLE `user_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text_id` int DEFAULT NULL,
  `content` text,
  `user_id` int DEFAULT NULL,
  `type` tinyint DEFAULT NULL,
  `huifu` int DEFAULT NULL,
  `huifu_huifu` int DEFAULT NULL,
  `show2` tinyint DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `show1` tinyint DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_comment
-- ----------------------------
INSERT INTO `user_comment` VALUES ('1', '58', ':smiley:\r\n1246\r\n974535', '1', '0', '0', '0', '0', '2023-11-11 16:21:33', '0', '2023-11-22');
INSERT INTO `user_comment` VALUES ('2', '58', '测试2', '2', '0', '0', '0', '0', '2023-11-11 16:21:53', '0', '2023-11-22');
INSERT INTO `user_comment` VALUES ('3', '58', '测试3', '3', '1', '2', '0', '0', '2023-11-11 16:22:28', '0', '2023-11-11');
INSERT INTO `user_comment` VALUES ('4', '58', '测试4', '1', '1', '2', '3', '0', '2023-11-11 16:23:13', '0', '2023-11-11');
INSERT INTO `user_comment` VALUES ('5', '58', '测试5', '1', null, '2', '0', '0', '2023-11-11 19:13:18', '0', '2023-11-11');
INSERT INTO `user_comment` VALUES ('7', '62', '测试6', '1', null, '0', '0', '0', '2023-11-11 20:44:51', '0', '2023-11-22');
INSERT INTO `user_comment` VALUES ('8', '62', '测试7', '1', null, '0', '0', '0', '2023-11-13 18:32:42', '0', '2023-11-11');
INSERT INTO `user_comment` VALUES ('9', '17', '测试11', '1', null, '0', '0', '0', '2023-11-22 15:44:16', '0', '2023-11-22');

-- ----------------------------
-- Table structure for `user_dianzan_sum`
-- ----------------------------
DROP TABLE IF EXISTS `user_dianzan_sum`;
CREATE TABLE `user_dianzan_sum` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text_id` int DEFAULT NULL,
  `sum` int DEFAULT NULL,
  `time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_dianzan_sum
-- ----------------------------
INSERT INTO `user_dianzan_sum` VALUES ('1', '17', '6', '2023-10-19');
INSERT INTO `user_dianzan_sum` VALUES ('2', '58', '1', '2023-10-20');
INSERT INTO `user_dianzan_sum` VALUES ('3', '17', '3', '2023-10-18');
INSERT INTO `user_dianzan_sum` VALUES ('4', '17', '3', '2023-10-26');

-- ----------------------------
-- Table structure for `user_fensi`
-- ----------------------------
DROP TABLE IF EXISTS `user_fensi`;
CREATE TABLE `user_fensi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `fensi_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_fensi
-- ----------------------------
INSERT INTO `user_fensi` VALUES ('1', '1', '2');
INSERT INTO `user_fensi` VALUES ('2', '2', '1');
INSERT INTO `user_fensi` VALUES ('3', '1', '3');
INSERT INTO `user_fensi` VALUES ('4', '1', '4');
INSERT INTO `user_fensi` VALUES ('7', '3', '1');

-- ----------------------------
-- Table structure for `user_liuyan`
-- ----------------------------
DROP TABLE IF EXISTS `user_liuyan`;
CREATE TABLE `user_liuyan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_liuyan
-- ----------------------------
INSERT INTO `user_liuyan` VALUES ('1', '1', '打卡打卡', '2023-11-09');
INSERT INTO `user_liuyan` VALUES ('2', '2', '2021.1.1到此已游', '2023-11-09');
INSERT INTO `user_liuyan` VALUES ('3', '1', '哈哈哈哈哈', '2023-11-09');
INSERT INTO `user_liuyan` VALUES ('4', '3', '不错不错', '2023-11-09');
INSERT INTO `user_liuyan` VALUES ('5', '1', '2023年来过', '2023-11-09');
INSERT INTO `user_liuyan` VALUES ('12', '1', '2023.12.1到此一游', '2023-12-01');
INSERT INTO `user_liuyan` VALUES ('13', '1', '111', '2024-03-27');
INSERT INTO `user_liuyan` VALUES ('14', '1', '222222', '2024-03-27');

-- ----------------------------
-- Table structure for `user_loginlocation`
-- ----------------------------
DROP TABLE IF EXISTS `user_loginlocation`;
CREATE TABLE `user_loginlocation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `location` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_loginlocation
-- ----------------------------
INSERT INTO `user_loginlocation` VALUES ('1', '1', '中国 河南 驻马店  移动', '2023-10-21 18:12:29');
INSERT INTO `user_loginlocation` VALUES ('2', '1', '中国 河南 驻马店  移动', '2023-10-21 18:56:55');
INSERT INTO `user_loginlocation` VALUES ('3', '1', '中国 河南 驻马店  移动', '2023-10-21 20:17:32');
INSERT INTO `user_loginlocation` VALUES ('4', '2', '中国 河南 驻马店  移动', '2023-10-21 21:07:53');
INSERT INTO `user_loginlocation` VALUES ('5', '2', '中国 河南 驻马店  移动', '2023-10-22 13:39:38');
INSERT INTO `user_loginlocation` VALUES ('6', '1', '中国 河南 驻马店  移动', '2023-10-22 13:49:10');
INSERT INTO `user_loginlocation` VALUES ('7', '1', '中国 河南 驻马店  移动', '2023-10-23 07:55:28');
INSERT INTO `user_loginlocation` VALUES ('8', '1', '中国 河南 驻马店  移动', '2023-10-24 14:24:41');
INSERT INTO `user_loginlocation` VALUES ('9', '1', '中国 河南 驻马店  移动', '2023-10-25 16:40:06');
INSERT INTO `user_loginlocation` VALUES ('10', '1', '保留地址 保留地址   ', '2023-10-26 13:35:00');
INSERT INTO `user_loginlocation` VALUES ('11', '2', '中国 河南 驻马店  移动', '2023-10-26 17:38:54');
INSERT INTO `user_loginlocation` VALUES ('12', '1', '中国 河南 驻马店  移动', '2023-10-27 13:14:00');
INSERT INTO `user_loginlocation` VALUES ('13', '1', '中国 河南 驻马店  移动', '2023-10-28 12:52:20');
INSERT INTO `user_loginlocation` VALUES ('14', '3', '中国 河南 驻马店  移动', '2023-10-28 12:59:25');
INSERT INTO `user_loginlocation` VALUES ('15', '4', '中国 河南 驻马店  移动', '2023-10-28 13:09:30');
INSERT INTO `user_loginlocation` VALUES ('16', '1', '中国 河南 驻马店  移动', '2023-10-28 13:11:42');
INSERT INTO `user_loginlocation` VALUES ('17', '3', '中国 河南 驻马店  移动', '2023-10-28 18:37:36');
INSERT INTO `user_loginlocation` VALUES ('18', '1', '中国 河南 驻马店  移动', '2023-10-29 12:44:44');
INSERT INTO `user_loginlocation` VALUES ('19', '3', '中国 河南 驻马店  移动', '2023-10-29 13:02:30');
INSERT INTO `user_loginlocation` VALUES ('20', '2', '中国 河南 驻马店  移动', '2023-10-29 17:51:04');
INSERT INTO `user_loginlocation` VALUES ('21', '1', '中国 河南 驻马店  移动', '2023-10-29 17:51:53');
INSERT INTO `user_loginlocation` VALUES ('22', '2', '中国 河南 驻马店  移动', '2023-10-29 18:57:50');
INSERT INTO `user_loginlocation` VALUES ('23', '1', '中国 河南 驻马店  移动', '2023-10-30 13:43:57');
INSERT INTO `user_loginlocation` VALUES ('24', '1', '中国 河南 驻马店  移动', '2023-11-02 13:30:42');
INSERT INTO `user_loginlocation` VALUES ('25', '1', '中国 河南 驻马店  移动', '2023-11-04 13:04:16');
INSERT INTO `user_loginlocation` VALUES ('26', '1', '中国 河南 驻马店  移动', '2023-11-05 19:17:39');
INSERT INTO `user_loginlocation` VALUES ('27', '3', '中国 河南 驻马店  移动', '2023-11-06 17:18:15');
INSERT INTO `user_loginlocation` VALUES ('28', '1', '中国 河南 驻马店  移动', '2023-11-06 18:49:15');
INSERT INTO `user_loginlocation` VALUES ('29', '1', '中国 河南 驻马店  移动', '2023-11-07 17:00:14');
INSERT INTO `user_loginlocation` VALUES ('30', '1', '中国 河南 驻马店  移动', '2023-11-07 18:20:24');
INSERT INTO `user_loginlocation` VALUES ('31', '1', '中国 河南 驻马店  移动', '2023-11-08 14:26:50');
INSERT INTO `user_loginlocation` VALUES ('32', '1', '中国 河南 驻马店  移动', '2023-11-09 16:22:03');
INSERT INTO `user_loginlocation` VALUES ('33', '1', '中国 河南 驻马店  移动', '2023-11-10 08:44:21');
INSERT INTO `user_loginlocation` VALUES ('34', '1', '中国 河南 驻马店  移动', '2023-11-11 14:50:20');
INSERT INTO `user_loginlocation` VALUES ('35', '1', '中国 河南 驻马店  移动', '2023-11-13 15:02:03');
INSERT INTO `user_loginlocation` VALUES ('36', '1', '中国 河南 驻马店  移动', '2023-11-18 14:11:21');
INSERT INTO `user_loginlocation` VALUES ('37', '1', '中国 河南 驻马店  移动', '2023-11-22 15:36:20');
INSERT INTO `user_loginlocation` VALUES ('38', '1', '中国 河南 郑州  移动', '2023-11-24 21:34:25');
INSERT INTO `user_loginlocation` VALUES ('39', '1', '中国 河南 驻马店  移动', '2023-11-27 17:28:33');
INSERT INTO `user_loginlocation` VALUES ('41', '1', '中国 河南 驻马店  移动', '2023-11-27 17:59:44');
INSERT INTO `user_loginlocation` VALUES ('42', '1', '中国 河南 驻马店  移动', '2023-11-28 10:23:32');
INSERT INTO `user_loginlocation` VALUES ('43', '1', '中国 河南 驻马店  移动', '2023-11-28 10:23:55');
INSERT INTO `user_loginlocation` VALUES ('44', '1', '中国 河南 驻马店  移动', '2023-11-28 16:59:10');
INSERT INTO `user_loginlocation` VALUES ('45', '1', '中国 河南 驻马店  移动', '2023-11-30 17:42:58');
INSERT INTO `user_loginlocation` VALUES ('46', '3', '中国 河南 驻马店  移动', '2023-11-30 18:19:59');
INSERT INTO `user_loginlocation` VALUES ('47', '4', '中国 河南 驻马店  移动', '2023-11-30 18:20:51');
INSERT INTO `user_loginlocation` VALUES ('48', '2', '中国 河南 驻马店  移动', '2023-11-30 19:00:08');
INSERT INTO `user_loginlocation` VALUES ('49', '1', '中国 河南 驻马店  移动', '2023-11-30 19:03:57');
INSERT INTO `user_loginlocation` VALUES ('50', '1', '中国 河南 驻马店  移动', '2023-12-01 17:28:17');
INSERT INTO `user_loginlocation` VALUES ('51', '1', '中国 河南 驻马店  移动', '2024-03-27 19:37:11');
INSERT INTO `user_loginlocation` VALUES ('52', '3', '中国 河南 驻马店  移动', '2024-03-27 19:44:06');
INSERT INTO `user_loginlocation` VALUES ('53', '1', '中国 河南 驻马店  移动', '2024-03-27 19:49:55');

-- ----------------------------
-- Table structure for `user_permission`
-- ----------------------------
DROP TABLE IF EXISTS `user_permission`;
CREATE TABLE `user_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `islogin` tinyint DEFAULT NULL,
  `text_limit` tinyint DEFAULT NULL,
  `user_limit` tinyint DEFAULT NULL,
  `shouye_limit` tinyint DEFAULT NULL,
  `iform_limit` tinyint DEFAULT NULL,
  `examine_limit` tinyint DEFAULT NULL,
  `limit` tinyint DEFAULT NULL,
  `liuyan_limit` tinyint DEFAULT NULL,
  `link_limit` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `user_search_history`
-- ----------------------------
DROP TABLE IF EXISTS `user_search_history`;
CREATE TABLE `user_search_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_search_history
-- ----------------------------
INSERT INTO `user_search_history` VALUES ('1', '1', 'vue', '2023-11-02 17:50:46');
INSERT INTO `user_search_history` VALUES ('5', '1', 'java', '2023-11-07 18:12:08');
INSERT INTO `user_search_history` VALUES ('6', '1', 's', '2023-11-07 18:18:30');
INSERT INTO `user_search_history` VALUES ('8', '1', '基于', '2024-03-27 19:42:24');
INSERT INTO `user_search_history` VALUES ('9', '1', 'spri', '2024-03-27 19:53:25');

-- ----------------------------
-- Table structure for `user_view_sum`
-- ----------------------------
DROP TABLE IF EXISTS `user_view_sum`;
CREATE TABLE `user_view_sum` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text_id` int DEFAULT NULL,
  `time` date DEFAULT NULL,
  `sum` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_view_sum
-- ----------------------------
INSERT INTO `user_view_sum` VALUES ('3', '57', '2023-10-19', '3');
INSERT INTO `user_view_sum` VALUES ('4', '58', '2023-10-19', '1');
INSERT INTO `user_view_sum` VALUES ('6', '57', '2023-10-18', '32');
INSERT INTO `user_view_sum` VALUES ('7', '57', '2023-10-17', '123');
INSERT INTO `user_view_sum` VALUES ('8', '57', '2023-10-16', '333');
INSERT INTO `user_view_sum` VALUES ('9', '57', '2023-10-15', '112');
INSERT INTO `user_view_sum` VALUES ('10', '57', '2023-10-14', '234');
INSERT INTO `user_view_sum` VALUES ('11', '57', '2023-10-13', '20');
INSERT INTO `user_view_sum` VALUES ('12', '17', '2023-10-19', '7');
INSERT INTO `user_view_sum` VALUES ('13', '30', '2023-10-19', '2');
INSERT INTO `user_view_sum` VALUES ('14', '62', '2023-10-19', '1');
INSERT INTO `user_view_sum` VALUES ('15', '17', '2023-10-20', '1');
INSERT INTO `user_view_sum` VALUES ('16', '62', '2023-10-20', '2');
INSERT INTO `user_view_sum` VALUES ('17', '57', '2023-10-20', '1');
INSERT INTO `user_view_sum` VALUES ('18', '31', '2023-10-20', '1');
INSERT INTO `user_view_sum` VALUES ('19', '61', '2023-10-20', '1');
INSERT INTO `user_view_sum` VALUES ('20', '17', '2023-10-24', '6');
INSERT INTO `user_view_sum` VALUES ('21', '58', '2023-10-24', '5');
INSERT INTO `user_view_sum` VALUES ('22', '62', '2023-10-24', '1');
INSERT INTO `user_view_sum` VALUES ('23', '61', '2023-10-24', '1');
INSERT INTO `user_view_sum` VALUES ('24', '59', '2023-10-24', '1');
INSERT INTO `user_view_sum` VALUES ('25', '17', '2023-10-26', '6');
INSERT INTO `user_view_sum` VALUES ('26', '30', '2023-10-26', '4');
INSERT INTO `user_view_sum` VALUES ('27', '54', '2023-10-26', '1');
INSERT INTO `user_view_sum` VALUES ('28', '57', '2023-10-26', '6');
INSERT INTO `user_view_sum` VALUES ('29', '17', '2023-10-27', '2');
INSERT INTO `user_view_sum` VALUES ('30', '43', '2023-10-28', '2');
INSERT INTO `user_view_sum` VALUES ('31', '31', '2023-10-28', '4');
INSERT INTO `user_view_sum` VALUES ('32', '30', '2023-10-28', '1');
INSERT INTO `user_view_sum` VALUES ('33', '57', '2023-10-30', '2');
INSERT INTO `user_view_sum` VALUES ('34', '17', '2023-10-30', '4');
INSERT INTO `user_view_sum` VALUES ('35', '61', '2023-10-30', '1');
INSERT INTO `user_view_sum` VALUES ('36', '58', '2023-10-30', '1');
INSERT INTO `user_view_sum` VALUES ('37', '60', '2023-10-30', '1');
INSERT INTO `user_view_sum` VALUES ('38', '62', '2023-10-30', '1');
INSERT INTO `user_view_sum` VALUES ('39', '58', '2023-11-02', '3');
INSERT INTO `user_view_sum` VALUES ('40', '31', '2023-11-02', '1');
INSERT INTO `user_view_sum` VALUES ('41', '17', '2023-11-02', '2');
INSERT INTO `user_view_sum` VALUES ('42', '54', '2023-11-05', '1');
INSERT INTO `user_view_sum` VALUES ('43', '62', '2023-11-05', '2');
INSERT INTO `user_view_sum` VALUES ('44', '57', '2023-11-05', '4');
INSERT INTO `user_view_sum` VALUES ('45', '58', '2023-11-05', '1');
INSERT INTO `user_view_sum` VALUES ('46', '17', '2023-11-05', '1');
INSERT INTO `user_view_sum` VALUES ('47', '30', '2023-11-05', '1');
INSERT INTO `user_view_sum` VALUES ('48', '62', '2023-11-06', '2');
INSERT INTO `user_view_sum` VALUES ('49', '57', '2023-11-06', '1');
INSERT INTO `user_view_sum` VALUES ('50', '59', '2023-11-06', '1');
INSERT INTO `user_view_sum` VALUES ('51', '60', '2023-11-06', '1');
INSERT INTO `user_view_sum` VALUES ('52', '61', '2023-11-06', '1');
INSERT INTO `user_view_sum` VALUES ('53', '58', '2023-11-06', '1');
INSERT INTO `user_view_sum` VALUES ('54', '57', '2023-11-10', '1');
INSERT INTO `user_view_sum` VALUES ('55', '62', '2023-11-11', '5');
INSERT INTO `user_view_sum` VALUES ('56', '58', '2023-11-11', '4');
INSERT INTO `user_view_sum` VALUES ('57', '57', '2023-11-11', '2');
INSERT INTO `user_view_sum` VALUES ('58', '62', '2023-11-13', '1');
INSERT INTO `user_view_sum` VALUES ('59', '58', '2023-11-18', '2');
INSERT INTO `user_view_sum` VALUES ('60', '17', '2023-11-22', '1');
INSERT INTO `user_view_sum` VALUES ('61', '62', '2023-11-24', '1');
INSERT INTO `user_view_sum` VALUES ('62', '57', '2023-11-27', '1');
INSERT INTO `user_view_sum` VALUES ('63', '62', '2023-11-27', '1');
INSERT INTO `user_view_sum` VALUES ('64', '62', '2023-11-28', '2');
INSERT INTO `user_view_sum` VALUES ('65', '59', '2023-11-28', '1');
INSERT INTO `user_view_sum` VALUES ('66', '57', '2023-11-30', '2');
INSERT INTO `user_view_sum` VALUES ('67', '59', '2023-11-30', '1');
INSERT INTO `user_view_sum` VALUES ('68', '60', '2023-11-30', '1');
INSERT INTO `user_view_sum` VALUES ('69', '43', '2023-12-01', '1');
INSERT INTO `user_view_sum` VALUES ('70', '62', '2023-12-01', '1');
INSERT INTO `user_view_sum` VALUES ('71', '58', '2023-12-01', '3');
INSERT INTO `user_view_sum` VALUES ('72', '17', '2023-12-03', '1');
INSERT INTO `user_view_sum` VALUES ('73', '62', '2024-01-06', '1');
INSERT INTO `user_view_sum` VALUES ('74', '31', '2024-01-06', '1');
INSERT INTO `user_view_sum` VALUES ('75', '54', '2024-01-06', '1');
INSERT INTO `user_view_sum` VALUES ('76', '62', '2024-02-26', '1');
INSERT INTO `user_view_sum` VALUES ('77', '62', '2024-03-04', '1');
INSERT INTO `user_view_sum` VALUES ('78', '62', '2024-03-27', '1');
INSERT INTO `user_view_sum` VALUES ('79', '17', '2024-03-27', '2');
