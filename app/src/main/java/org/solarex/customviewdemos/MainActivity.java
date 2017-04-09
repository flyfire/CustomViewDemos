package org.solarex.customviewdemos;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.solarex.customviewdemos.impl.Adapter;
import org.solarex.customviewdemos.impl.Model;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Adapter.VH.OnClickListener {
    private static final String TAG = "Solarex";
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
    }

    private void initData() {
        Intent intent = getIntent();
        String path = intent.getStringExtra("solarex_custom_view_path");
        if (path == null) {
            path = "";
        }

        ArrayList<Model> models = getData(path);
        Log.d(TAG, "initData | path = " + path + ",models = " + models);
        Adapter adapter = new Adapter(models, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(Intent intent) {
        Log.d(TAG, "onClick | intent = " + intent);
        startActivity(intent);
    }

    // get data
    public ArrayList<Model> getData(String path) {
        ArrayList<Model> mModels = new ArrayList<>();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN);
        mainIntent.addCategory("solarex.intent.category.customview");

        PackageManager pm = getPackageManager();
        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(mainIntent, 0);

        if (null == resolveInfos) {
            return mModels;
        }

        String[] prefixPath;
        String pathWithSlash = path;

        if (path.equals("")) {
            prefixPath = null;
        } else {
            prefixPath = path.split("/");
            pathWithSlash = path + "/";
        }

        int len = resolveInfos.size();
        Map<String, Boolean> entries = new HashMap<>();

        for (int i = 0; i < len; i++) {
            ResolveInfo info = resolveInfos.get(i);
            CharSequence labelSequence = info.loadLabel(pm);
            String label = labelSequence != null ? labelSequence.toString() : info.activityInfo
                    .name;
            if (pathWithSlash.length() == 0 || label.startsWith(pathWithSlash)) {
                String[] labelPath = label.split("/");
                String nextLabel = prefixPath == null ? labelPath[0] : labelPath[prefixPath.length];

                int prefixLen = prefixPath != null ? prefixPath.length : 0;
                Log.d(TAG, "getData | nextLabel = " + nextLabel + ",prefixLen = " + prefixLen);
                if (prefixLen == labelPath.length - 1) {
                    addItem(mModels, nextLabel, activityIntent(info.activityInfo.applicationInfo
                            .packageName, info.activityInfo.name));
                } else {
                    if (entries.get(nextLabel) == null) {
                        addItem(mModels, nextLabel, browserIntent(path.equals("") ? nextLabel :
                                path + "/" + nextLabel));
                        entries.put(nextLabel, true);
                    }
                }
            }
        }

        Collections.sort(mModels, sDispalyNameComparator);

        return mModels;
    }


    private static final Comparator<Model> sDispalyNameComparator = new Comparator<Model>() {

        private final Collator mCollator = Collator.getInstance();

        @Override
        public int compare(Model o1, Model o2) {
            return mCollator.compare(o1.getTitle(), o2.getTitle());
        }
    };

    private Intent activityIntent(String pkg, String componentName) {
        Intent intent = new Intent();
        intent.setClassName(pkg, componentName);
        return intent;
    }

    private Intent browserIntent(String path) {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        Log.d(TAG, "browserIntent | path = " + path);
        intent.putExtra("solarex_custom_view_path", path);
        return intent;
    }

    private void addItem(ArrayList<Model> models, String title, Intent intent) {
        Model tmp = new Model(title, intent);
        Log.d(TAG, "addItem | tmp = " + tmp);
        models.add(tmp);
    }


}
