# WinterExamination #
```
最后，还是只能把这个残次品交上来，已经想不到该怎么改了   
最初的规划和最后的成品有些相斥，用了各种方法调节，不过最后就是虽然做到了一些功能但是留下了一堆bug改不了了。。。
一开始是想用MVP架构的，结果后面就不知道在干什么了
```
![]( "")
**那么，以下是介绍**   
## 开始界面 ##
就是想做一个引导界面，这个一开始的界面~~(除了复用知乎日报作业的那几张图)~~是用PS处理得到的    
![start.gif](https://github.com/btazsq/WinterExamination/blob/master/gif/start.gif "引导界面")
## 注册和登录界面 ##
标题栏是自定义控件，不是每个活动都写一遍   
但是那个可隐藏的输入密码那里没用自定义控件，而是专门写了两个类来管理    
~~但是明明自定义控件就好了呀，当时人傻了~~    
哦，对了，像网络连接呀、实现功能啊是专门写了对应的类去实现的
比如Http管网络、RealizeAPI实现功能、TxtData管数据存储。。
![register.gif](https://github.com/btazsq/WinterExamination/blob/master/gif/register.gif "注册界面")
![password.gif](https://github.com/btazsq/WinterExamination/blob/master/gif/password.gif "密码")
