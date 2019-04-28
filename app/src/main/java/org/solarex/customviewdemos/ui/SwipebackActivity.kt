package org.solarex.customviewdemos.ui

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.Toast
import com.solarexsoft.solarexswipeback.CardConfig
import com.solarexsoft.solarexswipeback.CardItemTouchHelperCallback
import com.solarexsoft.solarexswipeback.CardLayoutManager
import com.solarexsoft.solarexswipeback.OnSwipeListener
import org.solarex.customviewdemos.R

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 21:23/2019/4/28
 *    Desc:
 * </pre>
 */
 
class SwipebackActivity : BaseCustomViewActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_swipeback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imgs = getData()

        val rv_main = findViewById<RecyclerView>(R.id.rv_main)
        val adapter = CardAdapter(imgs)
        val itemTouchCallback = CardItemTouchHelperCallback(adapter, imgs)
        itemTouchCallback.setOnSwipedListener(object : OnSwipeListener<Any> {
            override fun onSwipedClear() {
                Toast.makeText(this@SwipebackActivity, "swipe clear", Toast.LENGTH_SHORT).show()
                rv_main.postDelayed(object : Runnable{
                    override fun run() {
                        val imgsRegenerated = getData()
                        adapter.setData(imgsRegenerated)
                        itemTouchCallback.setAdapter(adapter)
                        itemTouchCallback.setData(imgsRegenerated)
                    }

                }, 1000)
            }

            override fun onSwiping(viewHolder: RecyclerView.ViewHolder?, ratio: Float, direction: Int) {
                val holder : CardViewHolder = viewHolder as CardViewHolder
                if (direction == CardConfig.SWIPING_LEFT) {
                    holder.iv_like.alpha = Math.abs(ratio)
                } else if (direction == CardConfig.SWIPING_RIGHT) {
                    holder.iv_dislike.alpha = Math.abs(ratio)
                } else {
                    holder.iv_like.alpha = 0f
                    holder.iv_dislike.alpha = 0f
                }
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, t: Any?, direction: Int) {
                val holder : CardViewHolder = viewHolder as CardViewHolder
                holder.itemView.alpha = 1f
                holder.iv_like.alpha = 0f
                holder.iv_dislike.alpha = 0f
                var msg = if (direction == CardConfig.SWIPED_LEFT) "swiped left" else "swiped right"
                Toast.makeText(this@SwipebackActivity, msg, Toast.LENGTH_SHORT).show()
            }

        })
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        val cardLayoutManager = CardLayoutManager(rv_main, itemTouchHelper)
        rv_main.layoutManager = cardLayoutManager
        itemTouchHelper.attachToRecyclerView(rv_main)
        rv_main.adapter = adapter
    }

    fun getData() : MutableList<Int> {
        val imgs = ArrayList<Int>()
        imgs.add(R.drawable.img_avatar_01);
        imgs.add(R.drawable.img_avatar_02);
        imgs.add(R.drawable.img_avatar_03);
        imgs.add(R.drawable.img_avatar_04);
        imgs.add(R.drawable.img_avatar_06);
        imgs.add(R.drawable.img_avatar_07);
        return imgs
    }
}