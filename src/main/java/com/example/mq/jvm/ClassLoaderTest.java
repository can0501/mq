package com.example.mq.jvm;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author 钟金灿
 * @since 2022/3/11
 */
public class ClassLoaderTest {




    @Test
    public void testInitialCL1(){

        try {
            System.out.println("start");
            ClassDemo classDemo = new ClassDemo();
            System.out.println("ClassDemo classLoader ：" + ClassDemo.class.getClassLoader());
            classDemo.getMath();//此方法会触发StrictMath类的加载
            System.out.println("------");
//            Class<?> aClass = Class.forName("java.lang.StrictMath");
            System.out.println(StrictMath.class.getClassLoader());
            Class<?> strictMathClass = ClassLoader.getSystemClassLoader().loadClass("java.lang.StrictMath");
            System.out.println(strictMathClass.getClassLoader());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInitialCL(){
        //查看GenericSignatureFormatError的初始类加载器
        Class<?> StrictMath  = null;
        try {
            System.out.println("start");
            StrictMath = ClassLoader.getSystemClassLoader().loadClass("java.lang.StrictMath");
            System.out.println(StrictMath.getClassLoader());
            StrictMath = ClassLoader.getSystemClassLoader().loadClass("java.lang.StrictMath");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    /**
     * 父加载器加载不了,子加载器所加载的类，
     * 父加载器加载Parent
     * 子加载器加载son
     * Parent#getSon 方法里 new Son()对象.//报错,找不到Son的类定义.
     */


    @Test
    public void testParentCanntFindSon(){
        ClassLoader customClassLoader01 = ClassLoader.getSystemClassLoader();

        try {
            Class<?> classParent = customClassLoader01.loadClass("com.example.mq.jvm.Parent");
            System.out.println("classParent:" + classParent.getClassLoader());
            Class<?> classSon = customClassLoader01.loadClass("com.example.mq.jvm.Son");
            System.out.println("classSon:" + classSon.getClassLoader());
            Object objParent = classParent.newInstance();

            Object objSon = classSon.newInstance();

            Method setSon = classParent.getMethod("setSon",Object.class);
            Object resultSon = setSon.invoke(objParent, objSon);
            System.out.println(resultSon.getClass());
            System.out.println(resultSon.getClass().getClassLoader());

            try {
                Method getSon = classParent.getMethod("getSon");
                Object invoke = getSon.invoke(objParent);
                System.out.println(invoke.getClass().getClassLoader());
            }catch (Exception exp){
                //java.lang.NoClassDefFoundError: com/rock/Son
                exp.printStackTrace();
            }
        }catch (Exception exp){
            exp.printStackTrace();
        }
    }


    /**
     * StrictMath 是boostrap类加载器加载的。
     * loadClass的方式，AppClassLoader不是其初始类
     * trictMath 是boostrap类加载器加载的。
     * import的方式再使用，AppClassLoader是其初始类
     */
    @Test
    public void testInitialCL2(){
        Class<?> StrictMath  = null;
        try {
            System.out.println("start");
            StrictMath = ClassLoader.getSystemClassLoader().loadClass("java.lang.StrictMath");
            System.out.println(StrictMath.getClassLoader());
            StrictMath = ClassLoader.getSystemClassLoader().loadClass("java.lang.StrictMath");

            System.out.println("start2");
            ClassDemo classDemo = new ClassDemo();
            System.out.println("ClassDemo classLoader ：" + ClassDemo.class.getClassLoader());
            classDemo.getMath();//此方法会触发StrictMath类的加载
            System.out.println("------");
            System.out.println(StrictMath.class.getClassLoader());
            Class<?> strictMathClass = ClassLoader.getSystemClassLoader().loadClass("java.lang.StrictMath");
            System.out.println(strictMathClass.getClassLoader());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void te(){
        System.out.println(ttt(null));
    }

    private Integer ttt(Integer integer){

        try {
            if (integer.intValue() == 0) {

            }
            return 1;
        } catch (RuntimeException e) {
//            e.printStackTrace();
            return -1;
        }finally {
            System.out.println(0);
        }

    }
}
