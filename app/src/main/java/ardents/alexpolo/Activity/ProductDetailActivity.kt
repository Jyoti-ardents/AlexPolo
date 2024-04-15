package ardents.alexpolo.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ardents.alexpolo.Adapter.ProductSizeAdapter
import ardents.alexpolo.Model.ProductSize
import ardents.alexpolo.R
import ardents.alexpolo.databinding.ActivityProductDetailBinding
import com.denzcoskun.imageslider.models.SlideModel

class ProductDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.productDetailHeader.txtHeader.text=getString(R.string.product)

        val productSliderList=ArrayList<SlideModel>()
        productSliderList.add(SlideModel(R.drawable.s2))
        productSliderList.add(SlideModel(R.drawable.s3))
        binding.productSlider.setImageList(productSliderList)

        binding.productDetailHeader.btnBack.setOnClickListener {
            finish()
        }

        val productSizeList=ArrayList<ProductSize>()
        productSizeList.add(ProductSize("S"))
        productSizeList.add(ProductSize("M"))
        productSizeList.add(ProductSize("L"))
        productSizeList.add(ProductSize("XL"))
        productSizeList.add(ProductSize("XXL"))
        val adapter= ProductSizeAdapter(applicationContext,productSizeList)
        binding.productSizeRecycler.adapter=adapter


        binding.btnBuyNow.setOnClickListener {
            startActivity(Intent(applicationContext,SavedAddressActivity::class.java))
            finish()
        }


    }
}