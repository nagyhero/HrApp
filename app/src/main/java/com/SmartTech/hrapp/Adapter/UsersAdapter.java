package com.SmartTech.hrapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.SmartTech.hrapp.Holder.UsersHolder;
import com.SmartTech.hrapp.InterFaces.OnPress;
import com.SmartTech.hrapp.Model.UsersModel;
import com.SmartTech.hrapp.R;
import com.SmartTech.hrapp.Views.MyViewHolder;

import java.util.ArrayList;


public class UsersAdapter  extends RecyclerView.Adapter<UsersHolder> implements Filterable {

    private ArrayList<UsersModel> arrayList;
    private ArrayList<UsersModel> arrayListFiltered;
    private Context context;
    private OnPress onPress;
    private OnPress onPressMore;


    public UsersAdapter(ArrayList<UsersModel> arrayList, Context context,OnPress onPress,OnPress onPressMore) {
        this.arrayList = arrayList;
        this.arrayListFiltered = arrayList;
        this.context = context;
        this.onPress = onPress;
        this.onPressMore = onPressMore;

    }

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return (UsersHolder) MyViewHolder.myuserholder(context,parent);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersHolder holder, final int position) {


        // get model
        UsersModel user=arrayListFiltered.get(position);


        // name
        holder.name.setText(user.getName());

        // position name
        holder.positionName.setText(user.getPositionName());


        // click more

        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onPressMore.onClick(v,position);
            }
        });

        // click view profile
        holder.layoutContanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPress.onClick(v,position);
            }
        });




        // colors
        int headColor=R.color.colorPrimary;
        int secondColor=R.color.hollowcustom;
        if (user.getGender().equals("female")){
             headColor=R.color.colorAccent;
             secondColor=R.color.hollowcustom5;
        }

        holder.layoutContanier.setBackgroundTintList(context.getResources().getColorStateList(secondColor));
        holder.textClick.setTextColor(context.getResources().getColor(headColor));
        holder.more.setImageTintList(context.getResources().getColorStateList(headColor));

        // image
        if (user.getImage().contains("null")){
            holder.imageView.setVisibility(View.INVISIBLE);
            holder.userNameView.setVisibility(View.VISIBLE);
            holder.userNameView.setBackgroundTintList(ContextCompat.getColorStateList(context,headColor));
            holder.subName.setText(user.getName().substring(0,1).toUpperCase());
        }else {
            holder.imageView.setVisibility(View.VISIBLE);
            holder.userNameView.setVisibility(View.INVISIBLE);
            Glide.with(context).load(user.getImage())
                    .into(holder.imageView);
        }



    }




    @Override
    public int getItemCount() {
        return arrayListFiltered.size();
    }


    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                arrayListFiltered = new ArrayList<>();
                if (charString.isEmpty()) {
                    arrayListFiltered = arrayList;
                } else {
                    ArrayList<UsersModel> filteredList = new ArrayList<>();
                    for (UsersModel row : arrayList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match

                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getName().contains(charSequence)) {
                            Log.d("name",row.getName());
                            filteredList.add(row);
                        }
                    }

                    arrayListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = arrayListFiltered;
                Log.d("count", String.valueOf(arrayListFiltered.size()));
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                arrayListFiltered = (ArrayList<UsersModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }




}
