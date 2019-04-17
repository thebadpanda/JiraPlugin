package com.thebadpanda.balancedparentheses.jira.customfields;

import java.util.Stack;

public class CheckForBalanceImpl implements CheckForBalance {

    @Override
    public boolean isBracketsBalanced(String string) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
