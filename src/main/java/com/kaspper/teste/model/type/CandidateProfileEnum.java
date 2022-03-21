package com.kaspper.teste.model.type;

import java.util.Arrays;

public enum CandidateProfileEnum {

    JUNIOR,
    PLENO,
    SENIOR;

    public static CandidateProfileEnum findByValue(String value){

        return Arrays.stream(CandidateProfileEnum.values())
                .filter(e -> value.equalsIgnoreCase(e.toString()))
                .findFirst().orElse(null);
    }
}
