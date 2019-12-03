package com.supportingonline.hrapp.Adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.supportingonline.hrapp.Holder.NotificationsHolder;
import com.supportingonline.hrapp.InterFaces.OnPress;
import com.supportingonline.hrapp.Model.NotificationsModel;
import com.supportingonline.hrapp.R;
import com.supportingonline.hrapp.Views.MyViewHolder;

import java.util.ArrayList;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsHolder> {


    private ArrayList<NotificationsModel> arrayList;
    private Context context;
    private OnPress  onPress;

    public NotificationsAdapter(ArrayList<NotificationsModel> arrayList, Context context, OnPress onPress) {
        this.arrayList = arrayList;
        this.context = context;
        this.onPress = onPress;
    }

    @NonNull
    @Override
    public NotificationsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MyViewHolder.notificationsHolder(context, R.layout.recycler_notification,parent);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void removeItem(int position) {
        arrayList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(NotificationsModel item, int position) {
        arrayList.add(position, item);
        notifyItemInserted(position);
    }

    public ArrayList<NotificationsModel> getData() {
        return arrayList;
    }
}
