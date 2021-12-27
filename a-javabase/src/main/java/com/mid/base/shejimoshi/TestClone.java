package com.mid.base.shejimoshi;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * super.clone()是浅拷贝,只能拷贝对象中的基本属性,对象类型则只拷贝引用
 * 深克隆则需要把引用对象也clone
 */
public class TestClone {

    public static void main(String[] args) {
        /*QianClone qianClone = new QianClone();
        qianClone.setName("name1");
        qianClone.setQuote(new Quote("quoteName1"));

        QianClone qianClone1 = qianClone.clone();
        qianClone1.setName("name2");
        qianClone1.getQuote().setQuoteName("quoteName2");

        System.out.println(qianClone.getName());
        System.out.println(qianClone1.getName());
        System.out.println(qianClone.getQuote().getQuoteName());
        System.out.println(qianClone1.getQuote().getQuoteName());*/

        /*结果: name1
             name2
             quoteName2
             quoteName2          结论:引用类型只clone了引用*/

        ShenClone shenClone = new ShenClone();
        shenClone.setName("name1");
        shenClone.setQuote(new Quote("quoteName1"));

        ShenClone shenClone1 = shenClone.clone();
        shenClone1.setName("name2");
        shenClone1.getQuote().setQuoteName("quoteName2");

        System.out.println(shenClone.getName());
        System.out.println(shenClone1.getName());
        System.out.println(shenClone.getQuote().getQuoteName());
        System.out.println(shenClone1.getQuote().getQuoteName());
    }

}

/**
 * 浅拷贝
 */
@Data
class QianClone implements Cloneable {
    private String name;
    private Quote quote;

    //重写克隆方法
    @Override
    public QianClone clone() {
        QianClone qianClone = null;
        try {
            qianClone = (QianClone) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return qianClone;
    }
}

@Data
class ShenClone implements Cloneable {
    private String name;
    private Quote quote;

    //重写克隆方法
    @Override
    public ShenClone clone() {
        ShenClone shenClone = null;
        try {
            shenClone = (ShenClone) super.clone();
            Quote quote =  this.quote.clone();
            shenClone.setQuote(quote);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return shenClone;
    }
}

/**
 * 引用对象
 */
@Data
@AllArgsConstructor
class Quote implements Cloneable{
    private String quoteName;

    @Override
    public Quote clone() {
        Quote quote = null;
        try {
            quote = (Quote) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return quote;
    }

}