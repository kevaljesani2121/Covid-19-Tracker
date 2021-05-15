package com.kpatel.covid19tracker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kpatel.covid19tracker.Activity.Track_countries;
import com.kpatel.covid19tracker.Model.CountryModel;
import com.kpatel.covid19tracker.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnItemClick;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryHolder> {

    public static ArrayList<CountryModel> countryModelList;
    public static ArrayList<CountryModel> countryAdapterListFilters;
    Context context;
    OnViewClickListener onViewClickListener;


    public CountryAdapter(Context context, ArrayList<CountryModel> countryModelList, OnViewClickListener onViewClickListener) {
        this.context = context;
        CountryAdapter.countryModelList = countryModelList;
        countryAdapterListFilters = countryModelList;
        this.onViewClickListener = onViewClickListener;
    }

    @NonNull
    @Override
    public CountryAdapter.CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryHolder(LayoutInflater.from(context).inflate(R.layout.view_row_country_list, null));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.CountryHolder holder, int position) {
        Glide.with(context).load(countryAdapterListFilters.get(position).getFlag()).into(holder.ivCountryImage);
        holder.tvCountryName.setText(countryAdapterListFilters.get(position).getCountry());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onViewClickListener != null) {
                    onViewClickListener.OnItemClick(position);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return countryAdapterListFilters.size();
    }

    @NonNull
//    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();

                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = countryModelList.size();
                    filterResults.values = countryModelList;
                } else {

                    List<CountryModel> tempCountryList = new ArrayList<>();
//
//                    tempCountryList.clear();
                    String searchString = constraint.toString().toLowerCase();

                    for (CountryModel itemModel : countryModelList) {
                        if (itemModel.getCountry().toLowerCase().contains(searchString)) {
                            tempCountryList.add(itemModel);
                        }
                        filterResults.count = tempCountryList.size();
                        filterResults.values = tempCountryList;
                    }

                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                countryAdapterListFilters = (ArrayList<CountryModel>) results.values;
                Track_countries.countryModelList = (ArrayList<CountryModel>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    public interface OnViewClickListener {

        void OnItemClick(int position);
    }

    public class CountryHolder extends RecyclerView.ViewHolder {

        ImageView ivCountryImage;
        TextView tvCountryName;

        OnItemClick onItemClick;

        public CountryHolder(@NonNull View itemView) {
            super(itemView);

            tvCountryName = itemView.findViewById(R.id.tvCountryName);
            ivCountryImage = itemView.findViewById(R.id.ivCountryImage);

        }
    }
}