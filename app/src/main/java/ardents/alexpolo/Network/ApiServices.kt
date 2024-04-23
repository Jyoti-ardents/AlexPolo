package ardents.alexpolo.Network

import ardents.alexpolo.Model.ForgotPassword
import ardents.alexpolo.Model.LoginModel
import ardents.alexpolo.Model.ProfileUpdateModel
import ardents.alexpolo.Model.ProfileUpdateRequest
import ardents.alexpolo.Model.RegisterModel
import ardents.alexpolo.Model.UserInfoModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiServices {
    @FormUrlEncoded
    @POST("auth/register")
    suspend fun register(
        @Field("f_name") f_name:String,
        @Field("l_name") l_name:String,
        @Field("email") email:String,
        @Field("phone") phone:String,
        @Field("password") password:String
    ):Response<RegisterModel>


    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email:String,
        @Field("password") password:String
    ):Response<LoginModel>

    @FormUrlEncoded
    @POST("auth/forgot-password")
    suspend fun forgotPassword(
        @Field("identity") mail:String
    ):Response<ForgotPassword>

    @GET("customer/info")
    suspend fun userInfo(
        @Header("Authorization") token:String
    ):Response<UserInfoModel>

    @PUT("customer/update-profile")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Body body:ProfileUpdateRequest
    ):Response<ProfileUpdateModel>


}