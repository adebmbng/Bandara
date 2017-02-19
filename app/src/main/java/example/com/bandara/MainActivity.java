package example.com.bandara;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textViewSearch;
    private LinearLayoutManager lLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //textViewSearch = (TextView) findViewById(R.id.textViewSearch);

        List<ItemObjectPromo> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(MainActivity.this);

        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapterPromo rcAdapter = new RecyclerViewAdapterPromo(MainActivity.this, rowListItem);
        rView.setAdapter(rcAdapter);

        Button buttonBook = (Button) findViewById(R.id.btn_book);
        buttonBook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ConfirmationActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        //MenuItem searchItem = menu.findItem(R.id.search);
        final MenuItem profileItem = menu.findItem(R.id.profile);
        final MenuItem historyItem = menu.findItem(R.id.history);

//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
//            @Override
//            public boolean onQueryTextSubmit(String query){
//                textViewSearch.setText("Hasil pencarian Query:" + query);
//                searchView.clearFocus();
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText){
//                return false;
//            }
//        });

//        searchView.setOnCloseListener(new SearchView.OnCloseListener(){
//            @Override
//            public boolean onClose(){
//                profileItem.setVisible(true);
//                historyItem.setVisible(true);
//                return true;
//            }
//        });

//        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                profileItem.setVisible(false);
//                historyItem.setVisible(false);
//                return true;
//            }
//        });

        profileItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(i);
                return true;
            }
        });

        historyItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(i);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<ItemObjectPromo> getAllItemList(){
        List<ItemObjectPromo> allItems = new ArrayList<>();
        allItems.add(new ItemObjectPromo("Promo 10%", R.drawable.newyork));
        allItems.add(new ItemObjectPromo("Promo 15%", R.drawable.canada));
        allItems.add(new ItemObjectPromo("Promo 20%", R.drawable.uk));
        allItems.add(new ItemObjectPromo("Promo 25%", R.drawable.germany));
        allItems.add(new ItemObjectPromo("Promo 30%", R.drawable.sweden));

        return allItems;
    }
}
