package com.example.rikirikmen.trashbin;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Riki Rikmen on 6/4/2016.
 */

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.MyViewHolder>{
    private Context context;
    private List<Trash> trashList;
    private LayoutInflater inflater;
    public ButtonAdapter(Context c, List<Trash> t) {
        this.context = c;
        this.trashList = t;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.card_view_main, parent, false));
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


            holder.btnTrash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int a = trashList.get(position).getTrashQty();
                    a++;
                    trashList.get(position).setTrashQty(a);
                    notifyDataSetChanged();
                }
            });

        }
    }

    public List<Trash> getList(){
        return trashList;
    }
    @Override
    public int getItemCount() {
        return trashList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTrash;
        public TextView txtTrashQty;
        public ImageButton btnTrash;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtTrash = (TextView) itemView.findViewById(R.id.txtTrash);
            txtTrashQty = (TextView) itemView.findViewById(R.id.txtTrashQty);
            btnTrash = (ImageButton) itemView.findViewById(R.id.btnTrash);

        }
    }
}
