package com.himal77.apigateway.domain.aws;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
public class StepFunctionBody {
    public String input;
    public String stateMachineArn;
}
