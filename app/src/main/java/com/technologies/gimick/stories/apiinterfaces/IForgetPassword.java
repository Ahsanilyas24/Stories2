package com.technologies.gimick.stories.apiinterfaces;

/*
This is interface for forget password. Interface is only used to declare function we will call this interface in another
class for data sharing purposes.
*/

import com.technologies.gimick.stories.models.FlatDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface IForgetPassword {
    @GET("service.php?operation=forgetPassword")
    Call<FlatDTO> forgetPassword(@Query("email") String email);

    @GET("service.php?operation=resetPassword")
    Call<FlatDTO> resetPassword(@Query("email") String email, @Query("oldPass") String oldPass, @Query("newPass") String newPass);
}
