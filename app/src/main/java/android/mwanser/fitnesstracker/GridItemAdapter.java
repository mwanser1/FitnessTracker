package android.mwanser.fitnesstracker;

/**
 * Created by Wanser on 3/22/17.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Wanser on 3/21/17.
 * Code ideas taken from:
 * -------
 * http://stackoverflow.com/questions/19699284/custom-layout-as-an-item-for-a-grid-view
 * for adapter class help
 * -------
 *
 */


public class GridItemAdapter extends BaseAdapter {
    private Context context;
    private final Integer[] mThumbIds;
    private String[] mText= {
            "Edit Info", //0
            "Calories",//1
            "Bluetooth Connect",//2
            "GPS run", //3
            "Workout",//4
            "Fitness Test",//5
            "View prior",//6
            "Logout",//7
            "Empty"//8
    };

    public GridItemAdapter(Context context, Integer[] mThumbIds) {
        this.context = context;
        this.mThumbIds = mThumbIds;
        Log.d("**GridItemAdapter","Constructor");
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.grid_item, null);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);
            TextView textview = (TextView) gridView.findViewById(R.id.gird_item_text);
            textview.setText(mText[position]);
            imageView.setImageResource(mThumbIds[position]);


        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}