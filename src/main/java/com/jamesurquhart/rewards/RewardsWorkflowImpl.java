package com.jamesurquhart.rewards;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import io.temporal.common.RetryOptions;

import java.time.Duration;


/**
 *
 * @author jamesurquhart
 */
public class RewardsWorkflowImpl implements RewardsWorkflow {
    
    private RewardsAccount rewardsAccount;
    
    private final RetryOptions retryoptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(30))
            .setMaximumInterval(Duration.ofSeconds(100))
            .setBackoffCoefficient(2)
            .setMaximumAttempts(500)
            .build();

    private final ActivityOptions defaultActivityOptions = ActivityOptions.newBuilder()
            // Timeout options specify when to automatically timeout Activities if the process is taking too long.
            .setStartToCloseTimeout(Duration.ofMinutes(5))
            // Optionally provide customized RetryOptions.
            // Temporal retries failures by default, this is simply an example.
            .setRetryOptions(retryoptions)
            .build();
    
    private final UpdateRewardsActivity updateActivity = Workflow.newActivityStub(UpdateRewardsActivity.class, defaultActivityOptions);
    private final CancelRewardsProgramActivity cancelRewardsProgramActivity = Workflow.newActivityStub(CancelRewardsProgramActivity.class, defaultActivityOptions);
    
    @Override
    public void createRewardsProgram(RewardsAccount account) {
        
        if (account == null) {
            System.out.print("No account passed to createReward");
            return;
        }
        
        this.rewardsAccount = account;
        
        System.out.print("Entering createRewardsProgram loop");
        while (true) {
            
            //TODO Need to handle ContinueAsNew
            
            Workflow.await(() -> this.rewardsAccount.isCancelled);
            
            if (this.rewardsAccount.isCancelled) {
                System.out.print("\nAccount cancelled\n");
                return;
            }
            
            /*
            try {
                Thread.sleep(1000);
            } catch (Exception e) {      
               System.out.printf("Sleep in createRewardsProgram interrupted\n\n%s", e);
            }
            */
        }        
    }
    
    @Override
    public void addPoints(long earnedPoints) {
        System.out.printf("\naddPoints signal received in workflow for %s points\n", earnedPoints);
        this.rewardsAccount = updateActivity.addPoints(rewardsAccount, earnedPoints);
        System.out.printf("\naddPoints signal completed. New points are %s\n", this.rewardsAccount.getPoints());
 
    }
    
    @Override
    public void cancelRewardsProgram() {
        this.rewardsAccount = cancelRewardsProgramActivity.cancelRewardsProgram(rewardsAccount);
    }
    
    @Override
    public RewardsAccount getRewardsProgramData() {
        System.out.print("\ngetRewardsProgramData called in RewardsWorkflowImpl\n");
        return this.rewardsAccount;
    }
    
}
