package com.fastcampus.ch3.aop;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public class AopMain {
    public static void main(String[] args) throws Exception{
        MyAdvice myAdvice = new MyAdvice();

        Class myClass = Class.forName("com.fastcampus.ch3.aop.Myclass");
        Object obj = myClass.newInstance();


        for (Method m : myClass.getDeclaredMethods()){
            myAdvice.invok(m,obj,null);
        }

    }
}

class MyAdvice {
    void invok(Method m, Object obj, Object... args) throws Exception {
        System.out.println("[before]");
        m.invoke(obj,args);     // aaa(),aaa2(),bbb() 메서드 들을 호출 가능하게 할 것이다
        System.out.println("[after]");
    }
}

class Myclass {
    void aaaa() {
        System.out.println("aaa() is called");
    }

    void aaaa2() {
        System.out.println("aaa() is called");
    }

    void bbb() {
        System.out.println("aaa() is called");
    }
}