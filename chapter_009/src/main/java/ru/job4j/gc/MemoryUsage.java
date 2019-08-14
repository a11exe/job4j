/*
  @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 26.07.2019
 */
package ru.job4j.gc;

import java.lang.ref.WeakReference;

import static com.carrotsearch.sizeof.RamUsageEstimator.sizeOf;


/** @noinspection deprecation*/
public class MemoryUsage {

    public static class User {
        private final String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() {
            System.out.println(String.format("finalize %s", name));
        }
    }

    public static class SimpleUser {

    }

    public static void main(String[] args) {
        info();

        System.out.println(String.format("integer size %s B", sizeOf(new Integer(5))));
        System.out.println(String.format("string size %s B", sizeOf(new String())));
        System.out.println(String.format("simple object size %s B", sizeOf(new SimpleUser())));
        System.out.println(String.format("bare object size %s B", sizeOf(new Object())));
        System.out.println(String.format("user size %s B", sizeOf(new User("name"))));
        System.out.println(String.format("weak user size %s B", sizeOf(new WeakReference<>(new User("User")))));

        for (int i = 0; i < 10000; i++) {
            WeakReference<User> user = new WeakReference<>(new User("User " + i));
            usedMemory(i + 1);
            System.out.println(String.format("estimated object size %s", sizeOf(user)));
            user = null;

        }
        System.out.println("finish");
    }

    public static void info() {
        int kb = 1024;
        Runtime runtime = Runtime.getRuntime();
        System.out.println(String.format("Total memory %s KB", runtime.totalMemory() / kb));
    }

    public static void usedMemory(int count) {
        int kb = 1024;
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / kb;

        System.out.println(String.format("Used memory %s KB. Average user size %s B", usedMemory, (runtime.totalMemory() - runtime.freeMemory()) / count));
    }

}
