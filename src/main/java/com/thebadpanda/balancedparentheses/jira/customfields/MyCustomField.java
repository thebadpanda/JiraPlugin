package com.thebadpanda.balancedparentheses.jira.customfields;

import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.issue.customfields.impl.GenericTextCFType;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.fields.TextFieldCharacterLengthValidator;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.plugin.spring.scanner.annotation.imports.JiraImport;
import com.thebadpanda.balancedparentheses.api.CheckForBalance;

import javax.inject.Inject;

@Scanned
public class MyCustomField extends GenericTextCFType {

    @ComponentImport
    private final CustomFieldValuePersister customFieldValuePersister;

    @ComponentImport
    private final GenericConfigManager genericConfigManager;

    CheckForBalance checkForBalance;


    @Inject
    public MyCustomField(@JiraImport CustomFieldValuePersister customFieldValuePersister,
                         @JiraImport GenericConfigManager genericConfigManager,
                         @JiraImport TextFieldCharacterLengthValidator textFieldCharacterLengthValidator,
                         @JiraImport JiraAuthenticationContext jiraAuthenticationContext) {
        super(customFieldValuePersister, genericConfigManager, textFieldCharacterLengthValidator, jiraAuthenticationContext);
        this.customFieldValuePersister = customFieldValuePersister;
        this.genericConfigManager = genericConfigManager;
    }

    @Override
    public String getSingularObjectFromString(String string) throws FieldValidationException {
        if (string == null) {
            return null;
        }
        if (!checkForBalance.isBracketsBalanced(string)) {
            throw new FieldValidationException("Brackets are not balanced");
        } else {
            return string;
        }
    }
}
