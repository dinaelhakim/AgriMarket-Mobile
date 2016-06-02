package ta2awi.specific_t2awi;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.example.lenovo.testslidenerd.AddProduct;
import com.example.lenovo.testslidenerd.R;

import chemicals.all_chemicals.Chemicals;
import luncher.landing_page.MainActivity;
import ta2awi.T2awiTabPagesAdapter;
import ta2awi.all_t2awi.Ta2awy;

public class SpecificT2awi extends AppCompatActivity {

    FloatingActionButton addProductButton;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    String[]  title;
    String[]  subTitle;
    int[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);
        initUI();
        initData();
        viewPager.setAdapter(new T2awiTabPagesAdapter(this,getBaseContext(),title,subTitle,images));
        tabLayout.setupWithViewPager(viewPager);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //to display back button
        setAddButtonOnClickListener();
    }

    private void initUI(){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        addProductButton=(FloatingActionButton)findViewById(R.id.addProductButton);
    }
    private void initData(){
        images=new int[]{R.drawable.b2oliat,R.drawable.b2oliat,R.drawable.b2oliat,R.drawable.b2oliat};
        title=getResources().getStringArray(R.array.pricesList);
        subTitle=getResources().getStringArray(R.array.locationList);
    }
    private void setAddButtonOnClickListener(){
        addProductButton=(FloatingActionButton)findViewById(R.id.addProductButton);
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpecificT2awi.this, AddProduct.class);
                startActivity(intent);
            }
        });

    }

    //To be back when you click back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case  android.R.id.home:
                Intent intent=new Intent(this,Ta2awy.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,Ta2awy.class);
        startActivity(intent);
        finish();
    }

}
