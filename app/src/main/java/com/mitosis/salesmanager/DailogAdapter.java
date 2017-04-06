package com.mitosis.salesmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;
import static com.mitosis.salesmanager.R.id.view;

/**
 * Created by mitosis on 17/3/17.
 */

public class DailogAdapter extends ArrayAdapter<String> {

    private final Context context;

    ArrayList<String> firstname = new ArrayList<String>();
    ArrayList<String> imageurl = new ArrayList<String>();
    ArrayList<String> lastnamee = new ArrayList<String>();

    ArrayList<String> userIds = new ArrayList<String>();
   public static String assigns;
    public DailogAdapter(Context context, ArrayList<String> contactName, ArrayList<String> usersId,ArrayList<String> imageurl) {
        super(context, R.layout.dailogadap, contactName);
        this.getContext();
        this.context = context;
        this.firstname = contactName;
        this.imageurl=imageurl;
      //  this.lastnamee=lastnamee;
        this.userIds=usersId;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.dailogadap, parent, false);
        TextView txt=(TextView)rowView.findViewById(R.id.txtdailog);
        ImageView aa=(ImageView)rowView.findViewById(R.id.saleimage);
        ImageView aaq=(ImageView)rowView.findViewById(R.id.hide);
        aaq.setVisibility(View.GONE);
        txt.setText(firstname.get(position));
        assigns=userIds.get(position);

       /* if (position == MainUserFeedFragment.getCurrentSelectedItemId())
        {
            //TODO: set the proper selection color here:}            aaq .setVisibility(View.VISIBLE);
        } else {
            aaq .setVisibility(View.INVISIBLE);
        }
*/
        Picasso.with(context)
                .load(imageurl.get(position))
                .placeholder(R.drawable.placeholder)   // optional
                .error(R.drawable.profile)     // optional
                .resize(400,400)                        // optional
                .into(aa);
        return  rowView;
    }
}