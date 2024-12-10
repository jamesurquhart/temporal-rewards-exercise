package com.jamesurquhart.rewards;

/**
 *
 * @author jamesurquhart
 */
public class CancelRewardsProgramActivityImpl implements CancelRewardsProgramActivity {
    
    @Override
    public RewardsAccount cancelRewardsProgram(RewardsAccount account) {
        account.isCancelled = true;
        
        return account;
    }
}
