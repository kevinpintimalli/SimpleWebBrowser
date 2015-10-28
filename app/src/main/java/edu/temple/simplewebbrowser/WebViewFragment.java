package edu.temple.simplewebbrowser;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;


public class WebViewFragment extends Fragment {

    private EditText urlView;
    private Button go;
    private WebView display;


    public static WebViewFragment newInstance() {
        WebViewFragment fragment = new WebViewFragment();
        return fragment;
    }

    public WebViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);

        urlView = (EditText) view.findViewById(R.id.webText);
        go = (Button) view.findViewById(R.id.goButton);
        display = (WebView) view.findViewById(R.id.webView);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlView.getText().toString();

                DownloadURL downloadURL = new DownloadURL(display);
                downloadURL.execute(url);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }



}
