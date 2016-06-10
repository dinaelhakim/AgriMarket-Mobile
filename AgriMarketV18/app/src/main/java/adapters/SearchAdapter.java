package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.testslidenerd.R;

import java.util.ArrayList;


public class SearchAdapter extends BaseAdapter{


    ArrayList<String> imageUrlsList;
    ArrayList<String> titlesList;
    ArrayList<String> subTitlesList;
    ArrayList<Boolean> isRecommended;
    Context context;

    public SearchAdapter(Context context,ArrayList<String> imageUrlsList, ArrayList<String> titlesList, ArrayList<String> subTitlesList, ArrayList<Boolean> isRecommended) {
        this.imageUrlsList = imageUrlsList;
        this.titlesList = titlesList;
        this.subTitlesList = subTitlesList;
        this.isRecommended = isRecommended;
        this.context = context;
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

        TextView textView1=(TextView)row.findViewById(R.id.textView1);

        textView1.setText(titlesList.get(position));

        return null;
    }

}
