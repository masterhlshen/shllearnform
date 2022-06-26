package com.shl.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CaseJudge {
    public static void main(String[] args) {
        String[] arr = "( a && b || ( c || b ) ) && ( a && b ) c".split(" ");
        System.out.println("length = " + arr.length);
        Result result = solution(arr, 0, arr.length - 1);
        if (result == null) {
            System.out.println("不合法");
        } else {
            System.out.println(result.exp);
        }

    }

    private static Result solution(String[] a, int lo, int hi) {
        if (lo > hi) return null;
        Stack<String> optStack = new Stack<>();
        Stack<String> expressionStack = new Stack<>();
        String ele;
        for (int i = lo; i <= hi; i++) {
            System.out.println("i = " + i);
            ele = a[i];
            if (")".equals(ele)) {
                return (optStack.isEmpty() && !expressionStack.isEmpty()) ? new Result(expressionStack.pop(), i) : null;
            }

            if ("(".equals(ele)) {
                Result r = solution(a, i + 1, hi);
                if (r != null) {
                    i = r.index;
                    ele = r.exp;
                } else {
                    return null;
                }
            }

            if (opt.containsKey(ele)) {
                if (expressionStack.isEmpty()) {
                    return null;
                }
                optStack.push(ele);
            } else {
                if (expressionStack.isEmpty()) {
                    expressionStack.push(ele);
                } else if (!optStack.isEmpty()) {
                    String opts = optStack.pop();
                    String pop = expressionStack.pop();
                    expressionStack.push(pop + opts + ele);
                } else {
                    return null;
                }

            }
        }

        return (optStack.isEmpty() && !expressionStack.isEmpty()) ? new Result(expressionStack.pop(), 0) : null;
    }

    static class Result {
        String exp;
        Integer index;

        Result(String exp, Integer index) {
            this.exp = exp;
            this.index = index;
        }
    }

    static Map<String, String> opt = new HashMap<>();

    static {
        opt.put("&&", "");
        opt.put("||", "");
    }
}
