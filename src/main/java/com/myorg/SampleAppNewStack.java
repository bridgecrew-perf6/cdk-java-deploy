package com.myorg;

import software.amazon.awscdk.Duration;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.sns.Topic;
import software.amazon.awscdk.services.sns.subscriptions.SqsSubscription;
import software.amazon.awscdk.services.sqs.Queue;
import software.constructs.Construct;

public class SampleAppNewStack extends Stack {
    public SampleAppNewStack(final Construct parent, final String id) {
        this(parent, id, null);
    }

    public SampleAppNewStack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);

        final Queue queue = Queue.Builder.create(this, "SampleAppNewQueue")
                .visibilityTimeout(Duration.seconds(300))
                .build();

        final Topic topic = Topic.Builder.create(this, "SampleAppNewTopic")
                .displayName("My Last Topic Yeah")
                .build();

        topic.addSubscription(new SqsSubscription(queue));

        Bucket.Builder.create(this, "SampleAppNew Bucket")
            .versioned(true)
            .build();
    }
}
