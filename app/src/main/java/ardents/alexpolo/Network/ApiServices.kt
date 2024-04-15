package ardents.alexpolo.Network

import ardents.alexpolo.Model.ForgotPassword
import ardents.alexpolo.Model.LoginModel
import ardents.alexpolo.Model.RegisterModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServices {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("f_name") f_name:String,
        @Field("l_name") l_name:String,
        @Field("email") email:String,
        @Field("phone") phone:String,
        @Field("password") password:String
    ):Response<RegisterModel>


    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email:String,
        @Field("password") password:String
    ):Response<LoginModel>

    @FormUrlEncoded
    @POST("forgot-password")
    suspend fun forgotPassword(
        @Field("identity") mail:String
    ):Response<ForgotPassword>
}