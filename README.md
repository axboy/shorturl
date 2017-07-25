# 短链接管理

[访问地址](https://url.wazitang.cn/)

[参考文章](https://www.zhihu.com/question/29270034)

[jpa 查询一个](https://stackoverflow.com/questions/2061068/jpa-find-the-last-entry/43819641#43819641)

- 生成规则

1. 将10进制id转换为62进制，再加两位随机字符串。

- 查询

1. 根据编码计算出id，然后查询数据库,暂时未做缓存。
