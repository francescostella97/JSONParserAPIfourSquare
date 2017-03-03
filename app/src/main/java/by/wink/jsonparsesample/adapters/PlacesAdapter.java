package by.wink.jsonparsesample.adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.ArrayList;

import by.wink.jsonparsesample.R;
import by.wink.jsonparsesample.models.Place;

/**
 * Created by amine on 01/03/17.
 */

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder> {

    private ArrayList<Place> dataSet = new ArrayList<>();

    public void setDataSet(ArrayList<Place> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);

        return new PlaceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {

        Place place = dataSet.get(position);
        holder.placeName.setText(place.getName());
        holder.placeAdress.setText(place.getAddress());
        if(place.getAddress().equals("")){
            holder.placeMap.setVisibility(View.GONE);
        }
        else{
            holder.placeMap.setVisibility(View.VISIBLE);
        }
        if(place.getContact().getPhone().equals("")){
            holder.placePhone.setVisibility(View.GONE);
            holder.placePhoneBtn.setVisibility(View.GONE);
        }
        else {
            holder.placePhone.setVisibility(View.VISIBLE);
            holder.placePhoneBtn.setVisibility(View.VISIBLE);
            holder.placePhone.setText(place.getContact().getPhone());
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView placeName;
        TextView placeAdress;
        TextView placePhone;
        ImageButton placeMap,placePhoneBtn;

        PlaceViewHolder(final View itemView) {
            super(itemView);
            placeName = (TextView) itemView.findViewById(R.id.place_name);
            placeAdress = (TextView) itemView.findViewById(R.id.place_address);
            placeMap = (ImageButton) itemView.findViewById(R.id.place_map);
            placePhone = (TextView) itemView.findViewById(R.id.place_phone);

            placePhoneBtn = (ImageButton) itemView.findViewById(R.id.place_phone_btn);
            placePhoneBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    //Uri uri = Uri.parse("geo:0,0?q="+placeAdress.getText().toString());
                    double lat = dataSet.get(getAdapterPosition()).getLatitiude();
                    double lng = dataSet.get(getAdapterPosition()).getLongitude();
                    Uri uri = Uri.parse("geo:0,0?q="+lat+","+lng+(placeAdress.getText().toString()));
                    intent.setData(uri);
                    itemView.getContext().startActivity(intent);

                }
            });
            placePhoneBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!dataSet.get(getAdapterPosition()).getContact().getPhone().equals("")) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("tel:" + dataSet.get(getAdapterPosition()).getContact().getPhone()));
                        itemView.getContext().startActivity(intent);
                    }
                }
            });
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            /*menu.setHeaderTitle("Select The Action");
            menu.add(0, v.getId(), 0, "Call");//groupId, itemId, order, title
            menu.add(0, v.getId(), 0, "SMS");*/
        }
    }
}
