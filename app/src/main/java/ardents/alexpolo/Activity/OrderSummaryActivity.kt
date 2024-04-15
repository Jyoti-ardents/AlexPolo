package ardents.alexpolo.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ardents.alexpolo.R
import ardents.alexpolo.databinding.ActivityOrderSummaryBinding

class OrderSummaryActivity : AppCompatActivity() {
    lateinit var binding: ActivityOrderSummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityOrderSummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.oderSummaryHeader.txtHeader.text=getString(R.string.order_summary)


        binding.btnProceedToPay.setOnClickListener {
            startActivity(Intent(applicationContext,PaymentActivity::class.java))
        }

        binding.oderSummaryHeader.btnBack.setOnClickListener {
            finish()
        }
    }
}