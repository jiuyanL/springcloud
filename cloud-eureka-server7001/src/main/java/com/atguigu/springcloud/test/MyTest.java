package com.atguigu.springcloud.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyTest {
    public static void main(String[] args) {
        //随机生成一个8位数 要求只能由三个数字 5字母
        /*
        * 1.两个数组分别装字母数字
        * 2.在数字数组中随机取出来3个 能不能重复？
        * 3.在字母数组中随机取出5个
        * 4.取出数据拼接成字符串
        * 5.打乱字符串
        *
        * */
        int[] arr ={0,1,2,3,4,5,6,7,8,9};
        char[] chars ={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','u','r','s','t','u','v','w','x','y','z'};
        String str="";
        for (int i=0;i<3;i++){
            int index = (int) (Math.random()*arr.length);
            int var = arr[index];
            str+=var;
        }

        for (int i=0; i<5;i++){
            int index = (int) (Math.random()*chars.length);
            char ch = chars[index];
            str+=ch;
        }

        List<String> list = Arrays.asList(str.split(""));

        Collections.shuffle(list);
        System.out.println(list);

    }
}
