package com.example.rikirikmen.trashbin;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Thomas on 6/5/2016.
 */
public class AdapterExpandable extends BaseExpandableListAdapter {

    private Activity context;
    private Map<String, List<String>> itemCollections;
    private List<String> items;

    public AdapterExpandable(Activity context, List<String> items,
                             Map<String, List<String>> itemCollections) {
        this.context = context;
        this.itemCollections = itemCollections;
        this.items = items;
    }

    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return itemCollections.get(items.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return items.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return itemCollections.get(items.get(groupPosition));
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String itemName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.group_item, null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.groupItem);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(itemName);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        final List<String> item = (List<String>) getChild(groupPosition, childPosition);
//        LayoutInflater inflater = context.getLayoutInflater();
//
//        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.child_item, null);
//        }
//
//        GridView gridItemView = (GridView) convertView.findViewById(R.id.gridView);
//        gridItemView.setAdapter(new ArrayAdapter<String>(this.context, android.R.layout.simple_list_item_1, item));
//        return convertView;

        RecyclerView recyclerView;
        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_item, null);
        }

        recyclerView = (RecyclerView) convertView.findViewById(R.id.recycler_view_trash);
        List<Trash> trashList = new ArrayList<>();
        ButtonAdapter adapter = new ButtonAdapter(this.context, trashList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.context, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        int[] buttonImage = new int[]{
                R.drawable.apple, R.drawable.asparagus,
                R.drawable.aubergine, R.drawable.avocado,
                R.drawable.baguette, R.drawable.bacon,
                R.drawable.banana, R.drawable.beans,
                R.drawable.blueberries, R.drawable.biscuit,
                R.drawable.boiled, R.drawable.bowl,
                R.drawable.bread, R.drawable.butcher,
                R.drawable.cabbage
        };
        Trash trash = new Trash("Example 1", 0, buttonImage[0]);
        trashList.add(trash);

        for (int i = 1; i < buttonImage.length; i++) {
            int a = i + 1;
            trash = new Trash("Example" + a, 0, buttonImage[i]);
            trashList.add(trash);
        }

        adapter.notifyDataSetChanged();

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
