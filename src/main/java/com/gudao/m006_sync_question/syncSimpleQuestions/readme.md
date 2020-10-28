```shell
#问题
当我们对一个数字进行递增操作时，如果两个程序同时访问，
第一个线程读到count=0，并对其+1，在自己线程内部的内
存里还没有写回去的时候；第二个线程读到的count也是0，
并+1写回去；但是程序明明对count进行了两次+1操作，但
结果还是1。

#解决
那么我们对这个递增过程加上一把锁，当第一个程序访问的
时候，这个资源是它独占的，不允许别的线程访问计算，当
第一个线程计算完成并释放锁之后其它线程才能访问，这样
就保证了线程安全。
```