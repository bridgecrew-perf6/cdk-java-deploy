package com.myorg;

import software.amazon.awscdk.App;

public final class SampleAppNewApp {
    public static void main(final String[] args) {
        App app = new App();

        new SampleAppNewStack(app, "SampleAppNewStack");

        app.synth();
    }
}
