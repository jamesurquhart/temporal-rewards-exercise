package com.jamesurquhart.rewards;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

/**
 *
 * @author jamesurquhart
 */
@ActivityInterface
public interface CancelRewardsProgramActivity {
    
    @ActivityMethod
    public RewardsAccount cancelRewardsProgram(RewardsAccount account);
    
}
