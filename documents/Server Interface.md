服务器端接口
==================
*TODO: 维护这个 MARKDOWN 来维护接口列表！！！*


## 用户部分
### 属性（获取和提供双接口）
用户名

加密的密码（md5 + 固定盐吧，我比较懒）

### 行为
#### 获取类
提交的开课信息

单个的选课信息

#### 提供类
所有的（或某个限定条件下的）已开课程信息

所有的（或某个限定条件下的）已选课程信息

## 数据库
### 用户表 users
|名称|描述|
|---|---|
|uid|ID，**主键**, auto inc|
|username|登录用的, unique|
|nickname|显示用的|
|email|邮箱名|
|password|记得加密！！！|

### 课程目录 courses
|名称|描述|
|---|---|
|id|课程，**主键**, auto inc|
|title|显示名称|
|t_uid|老师的 UID，**外键**|
|img|图片 URL|
|content|介绍|


### 知识点表 course_pages
|名称|描述|
|---|---|
|id|这个应该要和上传的页面号一致，打个比方页面显示 av10492，这里记录10492，**主键**, auto inc|
|c_id|课程 ID，**外键**|
|number|该知识点在该课程中的编号|
|title|此节课程标题|
|content|内容|
|link|资源的URL，以空格隔开，存入前先转义|

### 选课总表 choose_course
|名称|描述|
|---|---|
|c_id|课程 ID|
|s_id|学生 UID|

### 资源表 resources
|名称|描述|
|---|---|
|c_id|课程 ID|
|number|资源排序|
|content|放置资源内容|

### 知识点统计 statistics
|名称|描述|
|---|---|
|s_id|学生 ID|
|c_id|课程 ID|
|cp_id|知识点 ID|

### 作业表 homework
|名称|描述|
|---|---|
|id|**主键**, auto inc|
|c_id|课程 ID|
|beg_time|开始时间|
|end_time|截止时间|
|title|作业标题|
|content|作业内容|

### 作业答案 homework_submit
|名称|描述|
|---|---|
|hw_id|作业 ID|
|s_id|完成作业的学生 ID|
|content|答题内容|
|score|作业得分|

### 讨论版（网游世界频道） talk
|名称|描述|
|---|---|
|id|讨论号，auto inc，**主键**|
|uid|发布者 ID|
|content|发布内容|
|time|发布时间|