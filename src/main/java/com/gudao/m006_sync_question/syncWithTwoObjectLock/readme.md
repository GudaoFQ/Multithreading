```shell
# 问题
当使用Object作为锁的时候，不同的线程要锁的是同一个object才能实现线程等待，不同线程如果锁的不是同一个object，那么这个线程也可以对synchronized中的内容进行操作
# 解决
在Object前面添加final，final Object o = new Object
```