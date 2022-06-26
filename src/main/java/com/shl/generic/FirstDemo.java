package com.shl.generic;

public class FirstDemo {
    public static void main(String[] args) {
        Pair<String> p = new Pair<>("aaa", "bbb");
        // Pair<Integer> p2 = new Pair<>("2", "3");

        System.out.println(">>>f = " + p.getFirst());

        Pair2<String, Integer> pp2 = new Pair2<>("沈", 28);
    }

    <V> V getParam(V v) {
        return v;
    }

    static class Pair<T> {
        T first;
        T second;

        public Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public T getSecond() {
            return second;
        }
    }

    static class Pair2<T, K> {
        T first;
        K second;

        public Pair2(T first, K second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public K getSecond() {
            return second;
        }
    }
}
