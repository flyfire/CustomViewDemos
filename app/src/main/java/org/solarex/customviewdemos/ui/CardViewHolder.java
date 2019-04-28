package org.solarex.customviewdemos.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import org.solarex.customviewdemos.R;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 11:24/2018/10/12
 *    Desc:
 * </pre>
 */
public class CardViewHolder extends RecyclerView.ViewHolder {
    ImageView iv_avatar;
    ImageView iv_like;
    ImageView iv_dislike;

    public CardViewHolder(View itemView) {
        super(itemView);
        iv_avatar = itemView.findViewById(R.id.iv_avatar);
        iv_like = itemView.findViewById(R.id.iv_like);
        iv_dislike = itemView.findViewById(R.id.iv_dislike);
    }
}
