package com.example.rikirikmen.trashbin.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rikirikmen.trashbin.R;
import com.example.rikirikmen.trashbin.Singleton.Singleton;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

import parceable.Trash;

/**
 * Created by Riki Rikmen on 6/4/2016.
 */

public class AdapterButton extends RecyclerView.Adapter<AdapterButton.MyViewHolder>{
    private Context context;
    private List<Trash> trashList;
    private LayoutInflater inflater;
    private Object indexGroup;

    public AdapterButton(Context c, List<Trash> t, Object indexGroup) {
        this.context = c;
        this.trashList = t;
        this.indexGroup = indexGroup;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.cardview_main, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (getItemCount()==0){
            Toast.makeText(context, "nope", Toast.LENGTH_SHORT).show();
        }
        else {
            holder.txtTrash.setText(String.valueOf(trashList.get(position).getTrashName()));
            holder.txtTrashQty.setText(String.valueOf(trashList.get(position).getTrashQty()));
            Picasso.with(context).load(trashList.get(position).getTrashImg()).resize(150,150).into(holder.btnTrash);

             // apply custom vibrate animation
            holder.btnTrash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Animation animVibrate = AnimationUtils.loadAnimation(context, R.anim.anim_vibrate);
                    holder.cardView.setAnimation(animVibrate);
                    v.startAnimation(animVibrate); // START Animation
                    int a = trashList.get(position).getTrashQty();
                    a++;
                    trashList.get(position).setTrashQty(a);

                    Map<String, List<Trash>> itemCollections = Singleton.getInstance().getItemCollections();
                    itemCollections.put(indexGroup.toString(), trashList);
                    Singleton.getInstance().setItemCollections(itemCollections);

                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return trashList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTrash;
        public TextView txtTrashQty;
        public ImageButton btnTrash;
        public CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtTrash = (TextView) itemView.findViewById(R.id.txtTrash);
            txtTrashQty = (TextView) itemView.findViewById(R.id.txtTrashQty);
            btnTrash = (ImageButton) itemView.findViewById(R.id.btnTrash);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

}
