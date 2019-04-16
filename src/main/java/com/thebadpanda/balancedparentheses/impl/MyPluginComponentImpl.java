package com.thebadpanda.balancedparentheses.impl;

import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.ApplicationProperties;
import com.thebadpanda.balancedparentheses.api.MyPluginComponent;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Scanner;

@ExportAsService({MyPluginComponent.class})
@Named("myPluginComponent")
public class MyPluginComponentImpl implements MyPluginComponent {

    @ComponentImport
    private final ApplicationProperties applicationProperties;

    @Inject
    public MyPluginComponentImpl(final ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public String getName() {
        if (null != applicationProperties) {
            return "myComponent:" + applicationProperties.getDisplayName();
        }

        return "myComponent";
    }

    @Override
    public String checkBalanced(String input) {
        long countLeft;
        long countRight;

        System.out.println("enter string: ");

        Scanner sc = new Scanner(System.in);
        String res = sc.hasNext() ? sc.next() : "";

        countLeft = res.codePoints()
                .mapToObj(c -> String.valueOf((char) c))
                .filter(s -> s.equals("(")).count();
        countRight = res.codePoints()
                .mapToObj(c -> String.valueOf((char) c))
                .filter(s -> s.equals(")")).count();

        if (countLeft == countRight) {
            return input;
        } else {
            return "Brackets are not balanced!";
        }
    }
}