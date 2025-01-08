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
        
        if ((account.getPoints() >= 0) && (account.getPoints() < 500)) {
            account.setStatus(RewardsAccount.Status.BASIC);
        } else if ((account.getPoints() >= 501) && (account.getPoints() < 1000)) {
            account.setStatus(RewardsAccount.Status.GOLD);
        } else {
            account.setStatus(RewardsAccount.Status.PLATINUM);
        }
        
        return account;
    }
    
    @Override
    public RewardsAccount cancelPoints(RewardsAccount account, long pointsCancelled) {
        account.removePoints(pointsCancelled);
        
        return account;
    }
}
