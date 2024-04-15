package ardents.alexpolo.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ardents.alexpolo.Model.ProductSize
import ardents.alexpolo.databinding.ProductsizeLayBinding

class ProductSizeAdapter(val context: Context,val sizelist:List<ProductSize>) : RecyclerView.Adapter<ProductSizeAdapter.ViewHolder>() {
    class ViewHolder(val binding: ProductsizeLayBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater=LayoutInflater.from(context)
        val binding=ProductsizeLayBinding.inflate(layoutInflater,parent,false)
        val viewHolder=ViewHolder(binding)
        return viewHolder

    }

    override fun getItemCount(): Int {
        return sizelist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding.productSize.text=sizelist.get(position).size
    }
}