package com.timsterj.imgur.utils;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assert_;
import static org.junit.Assert.*;

public class FormatUtilsTest {

    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void getDateFormatFromEpochTime_NormalEpochTime(){
        int epochTime = 1586286898;
        String expected = "07/04/2020 22:14";
        String current = FormatUtils.getDateFormatFromEpochTime(epochTime);

        assert_().that(current).matches(expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getDateFormatFromEpochTime_InvalidEpochTime_ThrowException(){
        int epochTime = 0;

        FormatUtils.getDateFormatFromEpochTime(epochTime);

    }

}