package edu.wgu.grimes.abm1.model;

public class STATUS {
    public enum COURSE {
        IN_PROGRESS,
        COMPLETED,
        DROPPED,
        PLAN_TO_TAKE
    }
    public enum ASSESSMENT {
        NOT_ATTEMPTED,
        FAILED,
        PASS
    }
}
