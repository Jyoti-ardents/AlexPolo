package ardents.alexpolo.Activity

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ardents.alexpolo.Adapter.ProductSizeAdapter
import ardents.alexpolo.Model.ProductSize
import ardents.alexpolo.R
import ardents.alexpolo.ViewModel.ProductViewModel
import ardents.alexpolo.databinding.ActivityProductDetailBinding
import ardents.alexpolo.utils.NetworkResult
import ardents.alexpolo.utils.SharedPrefManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductDetailBinding
    lateinit var viewModel: ProductViewModel
    lateinit var productId:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel=ViewModelProvider(this).get(ProductViewModel::class.java)

        binding.productDetailHeader.txtHeader.text=getString(R.string.product)
        val productSlugId=intent.getStringExtra("productSlugId")
        val productId=intent.getStringExtra("productId").toString()
        Log.d("hemedata",productId)
        Log.d("hemedata",productSlugId.toString())



        val productSliderList=ArrayList<SlideModel>()
       // productSliderList.add(SlideModel(R.drawable.s2))
        //productSliderList.add(SlideModel(R.drawable.s3))
        //binding.productSlider.setImageList(productSliderList)

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

        viewModel.getProductDetail(productSlugId!!)

        viewModel.productDetails.observe(this, Observer {
            when(it){
                is NetworkResult.Success->{
                    binding.productName.text=it.data?.name
                    binding.cutprice.text="₹"+it.data?.unit_price?.toInt().toString()
                    binding.cutprice.paintFlags=binding.cutprice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    binding.finalprice.text="₹"+it.data?.purchase_price?.toInt().toString()
                    binding.productShortDesc.text=it.data?.details
                    //productId=it.data?.id.toString()

                    val imagelist=ArrayList<String>()
                   //productSliderList.add(SlideModel(it.data?.images,ScaleTypes.FIT))
                    imagelist.add(it.data?.images.toString())
                    Log.d("productdetails",imagelist.toString())

                }
                is NetworkResult.Error->{
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                    Log.d("productdetails",it.message.toString())
                }
                is NetworkResult.Loading->
                    Toast.makeText(this,"something went wrong",Toast.LENGTH_SHORT).show()
            }
//            if (it.data!= null){
//                binding.productName.text=it.data.name
//                binding.cutprice.text=it.data.unit_price.toString()
//                binding.finalprice.text=it.data.purchase_price.toString()
//                binding.productShortDesc.text=it.data.details
//
//            }else{
//                Toast.makeText(this,"jyoti",Toast.LENGTH_SHORT).show()
//            }
        })


        Log.d("productdetails",productId.toString())
        binding.btnBuyNow.setOnClickListener {
            startActivity(Intent(applicationContext,SavedAddressActivity::class.java))
            finish()
        }
        viewModel.addToCart.observe(this, Observer {
            when(it) {
                is NetworkResult.Success ->
                    Toast.makeText(this, it.data.toString(), Toast.LENGTH_SHORT).show()

                is NetworkResult.Error ->
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

                is NetworkResult.Loading ->
                    Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
       // Toast.makeText(this,productId.toString(),Toast.LENGTH_SHORT).show()

        binding.btnAddToCart.setOnClickListener {
            val token=SharedPrefManager.getInstance(this).getToken()?.token.toString()
            if (token!=null){
                Log.d("productdetails",token)
                Toast.makeText(this, productId, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
                viewModel.addToCart(token,productId,"2")
            }else{
                Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
            }

        }


    }
}