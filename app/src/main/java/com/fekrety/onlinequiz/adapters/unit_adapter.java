package com.fekrety.onlinequiz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.fekrety.onlinequiz.R;
import  com.fekrety.onlinequiz.model.Units;
import com.trycatch.mysnackbar.Prompt;
import com.trycatch.mysnackbar.TSnackbar;

import java.util.ArrayList;

import static  com.fekrety.onlinequiz.ui.units_explore.downloadFile;

public class unit_adapter extends ArrayAdapter<Units> {
    Context context;
    private ArrayList<Units> munits;
    View view;
    ViewGroup viewGroup;
    public unit_adapter(Context context , ArrayList<Units> products, ViewGroup viewGroup){
        super (context, R.layout.units,products);
        this.context=context;
        this.munits=products;
        this.viewGroup=viewGroup;

    }
    public  class Holder {
        TextView description;
        View view ;
        Button Ex , Ass,book;
    }


    @Override
    @NonNull
    public View getView (final int position , View convertView, ViewGroup parent){
        Units data =getItem(position);
        Holder viewHolder;
        if (convertView==null){
            viewHolder =new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());


            convertView = inflater.inflate(R.layout.units,parent,false);
                view=convertView;
            viewHolder.description = (TextView) convertView.findViewById(R.id.unit_descrpition);
            viewHolder.view = (View) convertView.findViewById(R.id.view);
            viewHolder.book = (Button) convertView.findViewById(R.id.Bu_Book);


            convertView.setTag(viewHolder);

        }else {
            viewHolder=(Holder)convertView.getTag();

        }

        viewHolder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TSnackbar snackBarr = TSnackbar.make(viewGroup.findViewById(android.R.id.content), "downloading...", TSnackbar.LENGTH_LONG, TSnackbar.APPEAR_FROM_TOP_TO_DOWN);
                snackBarr.setActionTextColor(context.getResources().getColor(R.color.Black));
                snackBarr.setPromptThemBackground(Prompt.SUCCESS);
                snackBarr.addIconProgressLoading(0, true, false);
                snackBarr.show();
                final int final_pos = position;
                downloadFile(context,munits.get(final_pos).getFile_name(),
                        ".pdf","PDF/",munits.get(final_pos).getBook_Url());
            }
        });
        viewHolder.description.setText(data.getUnit_desc());
        YoYo.with(Techniques.Wave).duration(150).repeat(2).playOn(viewHolder.description);
       // YoYo.with(Techniques.Landing).duration(200).repeat(5).playOn(   viewHolder.view);


        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public boolean isEnabled(int position)
    {
        return true;
    }
}
