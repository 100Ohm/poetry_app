# 念诗：为你写一首诗。

这是一个以诗词为主题的Android开源社交软件，在这里，你可以看到别人写的诗评，新作的诗词；你也可以发表诗评，或者自己写一些诗，一篇篇收录成诗集。

如果遇到志同道合的朋友，可以互相评论、私聊，可以收藏对方写的作品和诗评。

这个项目仍在开发阶段，目前只完成了界面部分，以及接入了部分后台。

# 所使用的技术及使用理由

**项目架构：**

使用mvp模式进行开发（项目架构）

**资源适配：**

实现界面换肤，界面语言切换（资源适配）

**常见框架的使用：**

使用okhttp实现联网下载服务器上的资源（网络请求）

Java多线程（后台线程上传数据，后台线程加载等）

View触摸事件分发机制，解决滑动冲突（ViewPager与自定义CardView冲突）

本地小数据量数据存储使用SharePreference

Realm数据库（数据库）

使用Bmob做后端（后端接入)

使用org.json解析服务器返回的JSON数据（数据解析）

今日诗词SDK接入

Android Supportv7库使用（界面）

**常规功能的使用：**

封装各种工具类，如日志打印、常量定义、SharePreference存储工具、dip/px/sp互相转化的工具、将时间转换成格式化字符串的工具、对String的操作的工具。

对必要的界面资源进行简单的动画设计（动画）

# 界面预览

![image_day](C:\Users\ohm\AndroidStudioProjects\poetry_app\image_day.png)

![image_message_list](C:\Users\ohm\AndroidStudioProjects\poetry_app\image_message_list.png)

![image_night](C:\Users\ohm\AndroidStudioProjects\poetry_app\image_night.png)

![image_personal](C:\Users\ohm\AndroidStudioProjects\poetry_app\image_personal.jpg)

![image_personal_book](C:\Users\ohm\AndroidStudioProjects\poetry_app\image_personal_book.jpg)

![image_poetry_card](C:\Users\ohm\AndroidStudioProjects\poetry_app\image_poetry_card.png)

![image_poetry_comment](C:\Users\ohm\AndroidStudioProjects\poetry_app\image_poetry_comment.png)

![image_poetry_lib1](C:\Users\ohm\AndroidStudioProjects\poetry_app\image_poetry_lib1.png)

![image_poetry_lib2](C:\Users\ohm\AndroidStudioProjects\poetry_app\image_poetry_lib2.png)

