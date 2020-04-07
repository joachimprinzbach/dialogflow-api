package com.joachimprinzbach.incubator.dialogflowapi.domain;

import lombok.Data;

@Data
public class PullRequest {

    private final int id;
    private final String organisation;
    private final String repository;

    public String getFullName() {
        return organisation + "/" + repository;
    }

}
