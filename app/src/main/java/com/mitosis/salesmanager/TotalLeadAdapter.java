package com.mitosis.salesmanager;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mitosis on 27/3/17.
 */

public class TotalLeadAdapter extends ArrayAdapter<String> {
    private Context context;
    TextView name, total,completed,pending;
   ImageView repimage;
    // public static TextView useridddd;
    private ArrayList<String> firstname=new ArrayList<String>();
    private  ArrayList<String> lastName=new ArrayList<String>();
    private  ArrayList<String> totalcount=new ArrayList<String>();
    private  ArrayList<String> pendingcount=new ArrayList<String>();
    private  ArrayList<String> completecount=new ArrayList<String>();
    private ArrayList<String> username=new ArrayList<String>();
    private ArrayList<String> uuuserid=new ArrayList<String>();
    private ArrayList<String> uimage=new ArrayList<String>();
   public static String user;
    public TotalLeadAdapter(Context context, ArrayList<String> firstname, ArrayList<String> lastName, ArrayList<String> totalcount, ArrayList<String> completecount, ArrayList<String> pendingcount, ArrayList<String> usenamee,ArrayList<String> uuuserid,ArrayList<String> image) {
        super(context, R.layout.header, firstname);
        this.context=context;
        this.firstname=firstname;
        this.lastName=lastName;
        this.totalcount=totalcount;
        this.completecount=completecount;
        this.pendingcount=pendingcount;
        this.username=usenamee;
        this.uuuserid=uuuserid;
        this.uimage=image;
    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.header, parent, false);
        name=(TextView)rowView.findViewById(R.id.doconame) ;
        total=(TextView)rowView.findViewById(R.id.total);
        completed=(TextView)rowView.findViewById(R.id.completed);
        pending=(TextView)rowView.findViewById(R.id.pending);
        repimage=(ImageView)rowView.findViewById(R.id.image_lead);
        name.setText(firstname.get(position));
        total.setText(totalcount.get(position));
        completed.setText(completecount.get(position));
        pending.setText(pendingcount.get(position));
        Picasso.with(context)
                .load(uimage.get(position))
                .placeholder(R.drawable.placeholder)   // optional
                .error(R.drawable.doctor)     // optional
                .resize(400,400)                        // optional
                .into(repimage);
        return rowView;
    }
}