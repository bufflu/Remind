# Remind
云提醒，在提醒事项后，会在设置的提醒时间前通过远程方式提醒用户处理代办事项。


## 展示
### 1. 登录
![login](https://github.com/bufflu/Remind/blob/master/img/login.png)

### 2. 注册
![register](https://github.com/bufflu/Remind/blob/master/img/register.png)

### 3. 日历
![calendar](https://github.com/bufflu/Remind/blob/master/img/calendar.png)

### 4. 提醒列表
![remind](https://github.com/bufflu/Remind/blob/master/img/remindlist.png)

## 说明
### 1. 开发
项目后端使用Java8+SpringBoot2.1.6+MybatisPlus3.1.2开发，前端使用Html+Ajax+Echars，数据库使用H2。

### 2. 模块划分
![model](https://github.com/bufflu/Remind/blob/master/img/model2.png)

项目目前分为登录(Login)、用户(User)、日历(Calendar)、提醒(Remind)、提醒列表操作(Handle)、推送(Send)主要模块。

登录：用户登录，提供扩展接口，可以自定义登录方式。

用户：用户的注册和密码重置等。

日历：后台维护可以添加提醒事项的日期，展示这些日期和每天已经添加的提醒事项数量。

提醒：增加、删除和展示提醒事项。

提醒列表操作：后台维护一个列表，用来存放提醒事项，添加提示时同时入列表，满足推送条件的提醒项出列，交给Send模块发送给用户。列表可以自定义实现，目前使用数据库表实现。

推送：将提醒事项推送至用户，提供扩展接口，目前没有实现该功能，可以选择短信、微信等方式通知。

### 3. 扩展

**LoginService**
```java
public interface LoginService {
    // 登录
    void login(User user, HttpServletRequest request);
    // 检查登录状态
    boolean checkLoginStatus(Object... objects);
    // 获取session中用户信息
    User getSessionUser(HttpServletRequest request);
}
```

**PushListHandle**
```java
public interface PushListHandle {  
    // 推送
    boolean send(Remind remind);
    // 添加
    void add(Remind remind);
}
```

**CustomConfig**

实现LoginService和PushListHandle接口后可以在此配置类中替换掉原有的默认实现。
