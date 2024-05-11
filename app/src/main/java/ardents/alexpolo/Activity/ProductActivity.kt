package ardents.alexpolo.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ardents.alexpolo.Adapter.ProductAdapter
import ardents.alexpolo.Model.SubCategoryModel
import ardents.alexpolo.databinding.ActivityProductBinding

class ProductActivity : AppCompatActivity() {
    lateinit var binding:ActivityProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val subCategoryList=ArrayList<SubCategoryModel>()
        subCategoryList.add(SubCategoryModel("https://www.google.com/imgres?imgurl=https%3A%2F%2Fassets.ajio.com%2Fmedias%2Fsys_master%2Froot%2F20230720%2Fo4uH%2F64b94a8feebac147fc7eab72%2F-473Wx593H-443016054-blue-MODEL.jpg&tbnid=ns2Mn_a6IxvG8M&vet=12ahUKEwjPgL2VkYOFAxWzTGwGHQrkBpMQMyhMegUIARC0Ag..i&imgrefurl=https%3A%2F%2Fwww.ajio.com%2Fdnmx-mid-wash-ripped-slim-fit-jeans%2Fp%2F443016054_blue&docid=79J1dlGku5txmM&w=473&h=593&itg=1&q=jeans%20image&hl=en-GB&ved=2ahUKEwjPgL2VkYOFAxWzTGwGHQrkBpMQMyhMegUIARC0Ag","5.0","Tshirt","500","300","15%"))
        subCategoryList.add(SubCategoryModel("https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.realmenrealstyle.com%2Fwp-content%2Fuploads%2F2021%2F07%2Fmens-jeans.jpg&tbnid=2rKangj6qCjh_M&vet=12ahUKEwjPgL2VkYOFAxWzTGwGHQrkBpMQMygJegUIARCHAQ..i&imgrefurl=https%3A%2F%2Fwww.realmenrealstyle.com%2Fjeans-for-body-type%2F&docid=N2fGfnXVZcTgCM&w=2048&h=1400&q=jeans%20image&hl=en-GB&ved=2ahUKEwjPgL2VkYOFAxWzTGwGHQrkBpMQMygJegUIARCHAQ","5.0","Tshirt","500","300","15%"))
      //  val adpter= ProductAdapter(applicationContext,subCategoryList)
      //  binding.productRecycler.adapter=adpter
    }
}