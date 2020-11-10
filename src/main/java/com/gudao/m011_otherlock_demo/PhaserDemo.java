package com.gudao.m011_otherlock_demo;

import java.util.concurrent.Phaser;

/**
 * Author : GuDao
 * 2020-11-10
 */

public class PhaserDemo {
    static PhaserMarriage marriage = new PhaserMarriage();

    public static void main(String[] args) {
        //设置注册量
        PhaserDemo.marriage.bulkRegister(7);

        for (int i = 0; i < 5; i++) {
            new Thread(new Person("Person"+i)).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }

}

class Person implements Runnable {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void register(){
        System.out.println(this.name+"来了");
        //让线程到达此处，满足向下执行的条件后再向下走
        PhaserDemo.marriage.arriveAndAwaitAdvance();
    }

    public void eat(){
        System.out.println(this.name+"吃完了");
        //让线程到达此处，满足向下执行的条件后再向下走
        PhaserDemo.marriage.arriveAndAwaitAdvance();
    }

    public void sleep(){
        if(name.equals("新郎") || name.equals("新娘")){
            //让线程到达此处，满足向下执行的条件后再向下走
            PhaserDemo.marriage.arriveAndAwaitAdvance();
            System.out.println(name+"入洞房");
        }else{
            //将其它线程注销
            PhaserDemo.marriage.arriveAndDeregister();
        }
    }

    @Override
    public void run() {
        register();
        eat();
        sleep();
    }
}

class PhaserMarriage extends Phaser {
    /**
     * 重写的方法,在即将到来的相位提前执行一个动作,并控制终止。这种方法党推进这一移相器
     * (当所有其他等待各方都处于休眠状态)到达时被调用。如果这个方法返回true ,
     * 这将移相器根据预先设定的最后终止状态,并随后将呼叫isTerminated将返回true.
     * 任何(未选中)异常或错误通过该方法的调用抛出被传播到方试图推进这个移相器,
     * 在这种情况下没有提前发生。
     *
     * 该方法的参数提供该相位器普遍用于当前过渡的状态。调用到达,登记,
     * 和从内有关此移相器等待方法的效果onAdvance是不确定的,并且不应当被上依赖。
     * 如果该相位器是一个分层组相位器中的一个成员,然后onAdvance仅针对每个预先其根相位器调用。
     *
     * 支持最常见的用例,此方法返回的默认实现true当注册方的数量已变为零作为党调用的结果arriveAndDeregister.
     * 您可以禁用此行为,从而使持续在未来的登记,通过重写此方法总是返回false:
     *
     * @param phase 进入此方法时的当前阶段号
     * @param registeredParties 当前注册方的数量
     * @return
     */
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {//这个方法的返回值是boolean类型的，所以case中的返回值都是boolean类型

        /*
        protected boolean onAdvance(int phase, int registeredParties) {
            return registeredParties == 0;
        }

        onAdvance的内部实现，默认会将注册方数量置0【每次返回return false后就置0】
         */

        switch (phase){
            case 0:
                System.out.println("人都到齐了，开始吃饭");
                return false;
            case 1:
                System.out.println("吃完饭了，婚礼开始");
                return false;
            case 2:
                System.out.println("婚礼结束了，新郎新娘入洞房");
                return true;
            default:
                return true;
        }
    }
}