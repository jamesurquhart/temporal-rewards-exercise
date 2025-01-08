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
    
    /*
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
    */
    
    @Override
    public void createRewardsProgram(RewardsAccount account) {
        
        if (account == null) {
            System.out.print("No account passed to createReward");
            return;
        }
        
        this.rewardsAccount = account;
        
        System.out.print("Entering createRewardsProgram loop");
        while (true) {
            
            /* TODO Handle signals here by recording signal received on 
            RewardsAccount and checking for activities in this loop one at a 
            time. Avoids race conditions, per Mike at Temporal. */
            
            //TODO Need to handle ContinueAsNew
            
            //Workflow.await(() -> this.rewardsAccount.isCancelled);
            
            if (this.rewardsAccount.activityList.size() > 0) {
                ActivityPair pair = this.rewardsAccount.activityList.removeFirst();
                this.processActivity(pair);
            }
            
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
        this.rewardsAccount.addActivity("addPoints", String.valueOf(earnedPoints));
        System.out.printf("\naddPoints signal completed. New points are %s\n", this.rewardsAccount.getPoints());
 
    }
    
    @Override
    public void cancelRewardsProgram() {
        this.rewardsAccount.addActivity("cancelRewardsProgram", null);
    }
    
    @Override
    public RewardsAccount getRewardsProgramData() {
        System.out.print("\ngetRewardsProgramData called in RewardsWorkflowImpl\n");
        return this.rewardsAccount;
    }
    
    public void processActivity(ActivityPair activity) {
        switch (activity.getName()) {
            case "addPoints": {
                this.rewardsAccount.addPoints(Long.parseLong(activity.getArguments()));
            }
            case "cancelRewardsProgram": {
                this.rewardsAccount.isCancelled = true;
            }
        }
    }
    
}
