package com.kcode.previewimage

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_preview.*


class PreviewActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_POS = "position"
        private const val EXTRA_URLS = "urls"
        fun start(urls: ArrayList<String>, position: Int, context: Context) {
            context.startActivity(Intent(context, PreviewActivity::class.java).apply {
                putExtra(EXTRA_POS, position)
                putExtra(EXTRA_URLS, urls)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        val urls = intent?.getStringArrayListExtra(EXTRA_URLS) ?: ArrayList()
        val position = intent?.getIntExtra(EXTRA_POS, 0) ?: 0
        if (urls.isEmpty()) {
            finish()
            return
        }

        initPageNav(urls.size, position)
        viewPager.adapter = PreviewAdapter(urls, application,object : PreviewImageClickListener{
            override fun onClick(position: Int, view: View) {
                finish()
            }

        })

        viewPager.setCurrentItem(position, false)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    onPageChange(viewPager.currentItem)
                }
            }
        })
    }

    private fun initPageNav(size: Int, position: Int) {
        for (i in 0 until size) {
            val view = LayoutInflater.from(applicationContext)
                .inflate(R.layout.preview_image_nav, null, false)

            view as ImageView
            if (i == position) {
                view.setBackgroundResource(R.drawable.bg_preview_image_nav_checked)
            } else {
                view.setBackgroundResource(R.drawable.bg_preview_image_nav_normal)
            }

            ll_nav.addView(view)
            val params = view.layoutParams as LinearLayout.LayoutParams
            params.marginStart = toPx(3f).toInt()
            params.marginEnd = toPx(3f).toInt()

        }
    }

    private fun onPageChange(position: Int) {
        val count = ll_nav.childCount
        for (i in 0 until count) {
            val view = ll_nav.getChildAt(i) as ImageView
            if (i == position) {
                view.setBackgroundResource(R.drawable.bg_preview_image_nav_checked)
            } else {
                view.setBackgroundResource(R.drawable.bg_preview_image_nav_normal)
            }
        }
    }

    private fun toPx(dip: Float): Float {
        val r: Resources = resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dip,
            r.displayMetrics
        )
    }


}
