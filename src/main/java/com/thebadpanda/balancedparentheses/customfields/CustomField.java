package com.thebadpanda.balancedparentheses.customfields;

import com.atlassian.jira.issue.customfields.impl.GenericTextCFType;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.fields.TextFieldCharacterLengthValidator;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.ApplicationProperties;
import com.thebadpanda.balancedparentheses.api.MyPluginComponent;
import com.thebadpanda.balancedparentheses.impl.MyPluginComponentImpl;

@Scanned
public class CustomField extends GenericTextCFType {
    @ComponentImport
    private final ApplicationProperties applicationProperties;

    protected CustomField(CustomFieldValuePersister customFieldValuePersister, GenericConfigManager genericConfigManager, TextFieldCharacterLengthValidator textFieldCharacterLengthValidator, JiraAuthenticationContext jiraAuthenticationContext, ApplicationProperties applicationProperties) {
        super(customFieldValuePersister, genericConfigManager, textFieldCharacterLengthValidator, jiraAuthenticationContext);
        this.applicationProperties = applicationProperties;
    }

    @Override
    public String getStringFromSingularObject(String value) {
        if (value == null) {
            return null;
        }

        MyPluginComponent myPluginComponent = new MyPluginComponentImpl(applicationProperties);
        return myPluginComponent.checkBalanced(value);
    }
}
