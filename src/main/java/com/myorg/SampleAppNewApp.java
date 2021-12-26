package com.myorg;

import java.util.Objects;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

public final class SampleAppNewApp {
    public static void main(final String[] args) {
        App app = new App();

        String environmentName = System.getenv("CDK_DEPLOY_STACK_NAME");
        Objects.requireNonNull(environmentName, "environment variable 'CDK_DEPLOY_STACK_NAME' must not be null");
        
        Environment awsEnvironment = makeEnv(null, null);

        new SampleAppNewStack(app, "SampleAppNewStack", StackProps.builder()
                .stackName(environmentName + "-SampleApp")
                .env(awsEnvironment)
                .build());

        app.synth();
    }

    static Environment makeEnv(String account, String region) {
        account = (account == null) ? System.getenv("CDK_DEPLOY_ACCOUNT") : account;
        region = (region == null) ? System.getenv("CDK_DEPLOY_REGION") : region;
        account = (account == null) ? System.getenv("CDK_DEFAULT_ACCOUNT") : account;
        region = (region == null) ? System.getenv("CDK_DEFAULT_REGION") : region;

        return Environment.builder()
                .account(account)
                .region(region)
                .build();
    }
}
