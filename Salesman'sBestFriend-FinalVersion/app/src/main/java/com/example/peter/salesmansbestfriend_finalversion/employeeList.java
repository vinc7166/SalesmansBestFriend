package com.example.peter.salesmansbestfriend_finalversion;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.app.ListActivity;
import android.content.Intent;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;


public class employeeList extends ListActivity  implements OnItemClickListener {
    String employees;
    TheDataBase P = new TheDataBase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        employees = P.grabNames();
        String[] emps=employees.split(":");

        ArrayAdapter<String> A = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txvEmployee, emps);
        setListAdapter(A);

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                TextView selectedFromList = (TextView)view.findViewById(R.id.txvEmployee);
                String text = selectedFromList.getText().toString();
//                if(text.equals("Add Employee")){
//                    Intent I = new Intent(view.getContext(), employeeEntry.class);
//                    startActivity(I);
//                    finish();
//                }
//                else{
//                    Intent I = new Intent(view.getContext(), fullEmployeeView.class);
//                    I.putExtra("Name", text);
//                    startActivity(I);
//                    finish();
//                }
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){

        Toast.makeText(this, "Test", Toast.LENGTH_LONG).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_employee_list, menu);
        return true;
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
}
