package com.joachimprinzbach.incubator.dialogflowapi.domain;

import com.joachimprinzbach.incubator.dialogflowapi.domain.PullRequest;
import lombok.Value;

@Value
public class PullRequestComment {

    private final String commentText;
    private final PullRequest pullRequest;
}
