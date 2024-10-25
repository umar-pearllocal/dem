import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R

class TextAdapter(private val items: MutableList<String>) : RecyclerView.Adapter<TextAdapter.TextViewHolder>() {
    private val selectedItems = mutableSetOf<Int>()
    inner class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.item_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout, parent, false)
        return TextViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.textView.text = items[position]

        holder.itemView.setBackgroundColor(
            if (selectedItems.contains(position))
                holder.itemView.context.getColor(R.color.selected_color) // Replace with your selected color
            else
                holder.itemView.context.getColor(R.color.default_color) // Replace with your default color
        )

        holder.itemView.setOnClickListener {
            if (selectedItems.contains(position)) {
                selectedItems.remove(position) // Remove from selected
            } else {
                selectedItems.add(position) // Add to selected
            }
            notifyItemChanged(position) // Notify that the item has changed
        }

    }

    override fun getItemCount(): Int = items.size

    fun removeSelectedItems(): List<String> {
        val removedItems = selectedItems.sortedDescending().map { items[it] }
        selectedItems.clear()
        for (index in removedItems.indices) {
            items.remove(removedItems[index])
            notifyItemRemoved(index)
        }
        return removedItems
    }

    fun addItems(newItems: List<String>) {
        val startPosition = items.size
        items.addAll(newItems)
        notifyItemRangeInserted(startPosition, newItems.size)
    }

    fun getSelectedItems(): List<String> {
        return selectedItems.map { items[it] }
    }
}
