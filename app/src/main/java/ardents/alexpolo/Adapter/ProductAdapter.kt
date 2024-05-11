package ardents.alexpolo.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ardents.alexpolo.Activity.ProductDetailActivity
import ardents.alexpolo.Model.ProductModelItem
import ardents.alexpolo.Model.SubCategoryModel
import ardents.alexpolo.databinding.SubcategoryLayoutBinding

class ProductAdapter(val context: Context, var subCategoryList: List<ProductModelItem>): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    class ViewHolder(val binding:SubcategoryLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    }
    fun updateProductList(list:List<ProductModelItem>)
    {
        subCategoryList=list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater=LayoutInflater.from(context)
        val binding=SubcategoryLayoutBinding.inflate(layoutInflater,parent,false)
        val viewHolder=ViewHolder(binding)
        return viewHolder
    }

    override fun getItemCount(): Int {
       return subCategoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.productRating.text="5.0"
        holder.binding.productName.text=subCategoryList.get(position).name
        //Glide.with(context).load(subCategoryList.get(position).img).into(holder.binding.subcatImg)
        holder.binding.productPrice.text= subCategoryList.get(position).purchasePrice.toString()
        holder.binding.productOldprice.text=subCategoryList.get(position).unitPrice.toString()
        holder.binding.productOffer.text=subCategoryList.get(position).discount.toString()+"% off"
        holder.binding.subCategoryLayout.setOnClickListener {
            val intent=Intent(context,ProductDetailActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)

        }
    }
}