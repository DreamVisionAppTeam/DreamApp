package jp.co.dreamvision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private HashMap<String, HashMap<String, String>> destinationMap;
    private OriginalAdapter desAdapter;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HashMap<String,String> detailMap = new HashMap<String,String>();
        detailMap.put("mailAddress", "xxxx@yyy.jp");
        detailMap.put("lat", "35.697936");
        detailMap.put("long", "139.77309");
        detailMap.put("line","JR Yamanote");

        destinationMap = new HashMap<String, HashMap<String,String>>();
        destinationMap.put("秋葉原", detailMap);
        destinationMap.put("東京", detailMap);

        ListView destList = (ListView)findViewById(R.id.destinationList);

        //desAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, hoge);
        desAdapter = new OriginalAdapter();

        destList.setAdapter(desAdapter);
        destList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> p,View v, int r, long id){
                // インテントのインスタンス生成
                Intent intent = new Intent(MainActivity.this, SelectMapActivity.class);
                // 次画面のアクティビティ起動
                startActivity(intent);
            }
        });
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            //View rootView = inflater.inflate(R.layout.fragment_main, container,
            //		false);
            return null;
        }
    }

    private class OriginalAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO 自動生成されたメソッド・スタブ

            return destinationMap.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO 自動生成されたメソッド・スタブ

            List<String> keys = new ArrayList<String>();
            //Set<String> desKeys = (Set<String>) destinationMap.keySet();
            for (String Key:destinationMap.keySet()) {
                keys.add(Key);
            }
            return keys.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO 自動生成されたメソッド・スタブ
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout layout = new LinearLayout(getApplicationContext());
            TextView tex = new TextView(getApplicationContext());
            tex.setTextSize(40);
            tex.setTextColor(Color.BLACK);
            tex.setText((String)getItem(position));
            //layout.addView(tex,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            layout.addView(tex,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            //LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //contextView = inflater.inflate(, null);


            return layout;
        }

    }
}