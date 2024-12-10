/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.jamesurquhart.rewards;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowStub;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import java.time.Duration;

/**
 *
 * @author jamesurquhart
 */
public class SimpleRewardsProgramActions {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        RewardsAccount rewardsAccount;
        
        //Get the workflowID from args
        String workflowID = (String)args[0];
        System.out.printf("\nWorflow ID: %s\n", workflowID);
        
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowClient client = WorkflowClient.newInstance(service);

        RewardsWorkflow workflow = client.newWorkflowStub(RewardsWorkflow.class, workflowID);
        if (workflow == null)
            System.out.print("No workflow stub recovered");
        else
            System.out.printf("workflow toString: %s", workflow.toString());
        
        /*
        rewardsAccount = workflow.getRewardsProgramData();
        System.out.printf("\nID for this account is %s", String.valueOf(rewardsAccount.ID));
        */
        
        System.out.print("\naddPoints signal attempted\n");
        workflow.addPoints(501);
        System.out.print("\ngetRewardsProgramData attempted\n");
        
        /*
        rewardsAccount = workflow.getRewardsProgramData();
        System.out.printf("\n501 points added. New balance is %s. New status is %s", rewardsAccount.points, rewardsAccount.status.toString());
        */

        workflow.addPoints(501);
        rewardsAccount = workflow.getRewardsProgramData();
        System.out.printf("\nAnother 501 points added. New balance is %s. New status is %s", rewardsAccount.points, rewardsAccount.status.toString());
        
        workflow.cancelRewardsProgram();
        System.out.print("Rewards program cancelled. Check to see if workflow completed.");
    }
    
}
