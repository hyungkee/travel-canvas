package app.heuristy.dev.travelcanvas.controller.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.heuristy.dev.travelcanvas.R;
import app.heuristy.dev.travelcanvas.controller.adapter.SearchPersonAdapter;
import app.heuristy.dev.travelcanvas.model.Person;

public class SearchPersonActivity extends AppCompatActivity {


    private SearchPersonAdapter searchPersonAdapter;
    private List<Person> searchList = new ArrayList<Person>();
    private ListView searchListView;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_person);


        searchListView = (ListView)findViewById(R.id.list_companions);

        // 여기서 연락처 내에서 회원/비회원을 구분해서 추가 가능하도록!

        searchList.add(new Person(-1, "회원 목록", ""));
        searchList.add(new Person(1, "박형기", "010-0000-0000"));
        searchList.add(new Person(2, "김형수", "010-0000-0000"));
        searchList.add(new Person(3, "하찬근", "010-0000-0000"));
        searchList.add(new Person(4, "최지웅", "010-0000-0000"));
        searchList.add(new Person(5, "허성연", "010-0000-0000"));
        searchList.add(new Person(6, "김성은", "010-0000-0000"));
        searchList.add(new Person(7, "이재성", "010-0000-0000"));
        searchList.add(new Person(8, "강원준", "010-0000-0000"));
        searchList.add(new Person(1, "박형기", "010-0000-0000"));
        searchList.add(new Person(2, "김형수", "010-0000-0000"));
        searchList.add(new Person(3, "하찬근", "010-0000-0000"));
        searchList.add(new Person(4, "최지웅", "010-0000-0000"));
        searchList.add(new Person(5, "허성연", "010-0000-0000"));
        searchList.add(new Person(6, "김성은", "010-0000-0000"));
        searchList.add(new Person(7, "이재성", "010-0000-0000"));
        searchList.add(new Person(8, "강원준", "010-0000-0000"));
        searchList.add(new Person(4, "최지웅", "010-0000-0000"));
        searchList.add(new Person(5, "허성연", "010-0000-0000"));
        searchList.add(new Person(6, "김성은", "010-0000-0000"));
        searchList.add(new Person(7, "이재성", "010-0000-0000"));
        searchList.add(new Person(8, "강원준", "010-0000-0000"));
        searchList.add(new Person(1, "박형기", "010-0000-0000"));
        searchList.add(new Person(2, "김형수", "010-0000-0000"));
        searchList.add(new Person(3, "하찬근", "010-0000-0000"));
        searchList.add(new Person(4, "최지웅", "010-0000-0000"));
        searchList.add(new Person(5, "허성연", "010-0000-0000"));
        searchList.add(new Person(6, "김성은", "010-0000-0000"));
        searchList.add(new Person(7, "이재성", "010-0000-0000"));
        searchList.add(new Person(1, "박형기", "010-0000-0000"));
        searchList.add(new Person(2, "김형수", "010-0000-0000"));
        searchList.add(new Person(3, "하찬근", "010-0000-0000"));
        searchList.add(new Person(4, "최지웅", "010-0000-0000"));
        searchList.add(new Person(5, "허성연", "010-0000-0000"));
        searchList.add(new Person(6, "김성은", "010-0000-0000"));
        searchList.add(new Person(7, "이재성", "010-0000-0000"));
        searchList.add(new Person(8, "강원준", "010-0000-0000"));
        searchList.add(new Person(-1, "비회원 목록", ""));
        searchList.add(new Person(1, "박형기", "010-0000-0000"));
        searchList.add(new Person(2, "김형수", "010-0000-0000"));
        searchList.add(new Person(3, "하찬근", "010-0000-0000"));
        searchList.add(new Person(4, "최지웅", "010-0000-0000"));
        searchList.add(new Person(5, "허성연", "010-0000-0000"));
        searchList.add(new Person(6, "김성은", "010-0000-0000"));
        searchList.add(new Person(7, "이재성", "010-0000-0000"));
        searchList.add(new Person(8, "강원준", "010-0000-0000"));
        searchList.add(new Person(1, "박형기", "010-0000-0000"));
        searchList.add(new Person(2, "김형수", "010-0000-0000"));
        searchList.add(new Person(3, "하찬근", "010-0000-0000"));
        searchList.add(new Person(4, "최지웅", "010-0000-0000"));
        searchList.add(new Person(5, "허성연", "010-0000-0000"));
        searchList.add(new Person(6, "김성은", "010-0000-0000"));
        searchList.add(new Person(7, "이재성", "010-0000-0000"));
        searchList.add(new Person(4, "최지웅", "010-0000-0000"));
        searchList.add(new Person(5, "허성연", "010-0000-0000"));
        searchList.add(new Person(6, "김성은", "010-0000-0000"));
        searchList.add(new Person(7, "이재성", "010-0000-0000"));
        searchList.add(new Person(8, "강원준", "010-0000-0000"));
        searchList.add(new Person(1, "박형기", "010-0000-0000"));
        searchList.add(new Person(2, "김형수", "010-0000-0000"));
        searchList.add(new Person(3, "하찬근", "010-0000-0000"));
        searchList.add(new Person(4, "최지웅", "010-0000-0000"));
        searchList.add(new Person(5, "허성연", "010-0000-0000"));
        searchList.add(new Person(6, "김성은", "010-0000-0000"));
        searchList.add(new Person(7, "이재성", "010-0000-0000"));
        searchList.add(new Person(8, "강원준", "010-0000-0000"));

        searchPersonAdapter = new SearchPersonAdapter(searchList);
        searchListView.setAdapter(searchPersonAdapter);
        searchListView.setTextFilterEnabled(true);

        searchView = (SearchView)findViewById(R.id.search_companion);
        setupSearchView();
    }

    private void setupSearchView(){
        searchView.setSubmitButtonEnabled(false);
        searchView.setQueryHint("연락처에서 검색..");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if("".equals(newText)){
                    searchListView.clearTextFilter();
                }else{
                    searchListView.setFilterText(newText);
                }
                return false;
            }
        });

    }

}
