package ardents.alexpolo.Network

import ardents.alexpolo.Model.AddToCartModel
import ardents.alexpolo.Model.BannerModelItem
import ardents.alexpolo.Model.BestSellingModel
import ardents.alexpolo.Model.CategoryModelItem
import ardents.alexpolo.Model.DeleteAccountModel
import ardents.alexpolo.Model.ForgotPassword
import ardents.alexpolo.Model.LoginModel
import ardents.alexpolo.Model.ProductDetailsModel
import ardents.alexpolo.Model.ProductModelItem
import ardents.alexpolo.Model.ProductsItem
import ardents.alexpolo.Model.ProfileUpdateModel
import ardents.alexpolo.Model.ProfileUpdateRequest
import ardents.alexpolo.Model.RegisterModel
import ardents.alexpolo.Model.UserInfoModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

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

    @GET("categories")
    suspend fun getCategory(): Response<List<CategoryModelItem>>

    @GET("banners")
    suspend fun bannerData(@Query("banner_type") banner_type:String):Response<List<BannerModelItem>>

    @GET("categories/products/{id}")
    suspend fun products(@Path("id") id:String):Response<List<ProductModelItem>>

    @GET("customer/account-delete/{id}")
    suspend fun deleteAccount(
        @Header("Authorization") token:String,
        @Path("id") id:String):Response<DeleteAccountModel>

    @GET("products/details/{id}")
    suspend fun ProductDetails(
        @Path("id") slugid:String):Response<ProductDetailsModel>

    @FormUrlEncoded
    @POST("cart/add")
    suspend fun addToCart(
        @Header("Authorization") token:String,
        @Field("id") productId:String,
        @Field("quantity") quantity:String
    ):Response<AddToCartModel>

    @GET("products/latest")
    suspend fun latestProduct():Response<BestSellingModel>

    @GET("products/top-rated")
    suspend fun topRatedProducts():Response<BestSellingModel>

    @GET("products/best-sellings")
    suspend fun bestSellingProduct():Response<BestSellingModel>

    @GET("products/discounted-product")
    suspend fun discountProduct():Response<BestSellingModel>

    @GET("products/search")
   suspend fun searchProduct(@Query("name") name:String):Response<BestSellingModel>


}