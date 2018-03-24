package com.lemon.realmdatabase.fragments.setup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.lemon.realmdatabase.R;
import com.lemon.realmdatabase.database.realm.RealmDatabase;
import com.lemon.realmdatabase.entity.Person;

/**
 * Created by lemon on 3/24/2018.
 */
@SuppressWarnings({"DefaultFileTemplate", "unused", "FieldCanBeLocal"})
public class AddPersonFragment extends Fragment {
    private EditText name,mail,phone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.person_fragment,container,false);
        name=view.findViewById(R.id.person_name);
        mail=view.findViewById(R.id.person_mail);
        phone=view.findViewById(R.id.person_phone);
        view.findViewById(R.id.save).setOnClickListener(v->{
            try(RealmDatabase realmDatabase=new RealmDatabase()) {
                realmDatabase.persist(new Person(name.getText().toString(),phone.getText().toString(),mail.getText().toString()));
                Toast.makeText(getActivity(), "A New Person Entity was Saved", Toast.LENGTH_SHORT).show();
                clearAll();
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
        return view;
    }

    private void clearAll() {
        name.setText("");
        phone.setText("");
        mail.setText("");
    }
}
