# 短链接管理

- 生成规则

数据库10进制id转换成62进制的短链接编码，再加2位随机字符串，避免根据规则来猜短链接。

- 查询

根据编码计算出id，然后查询数据库,暂时未做缓存。

- 参考的文章

[参考文章](https://www.zhihu.com/question/29270034)

[jpa 查询一个](https://stackoverflow.com/questions/2061068/jpa-find-the-last-entry/43819641#43819641)