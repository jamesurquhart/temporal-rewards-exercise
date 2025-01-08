package com.jamesurquhart.rewards;

/**
 *
 * @author jamesurquhart
 */
public class ActivityPair {
    String activityName;
    String activityArguments;

    ActivityPair(String activityName, String activityArguments) {
        this.activityName = activityName;
        this.activityArguments = activityArguments;
    }

    String getName() {
        return this.activityName;
    }

    String getArguments() {
        return this.activityArguments;
    }
}
