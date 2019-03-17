package com.example.sajib.expandible_listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    List<String> listDataHeader;
    HashMap<String,List<String>> listDataChild;
    private CustomAdapter customAdapter;
    private Button button;
    private int lastExpandposition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareData();
        expandableListView = findViewById(R.id.expid);
        customAdapter=new CustomAdapter(this,listDataHeader,listDataChild);
        expandableListView.setAdapter(customAdapter);
        button=findViewById(R.id.nextbuttonid);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
         String name=  listDataHeader.get(groupPosition);
                Toast.makeText(MainActivity.this,"group name is"+name, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                String nametwo=listDataHeader.get(groupPosition);
                Toast.makeText(MainActivity.this, "group is colappsed:"+nametwo, Toast.LENGTH_SHORT).show();
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String namethree=  listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                Toast.makeText(MainActivity.this,namethree+ "", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if(lastExpandposition!=-1 && lastExpandposition!=groupPosition){
                    expandableListView.collapseGroup(lastExpandposition);
                }
                lastExpandposition=groupPosition;
            }
        });

    }

    public void prepareData() {
        String[] headerString = getResources().getStringArray(R.array.abbreviation_header);
        String[] childString = getResources().getStringArray(R.array.abbreviation_child);

        listDataHeader=new ArrayList<>();
        listDataChild=new HashMap<>();
        for(int i=0; i<headerString.length;i++){
            listDataHeader.add(headerString[i]);
            List<String> child=new ArrayList<>();
            child.add(childString[i]);
            listDataChild.put(listDataHeader.get(i),child);
        }
    }

    public void next(View view) {
        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
    }
}
