package com.example.weisheng_cheng.mycursorlistview;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;


public class MyActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    ListView mListView;
    CursorLoader mCursorLoader;
    MyAdapter mAdapter;
    int MY_LOADER_ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mListView = (ListView)findViewById(R.id.my_list);
        mAdapter = new MyAdapter(this, R.layout.my_list_item, null, new String[]{CallLog.Calls.NUMBER, CallLog.Calls.DATE}, new int[]{R.id.name, R.id.date}, 0);
        mListView.setAdapter(mAdapter);
        getLoaderManager().initLoader(MY_LOADER_ID, null, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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

    @Override
    public Loader onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, CallLog.Calls.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        mAdapter.swapCursor(null);
    }
    class MyAdapter extends SimpleCursorAdapter {

            public MyAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
                super(context, layout, c, from, to, flags);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                super.bindView(view, context, cursor);
                long date = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE));
                SimpleDateFormat sdf = new SimpleDateFormat("E yyyy/MM/dd");
                String dateString = sdf.format(date);
                TextView dateView = (TextView)view.findViewById(R.id.date);
                dateView.setText(dateString);
            }
    }
}
