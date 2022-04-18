package com.thread.juc_stream;

import java.util.Arrays;
import java.util.List;


/**
 * 题目要求：一分钟以内完成此题，只能用一行代码实现
 * 现有6个用户，筛选：
 * 1、id必须是偶数
 * 2、年龄必须大于23岁
 * 3、用户名字母变成大写
 * 4、用户名字母倒着排序
 * 5、只输出一个用户
 */
public class StreamTest {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 23);
        User u2 = new User(2, "b", 25);
        User u3 = new User(3, "c", 28);
        User u4 = new User(4, "d", 20);
        User u5 = new User(5, "e", 17);
        User u6 = new User(6, "f", 22);

        List<User> users = Arrays.asList(u1, u2, u3, u4, u5, u6);

        users.stream()
                .filter((user) -> {
                    return user.getId() % 2 == 0;
                })
                .filter(user -> {
                    return user.getAge() > 23;
                })
                .map(user -> {
                    return user.getName().toUpperCase();
                })
                .sorted((user1, user2) -> {
                    return user2.compareTo(user1);
                })
                .limit(1)
                .forEach((user) -> {
                    System.out.println(user);
                });


    }
}
