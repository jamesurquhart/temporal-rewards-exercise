package com.jamesurquhart.rewards;

/**
 *
 * @author jamesurquhart
 */
public class UpdateRewardsActivityImpl implements UpdateRewardsActivity {
    
    @Override
    public RewardsAccount addPoints(RewardsAccount account, long pointsEarned) {
        System.out.printf("\naddPoints function executed in UpdateRewardsActivity for %s points\n", pointsEarned);
        account.addPoints(pointsEarned);
        
        return account;
    }
    
    @Override
    public RewardsAccount cancelPoints(RewardsAccount account, long pointsCancelled) {
        account.removePoints(pointsCancelled);
        
        return account;
    }
}
