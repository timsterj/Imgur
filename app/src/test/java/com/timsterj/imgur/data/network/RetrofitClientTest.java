package com.timsterj.imgur.data.network;

import com.google.android.material.textfield.TextInputLayout;
import com.timsterj.imgur.contracts.Contracts;

import org.junit.Before;
import org.junit.Test;

import retrofit2.Retrofit;

import static com.google.common.truth.Truth.assert_;
import static org.junit.Assert.*;

public class RetrofitClientTest {

    @Test(expected = NullPointerException.class)
    public void getClient_NullBaseUrl_ThrowException(){
        String baseUrl = null;

        RetrofitClient.getClient(baseUrl);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getClient_EmptyBaseUrl_ThrowException(){
        String baseUrl = "";

        RetrofitClient.getClient(baseUrl);
    }

    @Test
    public void getClient_NormalBaseUrl_Instance(){
        String baseUrl = "https://example.com/";

        Retrofit retrofit = RetrofitClient.getClient(baseUrl);

        assert_().that(retrofit).isNotNull();
    }


}