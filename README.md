# WinterExamination #
```
最后，还是只能把这个残次品交上来，已经想不到该怎么改了   
最初的规划和最后的成品有些相斥，用了各种方法调节，不过最后就是虽然做到了一些功能但是留下了一堆bug改不了了。。。
一开始是想用MVP架构的，结果后面就不知道在干什么了
```
apk:https://github.com/btazsq/WinterExamination/blob/master/app-release.apk
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
## 主要界面 ##
主界面用的viewpager，实际上不用的话可以减少太多麻烦了。。    
为了用代码改视觉之类的东西，把对象传来传去，留下了好多毛病    
![main.gif](https://github.com/btazsq/WinterExamination/blob/master/gif/main.gif "惨痛的教训")   
## 个人界面 ##
这个头像好难加载   
```
    一开始写的方法   
    /*
    加载网络图片   
     */
    public static Bitmap getImageBitmap(String url){
        Bitmap bitmap = null;
        try {
            HttpURLConnection connection = (HttpURLConnection)(new URL(url).openConnection());
            connection.setRequestMethod("GET");
            connection.setReadTimeout(8000);
            connection.setConnectTimeout(8000);
            connection.setDoInput(true);
            connection.connect();
            InputStream in = connection.getInputStream();
            Log.d(TAG, "code:"+connection.getResponseCode());
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
            connection.disconnect();
        } catch (Exception e) {
            Log.d(TAG, "getImageBitmap: "+e.toString());
        }
        return bitmap;
    }
    线程是调用这个方法的时候开    
```
一开始图片死活加载不出来，然后我去网上下载加载网络图片的Demo   
但网上的代码一样加载不出来
我就纳闷了，*但是精彩的地方来了*，我偶然回到我的app，发现头像加载出来了   
？！！   
实验这么久，就成功了2次，太不稳定了，最后换成了Glide   
![person.gif](https://github.com/btazsq/WinterExamination/blob/master/gif/person.gif "个人界面")
## 翻页 ##
![page.gif](https://github.com/btazsq/WinterExamination/blob/master/gif/page.gif "翻页")
## 点赞 ##
点赞之类的不刷新数字   
~~因为太多bug了，前期考虑不周到，导致后期不知道怎么改了~~   
![holder.gif](https://github.com/btazsq/WinterExamination/blob/master/gif/holder.gif "再次点是取消")   
## 评论 ##
最后的最后，评论的recycleVIEW和浏览与收藏是一样的，但是接受不到评论列表   
其实之前收藏列表也是空的，接收不到信息   
我把app装到手机上，时不时看看，结果有一天就突然收到了。。   
评论列表是一样的，只是没了收藏按钮，然后把点击view的事件改成了采纳   
![back.gif](https://github.com/btazsq/WinterExamination/blob/master/gif/back.gif "我太难了")
