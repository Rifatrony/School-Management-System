package com.rony.srmm.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rony.srmm.R;
import com.rony.srmm.Response.CostResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CostAdapter extends RecyclerView.Adapter<CostAdapter.CostViewHolder> {

    Context context;
    List<CostResponse> costResponseList;

    String fullDate;
    String date, month, year;

    public CostAdapter(Context context, List<CostResponse> costResponseList) {
        this.context = context;
        this.costResponseList = costResponseList;
    }

    @NonNull
    @Override
    public CostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cost_sample_layout, parent, false);
        return new CostViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CostViewHolder holder, int position) {
        CostResponse response = costResponseList.get(position);

        holder.costReasonTextView.setText(response.reason);
        holder.costDateTextView.setText(response.today);
        holder.costAmountTextView.setText(String.valueOf(response.amount));

        fullDate = response.getDate().toString();

        if (fullDate.isEmpty()){
            date = fullDate.substring(8,10);
            month = fullDate.substring(4,7);
            year = fullDate.substring(30,34);
            System.out.println("Year is "+year);
            System.out.println("Month is "+month);
            System.out.println("Date is "+date);
            holder.dateTextView.setText(date +" "+ month);
            holder.monthTextView.setText(year);
        }

        holder.cardView.setCardBackgroundColor(holder.itemView.getResources().getColor(getRandomColor(), null));

    }

    private int getRandomColor() {

        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.card1);
        colorCode.add(R.color.card2);
        colorCode.add(R.color.card4);
        colorCode.add(R.color.card6);
        colorCode.add(R.color.salmon);
        colorCode.add(R.color.deepKoamaru);
        colorCode.add(R.color.green);

        Random randomColor = new Random();

        int number = randomColor.nextInt(colorCode.size());
        return colorCode.get(number);

    }

    @Override
    public int getItemCount() {
        return costResponseList.size();
    }

    public class CostViewHolder extends RecyclerView.ViewHolder {

        TextView costReasonTextView, costDateTextView, costAmountTextView, dateTextView, monthTextView, yearTextView;
        CardView cardView;

        public CostViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);

            costReasonTextView = itemView.findViewById(R.id.costReasonTextView);
            costDateTextView = itemView.findViewById(R.id.costDateTextView);
            costAmountTextView = itemView.findViewById(R.id.costAmountTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);

        }
    }

}
