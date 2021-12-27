package com.mid.base.shejimoshi.prototype;

/**
 * 原型模式: 
 */
//实现Cloneable 接口的原型抽象类Prototype
class Prototype implements Cloneable {
    //重写clone方法
    @Override
    public Prototype clone() {
        Prototype prototype = null;
        try {
            prototype = (Prototype) super.clone();//调用Object方法
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return prototype;
    }
}

//实现原型类
class ConcretePrototype extends Prototype {
    public void show() {
        System.out.println("原型模式实现类");
    }
}

class Client {
    public static void main(String[] args) {
        ConcretePrototype cp = new ConcretePrototype();
        for (int i = 0; i < 10; i++) {
            ConcretePrototype clonecp = (ConcretePrototype) cp.clone();
            clonecp.show();
        }
    }
}
