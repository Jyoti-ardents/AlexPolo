package ardents.alexpolo.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ardents.alexpolo.R
import ardents.alexpolo.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.paymentHeader.txtHeader.text=getString(R.string.payment_method)


    }
}