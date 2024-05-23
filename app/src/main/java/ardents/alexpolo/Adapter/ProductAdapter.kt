package ardents.alexpolo.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ardents.alexpolo.Activity.ProductDetailActivity
import ardents.alexpolo.Model.ProductModelItem
import ardents.alexpolo.Model.SubCategoryModel
import ardents.alexpolo.databinding.ProductLayoutBinding


class ProductAdapter(val context: Context, var subCategoryList: List<ProductModelItem>): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    class ViewHolder(val binding:ProductLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    }
    fun updateProductList(list:List<ProductModelItem>)
    {
        subCategoryList=list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater=LayoutInflater.from(context)
        val binding=ProductLayoutBinding.inflate(layoutInflater,parent,false)
        val viewHolder=ViewHolder(binding)
        return viewHolder
    }

    override fun getItemCount(): Int {
       return subCategoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.productRating.text="6.0"
        holder.binding.productName.text=subCategoryList.get(position).name
        //Glide.with(context).load(subCategoryList.get(position).img).into(holder.binding.subcatImg)
        holder.binding.productPrice.text= "₹"+subCategoryList.get(position).purchase_price?.toInt().toString()
        holder.binding.productOldprice.text="₹"+subCategoryList.get(position).unit_price?.toInt().toString()
        holder.binding.productOldprice.paintFlags=holder.binding.productOldprice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        holder.binding.productOffer.text=subCategoryList.get(position).discount.toString()+"%"
        holder.binding.subCategoryLayout.setOnClickListener {
            val intent=Intent(context,ProductDetailActivity::class.java)
            intent.putExtra("productSlugId",subCategoryList.get(position).slug)
            intent.putExtra("productId",subCategoryList.get(position).id.toString())
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)

        }
    }
}