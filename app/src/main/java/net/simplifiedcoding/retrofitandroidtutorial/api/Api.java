package net.simplifiedcoding.retrofitandroidtutorial.api;

import net.simplifiedcoding.retrofitandroidtutorial.models.DefaultResponse;
import net.simplifiedcoding.retrofitandroidtutorial.models.LoginResponse;
import net.simplifiedcoding.retrofitandroidtutorial.models.PronosResponse;
import net.simplifiedcoding.retrofitandroidtutorial.models.SelectPronos;
import net.simplifiedcoding.retrofitandroidtutorial.models.SelectPronosResponse;
import net.simplifiedcoding.retrofitandroidtutorial.models.UsersResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    // PARTIE USER

    @FormUrlEncoded
    @POST("createuser")
    Call<DefaultResponse> createUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name,
            @Field("admin") int admin
    );

    @FormUrlEncoded
    @POST("userlogin")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("allusers")
    Call<UsersResponse> getUsers();

    @FormUrlEncoded
    @PUT("updateuser/{id}")
    Call<LoginResponse> updateUser(
            @Path("id") int id,
            @Field("email") String email,
            @Field("name") String name
    );

    @FormUrlEncoded
    @PUT("updatepassword")
    Call<DefaultResponse> updatePassword(
            @Field("currentpassword") String currentpassword,
            @Field("newpassword") String newpassword,
            @Field("email") String email
    );

    @DELETE("deleteuser/{id}")
    Call<DefaultResponse> deleteUser(@Path("id") int id);
    // ---------------------------------------------------------------- //

    // PARTIE PRONOS

    @FormUrlEncoded
    @POST("createpronos")
    Call<PronosResponse> createPronos(
            @Field("equipe1") String equipe1,
            @Field("equipe2") String equipe2,
            @Field("cote1") Float cote1,
            @Field("cote2") Float cote2,
            @Field("matchNull") String matchNull,
            @Field("resultat") String resultat
    );

    @GET("allpronos")
    Call<PronosResponse> getPronos();

    // -------------------------------------------------------------- //

    @FormUrlEncoded
    @POST("pronoselectbyuser")
    Call<SelectPronosResponse> getSelectPronos(
            @Field("idUser") int idUser
    );

    @FormUrlEncoded
    @POST("createselectpronos")
    Call<SelectPronosResponse> createSelectPronos(
            @Field("idPronos") int idPronos,
            @Field("idUser") int idUser
    );
}