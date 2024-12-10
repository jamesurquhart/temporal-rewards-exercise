package com.jamesurquhart.rewards;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

/**
 *
 * @author jamesurquhart
 */

@ActivityInterface
public interface UpdateRewardsActivity {
    
    @ActivityMethod
    public RewardsAccount addPoints(RewardsAccount account, long pointsEarned);
    
    @ActivityMethod
    public RewardsAccount cancelPoints(RewardsAccount account, long pointsCancelled);
}
