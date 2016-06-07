package com.example.rikirikmen.trashbin.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.rikirikmen.trashbin.R;

import java.util.List;
import java.util.Map;

import parceable.Trash;

/**
 * Created by Thomas on 6/5/2016.
 */
public class AdapterExpandable extends BaseExpandableListAdapter {

    private Activity context;
    private Map<String, List<Trash>> itemCollections;
    private List<String> items;

    public AdapterExpandable(Activity context, List<String> items,
                             Map<String, List<Trash>> itemCollections) {
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
        return 1;
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
            convertView = infalInflater.inflate(R.layout.expandable_group, null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.groupItem);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(itemName);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final List<Trash> trashList = (List<Trash>) getChild(groupPosition, childPosition);
        RecyclerView recyclerView;
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expandable_child, null);
        }

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.context, 3);
        AdapterButton adapter = new AdapterButton(this.context, trashList, getGroup(groupPosition));

        recyclerView = (RecyclerView) convertView.findViewById(R.id.recycler_view_trash);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
