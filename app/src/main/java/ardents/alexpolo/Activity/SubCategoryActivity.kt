package ardents.alexpolo.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.alexpolo.Adapter.SubCatAdapter
import ardents.alexpolo.Adapter.ProductAdapter
import ardents.alexpolo.Model.ChildesItem
import ardents.alexpolo.Model.ProductModelItem
import ardents.alexpolo.Model.SubCategoryModel
import ardents.alexpolo.ViewModel.ProductViewModel
import ardents.alexpolo.databinding.ActivitySubCategoryBinding
import ardents.alexpolo.utils.Constant
import ardents.alexpolo.utils.NetworkResult

class SubCategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivitySubCategoryBinding
    val subCatList:ArrayList<ChildesItem> = Constant.subCategoryData
    lateinit var viewModel: ProductViewModel
    lateinit var  productAdapter:ProductAdapter
    var productList:List<ProductModelItem> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySubCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel=ViewModelProvider(this).get(ProductViewModel::class.java)
        val subcategoryName=intent.getStringExtra("categoryName")
        val subcategoryId=intent.getStringExtra("categoryId")
        Log.d("hellomydata",subcategoryId.toString())



//        val subCategoryList=ArrayList<SubCategoryModel>()
//        subCategoryList.add(SubCategoryModel("https://www.google.com/imgres?imgurl=https%3A%2F%2Fassets.ajio.com%2Fmedias%2Fsys_master%2Froot%2F20230720%2Fo4uH%2F64b94a8feebac147fc7eab72%2F-473Wx593H-443016054-blue-MODEL.jpg&tbnid=ns2Mn_a6IxvG8M&vet=12ahUKEwjPgL2VkYOFAxWzTGwGHQrkBpMQMyhMegUIARC0Ag..i&imgrefurl=https%3A%2F%2Fwww.ajio.com%2Fdnmx-mid-wash-ripped-slim-fit-jeans%2Fp%2F443016054_blue&docid=79J1dlGku5txmM&w=473&h=593&itg=1&q=jeans%20image&hl=en-GB&ved=2ahUKEwjPgL2VkYOFAxWzTGwGHQrkBpMQMyhMegUIARC0Ag","5.0","Tshirt","500","300","15%"))
//        subCategoryList.add(SubCategoryModel("https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.realmenrealstyle.com%2Fwp-content%2Fuploads%2F2021%2F07%2Fmens-jeans.jpg&tbnid=2rKangj6qCjh_M&vet=12ahUKEwjPgL2VkYOFAxWzTGwGHQrkBpMQMygJegUIARCHAQ..i&imgrefurl=https%3A%2F%2Fwww.realmenrealstyle.com%2Fjeans-for-body-type%2F&docid=N2fGfnXVZcTgCM&w=2048&h=1400&q=jeans%20image&hl=en-GB&ved=2ahUKEwjPgL2VkYOFAxWzTGwGHQrkBpMQMygJegUIARCHAQ","5.0","Tshirt","500","300","15%"))
//
//        val adpter= ProductAdapter(applicationContext,subCategoryList)
//        binding.productRecycler.adapter=adpter
        productAdapter=ProductAdapter(applicationContext,productList)
        binding.productRecycler.adapter=productAdapter

        binding.subCategoryHeader.txtHeader.text=subcategoryName
        viewModel.productData.observe(this, Observer {
            when(it){
                is NetworkResult.Success->{
                    productList= it.data!!
                    productAdapter.updateProductList(productList)
                }
                is NetworkResult.Error->{
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading->{
                    Toast.makeText(this,"Please Wait",Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Toast.makeText(this,"Please Wait",Toast.LENGTH_SHORT).show()
                }
            }
        })
        subCatList.add(0,ChildesItem(name = "All", id = subcategoryId?.toInt()))
        val adapter=SubCatAdapter(applicationContext,subCatList){
           // viewModel.getProduct(subcategoryId.toString())
            viewModel.getProduct(it.id.toString())
        }
        binding.subcatRecycler.adapter=adapter



        binding.subCategoryHeader.btnBack.setOnClickListener {
            finish()
        }



    }
}