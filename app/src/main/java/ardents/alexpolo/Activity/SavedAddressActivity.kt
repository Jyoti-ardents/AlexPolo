package ardents.alexpolo.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ardents.alexpolo.R
import ardents.alexpolo.databinding.ActivitySavedAddressBinding

class SavedAddressActivity : AppCompatActivity() {
    lateinit var binding: ActivitySavedAddressBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySavedAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.savedAddressHeader.txtHeader.text=getString(R.string.new_address)

        binding.savedAddressHeader.btnBack.setOnClickListener {
            finish()
        }

        binding.btnCountinue.setOnClickListener {
            startActivity(Intent(applicationContext,OrderSummaryActivity::class.java))
        }
        binding.frameAddAddress.setOnClickListener {
            startActivity(Intent(this,AddressActivity::class.java))
        }
    }
}