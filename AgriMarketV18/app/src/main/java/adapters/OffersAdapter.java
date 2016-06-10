package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.lenovo.testslidenerd.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import web_services_connections_handlers.UniversalImageLoaderConfiguration;


public class OffersAdapter extends BaseAdapter {

    ArrayList<String> imageUrlsList;
    ArrayList<Object> titlesList;
    ArrayList<String> subTitlesList;
    ArrayList<Boolean> isRecommended;
    Context context;

    public OffersAdapter(Context context,ArrayList<String> imageUrlsList, ArrayList<Object> titlesList, ArrayList<String> subTitlesList, ArrayList<Boolean> isRecommended) {
        this.context=context;
        this.imageUrlsList = imageUrlsList;
        this.titlesList = titlesList;
        this.subTitlesList = subTitlesList;
        this.isRecommended = isRecommended;
    }

    @Override
    public int getCount() {
        return titlesList.size();
    }

    @Override
    public Object getItem(int position) {
        return titlesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;

        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.custom_row,parent,false);
        }else {
            row=convertView;
        }
        ImageView imageView1=(ImageView)row.findViewById(R.id.imageView1);
        TextView textView1=(TextView)row.findViewById(R.id.textView1);
        TextView textView2=(TextView)row.findViewById(R.id.textView2);
        ImageView image2=(ImageView)row.findViewById(R.id.voice);

        UniversalImageLoaderConfiguration.setConfig(context);
//        ImageLoader.getInstance().displayImage(imageUrlsList.get(position), imageView1);
        imageView1.setImageResource(R.drawable.product);
        textView1.setText((String) titlesList.get(position));
        textView2.setText(subTitlesList.get(position));


        if(isRecommended.get(position).equals(false)){
            image2.setVisibility(View.INVISIBLE);
        }else if(isRecommended.get(position).equals(true)){
            image2.setVisibility(View.VISIBLE);
            image2.setImageResource(android.R.drawable.btn_star_big_on);
        }
        return row;
    }
}
