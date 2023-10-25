package com.fastcampus.ch3.aop;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.transaction.annotation.Transactional;

public class AopMain {
    public static void main(String[] args) throws Exception {
        MyAdvice myAdvice = new MyAdvice();

        Class myClass = Class.forName("com.fastcampus.ch3.aop.Myclass");
        Object obj = myClass.newInstance();

        for (Method m : myClass.getDeclaredMethods()) {
            myAdvice.invok(m, obj, null);
        }

    }
}

class MyAdvice {
    Pattern p = Pattern.compile("a.*"); // 정규식 a로 시작하는 메서드만

    boolean matches(Method m) {
        Matcher matcher = p.matcher(m.getName());
        return matcher.matches();
    }

    void invok(Method m, Object obj, Object... args) throws Exception {
        if (m.getAnnotation(Transactional.class) != null)
            System.out.println("[before]");
        m.invoke(obj, args);     // aaa(),aaa2(),bbb() 메서드 들을 호출 가능하게 할 것이다

        if (m.getAnnotation(Transactional.class) != null)
            System.out.println("[after]");
    }
}

class Myclass {
    @Transactional
    void aaa() {
        System.out.println("aaa() is called");
    }

    void aaa2() {
        System.out.println("aaa2() is called");
    }

    void bbb() {
        System.out.println("bbb() is called");
    }
}