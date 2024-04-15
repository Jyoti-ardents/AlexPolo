package ardents.alexpolo.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ardents.alexpolo.Activity.ProductDetailActivity
import ardents.alexpolo.Model.SubCategoryModel
import ardents.alexpolo.databinding.SubcategoryLayoutBinding
import com.bumptech.glide.Glide

class SubCategoryAdapter(val context: Context, val subCategoryList: List<SubCategoryModel>): RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>() {
    class ViewHolder(val binding:SubcategoryLayoutBinding): RecyclerView.ViewHolder(binding.root) {

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
        holder.binding.subcatRating.text= subCategoryList.get(position).rating
       // holder.binding.subcatName.text=subCategoryList.get(position).product_name
        //Glide.with(context).load(subCategoryList.get(position).img).into(holder.binding.subcatImg)
        holder.binding.subcatPrice.text=subCategoryList.get(position).price
        holder.binding.subcatOldprice.text=subCategoryList.get(position).oldPrice
        holder.binding.subcatOffer.text=subCategoryList.get(position).discount+"off"
        holder.binding.subCategoryLayout.setOnClickListener {
            val intent=Intent(context,ProductDetailActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)

        }
    }
}