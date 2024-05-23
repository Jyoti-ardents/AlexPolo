package ardents.alexpolo.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ardents.alexpolo.Activity.ProductDetailActivity
import ardents.alexpolo.Model.ProductsItem
import ardents.alexpolo.databinding.ProductLayoutBinding


class HomeAdapter(val context: Context, var homelist:List<ProductsItem>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    class ViewHolder(val binding: ProductLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    }
    fun updateHomeList(list: List<ProductsItem?>?){
        homelist= list as List<ProductsItem>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater=LayoutInflater.from(context)
        val binding=ProductLayoutBinding.inflate(layoutInflater,parent,false)
        val viewHolder=ViewHolder(binding)
        return viewHolder
    }

    override fun getItemCount(): Int {
       return homelist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.productRating.text="6.0"
        holder.binding.productName.text=homelist.get(position).name
        holder.binding.productPrice.text= "₹"+homelist.get(position).purchase_price?.toInt().toString()
        holder.binding.productOldprice.text="₹"+homelist.get(position).unit_price?.toInt().toString()
        holder.binding.productOldprice.paintFlags=holder.binding.productOldprice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        holder.binding.productOffer.text=homelist.get(position).discount.toString()+"%"
        holder.binding.subCategoryLayout.setOnClickListener {
            val intent= Intent(context,ProductDetailActivity::class.java)
            intent.putExtra("productSlugId",homelist.get(position).slug)
            Toast.makeText(context,homelist.get(position).slug,Toast.LENGTH_SHORT).show()
            intent.putExtra("productId",homelist.get(position).id.toString())
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}