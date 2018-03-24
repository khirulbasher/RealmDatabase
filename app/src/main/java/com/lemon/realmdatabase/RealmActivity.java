package com.lemon.realmdatabase;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.lemon.realmdatabase.concurrent.ClientCallback;
import com.lemon.realmdatabase.concurrent.ClientHandler;
import com.lemon.realmdatabase.concurrent.Converter;
import com.lemon.realmdatabase.concurrent.FragmentCallback;
import com.lemon.realmdatabase.concurrent.Task;
import com.lemon.realmdatabase.concurrent.TaskManager;
import com.lemon.realmdatabase.database.realm.RealmDatabase;
import com.lemon.realmdatabase.entity.Person;
import com.lemon.realmdatabase.entity.converter.PersonConverter;
import com.lemon.realmdatabase.fragments.ListFragment;
import com.lemon.realmdatabase.fragments.setup.AddPersonFragment;
import com.lemon.realmdatabase.utility.util.Item;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmActivity extends AppCompatActivity implements ClientCallback,FragmentCallback{

    private Handler handler;
    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        handler=new ClientHandler(this);
        Realm.init(this);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder().name(RealmActivity.class.getSimpleName()+".realm").build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment=null;
        switch (item.getItemId()){
            case R.id.action_add_person:
                fragment=new AddPersonFragment();
                break;
            case R.id.action_show_person:
                fetchAll(Person.class,new PersonConverter());
                break;
        }
        if(fragment!=null)
            changeFragment(fragment);
        return super.onOptionsItemSelected(item);
    }

    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();
    }

    private <K> void fetchAll(Class<K> entityClass, Converter<K,Item> itemConverter) {
        new TaskManager<K,Item>(handler, clientCallback -> {
            try (RealmDatabase realmDatabase = new RealmDatabase()) {
                clientCallback.onPrepareCallback(itemConverter.convert(realmDatabase.findAllList(entityClass)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void onPrepareCallback(List<Item> items) {
        this.itemList=items;
        changeFragment(new ListFragment());
    }

    @Override
    public List<Item> getItems() {
        return this.itemList;
    }
}
