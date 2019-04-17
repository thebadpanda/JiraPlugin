package com.thebadpanda.balancedparentheses.jira.customfields;

import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.issue.customfields.impl.GenericTextCFType;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.fields.TextFieldCharacterLengthValidator;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.util.ErrorCollection;
import com.atlassian.jira.util.ErrorCollections;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.plugin.spring.scanner.annotation.imports.JiraImport;

import javax.inject.Inject;
import java.util.Stack;


@Scanned
public class MyCustomField extends GenericTextCFType {

    @ComponentImport
    private final CustomFieldValuePersister customFieldValuePersister;

    @ComponentImport
    private final GenericConfigManager genericConfigManager;


    @Inject
    protected MyCustomField(@JiraImport CustomFieldValuePersister customFieldValuePersister,
                            @JiraImport GenericConfigManager genericConfigManager,
                            @JiraImport TextFieldCharacterLengthValidator textFieldCharacterLengthValidator,
                            @JiraImport JiraAuthenticationContext jiraAuthenticationContext) {
        super(customFieldValuePersister, genericConfigManager, textFieldCharacterLengthValidator, jiraAuthenticationContext);
        this.customFieldValuePersister = customFieldValuePersister;
        this.genericConfigManager = genericConfigManager;
    }


    @Override
    public String getStringFromSingularObject(String value) {
        if (value == null) {
            return null;
        }
        if (!balancedParentheses(value)) {
           ErrorCollections.create("Brackets are not balanced", ErrorCollection.Reason.VALIDATION_FAILED);
            throw new FieldValidationException("Brackets are not balanced");
        } else {
            return value;
        }
    }

    private static boolean balancedParentheses(String string) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    ErrorCollections.create("Brackets are not balanced", ErrorCollection.Reason.VALIDATION_FAILED);
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}