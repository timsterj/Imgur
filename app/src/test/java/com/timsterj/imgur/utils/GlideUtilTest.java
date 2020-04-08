package com.timsterj.imgur.utils;

import com.bumptech.glide.Glide;

import org.junit.Test;
import org.mockito.internal.matchers.Null;

import static com.google.common.truth.Truth.assert_;
import static org.junit.Assert.*;

public class GlideUtilTest {

    @Test
    public void getThumbnailLink_NormalLink_Format(){
        String link = "https://example.com/abcdifj.abc";
        String expected = "https://example.com/abcdifjs.abc";

        String current = GlideUtil.getThumbnailLink(link);

        assert_().that(current).matches(expected);

    }

    @Test(expected = NullPointerException.class)
    public void getThumbnailLink_NullLink_ThrowException() {
        String link = null;

        GlideUtil.getThumbnailLink(link);
    }
}