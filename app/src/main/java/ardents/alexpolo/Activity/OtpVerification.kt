package ardents.alexpolo.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ardents.alexpolo.R
import ardents.alexpolo.databinding.ActivityOtpVerificationBinding

class OtpVerification : AppCompatActivity() {
    lateinit var binding: ActivityOtpVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityOtpVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val phone_number=intent.getStringExtra("number")
        binding.phoneNmber.text=phone_number.toString()


        binding.btnVerify.setOnClickListener {
            val intent= Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
    }
}