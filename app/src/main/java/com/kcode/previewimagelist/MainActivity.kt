package com.kcode.previewimagelist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kcode.previewimage.ImageLoader
import com.kcode.previewimage.PreviewImage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val images = arrayOf(
            "http://img4.imgtn.bdimg.com/it/u=3702648800,3967102116&fm=26&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=2588111137,2818876915&fm=26&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=379744524,1649842057&fm=26&gp=0.jpg"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvImage.layoutManager = GridLayoutManager(this, 3)
        rvImage.adapter = GridAdapter(images, this)

    }

    inner class GridAdapter(private val images: Array<String>, private val context: Context) :
        RecyclerView.Adapter<GridAdapter.ViewHolder>() {


        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val image: ImageView = itemView.findViewById(R.id.image)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_image, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return images.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            Glide.with(holder.image).load(images[position]).into(holder.image)
            holder.image.setOnClickListener {
                PreviewImage(this@MainActivity)
                    .setUrl(ArrayList<String>().apply {
                        addAll(images)
                    })
                    .setImageLoader(object : ImageLoader {
                        override fun onImageLoader(url: String, image: ImageView) {
                            Glide.with(applicationContext)
                                .load(url).into(image)
                        }

                    })
                    .setPosition(position)
                    .start()
            }
        }
    }

}
