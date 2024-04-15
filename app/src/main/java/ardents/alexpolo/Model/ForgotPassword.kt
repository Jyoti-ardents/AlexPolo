package ardents.alexpolo.Model

import com.google.gson.annotations.SerializedName

data class ForgotPassword(
    @SerializedName("message")
    val message: String
)