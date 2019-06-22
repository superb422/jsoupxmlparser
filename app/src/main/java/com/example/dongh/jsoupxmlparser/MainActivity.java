package com.example.dongh.jsoupxmlparser;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView statID,statnm,subwayID,subwaynm;
    Document doc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statID = findViewById(R.id.statID);
        statnm = findViewById(R.id.statname);
        subwayID = findViewById(R.id.subwayID);
        subwaynm = findViewById(R.id.subwayname);

        new Description().execute();
    }

    private class Description extends AsyncTask<Document, Document, Document> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Document doInBackground(Document... params) {
            List<Item> items = new ArrayList<>();

            try {
                doc = Jsoup.connect("https://gateway.openapi.t-money.co.kr:5556/gateway/subStnListGet/v1/subStnList/getStnList?serviceKey=01234567890&statnNm=강남")
                        .header("x-Gateway-APIKey","1cf26bcf-a996-487a-a0a7-91ba86b090c0")
                        .header("Accept","application/xml")
                        .parser(Parser.xmlParser())
                        .timeout(3000).get();


            } catch (IOException e) {
                e.printStackTrace();
            }
            return doc;

        }

        @Override
        protected void onPostExecute(Document result) {
            Elements elements = result.select("statnId");

            for (Element e : elements){
                statID.setText(e.text());
//                    statnm.setText(element2.text());
//                    subwayID.setText(element3.text());
//                    subwaynm.setText(element4.text());
            }
        }
    }

}
