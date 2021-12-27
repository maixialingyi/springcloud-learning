package com.mid.base;

import java.io.*;

public class TestSerializable {

    static class Person implements Serializable {
       private static final long serialVersionUID = -5809782578272943999L;
       private int age;

        public static long getSerialVersionUID() {
            return serialVersionUID;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /**
         * 序列化
         */
        /*Person person = new Person();
        person.setAge(25);
        // ObjectOutputStream 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("/Users/jiangshaoyue/Person.txt")));
        oo.writeObject(person);
        System.out.println("Person对象序列化成功！");
        oo.close();*/

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("/Users/jiangshaoyue/Person.txt")));
        Person person2 = (Person) ois.readObject();
        System.out.println("Person对象反序列化成功！");
        System.out.println(person2.toString());
    }
}
