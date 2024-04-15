package ardents.alexpolo.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ardents.alexpolo.Activity.SubCategoryActivity
import ardents.alexpolo.Model.CategoryModel
import ardents.alexpolo.databinding.CategoryLayoutBinding
import com.bumptech.glide.Glide

class CategoryAdapter(val context: Context,val categoryList:List<CategoryModel>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(val binding: CategoryLayoutBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val layoutInflater=LayoutInflater.from(context)
        val binding=CategoryLayoutBinding.inflate(layoutInflater,parent,false)
        val viewHolder= ViewHolder(binding)
        return viewHolder
    }

    override fun getItemCount(): Int {
       return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       Glide.with(context).load(categoryList.get(position).category_img).into(holder.binding.categoryImg)
        holder.binding.categoryName.text=categoryList.get(position).category_name
        holder.binding.cardCategory.setOnClickListener {
            val intent=Intent(context, SubCategoryActivity::class.java)
            intent.putExtra("categoryName",categoryList.get(position).category_name)
            context.startActivity(intent)

        }
    }
}