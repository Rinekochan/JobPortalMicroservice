package com.hoang.jobapplication.constant;

public final class Constants {

    private Constants() {
        // restrict instantiation
    }

    public static final String JOB_APPLICATION_TAG = "Job Application";

    public static final String DELETE_SUCCESSFUL_TAG = "Delete request processed successfully";

    public static final String DELETE_FAILED_TAG = "Delete request failed";

    public static final String JOB_APPLICATION_CREATION = "" +
            "<p>You have successfully sent an application to a recent job</p>" +
            "<p>Here's your job link: </p>";

    public static final String JOB_APPLICATION_UPDATE = "" +
            "<p>We would like to acknowledge that your recent job application status has changed</p>" +
            "<p>Here's your job link: </p>";

}
