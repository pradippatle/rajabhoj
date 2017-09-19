package com.pradipatle.rajabhoj.rajabhoj;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Aeon-02 on 9/11/2017.
 */

public class FbFeedsActivity extends Activity {
    private ImageView actionbar_icon;
    private ListView FeedList;
    private ArrayList<FbFeedsModel> FeedsArrayList;
    private FbFeedsAdapter FbFeedsAdapter;
    private TextView Title_bar;
    private String FbPageId ="/1924848391114798/feed";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fb_feeds_activity);
/*
        Bundle b = getIntent().getExtras();
        String SourcePage =b.getString("page");
        if(SourcePage!=null && SourcePage.equalsIgnoreCase("Schemes")){
            Title_bar.setText("Government Schemes");
            FbPageId = "/1924848391114798/feed";
        }else if(SourcePage!=null && SourcePage.equalsIgnoreCase("Help")){
            Title_bar.setText("Farmers Help");
            FbPageId = "/1694125200658653/feed";
        }else {
            Title_bar.setText("News & Events");
            FbPageId = "/129306537713760/feed";
        }*/
        FeedList = (ListView) findViewById(R.id.fb_feeds_listview);
        FeedsArrayList = new ArrayList<>();
        CommonMethods.showDialog(FbFeedsActivity.this,"Loading\nPlease wait....",false);
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                FbPageId,
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Log.i("Facebook", "Fb Post Response : " + response);
                        CommonMethods.dismissDialog();
                        try {
                            JSONObject jsonObject = new JSONObject(response.getRawResponse());
                            JSONArray dataArray = jsonObject.getJSONArray("data");
                            if (dataArray.length() > 0) {

                                for (int i = 0; i < dataArray.length(); i++) {
                                    FbFeedsModel feedModel = new FbFeedsModel();
                                    JSONObject dataObj = dataArray.getJSONObject(i);
                                    String FbFeed = dataObj.getString("message");
                                    String FeedTime = dataObj.getString("created_time");
                                    Log.i("FbFeed", "Response " + FbFeed);

                                    feedModel.created_time = FeedTime;
                                    feedModel.feeds = FbFeed;
                                    FeedsArrayList.add(feedModel);
                                }

                                if (FeedsArrayList.size() > 0) {
                                    FbFeedsAdapter= new FbFeedsAdapter(FeedsArrayList, FbFeedsActivity.this);
                                    FeedList.setAdapter(FbFeedsAdapter);
                                }
                            } else {
                                TextView nodata = (TextView) findViewById(R.id.nodata_id);
                                FeedList.setVisibility(View.GONE);
                                nodata.setVisibility(View.VISIBLE);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
        ).executeAsync();
    }
}
