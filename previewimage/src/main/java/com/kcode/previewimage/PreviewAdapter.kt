package com.kcode.previewimage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.chrisbanes.photoview.PhotoView

class PreviewAdapter(
    private val urls: ArrayList<String>,
    private val context: Context,
    private val listener: PreviewImageClickListener
) :
    RecyclerView.Adapter<PreviewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<PhotoView>(R.id.photoView)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_preview_image, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return urls.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setOnClickListener {
            listener.onClick(position, it)
        }
        ImageLoaderManager.imageLoader?.onImageLoader(url = urls[position], image = holder.image)
    }
}