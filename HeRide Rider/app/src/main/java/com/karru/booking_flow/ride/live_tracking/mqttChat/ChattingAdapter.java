package com.karru.booking_flow.ride.live_tracking.mqttChat;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.karru.booking_flow.ride.live_tracking.mqttChat.model.ChatData;
import com.karru.utility.Scaler;
import com.heride.rider.R;
import java.util.ArrayList;


/**
 * <h>ChattingAdapter</h>
 * Created by Ali on 12/22/2017.
 */

public class ChattingAdapter extends RecyclerView.Adapter
{
    private Context mContext;
    private ArrayList<ChatData>chatData;

    ChattingAdapter(Context mContext, ArrayList<ChatData> chatData)
    {
        this.mContext = mContext;
        this.chatData = chatData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_chatting_row,parent,false);
        return new ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        ViewHolders hldr = (ViewHolders) holder;
        if(chatData.get(position).getCustProType()==1)
        {
            hldr.rlCustMsg.setVisibility(View.VISIBLE);
            hldr.rlProMsg.setVisibility(View.GONE);
            if(chatData.get(position).getType()==1)
            {
                hldr.tvCustMsg.setText(chatData.get(position).getContent());
                hldr.tvCustMsg.setVisibility(View.VISIBLE);
                hldr.ivCustSendPic.setVisibility(View.GONE);
            }

            else
            {
                hldr.tvCustMsg.setVisibility(View.GONE);
                hldr.ivCustSendPic.setVisibility(View.VISIBLE);
                String url = chatData.get(position).getContent();
                if(!url.equals(""))
                {
                    Glide.with(mContext)
                            .load(url)
                            .apply(new RequestOptions().override((int)hldr.width,(int)hldr.height))
                            .into(hldr.ivCustSendPic);
                }
            }
        }else
        {
            hldr.rlCustMsg.setVisibility(View.GONE);
            hldr.rlProMsg.setVisibility(View.VISIBLE);
            if(chatData.get(position).getType()==1)
            {
                hldr.tvProMsg.setText(chatData.get(position).getContent());
                hldr.tvProMsg.setVisibility(View.VISIBLE);
                hldr.ivProReceivedPic.setVisibility(View.GONE);
            }
            else
            {
                hldr.tvProMsg.setVisibility(View.GONE);
                hldr.ivProReceivedPic.setVisibility(View.VISIBLE);
                String url = chatData.get(position).getContent();
                if(!url.equals(""))
                {
                    Glide.with(mContext)
                            .load(url)
                            .apply(new RequestOptions().override((int)hldr.width,(int)hldr.height))
                            .into(hldr.ivProReceivedPic);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return chatData.size();
    }

    class ViewHolders extends RecyclerView.ViewHolder
    {
        private RelativeLayout rlCustMsg,rlProMsg;
        private ImageView ivCustSendPic,ivProReceivedPic;
        private TextView tvCustMsg,tvProMsg;
        private double width,height;
        ViewHolders(View itemView) {
            super(itemView);
            double size[];
            size = Scaler.getScalingFactor(mContext);
            ivCustSendPic = itemView.findViewById(R.id.ivCustSendPic);
            ivProReceivedPic = itemView.findViewById(R.id.ivProReceivedPic);
            tvCustMsg = itemView.findViewById(R.id.tvCustMsg);
            tvProMsg = itemView.findViewById(R.id.tvProMsg);
            rlCustMsg = itemView.findViewById(R.id.rlCustMsg);
            rlProMsg = itemView.findViewById(R.id.rlProMsg);
            width = 200*size[0];
            height = 200*size[1];
        }
    }
}
