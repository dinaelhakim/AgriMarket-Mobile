package chemicals.all_chemicals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import luncher.landing_page.MainActivity;
import com.example.lenovo.testslidenerd.GenericAdapter;
import com.example.lenovo.testslidenerd.GenericListener;
import com.example.lenovo.testslidenerd.R;

public class Chemicals extends AppCompatActivity {

    ListView listView;
    String[] title;
    String[] subTitle;
    int[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //to display back button
        initDate();
        listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(new GenericAdapter(getBaseContext(),title,subTitle,images));
        listView.setOnItemClickListener(new GenericListener(9,this));
    }

    private void initDate(){
        images=new int[]{R.drawable.fer4,R.drawable.fer1,R.drawable.fer2,R.drawable.fer,R.drawable.fer4,R.drawable.fer1,R.drawable.fer2,R.drawable.fer};
        title=getResources().getStringArray(R.array.chemicalsTypes);
        subTitle=getResources().getStringArray(R.array.chemicalsDescription);
    }

    //To be back when you click back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case  android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
