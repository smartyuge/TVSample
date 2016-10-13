# TVSample

Android TV发展离不开Metro UI,先看最新的泰捷TV的会员区效果图，属于典型的Metro风格，如下：

![这里写图片描述](/images/device-2016-10-13-170829.png)

###什么是Metro UI:

Metro的设计意念来源于交通局巴士站站牌机场和地铁的指示牌给了微软设计团队灵感，设计团队说Metro是来源于美国华盛顿州金县都会交通局（King County Metro）的标识设计，其风格大量采用大字体，能吸引受众之注意力。微软认为Metro设计[2]  主题应该是：“光滑、快、现代”。Metro的图标设计也会不同于Android和iOS。

###Metro UI软件

Metro是微软为了方便开发者编写Metro风格的程序而提供的一个开发平台，可以调用微软WinRT暴露出来的接口编写Metro风格的程序。 而Metro风格的控件[3]  拓展Win8标准控件方法和属性,实现一些新的功能,如Component One Studio for WinRT XAML，Component One Studio for WinJS。在Windows8中开放的Windows应用程序市场也使用并推荐采用Metro风格界面的应用程序.

Metro 界面，开机后首先映入眼帘的第一个界面，个人感觉主要是为了触屏设备而设计的，但是在TV中使用起来也一样的方便。我们在Desktop中安装的程序以及 在应用商店中下载的程序都会在Metro中展现出来，所以我们要定期或不定期的对其进行分组、排序、整理，以方便我们的操作以及界面的美观。Metro界面同时提供了便捷的选项，使操作更加方便。

今天来仿照并实现这个Metro界面，以下是我实现的效果图：

![这里写图片描述](/images/device-2016-10-13-192016.png)

![这里写图片描述](/images/device-2016-10-13-191954.png)

gif图：(TV上没有好的录屏工具，盒子系统一般低于5.0，有些厂商已经升到5.0，我用的是i71，很老的盒子，基于API 17, 4.2.2的系统)

![这里写图片描述](/images/metro.gif)

说这么多，来看下代码实现吧，
先看下其中一个MetroItem的布局：

![这里写图片描述](/images/xml.png)

每一个MetroItemFrameLayout下包含一些子View,ImageView就是对应图片，CornerVew表示上下左右的的角标，TextView表示下方的描述，这些可视具体情况进行选择。

![这里写图片描述](/images/metro1.png)
![这里写图片描述](/images/metro2.png)

还有其他代码分析陆续在博客上更新。

####欢迎关注我的个人公众号，android 技术干货，问题深度总结，FrameWork源码解析，插件化研究，最新开源项目推荐
![这里写图片描述](https://github.com/hejunlin2013/RedPackage/blob/master/image/qrcode.jpg)

License
--------
```
Copyright (C) 2016 hejunlin

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

