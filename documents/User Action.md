用户行为
=========
这里的用户行为指的是涉及到账户操作的部分

## 服务器端
### LOG IN
JSON 结构：
```
{
    'type': 'log',
    'content':
    {
        'username': 'abcdefg',
        'password': 'aaabbbccc'
    }
}
```
#### 登录行为
1. 获取 `username`, `password`，与数据库的进行匹配。
2. 匹配完成后，设置 `logged` 为 1。
3. 页面获取成功的代码后设置 cookie/session 为已登录。

#### 注册行为
1. 获取 `username`，与数据库的进行匹配。
2. 若不存在，则将数据添加到数据库。

### IS LOGGED
JSON 结构：
```
{
    'type': 'isLog',
    'content':
    {
        'username': 'abcdefg'
    }
}
```
1. 获取 `username`，与数据库的进行匹配。
2. 匹配完成后，确定 `logged` 的值。
3. 当页面 cookie/session 为已登录且 `logged` 值为 1 时，认为其已登录。

