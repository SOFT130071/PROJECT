Servlet
========

## 首页热门课程
> 主要页面：index.jsp
> 
> 额外使用：index.js index.css
### UPLOAD
```
{
    type    : tophot
}
```
### DOWNLOAD
```
{
    0:{
        cid         : 1234
        title       : aaa  
        content     : bbb
        teacher_id  : 6789
        pic_url     : http://xxx.jpg
    },
    1:{
        cid         : 2222
        title       : ddd  
        content     : eee
        teacher_id  : 2333
        pic_url     : http://yyy.jpg
    }
}
```
> 长度为0-3
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance().getTopHotCourse()` 获取，返回值为 DOWONLOAD Json

## 首页近期课程
> 主要页面：index.jsp
> 
> 额外使用：index.js index.css
### UPLOAD
```
{
    type    : latest
}
```
### DOWNLOAD
```
{
    0:{
        cid         : 1234
        title       : aaa  
        content     : bbb
        teacher_id  : 6789
        pic_url     : http://xxx.jpg
    },
    1:{
        cid         : 2222
        title       : ddd  
        content     : eee
        teacher_id  : 2333
        pic_url     : http://yyy.jpg
    }
}
```
> 长度为0-3
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance().getLatestCourse()` 获取，返回值为 DOWONLOAD Json

## 登陆
> 主要页面：login.jsp
> 
> 额外使用：login.js login.css
### UPLOAD
```
{
    type: "login",
    username: qwer,
    password: asdf
}
```
### DESCRIPTION
使用 `ServiceFactory.getUserServiceInstance(param).login()` 获取，返回：
0x010101 : username不存在 
0x010102 : password不匹配 
1234     : uid

## 检查username的可用性
> 主要页面：register.jsp
> 
> 额外使用：register.js register.css
### UPLOAD
```
{
    type: "hasUsername",
    username: username
}
```
### DESCRIPTION
使用 `ServiceFactory.getUserServiceInstance().hasUsername(request.getParameter("username"))` 获取，返回：
false : username不存在 
true  : username存在 

## 发送验证邮件
> 主要页面：register.jsp
> 
> 额外使用：register.js register.css
### UPLOAD
```
{
    type: "mail",
    username: qwer,
    email: xxxx@xxx.xx
}
```
### DESCRIPTION
使用 `ServiceFactory.getUserServiceInstance(param).sendVerifyCode()` 

## 发送注册信息
> 主要页面：register.jsp
> 
> 额外使用：register.js register.css
### UPLOAD
```
{
    type: "register",
    username: qwer,
    password: asdf,
    nickname: zxcv,
    email: xxxx@xxx.xx,
    verify: mmmmmmmmmmmmmmmmmmmmm
}
```
### DESCRIPTION
使用 `ServiceFactory.getUserServiceInstance(param).register()` 获取，返回：
0x010201 : username存在 
0x010203 : 验证码错误
0x010202，0x010204 : 无法添加
1234     : uid

## 搜索结果
> 主要页面：search.jsp
> 
> 额外使用：search.js search.css
### UPLOAD
```
{
    type: "courseinfo",
    title: aaa,
    content: bbb,
    name: ccc
    order: hot/"
    choose: desc/ 
}
```
### DOWNLOAD
```
{
    0:{
        cid         : 1234
        title       : aaa  
        content     : bbb
        teacher_id  : 6789
        pic_url     : http://xxx.jpg
    },
    1:{
        cid         : 2222
        title       : ddd  
        content     : eee
        teacher_id  : 2333
        pic_url     : http://yyy.jpg
    }
}
```
长度为0-n
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance(param).doCourseInfo()` 获取，返回：
choose   : 升序或降序
order    : 按时间或是热度排序

## 课程信息
> 主要页面：course.jsp
> 
> 额外使用：course.js course.css
### UPLOAD
```
{
        type: "courseinfo",
        cid: 2345
}
```
### DOWNLOAD
```
{
    cid         : 2222
    title       : ddd  
    content     : eee
    teacher_id  : 2333
    pic_url     : http://yyy.jpg
}
```
长度为0-n
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance(param).doCourseInfo()` 获取

## 用户信息
> 主要页面：course.jsp
> 
> 额外使用：course.js course.css
### UPLOAD
```
{
     type: "userinfo",
     uid: 1234
}
```
### DOWNLOAD
```
{
    username: qwer,
    nickname: zxcv,
    email: xxxx@xxx.xx,
}
```
### DESCRIPTION
使用 `ServiceFactory.getUserServiceInstance(param).userInfo()` 获取

## 知识点列表
> 主要页面：course.jsp
> 
> 额外使用：course.js course.css
### UPLOAD
```
{
    type: "pagelist",
    cid: 1234
}
```
### DOWNLOAD
```
{
    0:{
        pid         : 1234
        cid         : 5432
        number      : 1.6.2
        title       : ggg
        content     : bbb
        url         : http://xxx.pptx
    },
    1:{
        pid         : 765
        cid         : 22222
        number      : 2.3.9
        title       : rrr
        content     : uuu
        url         : http://ggg
    }
}
```
长度为0-n
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance(param).getCoursePageInfo()` 获取

## 资源列表
> 主要页面：course.jsp
> 
> 额外使用：course.js course.css
### UPLOAD
```
{
    type: "resourcelist",
    cid: 2332
}
```
### DOWNLOAD
```
{
    0:{
        cid         : 5432
        number      : 1.6.2
        url         : http://xxx.pptx
    },
    1:{
        cid         : 22222
        number      : 2.3.9
        url         : http://ggg
    }
}
```
长度为0-n
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance(param).getResourceInfo()` 获取

## 课程作业列表
> 主要页面：course.jsp
> 
> 额外使用：course.js course.css
### UPLOAD
```
{
    type: "homeworklist",
    cid: 1234
}
```
### DOWNLOAD
```
{
    0:{
        hid         : 1234
        cid         : 5432
        title       : ggg
        content     : bbb
        start_time  : yyyy-mm-dd
        end_time    : yyyy-mm-dd
    },
    1:{
        hid         : 765
        cid         : 22222
        title       : rrr
        content     : uuu
        start_time  : yyyy-mm-dd
        end_time     : yyyy-mm-dd
    }
}
```
长度为0-n
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance(param).getHomeworkInfo()` 获取

## 课程是否已选
> 主要页面：course.jsp
> 
> 额外使用：course.js course.css
### UPLOAD
```
{
    type    : "hasJoin",
    cid     : 333,
    uid     : 444
}
```
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance(param).courseTableInfo().has("0")` 获取，返回：
true    : 已选
false   : 未选

## 退课
> 主要页面：course.jsp
> 
> 额外使用：course.js course.css
### UPLOAD
```
{
    type    : "dropoutcourse",
    cid     : 333,
    uid     : 444
}
```
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance(param).deleteCourseTable()` 

## 选课
> 主要页面：course.jsp
> 
> 额外使用：course.js course.css
### UPLOAD
```
{
    type    : "joincourse",
    cid     : 333,
    uid     : 444
}
```
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance(param).createCourseTable()` 

## 发布、修改、删除作业
> 主要页面：course.jsp
> 
> 额外使用：course.js course.css
### UPLOAD
```
发布
{
    type        : "homework",
    cid         : 2222,
    title       : zxcv,
    content     : xxxx,
    start_time  : yyyy-mm-dd
    end_time    : yyyy-mm-dd
}
删除
{
    type        : "homework",
    hid         : 1234,
    cid         : 2222,
}
修改
{
    type        : "homework",
    hid         : 1234,
    cid         : 2222,
    title       : zxcv,
    content     : xxxx,
    start_time  : yyyy-mm-dd
    end_time    : yyyy-mm-dd
}
```
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance(param).doHomework()`

## 发布资源
> 主要页面：course.jsp
> 
> 额外使用：course.js course.css
### UPLOAD
```
{
    type        : "aresource",
    cid         : 2222,
    number      : 1.2.3,
    url         : http://xxxxxx
}
```
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance(param).createRoursce()`

## 删除资源
> 主要页面：course.jsp
> 
> 额外使用：course.js course.css
### UPLOAD
```
{
    type        : "dresource",
    cid         : 2222,
    number      : 1.2.3,
}
```
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance(param).deleteResource()`

## 修改资源
> 主要页面：course.jsp
> 
> 额外使用：course.js course.css
### UPLOAD
```
{
    type        : "mresource",
    cid         : 2222,
    number      : 1.2.3,
    url         : http://xxxxxx
}
```
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance(param).modifyResource()`

## 发布、修改、删除知识点页面
> 主要页面：course.jsp
> 
> 额外使用：course.js course.css
### UPLOAD
```
发布
{
    type        : "page",
    cid         : 2222,
    title       : zxcv,
    content     : xxxx,
    number      : 2.3.1
    url         : http://wwwwwww
}
删除
{
    type        : "page",
    pid         : 1234,
}
修改
{
    type        : "page",
    pid         : 1234,
    cid         : 2222,
    title       : zxcv,
    content     : xxxx,
    number      : 2.3.1
    url         : http://wwwwwww
}
```
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance(param).doPage()`

## 开设、修改课程
> 主要页面：course.jsp
> 
> 额外使用：course.js course.css
### UPLOAD
```
发布
{
    type        : "course",
    title       : zxcv,
    content     : xxxx,
    number      : 2.3.1
    url         : http://wwwwwww
}
修改
{
    type        : "course",
    cid         : 2222,
    title       : zxcv,
    content     : xxxx,
    teacher_id  : 2.3.1
    pic_url     : http://wwwwwww
}
```
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance(param).doCourse()`

### 知识点列表 见知识点列表
> 主要页面：coursepage.jsp
> 
> 额外使用：coursepage.js coursepage.css

###添加浏览记录
> 主要页面：coursepage.jsp
> 
> 额外使用：coursepage.js coursepage.css
### UPLOAD
```
{
    type        : "add_record",
    cid         : 2222,
    pid         : 12,
    cid         : 444
}
```
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance(param).createRecord()`

## 作业信息
> 主要页面：homework.jsp
> 
> 额外使用：homework.js homework.css
### UPLOAD
```
{
    type: "homeworklist",
    hid : 1234
}
```
### DOWNLOAD
```
{
    0:{
        hid         : 1234
        cid         : 5432
        title       : ggg
        content     : bbb
        start_time  : yyyy-mm-dd
        end_time    : yyyy-mm-dd
    }
}
```
长度为0-n
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance(param).getHomeworkInfo()` 获取

## 做作业、修改作业答案、批改分数、可批改作业列表
> 主要页面：homework.jsp
> 
> 额外使用：homework.js homework.css
### UPLOAD
```
做作业
{
    type        : "answer",
    hid         : 2222,
    uid         : 4343,
    content     : xxxx,
}
修改作业答案
{
    type        : "answer",
    hid         : 2222,
    uid         : 4343,
    content     : xxxx,
}
批改分数
{
    type        : "answer",
    hid         : 2222,
    uid         : 4343,
    grade       : 2.3.1
}
可批改作业列表
{
    type        : "answer",
    hid         : 2222
}
```
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance(param).doAnswer()`

## 作业信息 同作业信息
> 主要页面：answer.jsp
> 
> 额外使用：answer.js answer.css

## 做作业、修改作业答案、批改分数、可批改作业列表 同做作业、修改作业答案、批改分数、可批改作业列表
> 主要页面：answer.jsp
> 
> 额外使用：answer.js answer.css

##个人选课列表、个人开课列表
> 主要页面：person.jsp
> 
> 额外使用：person.js person.css
### UPLOAD
```
个人选课列表
{
    type        : "courseinfo",
    uid         : 4343,
}
个人开课列表
{
    type        : "courseinfo",
    tid         : 4343,
}
```
### DOWNLOAD
```
{
    0:{
        cid         : 1234
        title       : aaa  
        content     : bbb
        teacher_id  : 6789
        pic_url     : http://xxx.jpg
    },
    1:{
        cid         : 2222
        title       : ddd  
        content     : eee
        teacher_id  : 2333
        pic_url     : http://yyy.jpg
    }
}
```
> 长度为0-n
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance().doCourseInfo()` 获取

##用户信息 同用户信息
> 主要页面：person.jsp
> 
> 额外使用：person.js person.css


##密码验证退课 同登陆
> 主要页面：person.jsp
> 
> 额外使用：person.js person.css

##课程浏览统计排名
> 主要页面：person.jsp
> 
> 额外使用：person.js person.css
### UPLOAD
```
{
    type        : "countrecord",
    cid         : 4343,
}
```
### DOWNLOAD
```
{
    0:{
        uid         : 1234
        count       : 3  
        nickname    : bbb
    },
    1:{
        uid         : 3333
        count       : 999
        nickname    : sdfg
    }
}
```
> 长度为0-n
### DESCRIPTION
使用 `ServiceFactory.getCourseServiceInstance().getRecordInfo()` 获取