package fertilizers.all_fertilizers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import luncher.landing_page.MainActivity;
import com.example.lenovo.testslidenerd.GenericAdapter;
import com.example.lenovo.testslidenerd.GenericListener;
import com.example.lenovo.testslidenerd.R;


public class Fertilizers extends AppCompatActivity {

    ListView listView;
    String[]  title;
    String[]  subTitle;
    int[] images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //to display back button
        listView=(ListView)findViewById(R.id.listView);
        initDate();
        listView.setAdapter(new GenericAdapter(getBaseContext(), title, subTitle, images));
        listView.setOnItemClickListener(new GenericListener(7, this));
    }

    private void initDate(){
        title=getResources().getStringArray(R.array.fertilizersTypes);
        subTitle=getResources().getStringArray(R.array.frtilizersDescription);
        images= new int[]{R.drawable.fer1, R.drawable.fer2, R.drawable.fer3, R.drawable.fer4};
    }

    //To be back when you click back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case  android.R.id.home:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
